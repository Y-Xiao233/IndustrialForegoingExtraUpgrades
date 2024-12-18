package net.yxiao233.ifeu.common.compact.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.yxiao233.ifeu.common.compact.kubejs.item.EfficiencyAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.item.ProcessingAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.item.SpeedAddonItemBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.schema.InfuserSchema;

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
    }
}
