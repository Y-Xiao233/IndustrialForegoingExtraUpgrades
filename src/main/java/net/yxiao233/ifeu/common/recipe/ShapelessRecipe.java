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
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEURecipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapelessRecipe implements Recipe<CraftingInput> {
    public static final MapCodec<ShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec((in) -> {
        return in.group(Ingredient.CODEC.listOf(0,9).fieldOf("inputs").forGetter((o) -> {
            return o.inputs;
        }),FluidStack.CODEC.fieldOf("inputFluid").forGetter((o) -> {
            return o.inputFluid;
        }),ItemStack.CODEC.fieldOf("output").forGetter((o) -> {
            return o.output;
        })).apply(in, ShapelessRecipe::new);
    });

    public List<Ingredient> inputs;
    public FluidStack inputFluid;
    public ItemStack output;

    public ShapelessRecipe(List<Ingredient> inputs, FluidStack inputFluid, ItemStack output){
        this.inputs = inputs;
        this.inputFluid = inputFluid;
        this.output = output;
    }

    public static void createRecipe(RecipeOutput recipeOutput, String name, ShapelessRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        recipeOutput.accept(rl, recipe, advancementHolder);
    }

    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu","shapeless/" + key);
    }

    public boolean matches(InventoryComponent<FluidCraftingTableEntity> inputs, FluidTankComponent<FluidCraftingTableEntity> tank) {
        if (this.inputs != null && tank != null && this.inputFluid != null) {
            List<ItemStack> handlerItems = new ArrayList();

            for(int i = 0; i < inputs.getSlots(); ++i) {
                if (!inputs.getStackInSlot(i).isEmpty()) {
                    handlerItems.add(inputs.getStackInSlot(i).copy());
                }
            }

            Iterator var12 = this.inputs.iterator();

            boolean found;
            do {
                if (!var12.hasNext()) {
                    return handlerItems.size() == 0 && tank.drainForced(this.inputFluid, IFluidHandler.FluidAction.SIMULATE).getAmount() == this.inputFluid.getAmount();
                }

                Ingredient ingredient = (Ingredient)var12.next();
                found = false;
                ItemStack[] var7 = ingredient.getItems();
                int var8 = var7.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    ItemStack stack = var7[var9];

                    int i;
                    for(i = 0; i < handlerItems.size(); ++i) {
                        if (ItemStack.isSameItem((ItemStack)handlerItems.get(i), stack)) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        handlerItems.remove(i);
                        break;
                    }
                }
            } while(found);

            return false;
        } else {
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
        return IFEUBlocks.FLUID_CRAFTING_TABLE.asItem().getDefaultInstance();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return IFEURecipes.SHAPELESS_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return IFEURecipes.SHAPELESS_TYPE.get();
    }
}
