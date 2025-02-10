package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;

public interface StoneWorkGenerateSchema {
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.inputKey("output");
    RecipeKey<Integer> WATER_NEED = NumberComponent.INT.otherKey("waterNeed");
    RecipeKey<Integer> LAVA_NEED = NumberComponent.INT.otherKey("lavaNeed");
    RecipeKey<Integer> WATER_CONSUME = NumberComponent.INT.otherKey("waterConsume");
    RecipeKey<Integer> LAVA_CONSUME = NumberComponent.INT.otherKey("lavaConsume");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,WATER_NEED,LAVA_NEED,WATER_CONSUME,LAVA_CONSUME);
}
