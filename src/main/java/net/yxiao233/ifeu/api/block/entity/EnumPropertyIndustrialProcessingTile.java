package net.yxiao233.ifeu.api.block.entity;

import com.buuz135.industrial.item.addon.ProcessingAddonItem;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.augment.AugmentTypes;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.ProgressBarScreenAddon;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import com.hrznstudio.titanium.item.AugmentWrapper;
import com.hrznstudio.titanium.module.BlockWithTile;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.yxiao233.ifeu.api.block.IEnumProperty;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EnumPropertyIndustrialProcessingTile<T extends EnumPropertyIndustrialProcessingTile<T, E>, E extends Enum<E> & IEnumProperty<E>> extends EnumPropertyIndustrialMachineTile<T, E> {
    @Save
    private final ProgressBarComponent<T> progressBar;

    public EnumPropertyIndustrialProcessingTile(BlockWithTile blockWithTile, final int x, final int y, BlockPos pos, BlockState state) {
        super(blockWithTile, pos, state);
        this.addProgressBar(this.progressBar = (new ProgressBarComponent<T>(x, y, this.getMaxProgress()) {
            @OnlyIn(Dist.CLIENT)
            public @NotNull List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    return new ProgressBarScreenAddon<>(x, y, EnumPropertyIndustrialProcessingTile.this.progressBar) {
                        public List<Component> getTooltipLines() {
                            List<Component> tooltip = new ArrayList<>();
                            ChatFormatting chatFormatting = ChatFormatting.GOLD;
                            tooltip.add(Component.literal(chatFormatting + Component.translatable("tooltip.titanium.progressbar.progress").getString() + ChatFormatting.WHITE + (new DecimalFormat()).format((long)EnumPropertyIndustrialProcessingTile.this.progressBar.getProgress()) + ChatFormatting.GOLD + "/" + ChatFormatting.WHITE + (new DecimalFormat()).format((long)EnumPropertyIndustrialProcessingTile.this.progressBar.getMaxProgress())));
                            int progress = EnumPropertyIndustrialProcessingTile.this.progressBar.getMaxProgress() - EnumPropertyIndustrialProcessingTile.this.progressBar.getProgress();
                            if (!EnumPropertyIndustrialProcessingTile.this.progressBar.getIncreaseType()) {
                                progress = EnumPropertyIndustrialProcessingTile.this.progressBar.getMaxProgress() - progress;
                            }

                            tooltip.add(Component.literal(chatFormatting + "ETA: " + ChatFormatting.WHITE + (new DecimalFormat()).format(Math.ceil((double)(progress * EnumPropertyIndustrialProcessingTile.this.progressBar.getTickingTime()) / 20.0 / (double)EnumPropertyIndustrialProcessingTile.this.progressBar.getProgressIncrease())) + ChatFormatting.DARK_AQUA + "s"));
                            tooltip.add(Component.literal(chatFormatting + Component.translatable("tooltip.industrialforegoing.usage").getString() + ChatFormatting.WHITE + EnumPropertyIndustrialProcessingTile.this.getTickPower() + ChatFormatting.DARK_AQUA + " FE" + ChatFormatting.GOLD + "/" + ChatFormatting.WHITE + ChatFormatting.DARK_AQUA + "t"));
                            return tooltip;
                        }
                    };
                });
            }
        }).setComponentHarness(this.getSelf()).setBarDirection(this.getBarDirection()).setCanReset((tileEntity) -> {
            return true;
        }).setOnStart(() -> {
            int maxProgress = (int)Math.floor((double)((float)this.getMaxProgress() * (this.hasAugmentInstalled(AugmentTypes.EFFICIENCY) ? AugmentWrapper.getType(this.getInstalledAugments(AugmentTypes.EFFICIENCY).get(0), AugmentTypes.EFFICIENCY) : 1.0F)));
            this.getProgressBar().setMaxProgress(maxProgress);
        }).setCanIncrease((tileEntity) -> {
            return this.getEnergy().getEnergyStored() >= this.getTickPower() && this.canIncrease() && this.getRedstoneManager().getAction().canRun(tileEntity.getEnvironmentValue(false, null)) && this.getRedstoneManager().shouldWork();
        }).setOnTickWork(() -> {
            this.getEnergy().extractEnergy(this.getTickPower(), false);
            this.getProgressBar().setProgressIncrease(this.hasAugmentInstalled(AugmentTypes.SPEED) ? (int) AugmentWrapper.getType(this.getInstalledAugments(AugmentTypes.SPEED).get(0), AugmentTypes.SPEED) : 1);
        }).setOnFinishWork(() -> {
            int operations = (int)(this.hasAugmentInstalled(ProcessingAddonItem.PROCESSING) ? AugmentWrapper.getType(this.getInstalledAugments(ProcessingAddonItem.PROCESSING).get(0), ProcessingAddonItem.PROCESSING) : 1.0F);

            for(int i = 0; i < operations; ++i) {
                if (this.canIncrease()) {
                    this.onFinish().run();
                }
            }

            this.getRedstoneManager().finish();
        }));
    }


    @SuppressWarnings("unchecked")
    public ProgressBarComponent<T> getProgressBar() {
        BlockEntity entity = getMainPropertyEntity(getBlockState());
        if(entity instanceof EnumPropertyIndustrialProcessingTile<?,?> tile){
            return (ProgressBarComponent<T>) tile.progressBar;
        }
        return null;
    }
    public int getMaxProgress() {
        return 100;
    }
    public abstract boolean canIncrease();

    public abstract Runnable onFinish();

    protected abstract int getTickPower();
    public ProgressBarComponent.BarDirection getBarDirection() {
        return ProgressBarComponent.BarDirection.ARROW_RIGHT;
    }
}
