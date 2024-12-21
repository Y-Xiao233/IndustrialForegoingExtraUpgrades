package net.yxiao233.ifeu.common.compact.kubejs.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.architectury.fluid.FluidStack;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentBuilder;
import dev.latvian.mods.kubejs.util.MapJS;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Iterator;

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

    public static RecipeComponent<OutputFluid> OUTPUT_FLUID = new RecipeComponent<>() {
        @Override
        public String componentType() {
            return "output_fluid";
        }

        @Override
        public ComponentRole role() {
            return ComponentRole.OUTPUT;
        }

        @Override
        public Class<?> componentClass() {
            return OutputFluid.class;
        }

        @Override
        public JsonElement write(RecipeJS recipe, OutputFluid value) {
            var stack = ((FluidStackJS) value);
            return stack.kjs$isEmpty() ? null : new JsonPrimitive(stack.getFluidStack().write(new CompoundTag()).toString());
        }

        private OutputFluid readString(RecipeJS recipe, String str) {
            try {
                return recipe.readOutputFluid(FluidStack.read(TagParser.parseTag(str)));
            } catch (CommandSyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public OutputFluid read(RecipeJS recipe, Object from) {
            if (from instanceof JsonPrimitive prim && prim.isString()) {
                return readString(recipe, prim.getAsString());
            }

            return recipe.readOutputFluid(from);
        }

        @Override
        public boolean hasPriority(RecipeJS recipe, Object from) {
            return recipe.outputFluidHasPriority(from);
        }

        @Override
        public String checkEmpty(RecipeKey<OutputFluid> key, OutputFluid value) {
            if (value.kjs$isEmpty()) {
                return "Output fluid '" + key.name + "' can't be empty!";
            }

            return "";
        }

        @Override
        public String toString() {
            return componentType();
        }
    };

    public static RecipeComponent<ResourceKey[]> RESOURCE_KEY = new RecipeComponent<>() {
        public String componentType() {
            return "resource_key";
        }

        @Override
        public Class<?> componentClass() {
            return ResourceKey[].class;
        }

        @Override
        public JsonElement write(RecipeJS recipe, ResourceKey[] keys) {
            JsonObject json = new JsonObject();
            if (keys.length > 0) {
                json.addProperty("type", keys[0].registry().toString());
                JsonArray array = new JsonArray();
                for (var registryKey : keys) {
                    array.add(registryKey.location().toString());
                }
                json.add("values", array);
            }
            return json;
        }

        @Override
        public ResourceKey[] read(RecipeJS recipe, Object from) {
            JsonObject element = MapJS.json(from);
            if (element == null) {
                return null;
            }

            ResourceKey[] registryKeys = new ResourceKey[0];
            if (element.getAsJsonObject().has("type")) {
                JsonArray values = element.getAsJsonObject().getAsJsonArray("values");
                registryKeys = new ResourceKey[values.size()];
                int i = 0;

                for (Iterator<JsonElement> iterator = values.iterator(); iterator.hasNext(); ++i) {
                    JsonElement jsonElement = iterator.next();
                    registryKeys[i] = ResourceKey.create(ResourceKey.createRegistryKey(new ResourceLocation(element.getAsJsonObject().get("type").getAsString())), new ResourceLocation(jsonElement.getAsString()));
                }
            }

            return registryKeys;
        }
    };

    public static RecipeComponentBuilder RARITY = new RecipeComponentBuilder(5)
            .add(IndustrialForegoingComponents.RESOURCE_KEY.key("whitelist"))
            .add(IndustrialForegoingComponents.RESOURCE_KEY.key("blacklist"))
            .add(NumberComponent.INT.key("depth_min"))
            .add(NumberComponent.INT.key("depth_max"))
            .add(NumberComponent.INT.key("weight"));
}
