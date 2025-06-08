package net.yxiao233.ifeu.common.utils;

import net.minecraft.core.BlockPos;

public class IntArrayBlockPosUtil {
    public static BlockPos intArrayToBlockPos(int[] xyz){
        if(xyz.length != 3){
            return BlockPos.ZERO;
        }
        return new BlockPos(xyz[0],xyz[1],xyz[2]);
    }

    public static int[] blockPosToIntArray(BlockPos blockPos){
        if(blockPos == null){
            return new int[]{0,0,0};
        }
        return new int[]{blockPos.getX(),blockPos.getY(),blockPos.getZ()};
    }
}
