package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.util.TickDuration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;

import java.util.List;

public interface DissolutionChamberSchema {
    RecipeKey<List<Ingredient>> INPUTS = IngredientComponent.UNWRAPPED_INGREDIENT_LIST.otherKey("input");
    RecipeKey<FluidIngredient> INPUT_FLUID = FluidIngredientComponent.FLUID_INGREDIENT.inputKey("inputFluid");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.inputKey("output");
    RecipeKey<FluidStack> OUTPUT_FLUID = FluidStackComponent.FLUID_STACK.outputKey("outputFluid").optional(FluidStack.EMPTY).allowEmpty();
    RecipeKey<TickDuration> TIME = TimeComponent.TICKS.otherKey("processingTime");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUTS,INPUT_FLUID,TIME,OUTPUT_FLUID);
}
