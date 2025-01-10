package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.TimeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.yxiao233.ifeu.common.compact.kubejs.components.IndustrialForegoingComponents;

public interface DissolutionChamberSchema {
    RecipeKey<InputItem[]> INPUTS = ItemComponents.INPUT_ARRAY.key("input");
    RecipeKey<InputFluid> INPUT_FLUID = IndustrialForegoingComponents.INPUT_FLUID.key("inputFluid");
    RecipeKey<OutputItem> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeKey<OutputFluid> OUTPUT_FLUID = IndustrialForegoingComponents.OUTPUT_FLUID.key("outputFluid").optional(EmptyFluidStackJS.INSTANCE).allowEmpty();
    RecipeKey<Long> TIME = TimeComponent.TICKS.key("processingTime");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUTS,INPUT_FLUID,TIME,OUTPUT_FLUID);
}
