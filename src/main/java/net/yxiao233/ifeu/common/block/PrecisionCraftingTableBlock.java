package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.PrecisionCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import org.jetbrains.annotations.NotNull;

public class PrecisionCraftingTableBlock extends IndustrialBlock<PrecisionCraftingTableEntity> {
    public PrecisionCraftingTableBlock() {
        super("precision_crafting_table", Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL).noOcclusion(), PrecisionCraftingTableEntity.class, IFEUItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return PrecisionCraftingTableEntity::new;
    }


    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.NONE;
    }
}
