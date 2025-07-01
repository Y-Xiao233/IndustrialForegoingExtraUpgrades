package net.yxiao233.ifeu.common.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantUtil {
    public static ItemStack enchant(ItemStack stack, Enchantment enchantment, int level){
        stack.enchant(enchantment,level);
        return stack;
    }

    public static ItemStack getEnchantedBook(Enchantment enchantment, int level){
        ItemStack stack = Items.ENCHANTED_BOOK.getDefaultInstance();
        stack.enchant(enchantment,level);
        return stack;
    }
}
