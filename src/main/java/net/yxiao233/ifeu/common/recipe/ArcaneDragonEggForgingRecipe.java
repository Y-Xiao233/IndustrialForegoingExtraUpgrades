package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ItemExistsCondition;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEURecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArcaneDragonEggForgingRecipe implements Recipe<CraftingInput> {
    public static final MapCodec<ArcaneDragonEggForgingRecipe> CODEC = RecordCodecBuilder.mapCodec((in) -> {
        return in.group(ItemStack.CODEC.fieldOf("input").forGetter((o) -> {
            return o.input;
        }), FluidStack.CODEC.fieldOf("inputFluid1").forGetter((o) -> {
            return o.inputFluid1;
        }), FluidStack.CODEC.fieldOf("inputFluid2").forGetter((o) -> {
            return o.inputFluid2;
        }),Codec.INT.fieldOf("processingTime").forGetter((o) -> {
            return o.processingTime;
        }), ItemStack.CODEC.optionalFieldOf("output").forGetter((o) -> {
            return o.output;
        }), FluidStack.CODEC.optionalFieldOf("outputFluid").forGetter((o) -> {
            return o.outputFluid;
        })).apply(in, ArcaneDragonEggForgingRecipe::new);
    });
    public ItemStack input;
    public FluidStack inputFluid1;
    public FluidStack inputFluid2;
    public int processingTime;
    public Optional<ItemStack> output;
    public Optional<FluidStack> outputFluid;
    public ArcaneDragonEggForgingRecipe() {}

    public ArcaneDragonEggForgingRecipe(ItemStack input, FluidStack inputFluid1, FluidStack inputFluid2, int processingTime, Optional<ItemStack> output, Optional<FluidStack> outputFluid){
        this.input = input;
        this.inputFluid1 = inputFluid1;
        this.inputFluid2 = inputFluid2;
        this.processingTime = processingTime;
        this.output = output;
        this.outputFluid = outputFluid;
    }

    public static void createRecipe(RecipeOutput recipeOutput, String name, ArcaneDragonEggForgingRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        List<ICondition> conditions = new ArrayList();
        if (recipe.output.isPresent()) {
            conditions.add(new ItemExistsCondition(BuiltInRegistries.ITEM.getKey(((ItemStack)recipe.output.get()).getItem())));
        }

        recipeOutput.accept(rl, recipe, advancementHolder, (ICondition[])conditions.toArray(new ICondition[conditions.size()]));
    }

    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu", "arcane_dragon_egg_forging/" + key);
    }

    public boolean matches(IItemHandler handler, FluidTankComponent tank1, FluidTankComponent tank2){
        if (input == null && tank1 == null && tank2 == null && inputFluid1 == null && inputFluid2 == null) return false;

        if(!ItemStack.isSameItem(handler.getStackInSlot(0),input)){
            return false;
        }

        boolean if1 = tank1.drainForced(inputFluid1, IFluidHandler.FluidAction.SIMULATE).getAmount() == inputFluid1.getAmount();
        boolean if2 = tank2.drainForced(inputFluid2, IFluidHandler.FluidAction.SIMULATE).getAmount() == inputFluid2.getAmount();
        return if1 && if2;
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
        return ((ItemStack)this.output.orElse(ItemStack.EMPTY)).copy();
    }
    public ItemStack getToastSymbol() {
        return new ItemStack(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING);
    }

    public RecipeSerializer<?> getSerializer() {
        return IFEURecipes.ARCANE_DRAGON_EGG_FORGING_SERIALIZER.get();
    }

    public RecipeType<?> getType() {
        return IFEURecipes.ARCANE_DRAGON_EGG_FORGING_TYPE.get();
    }
}
