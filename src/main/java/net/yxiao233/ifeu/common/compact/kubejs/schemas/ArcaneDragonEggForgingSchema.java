package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.util.TickDuration;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public interface ArcaneDragonEggForgingSchema{
    RecipeKey<ItemStack> INPUT = ItemStackComponent.ITEM_STACK.inputKey("input");
    RecipeKey<FluidStack> INPUT_FLUID_1 = FluidStackComponent.FLUID_STACK.inputKey("inputFluid1");
    RecipeKey<FluidStack> INPUT_FLUID_2 = FluidStackComponent.FLUID_STACK.inputKey("inputFluid2");
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.OPTIONAL_ITEM_STACK.outputKey("output").optional(ItemStack.EMPTY);
    RecipeKey<FluidStack> OUTPUT_FLUID = FluidStackComponent.OPTIONAL_FLUID_STACK.outputKey("outputFluid").optional(FluidStack.EMPTY);
    RecipeKey<TickDuration> TIME = TimeComponent.TICKS.otherKey("processingTime");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUT,INPUT_FLUID_1,INPUT_FLUID_2,TIME,OUTPUT_FLUID);
}
