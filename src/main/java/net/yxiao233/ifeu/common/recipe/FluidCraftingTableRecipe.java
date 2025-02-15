package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;

import java.util.*;

public class FluidCraftingTableRecipe implements Recipe<CraftingInput> {
    public static final MapCodec<FluidCraftingTableRecipe> CODEC = RecordCodecBuilder.mapCodec((in) -> {
        return in.group(Ingredient.CODEC.listOf(0,9).fieldOf("inputs").forGetter((o) -> {
            return o.inputs;
        }),FluidStack.CODEC.fieldOf("inputFluid").forGetter((o) -> {
            return o.inputFluid;
        }),ItemStack.CODEC.fieldOf("output").forGetter((o) -> {
            return o.output;
        })).apply(in, FluidCraftingTableRecipe::new);
    });

    public List<Ingredient> inputs;
    public FluidStack inputFluid;
    public ItemStack output;
    public FluidCraftingTableRecipe(){

    }

    public FluidCraftingTableRecipe(List<Ingredient> inputs, FluidStack inputFluid, ItemStack output){
        this.inputs = inputs;
        this.inputFluid = inputFluid;
        this.output = output;
    }

    public static void createRecipe(RecipeOutput recipeOutput, String name, FluidCraftingTableRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        recipeOutput.accept(rl, recipe, advancementHolder);
    }

    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu","fluid_crafting_table/" + key);
    }

    public boolean matches(NonNullList<InventoryComponent<FluidCraftingTableEntity>> inputs, FluidTankComponent<FluidCraftingTableEntity> tank) {
        if (this.inputs != null && tank != null && this.inputFluid != null) {
            NonNullList<Boolean> matches = NonNullList.withSize(9, false);

            for (int i = 0; i < this.inputs.size(); i++) {
                Iterator<ItemStack> iterator = Arrays.stream(this.inputs.get(i).getItems()).iterator();

                if(iterator.hasNext()){
                    ItemStack stack = iterator.next();
                    if(stack.is(ModContents.AIR.get())){
                        matches.set(i,inputs.get(i).getStackInSlot(0).isEmpty());
                    }else{
                        matches.set(i,inputs.get(i).getStackInSlot(0).is(stack.getItem()));
                    }
                }
            }

            boolean b2 = tank.drainForced(this.inputFluid, IFluidHandler.FluidAction.SIMULATE).getAmount() == this.inputFluid.getAmount();

            return matches.stream().allMatch(i -> i) && b2;
        }else{
            return false;
        }
    }
    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.output.copy();
    }

    @Override
    public ItemStack getToastSymbol() {
        return ModBlocks.FLUID_CRAFTING_TABLE.asItem().getDefaultInstance();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.FLUID_CRAFTING_TABLE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.FLUID_CRAFTING_TABLE_TYPE.get();
    }
}
