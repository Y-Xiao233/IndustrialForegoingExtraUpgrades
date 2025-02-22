package net.yxiao233.ifeu.api.block.entity;

import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.block.tile.PoweredTile;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public abstract class BetterGeneratorTile<T extends BetterGeneratorTile<T>> extends PoweredTile<T> {
    @Save
    public ProgressBarComponent<T> progressBar;
    public BetterGeneratorTile(BasicTileBlock<T> base, BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(base, blockEntityType, pos, state);

        this.addProgressBar(this.progressBar = this.getProgressBar().setComponentHarness(this.getSelf()).setCanIncrease((tileEntity) -> {
            boolean b1 = !this.isSmart();
            boolean b2 = this.getEnergyCapacity() - this.getEnergyStorage().getEnergyStored() >= this.getEnergyProducedEveryTick();
            boolean b3 = canIncrease();
            return b3 && (b1 || b2);
        }).setIncreaseType(false).setOnStart(() -> {
            this.progressBar.setMaxProgress(this.consumeFuel());
            this.progressBar.setProgress(this.progressBar.getMaxProgress());
            this.markForUpdate();
        }).setCanReset((tileEntity) -> {
            return this.canStart() && this.progressBar.getProgress() == 0;
        }).setOnTickWork(() -> {
            this.getEnergyStorage().setEnergyStored(this.getEnergyProducedEveryTick() + this.getEnergyStorage().getEnergyStored());
        }));
    }
    public abstract boolean canIncrease();

    public abstract int consumeFuel();

    public abstract boolean canStart();

    public abstract int getEnergyProducedEveryTick();

    public abstract ProgressBarComponent<T> getProgressBar();

    public abstract int getEnergyCapacity();

    public abstract int getExtractingEnergy();

    public boolean isSmart() {
        return true;
    }

    public void serverTick(Level level, BlockPos pos, BlockState state, T blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction facing = var5[var7];
            BlockPos checking = this.worldPosition.relative(facing);
            IEnergyStorage storage = (IEnergyStorage)level.getCapability(Capabilities.EnergyStorage.BLOCK, checking, facing.getOpposite());
            if (storage != null) {
                this.getEnergyStorage().extractEnergy(storage.receiveEnergy(this.getEnergyStorage().extractEnergy(this.getExtractingEnergy(), true), false), false);
            }
        }
    }

    @Nonnull
    protected EnergyStorageComponent<T> createEnergyStorage() {
        return new EnergyStorageComponent<>(this.getEnergyCapacity(), 0, this.getExtractingEnergy(), 10, 20);
    }
}
