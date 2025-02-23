package net.yxiao233.ifeu.common.utils;

import net.minecraft.core.BlockPos;

public class IntArrayBlockPosUtil {
    public static BlockPos intArrayToBlockPos(int[] xyz){
        return new BlockPos(xyz[0],xyz[1],xyz[2]);
    }

    public static int[] BlockPosToIntArray(BlockPos blockPos){
        return new int[]{blockPos.getX(),blockPos.getY(),blockPos.getZ()};
    }
}
