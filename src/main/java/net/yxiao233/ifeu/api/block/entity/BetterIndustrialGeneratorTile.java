package net.yxiao233.ifeu.api.block.entity;

import com.buuz135.industrial.block.tile.IndustrialGeneratorTile;
import com.buuz135.industrial.capability.tile.BigEnergyHandler;
import com.buuz135.industrial.config.ClientConfig;
import com.buuz135.industrial.gui.component.DonationGuiAddon;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.module.BlockWithTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public abstract class BetterIndustrialGeneratorTile<T extends BetterIndustrialGeneratorTile<T>> extends BetterGeneratorTile<T> {
    public BetterIndustrialGeneratorTile(BlockWithTile basicTileBlock, BlockPos blockPos, BlockState blockState) {
        super((BasicTileBlock)basicTileBlock.getBlock(), (BlockEntityType)basicTileBlock.type().get(), blockPos, blockState);
    }

    @OnlyIn(Dist.CLIENT)
    public void initClient() {
        super.initClient();
        if (ClientConfig.DONATION_LINK_ENABLED) {
            this.addGuiAddonFactory(() -> {
                return new DonationGuiAddon(-22, 4);
            });
        }

    }

    public ItemInteractionResult onActivated(Player playerIn, InteractionHand hand, Direction facing, double hitX, double hitY, double hitZ) {
        if (super.onActivated(playerIn, hand, facing, hitX, hitY, hitZ) == ItemInteractionResult.SUCCESS) {
            return ItemInteractionResult.SUCCESS;
        } else {
            this.openGui(playerIn);
            return ItemInteractionResult.SUCCESS;
        }
    }

    @Nonnull
    protected EnergyStorageComponent<T> createEnergyStorage() {
        return new BigEnergyHandler<T>(this.getEnergyCapacity(), 0, this.getExtractingEnergy(), 10, 20) {
            public void sync() {
                BetterIndustrialGeneratorTile.this.syncObject(BetterIndustrialGeneratorTile.this.getEnergyStorage());
            }
        };
    }
}
