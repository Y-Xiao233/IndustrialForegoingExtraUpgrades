package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.resources.ResourceLocation;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;

public class IFEUStructureModifyJS extends EventJS {
    static {
        IFEUMultiBlockStructures.init();
    }
    public void modify(IFEUMultiBlockStructures oldStructure, MultiBlockStructure newStructure){
        oldStructure.modify(newStructure);
    }

    public void modify(ResourceLocation id, MultiBlockStructure newStructure){
        var structure = IFEUMultiBlockStructures.getById(id);
        if(structure != null){
            structure.modify(newStructure);
        }else{
            throw new RuntimeException("Unable to find the structure corresponding to " + id);
        }
    }

    public void modify(ResourceLocation modifiedId, ResourceLocation id){
        var structure0 = IFEUMultiBlockStructures.getById(modifiedId);
        var structure1 = IFEUMultiBlockStructures.getById(id);
        if(structure0 != null){
            if(structure1 != null){
                structure0.modify(structure1.getStructure());
            }else{
                throw new RuntimeException("Unable to find the structure corresponding to " + id);
            }
        }else{
            throw new RuntimeException("Unable to find the structure corresponding to " + modifiedId);
        }
    }
}
