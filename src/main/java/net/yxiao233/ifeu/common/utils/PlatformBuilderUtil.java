package net.yxiao233.ifeu.common.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.yxiao233.ifeu.api.block.entity.IFEUAreaWorkingTile;

import java.util.ArrayList;
import java.util.List;

public class PlatformBuilderUtil {
    public static List<BlockPos> getFrameBlockPosList(IFEUAreaWorkingTile<?> entity){
        List<BlockPos> posList = new ArrayList<>();
        int bounds = entity.getFrameBounds();
        int range = entity.getLandRange();
        BlockPos entityPos = entity.getCenterPos();
        int d0 = adjustForDirection(entity.getFrameDirection().northSouth().get());
        int d1 = adjustForDirection(entity.getFrameDirection().westEast().get());

        int[] xs = posPrefix(entity,d1,entityPos.getX());
        int[] zs = posPrefix(entity,d0,entityPos.getZ());
        for(int x = xs[0]; x < xs[1]; x ++){
            if(d0 <= 0){
                for(int z = entityPos.getZ() + range * d0 + bounds * d0; z <= entityPos.getZ() - range * d0; z ++){
                    posList.add(new BlockPos(x,entityPos.getY() + entity.getYOffset(),z));
                }
            }else{
                for(int z = entityPos.getZ() - range * d0; z <= entityPos.getZ() + range * d0 + bounds * d0; z ++){
                    posList.add(new BlockPos(x,entityPos.getY() + entity.getYOffset(),z));
                }
            }
        }

        for(int z = zs[0]; z < zs[1]; z ++){
            for(int x = entityPos.getX() - range; x <= entityPos.getX() + range; x ++){
                posList.add(new BlockPos(x,entityPos.getY() + entity.getYOffset(),z));
            }
        }

        return posList;
    }

    public static List<BlockPos> getLandPosList(IFEUAreaWorkingTile<?> entity){
        List<BlockPos> posList = new ArrayList<>();
        BlockPos entityPos = entity.getCenterPos();
        int range = entity.getLandRange();
        for(int x = entityPos.getX() - range; x <= entityPos.getX() + range; x ++){
            for(int z = entityPos.getZ() - range; z <= entityPos.getZ() + range; z ++){
                posList.add(new BlockPos(x,entityPos.getY() + entity.getYOffset(),z));
            }
        }
        return posList;
    }
    private static int adjustForDirection(Direction direction) {
        return switch (direction) {
            case NORTH,WEST -> -1;
            default -> 1;
        };
    }

    private static int[] posPrefix(IFEUAreaWorkingTile<?> entity,int d, int XZ){
        int range = entity.getLandRange();
        int bounds = entity.getFrameBounds();
        int minXZ,maxXZ;
        if(d <= 0){
            minXZ = XZ - range - bounds;
            maxXZ = XZ - range;
        }else{
            minXZ = XZ + range + 1;
            maxXZ = XZ + range + bounds + 1;
        }
        return new int[]{minXZ,maxXZ};
    }
}
