package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.block.entity.FluidTransferEntity;

public class FluidTransferBlock extends IndustrialBlock<FluidTransferEntity> {
    public FluidTransferBlock() {
        super("test", Properties.copy(Blocks.IRON_BLOCK), FluidTransferEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return FluidTransferEntity::new;
    }
}
