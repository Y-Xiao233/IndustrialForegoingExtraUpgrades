package net.yxiao233.ifeu.mixin.entity;

import com.buuz135.industrial.block.tile.IndustrialMachineTile;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.block.tile.MachineTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.yxiao233.ifeu.api.capability.EnergyAddonEntry;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicInteger;

@Mixin(IndustrialMachineTile.class)
public abstract class MixinIndustrialMachineTile<T extends IndustrialMachineTile<T>> extends MachineTile<T> {
    @Unique
    private final AtomicInteger ifeu$baseCapacity = ifeu$getBaseCapacity();
    public MixinIndustrialMachineTile(BasicTileBlock<T> base, BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(base, blockEntityType, pos, state);
    }

    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem addonItem && addonItem.getType().equals(IFEUAugmentTypes.ENERGY)){
            return AugmentInventoryHelper.canAccept(this,augment);
        }
        return super.canAcceptAugment(augment);
    }

    @Unique
    public final AtomicInteger ifeu$getBaseCapacity(){
        return new AtomicInteger(this.getEnergyStorage().getMaxEnergyStored());
    }

    @Override
    public void serverTick(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull T blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        EnergyAddonEntry.create(this, this.ifeu$baseCapacity.get()).updateEnergyCapacity();
    }

    @Inject(
            method = "clientTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lcom/buuz135/industrial/block/tile/IndustrialMachineTile;)V",
            at = @At("RETURN")
    )
    private void onClientTick(Level level, BlockPos pos, BlockState state, T blockEntity, CallbackInfo ci){
        EnergyAddonEntry.create(this, ifeu$baseCapacity.get()).updateEnergyCapacity();
    }
}
