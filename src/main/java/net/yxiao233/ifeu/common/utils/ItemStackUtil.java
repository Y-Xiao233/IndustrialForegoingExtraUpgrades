package net.yxiao233.ifeu.common.utils;

import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;

import java.util.Optional;

import static net.minecraft.world.item.ItemStack.CODEC;

public class ItemStackUtil {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ItemStack parseOptional(CompoundTag tag) {
        return tag.isEmpty() ? ItemStack.EMPTY : ItemStack.of(tag);
    }

    public static CompoundTag saveOptional(ItemStack stack, CompoundTag tag) {
        return stack.isEmpty() ? new CompoundTag() : stack.save(tag == null || tag.isEmpty() ? new CompoundTag() : tag);
    }
}
