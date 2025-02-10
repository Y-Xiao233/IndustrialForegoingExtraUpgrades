package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidStackComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.TimeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.util.TickDuration;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public interface InfuserSchema{
    RecipeKey<ItemStack> OUTPUT = ItemStackComponent.ITEM_STACK.inputKey("output");
    RecipeKey<ItemStack> INPUT = ItemStackComponent.ITEM_STACK.inputKey("input");
    RecipeKey<FluidStack> INPUT_FLUID = FluidStackComponent.FLUID_STACK.inputKey("inputFluid");
    RecipeKey<TickDuration> TIME = TimeComponent.TICKS.otherKey("processingTime");
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,INPUT,INPUT_FLUID,TIME);
}