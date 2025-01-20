package net.yxiao233.ifeu.common.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class ComponentUtil {
    public static MutableComponent simpleComponent(String key,ChatFormatting chatFormatting){
        if(chatFormatting == null){
            return Component.translatable(key);
        }
        return Component.translatable(key).withStyle(chatFormatting);
    }

    public static MutableComponent simpleLiteralComponent(String value,ChatFormatting chatFormatting){
        if(chatFormatting == null){
            return Component.literal(value);
        }
        return Component.literal(value).withStyle(chatFormatting);
    }
}
