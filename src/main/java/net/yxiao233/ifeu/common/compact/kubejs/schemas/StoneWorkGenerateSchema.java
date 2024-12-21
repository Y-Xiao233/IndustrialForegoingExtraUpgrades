package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface StoneWorkGenerateSchema {
    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeKey<Integer> WATER_NEED = NumberComponent.INT.key("waterNeed");
    RecipeKey<Integer> LAVA_NEED = NumberComponent.INT.key("lavaNeed");
    RecipeKey<Integer> WATER_CONSUME = NumberComponent.INT.key("waterConsume");
    RecipeKey<Integer> LAVA_CONSUME = NumberComponent.INT.key("lavaConsume");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,WATER_NEED,LAVA_NEED,WATER_CONSUME,LAVA_CONSUME);
}
