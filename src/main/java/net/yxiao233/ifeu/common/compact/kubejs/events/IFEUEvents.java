package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public class IFEUEvents {
    public static final EventGroup GROUP = EventGroup.of("IFEUEvents");

    public static final EventHandler STRUCTURES = GROUP.startup("structureModify", () -> IFEUStructuresEvent.class);
}
