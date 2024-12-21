package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentBuilderMap;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.yxiao233.ifeu.common.compact.kubejs.components.IndustrialForegoingComponents;

public interface LaserDrillOreSchema {
    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeKey<RecipeComponentBuilderMap[]> RARITY = IndustrialForegoingComponents.RARITY.asArray().key("rarity");
    RecipeKey<InputItem> CATALYST = ItemComponents.INPUT.key("catalyst");
    RecipeKey<Integer> POINTER = NumberComponent.INT.key("pointer").optional(0);
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,CATALYST,RARITY,POINTER);
}
