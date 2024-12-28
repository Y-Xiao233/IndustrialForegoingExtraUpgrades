package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.TimeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.yxiao233.ifeu.common.compact.kubejs.components.IndustrialForegoingComponents;

public interface InfuserSchema{
    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<InputFluid> INPUT_FLUID = IndustrialForegoingComponents.INPUT_FLUID.key("inputFluid");
    RecipeKey<Long> TIME = TimeComponent.TICKS.key("processingTime");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUT,INPUT_FLUID,TIME);
}