package net.yxiao233.ifeu.common.compact.kubejs.components;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.architectury.fluid.FluidStack;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;

public class IndustrialForegoingComponents {
    public static RecipeComponent<InputFluid> INPUT_FLUID = new RecipeComponent<>() {
        @Override
        public String componentType() {
            return "input_fluid";
        }

        @Override
        public ComponentRole role() {
            return ComponentRole.INPUT;
        }

        @Override
        public Class<?> componentClass() {
            return InputFluid.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, InputFluid inputFluid) {
            var stack = ((FluidStackJS) inputFluid);
            return stack.kjs$isEmpty() ? null : new JsonPrimitive(stack.getFluidStack().write(new CompoundTag()).toString());
        }

        private InputFluid readString(RecipeJS recipe, String string){
            try {
                return recipe.readInputFluid(FluidStack.read(TagParser.parseTag(string)));
            } catch (CommandSyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public InputFluid read(RecipeJS recipeJS, Object o) {
            if(o instanceof JsonPrimitive primitive && primitive.isString()){
                return readString(recipeJS,primitive.getAsString());
            }
            return recipeJS.readInputFluid(o);
        }

        @Override
        public boolean hasPriority(RecipeJS recipe, Object from) {
            return recipe.inputFluidHasPriority(from);
        }

        @Override
        public String checkEmpty(RecipeKey<InputFluid> key, InputFluid value) {
            if(value.kjs$isEmpty()){
                return "Input fluid '" + key.name + "' can't be empty!";
            }
            return "";
        }

        @Override
        public String toString() {
            return componentType();
        }
    };
}
