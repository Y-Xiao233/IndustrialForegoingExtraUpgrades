package net.yxiao233.ifeu.common.compact.kubejs;

import com.hrznstudio.titanium.block.RotatableBlock;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.yxiao233.ifeu.api.structure.MultiBlockStructureBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUStructureEvents;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUStructureModifyJS;
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
        RegistryInfo.ITEM.addType("ifeu:heal_addon", HealAddonItemBuilder.class,HealAddonItemBuilder::new);
        RegistryInfo.ITEM.addType("ifeu:chance_addon", ChanceAddonItemBuilder.class,ChanceAddonItemBuilder::new);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.namespace("ifeu")
                .register("infuser", InfuserSchema.SCHEMA)
                .register("arcane_dragon_egg_forging",ArcaneDragonEggForgingSchema.SCHEMA)
                .register("shaped", ShapedSchema.SCHEMA)
                .register("shapeless", ShapelessSchema.SCHEMA)
                .register("precision_shaped", PrecisionShapedSchema.SCHEMA)
                .register("precision_shapeless", PrecisionShapelessSchema.SCHEMA);

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
        IFEUStructureEvents.STRUCTURE.register();
    }

    @Override
    public void afterInit() {
        IFEUStructureEvents.MODIFY.post(ScriptType.STARTUP, new IFEUStructureModifyJS());
    }
}
