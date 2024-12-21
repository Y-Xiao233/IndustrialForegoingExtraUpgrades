package net.yxiao233.ifeu.common.compact.kubejs.schemas;

import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentBuilderMap;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.yxiao233.ifeu.common.compact.kubejs.components.IndustrialForegoingComponents;

public interface LaserDrillFluidSchema {
    RecipeKey<OutputFluid> OUTPUT = IndustrialForegoingComponents.OUTPUT_FLUID.key("output");
    RecipeKey<RecipeComponentBuilderMap[]> RARITY = IndustrialForegoingComponents.RARITY.asArray().key("rarity");
    RecipeKey<InputItem> CATALYST = ItemComponents.INPUT.key("catalyst");
    RecipeKey<String> ENTITY = StringComponent.ANY.key("entity");
    RecipeKey<Integer> POINTER = NumberComponent.INT.key("pointer").optional(0);
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT,CATALYST,RARITY,ENTITY,POINTER);

}
