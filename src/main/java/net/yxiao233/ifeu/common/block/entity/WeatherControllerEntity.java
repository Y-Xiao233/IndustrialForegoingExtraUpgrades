package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.ProgressBarScreenAddon;
import com.hrznstudio.titanium.component.button.ArrowButtonComponent;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import com.hrznstudio.titanium.util.FacingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.yxiao233.ifeu.common.components.TextureGuiComponent;
import net.yxiao233.ifeu.common.config.machine.RuleControllerConfig;
import net.yxiao233.ifeu.common.config.machine.WeatherControllerConfig;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.networking.packet.BooleanSyncS2CPacket;
import net.yxiao233.ifeu.common.networking.packet.BooleanValueSyncS2C;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.utils.WeatherGetter;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class WeatherControllerEntity extends IndustrialProcessingTile<WeatherControllerEntity> implements BooleanValueSyncS2C {
    private WeatherGetter[] weathers = {WeatherGetter.WEATHER_CLEAR,WeatherGetter.WEATHER_RAIN,WeatherGetter.WEATHER_THUNDER};
    private int powerPerTick;
    @Save
    private SidedInventoryComponent<WeatherControllerEntity> input;
    @Save
    private ProgressBarComponent<WeatherControllerEntity> bar;
    @Save
    private int weather;
    private boolean finish = false;
    public WeatherControllerEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.WEATHER_CONTROLLER, 66, 40, blockPos, blockState);

        this.addInventory(this.input = (SidedInventoryComponent<WeatherControllerEntity>)(new SidedInventoryComponent<WeatherControllerEntity>("dragon_star_input", 33, 40, 1, 0)).setColor(DyeColor.PURPLE).setInputFilter((stack, integer) -> {
            return stack.is(ModContents.DRAGON_STAR.get());
        }).setOutputFilter((stack, integer) -> {
            return false;
        }).setComponentHarness(this));

        this.addProgressBar(this.bar = (new ProgressBarComponent<WeatherControllerEntity>(53, 20, 10) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    return new ProgressBarScreenAddon<WeatherControllerEntity>(WeatherControllerEntity.this.bar.getPosX(), WeatherControllerEntity.this.bar.getPosY(), this) {
                        public List<Component> getTooltipLines() {
                            Component[] var10000 = new Component[1];
                            ChatFormatting var10003 = ChatFormatting.GOLD;
                            var10000[0] = Component.literal("" + var10003 + "Amount: " + ChatFormatting.WHITE + NumberFormat.getNumberInstance(Locale.ROOT).format((long)WeatherControllerEntity.this.bar.getProgress()) + ChatFormatting.GOLD + "/" + ChatFormatting.WHITE + NumberFormat.getNumberInstance(Locale.ROOT).format((long)WeatherControllerEntity.this.bar.getMaxProgress()));
                            return Arrays.asList(var10000);
                        }
                    };
                });
            }
        }).setCanIncrease((tileEntity) -> {
            return false;
        }).setCanReset((tileEntity) -> {
            return false;
        }).setColor(DyeColor.PURPLE));


        this.addButton((new ArrowButtonComponent(119, 40, 14, 14, FacingUtil.Sideness.LEFT)).setId(1).setPredicate((playerEntity, compoundNBT) -> {
            --this.weather;
            this.finish = false;
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),false)));
            if (this.weather < 0) {
                this.weather = weathers.length - 1;
            }

            this.markForUpdate();
        }));
        this.addButton((new ArrowButtonComponent(155, 40, 14, 14, FacingUtil.Sideness.RIGHT)).setId(2).setPredicate((playerEntity, compoundNBT) -> {
            ++this.weather;
            this.finish = false;
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),false)));
            if (this.weather > weathers.length - 1) {
                this.weather = 0;
            }

            this.markForUpdate();
        }));

        this.powerPerTick = RuleControllerConfig.powerPerTick;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initClient() {
        super.initClient();

        this.addGuiAddonFactory(() -> {
            return new TextureGuiComponent(137,41) {
                @Override
                public AllGuiTextures getTexture() {
                    return weathers[weather].getTexture();
                }

                @Override
                public Component getComponent() {
                    return Component.translatable("weather.ifeu."+weathers[weather].getName());
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),false)));
            return new TextureGuiComponent(98,41) {
                @Override
                public AllGuiTextures getTexture() {
                    if(finish){
                        return AllGuiTextures.TRUE;
                    }
                    return AllGuiTextures.EMPTY;
                }

                @Override
                public Component getComponent() {
                    if(finish){
                        return Component.translatable("weather.ifeu.finish");
                    }
                    return Component.empty();
                }
            };
        });
    }

    @Override
    public boolean canIncrease() {
        this.increaseBar(this.input.getStackInSlot(0),this.bar);
        return this.bar.getProgress() >= 1;
    }

    private void increaseBar(ItemStack stack, ProgressBarComponent bar) {
        if (bar.getProgress() + 1 <= bar.getMaxProgress() && !stack.isEmpty()) {
            stack.shrink(1);
            bar.setProgress(bar.getProgress() + 1);
            this.markForUpdate();
        }

    }

    @Override
    public Runnable onFinish() {
        return () -> {
            this.bar.setProgress(this.bar.getProgress() - 1);
            weathers[weather].setWeather((ServerLevel) level);
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),true)));
            this.finish = true;
            this.markForUpdate();
        };
    }

    @NotNull
    @Override
    protected EnergyStorageComponent<WeatherControllerEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(WeatherControllerConfig.maxStoredPower, 10, 20);
    }

    @Override
    protected int getTickPower() {
        return this.powerPerTick;
    }

    @NotNull
    @Override
    public WeatherControllerEntity getSelf() {
        return this;
    }

    public void loadSettings(Player player, CompoundTag tag) {
        if (tag.contains("weather")) {
            this.weather = tag.getInt("weather");
        }

        super.loadSettings(player, tag);
    }

    public void saveSettings(Player player, CompoundTag tag) {
        tag.putInt("weather", this.weather);
        super.saveSettings(player, tag);
    }


    @Override
    public void setValue(boolean value) {
        this.finish = value;
    }

    @Override
    public boolean getValues() {
        return finish;
    }
}
