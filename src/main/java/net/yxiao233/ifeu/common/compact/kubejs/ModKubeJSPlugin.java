package net.yxiao233.ifeu.common.compact.kubejs;

import com.hrznstudio.titanium.block.RotatableBlock;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.yxiao233.ifeu.api.structure.MultiBlockStructureBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUEvents;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUStructureModifyJS;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUStructureRenderJS;
import net.yxiao233.ifeu.common.compact.kubejs.items.*;
import net.yxiao233.ifeu.common.compact.kubejs.schemas.*;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;
import net.yxiao233.ifeu.common.utils.TooltipHelper;

public class ModKubeJSPlugin extends KubeJSPlugin {

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("IFEUMultiBlockStructures", IFEUMultiBlockStructures.class);
        event.add("MultiBlockStructureBuilder", MultiBlockStructureBuilder.class);
        event.add("TooltipHelper", TooltipHelper.class);
        event.add("RotatableBlock", RotatableBlock.class);
    }

    @Override
    public void init() {
        RegistryInfo.ITEM.addType("industrialforegoing:speed_addon", SpeedAddonItemBuilder.class, SpeedAddonItemBuilder::new);
        RegistryInfo.ITEM.addType("industrialforegoing:efficiency_addon", EfficiencyAddonItemBuilder.class, EfficiencyAddonItemBuilder::new);
        RegistryInfo.ITEM.addType("industrialforegoing:processing_addon", ProcessingAddonItemBuilder.class, ProcessingAddonItemBuilder::new);
        RegistryInfo.ITEM.addType("industrialforegoing:range_addon", RangeAddonItemBuilder.class,RangeAddonItemBuilder::new);
        RegistryInfo.ITEM.addType("ifeu:thread_addon", ThreadAddonItemBuilder.class,ThreadAddonItemBuilder::new);
        RegistryInfo.ITEM.addType("ifeu:apple_addon", AppleAddonItemBuilder.class,AppleAddonItemBuilder::new);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.namespace("ifeu")
                .register("infuser", InfuserSchema.SCHEMA)
                .register("arcane_dragon_egg_forging",ArcaneDragonEggForgingSchema.SCHEMA)
                .register("shaped", ShapedSchema.SCHEMA)
                .register("shapeless", ShapelessSchema.SCHEMA);

        event.namespace("industrialforegoing")
                .register("crusher", CrusherSchema.SCHEMA)
                .register("dissolution_chamber", DissolutionChamberSchema.SCHEMA)
                .register("fluid_extractor", FluidExtractorSchema.SCHEMA)
                .register("laser_drill_fluid", LaserDrillFluidSchema.SCHEMA)
                .register("laser_drill_ore", LaserDrillOreSchema.SCHEMA)
                .register("stonework_generate", StoneWorkGenerateSchema.SCHEMA);
    }

    @Override
    public void registerEvents() {
        IFEUEvents.GROUP.register();
    }

    @Override
    public void afterInit() {
        var structure = new IFEUStructureModifyJS();
        IFEUEvents.STRUCTURE_MODIFY.post(ScriptType.STARTUP, structure);
    }
}
