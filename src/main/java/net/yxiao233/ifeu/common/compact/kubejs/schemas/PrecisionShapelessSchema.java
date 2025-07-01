package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BooleanComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.yxiao233.ifeu.common.compact.kubejs.components.IFEUComponents;

public interface PrecisionShapelessSchema {
    RecipeKey<InputItem[]> INPUTS = IFEUComponents.INPUT_ARRAY.key("inputs");
    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeKey<Boolean> CHANCE = BooleanComponent.BOOLEAN.key("chance");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUTS,CHANCE);
}
