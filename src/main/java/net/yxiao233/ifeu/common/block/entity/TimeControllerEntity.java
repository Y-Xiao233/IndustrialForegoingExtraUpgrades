package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IAsset;
import com.hrznstudio.titanium.api.client.IAssetType;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.ProgressBarScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonInfo;
import com.hrznstudio.titanium.component.button.ArrowButtonComponent;
import com.hrznstudio.titanium.component.button.ButtonComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import com.hrznstudio.titanium.util.FacingUtil;
import com.hrznstudio.titanium.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.yxiao233.ifeu.common.components.CustomTooltipComponent;
import net.yxiao233.ifeu.common.components.TextGuiComponent;
import net.yxiao233.ifeu.common.components.TextureGuiComponent;
import net.yxiao233.ifeu.common.config.machine.TimeControllerConfig;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.networking.packet.BooleanSyncS2CPacket;
import net.yxiao233.ifeu.common.networking.packet.BooleanValueSyncS2C;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.utils.KeyDownUtil;
import net.yxiao233.ifeu.common.utils.TimeGetter;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class TimeControllerEntity extends IndustrialProcessingTile<TimeControllerEntity> implements BooleanValueSyncS2C {
    public TimeGetter[] times = {TimeGetter.DAY,TimeGetter.NOON,TimeGetter.NIGHT,TimeGetter.MIDNIGHT,TimeGetter.T,TimeGetter.S,TimeGetter.D};
    private int powerPerTick;
    @Save
    private SidedInventoryComponent<TimeControllerEntity> input;
    @Save
    private ProgressBarComponent<TimeControllerEntity> bar;
    @Save
    public long time;
    @Save
    public int choose;
    @Save
    public boolean hasButtonTip = false;
    private ButtonComponent buttonComponent;
    private boolean finish = false;
    public TimeControllerEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.TIME_CONTROLLER, 66, 40, blockPos, blockState);

        this.addInventory(this.input = (SidedInventoryComponent<TimeControllerEntity>)(new SidedInventoryComponent<TimeControllerEntity>("dragon_star_input", 33, 40, 1, 0)).setColor(DyeColor.PURPLE).setInputFilter((stack, integer) -> {
            return stack.is(ModContents.DRAGON_STAR.get());
        }).setOutputFilter((stack, integer) -> {
            return false;
        }).setComponentHarness(this));

        this.addProgressBar(this.bar = (new ProgressBarComponent<TimeControllerEntity>(53, 20, 10) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    return new ProgressBarScreenAddon<TimeControllerEntity>(TimeControllerEntity.this.bar.getPosX(), TimeControllerEntity.this.bar.getPosY(), this) {
                        public List<Component> getTooltipLines() {
                            Component[] var10000 = new Component[1];
                            ChatFormatting var10003 = ChatFormatting.GOLD;
                            var10000[0] = Component.literal("" + var10003 + "Amount: " + ChatFormatting.WHITE + NumberFormat.getNumberInstance(Locale.ROOT).format((long)TimeControllerEntity.this.bar.getProgress()) + ChatFormatting.GOLD + "/" + ChatFormatting.WHITE + NumberFormat.getNumberInstance(Locale.ROOT).format((long)TimeControllerEntity.this.bar.getMaxProgress()));
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


        this.addButton((new ArrowButtonComponent(155, 23, 14, 14, FacingUtil.Sideness.TOP)).setId(1).setPredicate((playerEntity, compoundNBT) -> {
            --this.choose;
            this.finish = false;
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),false)));
            if (this.choose < 0) {
                this.choose = times.length - 1;
            }
            if(times[choose].hasTexture()){
                this.time = times[choose].getTime();
            }
            this.markForUpdate();
        }));

        this.addButton((new ArrowButtonComponent(155, 60, 14, 14, FacingUtil.Sideness.BOTTOM)).setId(2).setPredicate((playerEntity, compoundNBT) -> {
            ++this.choose;
            this.finish = false;
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),false)));
            if (this.choose > times.length - 1) {
                this.choose = 0;
            }
            if(times[choose].hasTexture()){
                this.time = times[choose].getTime();
            }
            this.markForUpdate();
        }));

        this.addButton((new ArrowButtonComponent(79, 20, 14, 14, FacingUtil.Sideness.LEFT)).setId(3).setPredicate((playerEntity, compoundNBT) -> {
            if(times[choose].hasTexture()){
                this.time = times[choose].getTime();
            }else{
                if(KeyDownUtil.isShiftKeyDown() && !KeyDownUtil.isCtrlKeyDown()){
                    this.time = time - 10;
                }else if(!KeyDownUtil.isShiftKeyDown() && KeyDownUtil.isCtrlKeyDown()){
                    this.time = time - 100;
                }else if(KeyDownUtil.isShiftKeyDown() && KeyDownUtil.isCtrlKeyDown()){
                    this.time = time - 1000;
                }else if(!KeyDownUtil.isShiftKeyDown() && !KeyDownUtil.isCtrlKeyDown()){
                    --this.time;
                }
            }
            this.finish = false;
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),false)));
            this.markForUpdate();
        }));

        this.addButton((new ArrowButtonComponent(135, 20, 14, 14, FacingUtil.Sideness.RIGHT)).setId(4).setPredicate((playerEntity, compoundNBT) -> {
            if(times[choose].hasTexture()){
                this.time = times[choose].getTime();
            }else{
                if(KeyDownUtil.isShiftKeyDown() && !KeyDownUtil.isCtrlKeyDown()){
                    this.time = time + 10;
                }else if(!KeyDownUtil.isShiftKeyDown() && KeyDownUtil.isCtrlKeyDown()){
                    this.time = time + 100;
                }else if(KeyDownUtil.isShiftKeyDown() && KeyDownUtil.isCtrlKeyDown()){
                    this.time = time + 1000;
                }else if(!KeyDownUtil.isShiftKeyDown() && !KeyDownUtil.isCtrlKeyDown()){
                    ++this.time;
                }
            }
            this.finish = false;
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),false)));
            this.markForUpdate();
        }));

        this.addButton(this.buttonComponent = (new ButtonComponent(136, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    StateButtonInfo[] buttonInfo = new StateButtonInfo[2];
                    IAssetType<IAsset> asset = AssetTypes.BUTTON_SIDENESS_ENABLED;
                    String[] tip = new String[2];
                    ChatFormatting chatFormatting = ChatFormatting.GOLD;
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.time_controller.has_tip", new Object[0]);
                    tip[1] = "tooltip.ifeu.time_controller.has_tip_1";
                    buttonInfo[0] = new StateButtonInfo(0, asset, tip);
                    asset = AssetTypes.BUTTON_SIDENESS_DISABLED;
                    tip = new String[2];
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.time_controller.without_tip", new Object[0]);
                    tip[1] = "tooltip.ifeu.time_controller.without_tip_1";
                    buttonInfo[1] = new StateButtonInfo(1, asset, tip);
                    return new StateButtonAddon(this, buttonInfo) {
                        public int getState() {
                            return TimeControllerEntity.this.hasButtonTip ? 0 : 1;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.hasButtonTip = !this.hasButtonTip;
            this.markForUpdate();
        }).setId(5));

        this.powerPerTick = TimeControllerConfig.powerPerTick;

    }

    @Override
    public void clientTick(Level level, BlockPos pos, BlockState state, TimeControllerEntity blockEntity) {
        if(choose > times.length - 1){
            choose = 0;
        }else if(choose < 0){
            choose = times.length - 1;
        }

        if(this.time < 0){
            this.time = 0;
        }
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, TimeControllerEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        if(this.time < 0){
            this.time = 0;
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initClient() {
        super.initClient();

        this.addGuiAddonFactory(() ->{
            return new TextureGuiComponent(155,41) {
                @Override
                public AllGuiTextures getTexture() {
                    return times[choose].getTexture();
                }

                @Override
                public Component getComponent() {
                    if(times[choose].hasTexture()){
                        return Component.translatable("time.ifeu."+times[choose].getName());
                    }
                    return Component.empty();
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            return new TextGuiComponent(158,42) {
                @Override
                public String getText() {
                    if(!times[choose].hasTexture()){
                        return times[choose].getName();
                    }
                    return "";
                }
            };
        });


        this.addGuiAddonFactory(() ->{
            return new TextGuiComponent(95,22) {
                @Override
                public String getText() {
                    return String.valueOf(time);
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
                        return Component.translatable("time.ifeu.finish");
                    }
                    return Component.empty();
                }
            };
        });


        this.addGuiAddonFactory(() ->{
            return new CustomTooltipComponent(79,20) {

                @Override
                public int[] width$height() {
                    return new int[]{14,14};
                }

                @Override
                public Component[] getComponents() {
                    return defaultKeyDownTips("reduce");
                }

                @Override
                public boolean hasTooltip() {
                    return hasButtonTip;
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            return new CustomTooltipComponent(135,20) {

                @Override
                public int[] width$height() {
                    return new int[]{14,14};
                }

                @Override
                public Component[] getComponents() {
                    return defaultKeyDownTips("add");
                }

                @Override
                public boolean hasTooltip() {
                    return hasButtonTip;
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
            times[choose].modifyTime(time);
            times[choose].setTime((ServerLevel) level);
            this.finish = true;
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos().getX(),getBlockPos().getY(),getBlockPos().getZ(),true)));
            this.markForUpdate();
        };
    }

    @Override
    protected int getTickPower() {
        return this.powerPerTick;
    }

    @NotNull
    @Override
    public TimeControllerEntity getSelf() {
        return this;
    }

    public void loadSettings(Player player, CompoundTag tag) {
        if (tag.contains("time")) {
            this.time = tag.getLong("time");
        }
        if (tag.contains("choose")) {
            this.choose = tag.getInt("choose");
        }
        if (tag.contains("hideButtonTip")) {
            this.hasButtonTip = tag.getBoolean("hideButtonTip");
        }
        super.loadSettings(player, tag);
    }

    public void saveSettings(Player player, CompoundTag tag) {
        tag.putLong("time", this.time);
        tag.putInt("choose",this.choose);
        tag.putBoolean("hideButtonTip",this.hasButtonTip);
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