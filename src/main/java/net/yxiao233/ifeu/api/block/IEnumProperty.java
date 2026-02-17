package net.yxiao233.ifeu.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IEnumProperty<T extends Enum<T> & StringRepresentable> extends StringRepresentable{
    List<T> getValues();
    String getName();
    RelativeBlockPos getRelativeBlockPos();
    IEnumProperty<T> getDefault();
    default BlockPos getBlockPos(BlockPos pos){
        return pos.offset(getRelativeBlockPos().x(),getRelativeBlockPos().y(),getRelativeBlockPos().z());
    }
    default BlockPos getBlockPos(BlockPos pos, Direction direction){
        if(direction == null){
            return getBlockPos(pos);
        }
        return rotateOffset(pos,direction,getRelativeBlockPos().x(),getRelativeBlockPos().y(),getRelativeBlockPos().z());
    }
    default BlockPos getMainPos(BlockPos other){
        return other.offset(-getRelativeBlockPos().x(),-getRelativeBlockPos().y(),-getRelativeBlockPos().z());
    }
    default BlockPos getMainPos(BlockPos other, Direction direction){
        if(direction == null){
            return getMainPos(other);
        }
        int[] relativeDirection = rotateOffsetByFacing(getRelativeBlockPos().x(),getRelativeBlockPos().y(),getRelativeBlockPos().z(),direction);
        return other.offset(-relativeDirection[0],-relativeDirection[1],-relativeDirection[2]);
    }
    default BlockPos rotateOffset(BlockPos basePos, Direction facing, int x, int y, int z) {
        int[] rotated = rotateOffsetByFacing(x, y, z, facing);
        return basePos.offset(rotated[0], rotated[1], rotated[2]);
    }

    default int[] rotateOffsetByFacing(int x, int y, int z, Direction facing) {
        return switch (facing) {
            case SOUTH -> new int[]{-x, y, -z};
            case EAST -> new int[]{-z, y, x};
            case WEST -> new int[]{z, y, -x};
            default -> new int[]{x, y, z};
        };
    }

    @Override
    default @NotNull String getSerializedName(){
        return getName();
    }
}
