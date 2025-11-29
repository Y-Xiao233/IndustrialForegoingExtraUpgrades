package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidStackComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.compact.kubejs.component.IngredientsComponent;

import java.util.List;

public interface ShapelessSchema {
    RecipeKey<List<Ingredient>> INPUTS = IngredientsComponent.INGREDIENTS.inputKey("inputs");
    RecipeKey<FluidStack> INPUT_FLUID = FluidStackComponent.FLUID_STACK.inputKey("inputFluid");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.outputKey("output");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUTS,INPUT_FLUID);
}
