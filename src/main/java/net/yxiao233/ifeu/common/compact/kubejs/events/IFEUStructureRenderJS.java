package net.yxiao233.ifeu.common.compact.kubejs.events;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.ModList;
import net.yxiao233.ifeu.api.block.renderer.IFEUStructureEntityRendererJS;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;

public class IFEUStructureRenderJS extends EventJS{
    static {
        if(ModList.get().isLoaded("kubejs")){
            IFEUStructureEvents.REGISTRY.post(ScriptType.STARTUP,new IFEUStructureRegistryJS());
        }
    }
    @HideFromJS
    private static final HashMap<ResourceLocation, Pair<BlockEntityType<?>, MultiBlockStructure>> map = new HashMap<>();
    @HideFromJS
    private static final HashMap<ResourceLocation, Item> handMap = new HashMap<>();
    public void registry(ResourceLocation id, Item handItem, BlockEntityType<?> type){
        var structure = IFEUMultiBlockStructures.getById(id);
        if(structure != null){
            if(handItem == null || handItem.getDefaultInstance().is(Items.AIR) || handItem.getDefaultInstance().isEmpty()){
                registry(id,type);
            }else{
                map.put(id,Pair.of(type,structure.getStructure()));
                handMap.put(id,handItem);
            }
        }else{
            throw new RuntimeException("Unable to find the structure corresponding to " + id);
        }
    }

    public void registry(ResourceLocation id, BlockEntityType<?> type){
        var structure = IFEUMultiBlockStructures.getById(id);
        if(structure != null){
            map.put(id, Pair.of(type,structure.getStructure()));
            handMap.put(id, Items.AIR);
        }else{
            throw new RuntimeException("Unable to find the structure corresponding to " + id);
        }
    }

    @HideFromJS
    public static HashMap<ResourceLocation, Pair<BlockEntityType<?>, MultiBlockStructure>> getMap(){
        return map;
    }

    @HideFromJS
    public static void registryRender(EntityRenderersEvent.RegisterRenderers event) {
        IFEUStructureEvents.RENDER.post(ScriptType.STARTUP, new IFEUStructureRenderJS());
        map.forEach((id, pair) -> {
            event.registerBlockEntityRenderer(pair.getLeft(),context -> new IFEUStructureEntityRendererJS(context, pair.getRight(),handMap.get(id)));
        });
    }
}
