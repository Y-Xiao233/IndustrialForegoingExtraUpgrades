package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.yxiao233.ifeu.api.block.renderer.IFEUStructureEntityRendererJS;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;

public class IFEUStructureRenderJS extends EventJS{
    @HideFromJS
    private static final HashMap<String, Pair<BlockEntityType<?>, MultiBlockStructure>> map = new HashMap<>();
    @HideFromJS
    private static final HashMap<String, Item> handMap = new HashMap<>();

    public void registry(String id, Item handItem, BlockEntityType<?> type, MultiBlockStructure structure){
        if(handItem == null || handItem.getDefaultInstance().is(Items.AIR) || handItem.getDefaultInstance().isEmpty()){
            registry(id,type,structure);
        }
        if(!merge(id,type,structure)){
            map.put(id, Pair.of(type,structure));
        }
        if(handItemMerge(id, handItem)){
            handMap.put(id,handItem);
        }
    }

    public void registry(String id, BlockEntityType<?> type, MultiBlockStructure structure){
        if(!merge(id,type,structure)){
            map.put(id, Pair.of(type,structure));
        }
        if(handItemMerge(id, Items.AIR)){
            handMap.put(id, Items.AIR);
        }
    }

    @Info(value = "This method can only be used for the structures added in this event. If you want to modify the structures added in ifeu, please use IFEUEvents.structureModify")
    public void remove(String id){
        map.remove(id);
        handMap.remove(id);
    }

    @Info(value = "This method can only be used for the structures added in this event. If you want to modify the structures added in ifeu, please use IFEUEvents.structureModify")
    public boolean merge(String id, BlockEntityType<?> type ,MultiBlockStructure newStructure){
        if(map.containsKey(id)){
            map.merge(id, Pair.of(type,newStructure), (oldStructure, pair) -> pair);
            return true;
        }else{
            return false;
        }
    }

    @Info(value = "This method can only be used for the structures added in this event.")
    public boolean handItemMerge(String id, Item item){
        if(handMap.containsKey(id)){
            handMap.merge(id, item, (oldItem, newItem) -> newItem);
            return false;
        }else{
            return true;
        }
    }

    @Info(value = "This method can only be used for structures added in this event. To detect if there are structures added by ifeu, please use IFEUEvents.structureModify")
    public boolean contains(String id){
        return map.containsKey(id);
    }

    @Info(value = "This method can only obtain the corresponding MultiBlockStructure through the structure ID in the event")
    public MultiBlockStructure getStructureById(String id){
        return map.get(id).getRight();
    }
    @Info(value = "This method can only obtain the corresponding BlockEntityType through the structure ID in the event")
    public BlockEntityType<?> getBlockEntityTypeById(String id){
        return map.get(id).getLeft();
    }
    @HideFromJS
    public static HashMap<String, Pair<BlockEntityType<?>, MultiBlockStructure>> getMap(){
        return map;
    }

    @HideFromJS
    public static void registryRender(EntityRenderersEvent.RegisterRenderers event) {
        IFEUEvents.STRUCTURE_RENDER.post(ScriptType.STARTUP, new IFEUStructureRenderJS());
        map.forEach((id, pair) -> {
            event.registerBlockEntityRenderer(pair.getLeft(),context -> new IFEUStructureEntityRendererJS(context, pair.getRight(),handMap.get(id)));
        });
    }
}
