package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public class IFEUStructureEvents {
    public static final EventGroup STRUCTURE = EventGroup.of("IFEUStructureEvents");

    public static final EventHandler MODIFY = STRUCTURE.startup("modify", () -> IFEUStructureModifyJS.class);
    public static final EventHandler RENDER = STRUCTURE.startup("render", () -> IFEUStructureRenderJS.class);
    public static final EventHandler REGISTRY = STRUCTURE.startup("registry", () -> IFEUStructureRegistryJS.class);
}
