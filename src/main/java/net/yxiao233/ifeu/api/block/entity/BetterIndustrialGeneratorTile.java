package net.yxiao233.ifeu.api.block.entity;

import com.buuz135.industrial.capability.tile.BigEnergyHandler;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;

public abstract class BetterIndustrialGeneratorTile<T extends BetterIndustrialGeneratorTile<T>> extends BetterGeneratorTile<T> {
    public BetterIndustrialGeneratorTile(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> basicTileBlock, BlockPos blockPos, BlockState blockState) {
        super((BasicTileBlock)((RegistryObject)basicTileBlock.getLeft()).get(), (BlockEntityType)((RegistryObject)basicTileBlock.getRight()).get(), blockPos, blockState);
    }

    public InteractionResult onActivated(Player playerIn, InteractionHand hand, Direction facing, double hitX, double hitY, double hitZ) {
        if (super.onActivated(playerIn, hand, facing, hitX, hitY, hitZ) == InteractionResult.SUCCESS) {
            return InteractionResult.SUCCESS;
        } else {
            this.openGui(playerIn);
            return InteractionResult.SUCCESS;
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
