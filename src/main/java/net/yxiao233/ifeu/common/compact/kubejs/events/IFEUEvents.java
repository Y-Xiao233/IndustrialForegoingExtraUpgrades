package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public class IFEUEvents {
    public static final EventGroup GROUP = EventGroup.of("IFEUEvents");

    public static final EventHandler STRUCTURE_MODIFY = GROUP.startup("structureModify", () -> IFEUStructureModifyJS.class);
    public static final EventHandler STRUCTURE_RENDER = GROUP.startup("structureRender", () -> IFEUStructureRenderJS.class);
}
