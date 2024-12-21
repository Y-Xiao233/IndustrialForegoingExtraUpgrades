package net.yxiao233.ifeu.common.compact.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.yxiao233.ifeu.common.compact.kubejs.items.EfficiencyAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.items.ProcessingAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.items.SpeedAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.schemas.*;

public class ModKubeJSPlugin extends KubeJSPlugin {

    @Override
    public void init() {
        RegistryInfo.ITEM.addType("industrialforegoing:speed_addon", SpeedAddonItemBuilder.class, SpeedAddonItemBuilder::new);
        RegistryInfo.ITEM.addType("industrialforegoing:efficiency_addon", EfficiencyAddonItemBuilder.class, EfficiencyAddonItemBuilder::new);
        RegistryInfo.ITEM.addType("industrialforegoing:processing_addon", ProcessingAddonItemBuilder.class, ProcessingAddonItemBuilder::new);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.namespace("ifeu")
                .register("infuser", InfuserSchema.SCHEMA);

        event.namespace("industrialforegoing")
                .register("crusher", CrusherSchema.SCHEMA)
                .register("dissolution_chamber", DissolutionChamberSchema.SCHEMA)
                .register("fluid_extractor", FluidExtractorSchema.SCHEMA)
                .register("laser_drill_fluid", LaserDrillFluidSchema.SCHEMA)
                .register("laser_drill_ore", LaserDrillOreSchema.SCHEMA)
                .register("stonework_generate", StoneWorkGenerateSchema.SCHEMA);
    }
}
