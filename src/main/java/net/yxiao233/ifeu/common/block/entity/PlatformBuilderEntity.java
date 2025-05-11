package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.IndustrialForegoing;
import com.buuz135.industrial.block.tile.RangeManager;
import com.buuz135.industrial.utils.BlockUtils;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IAsset;
import com.hrznstudio.titanium.api.client.IAssetType;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonInfo;
import com.hrznstudio.titanium.component.button.ArrowButtonComponent;
import com.hrznstudio.titanium.component.button.ButtonComponent;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.util.FacingUtil;
import com.hrznstudio.titanium.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.FakePlayer;
import net.yxiao233.ifeu.api.block.entity.IFEUAreaWorkingTile;
import net.yxiao233.ifeu.api.components.ComponentGuiComponent;
import net.yxiao233.ifeu.api.components.CustomTooltipComponent;
import net.yxiao233.ifeu.api.components.TextGuiComponent;
import net.yxiao233.ifeu.api.networking.BooleanValueSyncS2C;
import net.yxiao233.ifeu.common.config.machine.PlatformBuilderConfig;
import net.yxiao233.ifeu.common.networking.ModNetWorking;
import net.yxiao233.ifeu.common.networking.packet.BooleanSyncS2CPacket;
import net.yxiao233.ifeu.common.networking.packet.PlatformBuilderEntityKeyDownSyncC2SPacket;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.utils.KeyDownUtil;
import net.yxiao233.ifeu.common.utils.PlatformBuilderUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class PlatformBuilderEntity extends IFEUAreaWorkingTile<PlatformBuilderEntity> implements BooleanValueSyncS2C {
    @Save
    public boolean isNorth = false;
    @Save boolean isWest = false;
    @Save
    public InventoryComponent<PlatformBuilderEntity> land;
    @Save
    public InventoryComponent<PlatformBuilderEntity> frame;
    @Save
    private int landRange;
    @Save
    private int frameBounds;
    @Save
    private int landIndex;
    @Save
    private int frameIndex;
    @Save
    public boolean hasButtonTip = true;
    @Save
    public int mode = 0;
    public static boolean isShiftDown;
    private boolean finish = false;
    private static final int maxLandRange = PlatformBuilderConfig.maxLandRange;
    private static final int maxFrameBounds = PlatformBuilderConfig.maxFrameBounds;
    public PlatformBuilderEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.PLATFORM_BUILDER, RangeManager.RangeType.BEHIND, false, 0, blockPos, blockState);

        this.addInventory(this.frame = (new SidedInventoryComponent<PlatformBuilderEntity>("frame_input", 46, 22, 1, 0)).setColor(DyeColor.YELLOW).setInputFilter((stack, integer) -> {
            return stack.getItem() instanceof BlockItem;
        }).setOutputFilter((stack, integer) -> {
            return false;
        }).setSlotLimit(256).setOnSlotChanged(((stack, integer) -> resetIndex())).setComponentHarness(this));

        this.addInventory(this.land = (new SidedInventoryComponent<PlatformBuilderEntity>("land_input", 46, 57, 1, 1)).setColor(DyeColor.GREEN).setInputFilter((stack, integer) -> {
            return stack.getItem() instanceof BlockItem;
        }).setOutputFilter((stack, integer) -> {
            return false;
        }).setSlotLimit(256).setOnSlotChanged(((stack, integer) -> resetIndex())).setComponentHarness(this));


        this.addButton((new ArrowButtonComponent(70, 20, 14, 14, FacingUtil.Sideness.LEFT)).setId(2).setPredicate((playerEntity, compoundNBT) -> {
            isNorth = !isNorth;
            resetIndex();
            this.markForUpdate();
        }));

        this.addButton((new ArrowButtonComponent(115, 20, 14, 14, FacingUtil.Sideness.RIGHT)).setId(3).setPredicate((playerEntity, compoundNBT) -> {
            isNorth = !isNorth;
            resetIndex();
            this.markForUpdate();
        }));

        this.addButton((new ArrowButtonComponent(70, 40, 14, 14, FacingUtil.Sideness.LEFT)).setId(4).setPredicate((playerEntity, compoundNBT) -> {
            isWest = !isWest;
            resetIndex();
            this.markForUpdate();
        }));
        this.addButton((new ArrowButtonComponent(115, 40, 14, 14, FacingUtil.Sideness.RIGHT)).setId(5).setPredicate((playerEntity, compoundNBT) -> {
            isWest = !isWest;
            resetIndex();
            this.markForUpdate();
        }));


        this.addButton((new ArrowButtonComponent(155, 21, 14, 14, FacingUtil.Sideness.TOP)).setId(6).setPredicate((playerEntity, compoundNBT) -> {
            if(isShiftDown){
                this.landRange = landRange + 10;
            }else{
                ++this.landRange;
            }
            if(landRange > maxLandRange){
                landRange = 0;
            }
            resetIndex();
            this.finish = false;
            this.markForUpdate();
        }));

        this.addButton((new ArrowButtonComponent(155, 58, 14, 14, FacingUtil.Sideness.BOTTOM)).setId(7).setPredicate((playerEntity, compoundNBT) -> {
            if(isShiftDown){
                this.landRange = landRange - 10;
            }else{
                --this.landRange;
            }
            if(landRange < 0){
                landRange = maxLandRange;
            }
            resetIndex();
            this.finish = false;
            this.markForUpdate();
        }));

        this.addButton((new ArrowButtonComponent(135, 21, 14, 14, FacingUtil.Sideness.TOP)).setId(8).setPredicate((playerEntity, compoundNBT) -> {
            if(isShiftDown){
                this.frameBounds = frameBounds + 10;
            }else{
                ++this.frameBounds;
            }
            if(frameBounds > maxFrameBounds){
                frameBounds = 0;
            }
            resetIndex();
            this.finish = false;
            this.markForUpdate();
        }));

        this.addButton((new ArrowButtonComponent(135, 58, 14, 14, FacingUtil.Sideness.BOTTOM)).setId(9).setPredicate((playerEntity, compoundNBT) -> {
            if(isShiftDown){
                this.frameBounds = frameBounds - 10;
            }else{
                --this.frameBounds;
            }
            if(frameBounds < 0){
                frameBounds = maxFrameBounds;
            }
            resetIndex();
            this.finish = false;
            this.markForUpdate();
        }));

        this.addButton((new ButtonComponent(118, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public @NotNull List<IFactory<? extends IScreenAddon>> getScreenAddons() {
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
                            return PlatformBuilderEntity.this.hasButtonTip ? 0 : 1;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.hasButtonTip = !this.hasButtonTip;
            this.markForUpdate();
        }).setId(10));

        this.addButton((new ButtonComponent(100, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public @NotNull List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    StateButtonInfo[] buttonInfo = new StateButtonInfo[2];
                    IAssetType<IAsset> asset = AssetTypes.BUTTON_SIDENESS_DISABLED;
                    String[] tip = new String[2];
                    ChatFormatting chatFormatting = ChatFormatting.GOLD;
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.platform_builder.destroy", new Object[0]);
                    tip[1] = "tooltip.ifeu.platform_builder.destroy_1";
                    buttonInfo[0] = new StateButtonInfo(0, asset, tip);
                    asset = AssetTypes.BUTTON_SIDENESS_ENABLED;
                    tip = new String[2];
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.platform_builder.skip", new Object[0]);
                    tip[1] = "tooltip.ifeu.platform_builder.skip_1";
                    buttonInfo[1] = new StateButtonInfo(1, asset, tip);
                    return new StateButtonAddon(this, buttonInfo) {
                        public int getState() {
                            return PlatformBuilderEntity.this.mode;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.mode = this.mode >= 1 ? 0: 1;
            this.markForUpdate();
        }).setId(11));


        this.addButton((new ButtonComponent(82, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public @NotNull List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    IAssetType<IAsset> asset = AssetTypes.BUTTON_SIDENESS_PUSH;
                    String[] tip = new String[1];
                    ChatFormatting chatFormatting = ChatFormatting.GOLD;
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.platform_builder.reset",new Object[0]);
                    StateButtonInfo info = new StateButtonInfo(0, asset, tip);
                    return new StateButtonAddon(this, info) {
                        public int getState() {
                            return 0;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            resetIndex();
            this.markForUpdate();
        }).setId(12));
    }


    @Override
    public void clientTick(Level level, BlockPos pos, BlockState state, PlatformBuilderEntity blockEntity) {
        if(this.landRange < 0){
            this.landRange = maxLandRange;
        }else if(this.landRange > maxLandRange){
            this.landRange = 0;
        }

        if(this.frameBounds < 0){
            this.frameBounds = maxFrameBounds;
        }else if(this.frameBounds > maxFrameBounds){
            this.frameBounds = 0;
        }

        if(level.isClientSide()){
            isShiftDown = KeyDownUtil.isShiftKeyDown();
            ModNetWorking.sendToServer(new PlatformBuilderEntityKeyDownSyncC2SPacket(isShiftDown));
        }
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, PlatformBuilderEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        if(!level.isClientSide()){
            ModNetWorking.sendToClient(new BooleanSyncS2CPacket(pos,finish));
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initClient() {
        super.initClient();

        this.addGuiAddonFactory(() -> {
            return new ComponentGuiComponent(99,22) {
                @Override
                public Component getText() {
                    return isNorth ? Component.translatable("direction.ifeu.north") : Component.translatable("direction.ifeu.south");
                }
            };
        });

        this.addGuiAddonFactory(() -> {
            return new ComponentGuiComponent(99,43) {
                @Override
                public Component getText() {
                    return isWest ? Component.translatable("direction.ifeu.west") : Component.translatable("direction.ifeu.east");
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            return new TextGuiComponent(139,43) {
                @Override
                public String getText() {
                    return String.valueOf(frameBounds);
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            return new TextGuiComponent(159,43) {
                @Override
                public String getText() {
                    return String.valueOf(landRange);
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            return new CustomTooltipComponent(135,19) {

                @Override
                public int[] width$height() {
                    return new int[]{14,14};
                }

                @Override
                public Component[] getComponents() {
                    return platformBuilder("add","frame_bounds");
                }

                @Override
                public boolean hasTooltip() {
                    return hasButtonTip;
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            return new CustomTooltipComponent(135,58) {

                @Override
                public int[] width$height() {
                    return new int[]{14,14};
                }

                @Override
                public Component[] getComponents() {
                    return platformBuilder("reduce","frame_bounds");
                }

                @Override
                public boolean hasTooltip() {
                    return hasButtonTip;
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            return new CustomTooltipComponent(155,19) {

                @Override
                public int[] width$height() {
                    return new int[]{14,14};
                }

                @Override
                public Component[] getComponents() {
                    return platformBuilder("add","land_range");
                }

                @Override
                public boolean hasTooltip() {
                    return hasButtonTip;
                }
            };
        });

        this.addGuiAddonFactory(() ->{
            return new CustomTooltipComponent(155,58) {

                @Override
                public int[] width$height() {
                    return new int[]{14,14};
                }

                @Override
                public Component[] getComponents() {
                    return platformBuilder("reduce","land_range");
                }

                @Override
                public boolean hasTooltip() {
                    return hasButtonTip;
                }
            };
        });
    }

    @Override
    public int getMaxProgress() {
        return PlatformBuilderConfig.maxProgress;
    }
    @Override
    public WorkAction work() {
        List<BlockPos> frames = PlatformBuilderUtil.getFrameBlockPosList(this);
        List<BlockPos> lands = PlatformBuilderUtil.getLandPosList(this);
        int needFrame = frames.size() - frameIndex;
        int needLand = lands.size() - landIndex;
        ItemStack landStack = land.getStackInSlot(0);
        ItemStack frameStack = frame.getStackInSlot(0);
        boolean land = landStack.getCount() > 0;
        boolean frame = frameStack.getCount() > 0;


        if(land){
            if(needLand > 0){
                if(mode == 0){
                    replace(lands.get(landIndex),getLandBlockState(),"land");
                }else{
                    skip(lands.get(landIndex),getLandBlockState(),"land");
                }
                return new WorkAction(1.0F,PlatformBuilderConfig.powerPerOperation);
            }else if(frame){
                if(needFrame > 0){
                    if(mode == 0){
                        replace(frames.get(frameIndex),getFrameBlockState(),"frame");
                    }else{
                        skip(frames.get(frameIndex),getFrameBlockState(),"frame");
                    }
                    return new WorkAction(1.0F,PlatformBuilderConfig.powerPerOperation);
                }
            }
        }else if(frame){
            if(needFrame > 0){
                if(mode == 0){
                    replace(frames.get(frameIndex),getFrameBlockState(),"frame");
                }else{
                    skip(frames.get(frameIndex),getFrameBlockState(),"frame");
                }
                return new WorkAction(1.0F,PlatformBuilderConfig.powerPerOperation);
            }
        }

        return new WorkAction(1.0F, 0);
    }

    private void replace(BlockPos pos, BlockState state, String type){
        FakePlayer fakePlayer = IndustrialForegoing.getFakePlayer(level,pos);
        fakePlayer.setGameMode(GameType.SURVIVAL);
        fakePlayer.setItemInHand(InteractionHand.MAIN_HAND,new ItemStack(Items.NETHERITE_PICKAXE));
        if(this.level.getBlockState(pos).isAir()){
            level.setBlockAndUpdate(pos,state);
            shrink(type);
            increaseIndex(type);
        }else{
            if(level.getBlockState(pos).is(state.getBlock())){
                increaseIndex(type);
            }else if(level.getBlockState(pos).getDestroySpeed(level,pos) >= 0.0F && level.getBlockState(pos).canHarvestBlock(level,pos,fakePlayer)){
                shrink(type);
                level.destroyBlock(pos,true);
                level.setBlockAndUpdate(pos,state);
                increaseIndex(type);
            }
        }
    }

    private void skip(BlockPos pos, BlockState state, String type){
        if(this.level.getBlockState(pos).isAir()){
            shrink(type);
            level.setBlockAndUpdate(pos,state);
            increaseIndex(type);
        }else{
            increaseIndex(type);
        }
    }
    private void shrink(String type){
        if (type.equals("frame")) {
            frame.getStackInSlot(0).shrink(1);
        }else{
            land.getStackInSlot(0).shrink(1);
        }
    }

    private void increaseIndex(String type){
        if (type.equals("frame")) {
            ++frameIndex;
        }else{
            ++landIndex;
        }
    }

    private void resetIndex(){
        frameIndex = 0;
        landIndex = 0;
    }

    @NotNull
    @Override
    public PlatformBuilderEntity getSelf() {
        return this;
    }

    @Override
    protected @NotNull EnergyStorageComponent<PlatformBuilderEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(PlatformBuilderConfig.maxStoredPower, 10, 20);
    }

    @Override
    public int getFrameBounds(){
        return frameBounds;
    }

    @Override
    public FrameDirection getFrameDirection(){
        return new FrameDirection(isNorth ? NorthSouth.NORTH : NorthSouth.SOUTH, isWest ? WestEast.WEST : WestEast.EAST);
    }

    @Override
    public int getLandRange(){
        return landRange;
    }

    @Override
    public int getYOffset() {
        return -1;
    }

    @Override
    public BlockState getFrameBlockState() {
        if(!frame.getStackInSlot(0).isEmpty()){
            return Block.byItem(frame.getStackInSlot(0).getItem()).defaultBlockState();
        }
        return Blocks.LIME_STAINED_GLASS.defaultBlockState();
    }

    @Override
    public BlockState getLandBlockState() {
        if(!land.getStackInSlot(0).isEmpty()){
            return Block.byItem(land.getStackInSlot(0).getItem()).defaultBlockState();
        }
        return Blocks.GLASS.defaultBlockState();
    }

    @Override
    public BlockPos getCenter() {
        return null;
    }

    @Override
    public Item getLandItem() {
        if(land.getStackInSlot(0).isEmpty()){
            return Items.AIR;
        }
        return land.getStackInSlot(0).getItem();
    }

    @Override
    public Item getFrameItem() {
        if(frame.getStackInSlot(0).isEmpty()){
            return Items.AIR;
        }
        return frame.getStackInSlot(0).getItem();
    }

    @Override
    public void setValue(boolean... value) {
        this.finish = value[0];
    }

    @Override
    public boolean[] getValues() {
        return new boolean[]{finish};
    }

    @Override
    public void saveSettings(Player player, CompoundTag tag) {
        if (tag.contains("is_north")) {
            this.isNorth = tag.getBoolean("is_north");
        }
        if (tag.contains("is_west")) {
            this.isWest = tag.getBoolean("is_west");
        }
        if (tag.contains("land_range")) {
            this.landRange = tag.getInt("land_range");
        }
        if (tag.contains("frame_bounds")) {
            this.frameBounds = tag.getInt("frame_bounds");
        }
        if (tag.contains("land_index")) {
            this.landIndex = tag.getInt("land_index");
        }
        if (tag.contains("frame_index")) {
            this.frameIndex = tag.getInt("frame_index");
        }
        if (tag.contains("mode")) {
            this.mode = tag.getInt("mode");
        }
        if (tag.contains("hideButtonTip")) {
            this.hasButtonTip = tag.getBoolean("hideButtonTip");
        }
        super.loadSettings(player, tag);
    }

    @Override
    public void loadSettings(Player player, CompoundTag tag) {
        tag.putBoolean("is_north", this.isNorth);
        tag.putBoolean("is_west",this.isWest);
        tag.putInt("land_range",this.landRange);
        tag.putInt("frame_bounds",this.frameBounds);
        tag.putInt("land_index",this.landIndex);
        tag.putInt("frame_index",this.frameIndex);
        tag.putInt("mode",this.mode);
        tag.putBoolean("hideButtonTip",this.hasButtonTip);
        super.saveSettings(player, tag);
    }
}