package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidStack;

public interface FluidExtractorSchema {
    RecipeKey<ItemStack> INPUT = ItemStackComponent.ITEM_STACK.inputKey("input");
    RecipeKey<Block> RESULT = BlockComponent.BLOCK.inputKey("result").allowEmpty();
    RecipeKey<Float> BREAK_CHANCE = NumberComponent.FLOAT.otherKey("breakChance");
    RecipeKey<FluidStack> OUTPUT = FluidStackComponent.FLUID_STACK.outputKey("output");
    RecipeKey<Boolean> DEFAULT_RECIPE = BooleanComponent.BOOLEAN.otherKey("defaultRecipe").optional(false);
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUT,BREAK_CHANCE,RESULT,DEFAULT_RECIPE);
}
