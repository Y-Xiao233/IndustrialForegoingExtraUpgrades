package net.yxiao233.ifeu.mixin.entity;

import com.buuz135.industrial.block.tile.IndustrialMachineTile;
import com.buuz135.industrial.block.tile.IndustrialWorkingTile;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.module.BlockWithTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IndustrialWorkingTile.class)
public abstract class MixinIndustrialWorkingTile<T extends IndustrialWorkingTile<T>> extends IndustrialMachineTile<T> {

    public MixinIndustrialWorkingTile(BlockWithTile basicTileBlock, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, blockPos, blockState);
    }

    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem addonItem && addonItem.getType().equals(IFEUAugmentTypes.CREATIVE)){
            return AugmentInventoryHelper.canAccept(this,augment);
        }
        return super.canAcceptAugment(augment);
    }

    @Override
    public void serverTick(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull T blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        if(AugmentInventoryHelper.contains(this, IFEUAugmentTypes.CREATIVE)){
            EnergyStorageComponent<T> energyStorage = this.getEnergyStorage();
            energyStorage.setEnergyStored(Integer.MAX_VALUE);
        }
    }
}
