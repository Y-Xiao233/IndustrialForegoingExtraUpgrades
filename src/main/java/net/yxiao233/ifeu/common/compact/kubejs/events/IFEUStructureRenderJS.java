package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;

public class IFEUStructureRenderJS implements KubeEvent {
    @HideFromJS
    private static final HashMap<String, Pair<BlockEntityType<?>, MultiBlockStructure>> map = new HashMap<>();

    public static void registry(String id, BlockEntityType<?> type, MultiBlockStructure structure){
        if(!merge(id,type,structure)){
            map.put(id, Pair.of(type,structure));
        }
    }

    @Info(value = "This method can only be used for the structures added in this event. If you want to modify the structures added in ifeu, please use IFEUEvents.structureModify")
    public static void remove(String id){
        map.remove(id);
    }

    @Info(value = "This method can only be used for the structures added in this event. If you want to modify the structures added in ifeu, please use IFEUEvents.structureModify")
    public static boolean merge(String id, BlockEntityType<?> type ,MultiBlockStructure newStructure){
        if(map.containsKey(id)){
            map.merge(id, Pair.of(type,newStructure), (oldStructure, pair) -> pair);
            return true;
        }else{
            return false;
        }
    }

    @Info(value = "This method can only be used for structures added in this event. To detect if there are structures added by ifeu, please use IFEUEvents.structureModify")
    public static boolean contains(String id){
        return map.containsKey(id);
    }

    @Info(value = "This method can only obtain the corresponding MultiBlockStructure through the structure ID in the event")
    public static MultiBlockStructure getStructureById(String id){
        return map.get(id).getRight();
    }
    @Info(value = "This method can only obtain the corresponding BlockEntityType through the structure ID in the event")
    public static BlockEntityType<?> getBlockEntityTypeById(String id){
        return map.get(id).getLeft();
    }
    @HideFromJS
    public static HashMap<String, Pair<BlockEntityType<?>, MultiBlockStructure>> getMap(){
        return map;
    }
}