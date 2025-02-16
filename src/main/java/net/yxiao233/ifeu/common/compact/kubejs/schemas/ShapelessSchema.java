package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.yxiao233.ifeu.common.compact.kubejs.components.IFEUComponents;
import net.yxiao233.ifeu.common.compact.kubejs.components.IndustrialForegoingComponents;

public interface ShapelessSchema {
    RecipeKey<InputItem[]> INPUTS = IFEUComponents.INPUT_ARRAY.key("inputs");
    RecipeKey<InputFluid> INPUT_FLUID = IndustrialForegoingComponents.INPUT_FLUID.key("inputFluid");
    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUTS,INPUT_FLUID);
}
