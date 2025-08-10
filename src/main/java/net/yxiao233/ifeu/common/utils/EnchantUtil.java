package net.yxiao233.ifeu.common.utils;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class EnchantUtil {
    public static ItemStack enchant(Level level, ItemStack stack, ResourceKey<Enchantment> enchantment, int tier){
        stack.enchant(level.registryAccess().holderOrThrow(enchantment),tier);
        return stack;
    }

    public static ItemStack getEnchantedBook(Level level, ResourceKey<Enchantment> enchantment, int tier){
        ItemStack stack = Items.ENCHANTED_BOOK.getDefaultInstance();
        stack.enchant(level.registryAccess().holderOrThrow(enchantment),tier);
        return stack;
    }
}
