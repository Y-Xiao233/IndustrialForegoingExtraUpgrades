package net.yxiao233.ifeu.common.compact.kubejs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.script.ScriptTypeHolder;
import net.minecraft.core.registries.Registries;
import net.yxiao233.ifeu.api.structure.MultiBlockStructureBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUEvents;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUStructuresEvent;
import net.yxiao233.ifeu.common.compact.kubejs.items.*;
import net.yxiao233.ifeu.common.compact.kubejs.schemas.*;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;

public class ModKubeJSPlugin implements KubeJSPlugin {

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("IFEUMultiBlockStructures", IFEUMultiBlockStructures.class);
        bindings.add("MultiBlockStructureBuilder", MultiBlockStructureBuilder.class);
    }

    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.of(Registries.ITEM, reg ->{
            reg.add("industrialforegoing:speed_addon",SpeedAddonItemBuilder.class, SpeedAddonItemBuilder::new);
            reg.add("industrialforegoing:efficiency_addon", EfficiencyAddonItemBuilder.class, EfficiencyAddonItemBuilder::new);
            reg.add("industrialforegoing:processing_addon", ProcessingAddonItemBuilder.class, ProcessingAddonItemBuilder::new);
            reg.add("industrialforegoing:range_addon", RangeAddonItemBuilder.class,RangeAddonItemBuilder::new);
            reg.add("ifeu:thread_addon", ThreadAddonItemBuilder.class,ThreadAddonItemBuilder::new);
            reg.add("ifeu:apple_addon", AppleAddonItemBuilder.class,AppleAddonItemBuilder::new);
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

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(IFEUEvents.GROUP);
    }

    @Override
    public void afterInit() {
        var structure = new IFEUStructuresEvent();
        IFEUEvents.STRUCTURES.post(ScriptType.STARTUP, structure);
    }
}
