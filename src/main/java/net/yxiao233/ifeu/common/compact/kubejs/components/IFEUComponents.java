package net.yxiao233.ifeu.common.compact.kubejs.components;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;

public class IFEUComponents {
    public static RecipeComponent<InputItem> INPUT = new RecipeComponent<>() {
        @Override
        public String componentType() {
            return "input_item";
        }

        @Override
        public ComponentRole role() {
            return ComponentRole.INPUT;
        }

        @Override
        public Class<?> componentClass() {
            return InputItem.class;
        }

        @Override
        public JsonElement write(RecipeJS recipeJS, InputItem inputItem) {
            if(inputItem.isEmpty()){
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("item", "ifeu:air");
                return jsonobject;
            }else{
                return recipeJS.writeInputItem(inputItem);
            }
        }

        @Override
        public InputItem read(RecipeJS recipeJS, Object o) {
            return recipeJS.readInputItem(o);
        }
    };

    public static RecipeComponent<InputItem[]> INPUT_ARRAY = INPUT.asArray();
}
