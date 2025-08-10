package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BooleanComponent;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface PrecisionShapedSchema {
    RecipeKey<List<Ingredient>> INPUTS = IngredientComponent.UNWRAPPED_INGREDIENT_LIST.inputKey("inputs");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.outputKey("output");
    RecipeKey<Boolean> CHANCE = BooleanComponent.BOOLEAN.outputKey("chance");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUTS,CHANCE);
}
