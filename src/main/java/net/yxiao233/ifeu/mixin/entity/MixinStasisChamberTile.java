package net.yxiao233.ifeu.mixin.entity;

import com.buuz135.industrial.block.misc.tile.StasisChamberTile;
import com.buuz135.industrial.block.tile.IndustrialAreaWorkingTile;
import com.buuz135.industrial.block.tile.RangeManager;
import com.hrznstudio.titanium.module.BlockWithTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StasisChamberTile.class)
public abstract class MixinStasisChamberTile extends IndustrialAreaWorkingTile<StasisChamberTile> {
    @Unique
    private int industrialForegoingExtraUpgrades$healAddonTier = -1;

    public MixinStasisChamberTile(BlockWithTile basicTileBlock, RangeManager.RangeType type, boolean acceptsRangeUpgrades, int estimatedPower, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, type, acceptsRangeUpgrades, estimatedPower, blockPos, blockState);
    }


    @Redirect(
            method = "work",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;heal(F)V",
                    ordinal = 0
            )
    )
    private void redirectHeal(Mob entity, float amount) {
        entity.heal(1 + industrialForegoingExtraUpgrades$healAddonTier * 3);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, StasisChamberTile blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        industrialForegoingExtraUpgrades$healAddonTier = AugmentInventoryHelper.getAugmentTier(this, IFEUAugmentTypes.HEAL);
    }

    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem item && item.getType() == IFEUAugmentTypes.HEAL){
            return AugmentInventoryHelper.canAccept(this,augment);
        }
        return super.canAcceptAugment(augment);
    }
}
