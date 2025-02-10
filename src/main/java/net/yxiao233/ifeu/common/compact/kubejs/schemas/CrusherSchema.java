package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;

public interface CrusherSchema {
    RecipeKey<ItemStack> INPUT = ItemStackComponent.ITEM_STACK.inputKey("input");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.outputKey("output");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUT);
}
