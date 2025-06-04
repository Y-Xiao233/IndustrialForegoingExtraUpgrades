package net.yxiao233.ifeu.common.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class TagUtil {

    public static boolean contains(ItemStack stack, String key){
        return contains(stack.getTag(),key);
    }
    public static boolean contains(CompoundTag tag, String key){
        if(tag == null || tag.isEmpty()){
            return false;
        }else{
            return tag.contains(key);
        }
    }
}
