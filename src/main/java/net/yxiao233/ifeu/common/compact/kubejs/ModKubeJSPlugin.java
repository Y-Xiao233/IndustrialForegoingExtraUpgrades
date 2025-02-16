package net.yxiao233.ifeu.common.compact.kubejs;

import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.yxiao233.ifeu.common.compact.kubejs.items.EfficiencyAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.items.ProcessingAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.items.SpeedAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.schemas.*;

public class ModKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.of(Registries.ITEM, reg ->{
            reg.add("industrialforegoing:speed_addon",SpeedAddonItemBuilder.class, SpeedAddonItemBuilder::new);
            reg.add("industrialforegoing:efficiency_addon", EfficiencyAddonItemBuilder.class, EfficiencyAddonItemBuilder::new);
            reg.add("industrialforegoing:processing_addon", ProcessingAddonItemBuilder.class, ProcessingAddonItemBuilder::new);
        });
    }

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry event) {
        event.namespace("ifeu")
                .register("infuser", InfuserSchema.SCHEMA)
                .register("arcane_dragon_egg_forging",ArcaneDragonEggForgingSchema.SCHEMA)
                .register("shaped", ShapedSchema.SCHEMA)
                .register("shapeless",ShapelessSchema.SCHEMA);

        event.namespace("industrialforegoing")
                .register("crusher", CrusherSchema.SCHEMA)
                .register("dissolution_chamber", DissolutionChamberSchema.SCHEMA)
                .register("fluid_extractor", FluidExtractorSchema.SCHEMA)
                .register("stonework_generate", StoneWorkGenerateSchema.SCHEMA);
    }
}
