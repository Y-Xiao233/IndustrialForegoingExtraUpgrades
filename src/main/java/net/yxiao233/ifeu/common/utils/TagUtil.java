package net.yxiao233.ifeu.common.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.registry.IFEUDataComponentTypes;

import java.util.List;
import java.util.Objects;

public class TagUtil {
    public static boolean contains(ItemStack stack, String key){
        return contains(stack.get(IFEUDataComponentTypes.COMPOUND_TAG),key);
    }
    public static boolean contains(CompoundTag tag, String key){
        if(tag == null || tag.isEmpty()){
            return false;
        }else{
            return tag.contains(key);
        }
    }

    public static CompoundTag getOrDefault(CompoundTag tag, String key){
        if(contains(tag,key)){
            return (CompoundTag) tag.get(key);
        }else{
            return new CompoundTag();
        }
    }

    public static void saveInt(CompoundTag newTag, CompoundTag oldTag, String key){
        if(contains(oldTag,key)){
            newTag.putInt(key,oldTag.getInt(key));
        }
    }

    public static void saveAllBlockPos(CompoundTag newTag, CompoundTag oldTag, List<String> keys){
        if(newTag == null || oldTag == null){
            return;
        }
        keys.forEach(key ->{
            if(contains(oldTag,key)){
                newTag.putIntArray(key,oldTag.getIntArray(key));
            }
        });
    }

    public static void saveAllTag(CompoundTag newTag, CompoundTag oldTag, List<String> keys){
        if(newTag == null || oldTag == null){
            return;
        }
        keys.forEach(key ->{
            if(contains(oldTag,key)){
                newTag.put(key, Objects.requireNonNull(oldTag.get(key)));
            }
        });
    }
}
