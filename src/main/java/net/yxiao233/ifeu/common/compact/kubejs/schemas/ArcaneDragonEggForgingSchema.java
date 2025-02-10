package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidStackComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.TimeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.util.TickDuration;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public interface ArcaneDragonEggForgingSchema{
    RecipeKey<ItemStack> INPUT = ItemStackComponent.ITEM_STACK.inputKey("input");
    RecipeKey<FluidStack> INPUT_FLUID_1 = FluidStackComponent.FLUID_STACK.inputKey("inputFluid1");
    RecipeKey<FluidStack> INPUT_FLUID_2 = FluidStackComponent.FLUID_STACK.inputKey("inputFluid2");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.outputKey("output").optional(ItemStack.EMPTY).allowEmpty();
    RecipeKey<FluidStack> OUTPUT_FLUID = FluidStackComponent.FLUID_STACK.outputKey("outputFluid").optional(FluidStack.EMPTY).allowEmpty();
    RecipeKey<TickDuration> TIME = TimeComponent.TICKS.otherKey("processingTime");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUT,INPUT_FLUID_1,INPUT_FLUID_2,TIME,OUTPUT_FLUID);
}
