package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;

public class IFEUStructureRegistryJS extends EventJS {
    public void registry(ResourceLocation id, Item coreMachine, MultiBlockStructure structure){
        new IFEUMultiBlockStructures(id, () -> coreMachine, () -> structure);
    }
}
