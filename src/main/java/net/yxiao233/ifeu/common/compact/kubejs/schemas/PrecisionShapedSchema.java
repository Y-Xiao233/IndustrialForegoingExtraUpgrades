package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BooleanComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.yxiao233.ifeu.common.compact.kubejs.component.IngredientsComponent;

import java.util.List;

public interface PrecisionShapedSchema {
    RecipeKey<List<Ingredient>> INPUTS = IngredientsComponent.INGREDIENTS.inputKey("inputs");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.outputKey("output");
    RecipeKey<Float> CHANCE = NumberComponent.floatRange(0,1).outputKey("chance");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUTS,CHANCE);
}
