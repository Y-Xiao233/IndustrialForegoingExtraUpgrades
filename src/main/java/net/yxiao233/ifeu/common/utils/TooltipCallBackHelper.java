package net.yxiao233.ifeu.common.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class TooltipCallBackHelper {
    private Component component;
    public TooltipCallBackHelper(String translatableKey, ChatFormatting style){
        this(Component.translatable(translatableKey).withStyle(style));
    }
    public TooltipCallBackHelper(String translatableKey){
        this(Component.translatable(translatableKey));
    }

    public TooltipCallBackHelper(Component component){
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }
}
