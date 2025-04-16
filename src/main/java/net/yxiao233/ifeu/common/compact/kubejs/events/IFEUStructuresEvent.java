package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.KubeEvent;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;

public class IFEUStructuresEvent implements KubeEvent {
    public void modify(IFEUMultiBlockStructures oldStructure , MultiBlockStructure newStructure){
        oldStructure.modify(newStructure);
    }
}
