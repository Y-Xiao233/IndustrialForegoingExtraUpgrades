package net.yxiao233.ifeu.api.components;

import com.hrznstudio.titanium.component.IComponentHarness;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.sideness.IFacingComponent;
import com.hrznstudio.titanium.util.FacingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.yxiao233.ifeu.api.block.EnumPropertyTileBlock;
import net.yxiao233.ifeu.common.utils.ReflectionUtil;

public class EnumPropertyOutputSidedInventoryComponent<T extends IComponentHarness> extends SidedInventoryComponent<T> {
    public EnumPropertyOutputSidedInventoryComponent(String name, int xPos, int yPos, int size, int position) {
        super(name, xPos, yPos, size, position);
    }

    private boolean workSides(Level level, BlockPos pos, Direction blockFacing, int workAmount, IFacingComponent.FaceMode mode) {
        for (FacingUtil.Sideness sideness : this.getFacingModes().keySet()) {
            if (this.getFacingModes().get(sideness) == mode) {
                Direction real = FacingUtil.getFacingFromSide(blockFacing, sideness);
                BlockPos p = pos.relative(real);
                Block block = level.getBlockState(p).getBlock();
                do{
                    if(block instanceof EnumPropertyTileBlock<?,?>){
                        p = p.relative(real);
                        block = level.getBlockState(p).getBlock();
                    }
                }while (block instanceof EnumPropertyTileBlock<?,?>);
                if(block == Blocks.AIR){
                    continue;
                }
                IItemHandler cap = level.getCapability(Capabilities.ItemHandler.BLOCK, pos.relative(real), real.getOpposite());
                Boolean transfer = ReflectionUtil.runPrivateMethod("transfer", new Class[]{FacingUtil.Sideness.class, IItemHandler.class, IItemHandler.class, int.class}, this, SidedInventoryComponent.class, new Object[]{sideness, mode == FaceMode.PUSH ? this : cap, mode == FaceMode.PUSH ? cap : this, workAmount}, boolean.class);
                if (cap != null && Boolean.TRUE.equals(transfer)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean work(Level world, BlockPos pos, Direction blockFacing, int workAmount) {
        return this.workSides(world, pos, blockFacing, workAmount, FaceMode.PUSH) || this.workSides(world, pos, blockFacing, workAmount, FaceMode.PULL);
    }
}
