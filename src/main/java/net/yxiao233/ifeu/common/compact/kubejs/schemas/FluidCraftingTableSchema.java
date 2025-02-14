package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface FluidCraftingTableSchema {
    RecipeKey<InputItem[]> INPUT = ItemComponents.INPUT_ARRAY.key("inputs");
    RecipeKey<InputFluid> INPUT_FLUID = FluidComponents.INPUT.key("inputFluid");
    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUT,INPUT_FLUID);
}
