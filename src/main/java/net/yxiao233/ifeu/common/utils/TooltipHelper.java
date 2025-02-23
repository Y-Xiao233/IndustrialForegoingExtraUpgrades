package net.yxiao233.ifeu.common.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class TooltipHelper {
    public static boolean getKeyType(KeyType keyType) {
        return switch (keyType) {
            case SHIFT -> Screen.hasShiftDown();
            case ALT -> Screen.hasAltDown();
            case CONTROL -> Screen.hasControlDown();
        };
    }

    //Multiple
    public static void addTooltip(List<Component> tooltips, ItemStack itemStack, int index) {
        tooltips.add(Component.translatable(itemIdToKey(itemStack, index)));
    }

    public static void addTooltip(List<Component> tooltips, ItemStack itemStack, ChatFormatting style, int index, Object... obj) {
        tooltips.add(Component.translatable(itemIdToKey(itemStack, index), obj).withStyle(style));
    }

    public static void addTooltip(List<Component> tooltips, ItemStack itemStack, ChatFormatting style, int index) {
        tooltips.add(Component.translatable(itemIdToKey(itemStack, index)).withStyle(style));
    }

    public static String itemIdToKey(ItemStack itemStack, int index) {
        String rawKey = itemStack.getDescriptionId();
        return "tooltip" + rawKey.substring(rawKey.indexOf(".")) + index;
    }

    public static void addTooltipWhileKeyDown(KeyType keyType, List<Component> tooltips, ItemStack itemStack, int index) {
        if (getKeyType(keyType)) {
            addTooltip(tooltips, itemStack, index);
        } else {
            tooltips.add(Component.translatable("tooltip.ifeu.held." + keyType.getValue()).withStyle(ChatFormatting.GRAY));
        }
    }

    public static void addTooltipWhileKeyDown(KeyType keyType, List<Component> tooltips, ItemStack itemStack, ChatFormatting style, int index, Object... obj) {
        if (getKeyType(keyType)) {
            addTooltip(tooltips, itemStack, style, index, obj);
        } else {
            tooltips.add(Component.translatable("tooltip.ifeu.held." + keyType.getValue()).withStyle(ChatFormatting.GRAY));
        }
    }

    public static void addTooltipWhileKeyDown(KeyType keyType, List<Component> tooltips, ItemStack itemStack, ChatFormatting style, int index) {
        if (getKeyType(keyType)) {
            addTooltip(tooltips, itemStack, style, index);
        } else {
            tooltips.add(Component.translatable("tooltip.ifeu.held." + keyType.getValue()).withStyle(ChatFormatting.GRAY));
        }
    }

    //Single
    public static void addTooltip(List<Component> tooltips, ItemStack itemStack) {
        tooltips.add(Component.translatable(itemIdToKey(itemStack)));
    }

    public static void addTooltip(List<Component> tooltips, ItemStack itemStack, ChatFormatting style, Object... obj) {
        tooltips.add(Component.translatable(itemIdToKey(itemStack), obj).withStyle(style));
    }

    public static void addTooltip(List<Component> tooltips, ItemStack itemStack, ChatFormatting style) {
        tooltips.add(Component.translatable(itemIdToKey(itemStack)).withStyle(style));
    }

    public static String itemIdToKey(ItemStack itemStack) {
        String rawKey = itemStack.getDescriptionId();
        return "tooltip" + rawKey.substring(rawKey.indexOf("."));
    }

    public static void addTooltipWhileKeyDown(KeyType keyType, List<Component> tooltips, ItemStack itemStack) {
        if (getKeyType(keyType)) {
            addTooltip(tooltips, itemStack);
        } else {
            tooltips.add(Component.translatable("tooltip.ifeu.held." + keyType.getValue()).withStyle(ChatFormatting.GRAY));
        }
    }

    public static void addTooltipWhileKeyDown(KeyType keyType, List<Component> tooltips, ItemStack itemStack, ChatFormatting style, Object... obj) {
        if (getKeyType(keyType)) {
            addTooltip(tooltips, itemStack, style, obj);
        } else {
            tooltips.add(Component.translatable("tooltip.ifeu.held." + keyType.getValue()).withStyle(ChatFormatting.GRAY));
        }
    }

    public static void addTooltipWhileKeyDown(KeyType keyType, List<Component> tooltips, ItemStack itemStack, ChatFormatting style) {
        if (getKeyType(keyType)) {
            addTooltip(tooltips, itemStack, style);
        } else {
            tooltips.add(Component.translatable("tooltip.ifeu.held." + keyType.getValue()).withStyle(ChatFormatting.GRAY));
        }
    }

    public static void addTooltipWhileKeyDown(KeyType keyType, List<Component> tooltips, ItemStack itemStack, ITooltipContext context){
        if(getKeyType(keyType)){
            context.addTooltips();
        }else{
            tooltips.add(Component.translatable("tooltip.ifeu.held." + keyType.getValue()).withStyle(ChatFormatting.GRAY));
        }
    }

    public enum KeyType {
        SHIFT("shift"),
        ALT("alt"),
        CONTROL("control");

        private final String value;
        private KeyType(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
    }

    public interface ITooltipContext{
        public void addTooltips();
    }
}
