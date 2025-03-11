package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.buuz135.industrial.item.addon.RangeAddonItem;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IAsset;
import com.hrznstudio.titanium.api.client.IAssetType;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonInfo;
import com.hrznstudio.titanium.component.button.ButtonComponent;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.network.PacketDistributor;
import net.yxiao233.ifeu.api.components.TextGuiComponent;
import net.yxiao233.ifeu.api.networking.BlockPosSyncS2C;
import net.yxiao233.ifeu.api.networking.BooleanValueSyncS2C;
import net.yxiao233.ifeu.common.block.FluidTransferBlock;
import net.yxiao233.ifeu.common.config.machine.FluidTransferConfig;
import net.yxiao233.ifeu.common.item.ConnectToolItem;
import net.yxiao233.ifeu.common.networking.packet.BlockPosSyncS2CPacket;
import net.yxiao233.ifeu.common.networking.packet.BooleanSyncS2CPacket;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FluidTransferEntity extends IndustrialProcessingTile<FluidTransferEntity> implements BlockPosSyncS2C, BooleanValueSyncS2C {
    @Save
    public SidedFluidTankComponent<FluidTransferEntity> fluidStored;
    @Save
    public BlockPos connectBlockPos;
    @Save
    public boolean hasConnect;
    @Save
    public boolean mode;
    @Save
    public int maxConnectionDistance;
    @Save
    public boolean isFluidRender = true;
    public ButtonComponent modeComponent;
    public ButtonComponent buttonComponent2;
    public int maxTransfer;
    public FluidTransferEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.FLUID_TRANSFER,76,40, blockPos, blockState);

        this.addTank(this.fluidStored = (SidedFluidTankComponent<FluidTransferEntity>) new SidedFluidTankComponent<FluidTransferEntity>("fluid_stored", FluidTransferConfig.maxTankSize,52,19,0)
                .setColor(DyeColor.LIME)
                .setTankType(FluidTankComponent.Type.NORMAL)
                .setComponentHarness(this));


        this.addButton(this.buttonComponent2 = (new ButtonComponent(136, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    StateButtonInfo[] buttonInfo = new StateButtonInfo[2];
                    IAssetType<IAsset> asset = AssetTypes.BUTTON_SIDENESS_ENABLED;
                    String[] tip = new String[2];
                    ChatFormatting chatFormatting = ChatFormatting.GOLD;
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.fluid_crafting_table.render_fluid", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.render_fluid_1";
                    buttonInfo[0] = new StateButtonInfo(0, asset, tip);
                    asset = AssetTypes.BUTTON_SIDENESS_DISABLED;
                    tip = new String[2];
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.fluid_crafting_table.not_render_fluid", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.not_render_fluid_1";
                    buttonInfo[1] = new StateButtonInfo(1, asset, tip);
                    return new StateButtonAddon(this, buttonInfo) {
                        public int getState() {
                            return FluidTransferEntity.this.isFluidRender ? 0 : 1;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.isFluidRender = !this.isFluidRender;
            this.markForUpdate();
        }).setId(2));

        this.addButton(this.modeComponent = (new ButtonComponent(106, 40, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    StateButtonInfo[] buttonInfo = new StateButtonInfo[2];
                    IAssetType<IAsset> asset = AssetTypes.BUTTON_SIDENESS_PULL;
                    String[] tip = new String[2];
                    ChatFormatting chatFormatting = ChatFormatting.GOLD;
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.fluid_transfer.input", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_transfer.input_1";
                    buttonInfo[0] = new StateButtonInfo(0, asset, tip);
                    asset = AssetTypes.BUTTON_SIDENESS_PUSH;
                    tip = new String[2];
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.fluid_transfer.output", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_transfer.output_1";
                    buttonInfo[1] = new StateButtonInfo(1, asset, tip);
                    return new StateButtonAddon(this, buttonInfo) {
                        public int getState() {
                            return FluidTransferEntity.this.mode ? 0 : 1;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.mode = !this.mode;
            this.markForUpdate();
        }).setId(6));


        this.maxTransfer = FluidTransferConfig.baseMaxTransfer;
        this.maxConnectionDistance = FluidTransferConfig.defaultMaxConnectDistance;
    }


    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, FluidTransferEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        //连接移除逻辑
        if(connectBlockPos != null){
            if(level.getBlockEntity(connectBlockPos) instanceof FluidTransferEntity entity){
                if(entity.hasConnect && this.hasConnect){
                    if(!entity.connectBlockPos.equals(getBlockPos())){
                        this.connectBlockPos = null;
                        this.hasConnect = false;
                    }
                }
            }else{
                this.connectBlockPos = null;
                this.hasConnect = false;
            }
        }

        //连接范围逻辑
        getMaxConnectDistance();
        if(this.connectBlockPos != null){
            BlockEntity entity = level.getBlockEntity(this.connectBlockPos);
            if(entity instanceof FluidTransferEntity fluidTransferEntity){
                if(ConnectToolItem.getDistance(getBlockPos(),this.connectBlockPos) > ConnectToolItem.getCanConnectDistance(this,fluidTransferEntity)){
                    this.connectBlockPos = null;
                    this.hasConnect = false;
                }
            }
        }
        //单次传输最大值逻辑


        //发送至客户端
        PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(getBlockPos(),List.of(hasConnect))));
        if(this.connectBlockPos != null){
            PacketDistributor.sendToAllPlayers(new BlockPosSyncS2CPacket(getBlockPos(),this.connectBlockPos));
        }
    }

    @Override
    public void openGui(Player player) {
        if(!(player.getMainHandItem().getItem() instanceof ConnectToolItem)){
            super.openGui(player);
        }
    }

    public void getMaxConnectDistance(){
        SidedInventoryComponent<FluidTransferEntity> augments = this.getAugmentInventory();
        int dis = FluidTransferConfig.defaultMaxConnectDistance;
        for (int i = 0; i < augments.getSlots(); i++) {
            if(augments.getStackInSlot(i).getItem() instanceof RangeAddonItem rangeAddonItem){
                String raw = rangeAddonItem.getDescriptionId();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(raw);
                int tier = 0;
                if(matcher.find()){
                    tier = Integer.parseInt(matcher.group());
                }

                dis += tier;
                break;
            }
        }
        this.maxConnectionDistance = dis;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initClient() {
        super.initClient();



        this.addGuiAddonFactory(() ->{
            return new TextGuiComponent(40,87) {
                @Override
                public String getText() {
                    if(!hasConnect){
                        return String.valueOf("Pos: null");
                    }
                    if(connectBlockPos != null){
                        int x = connectBlockPos.getX();
                        int y = connectBlockPos.getY();
                        int z = connectBlockPos.getZ();
                        return String.valueOf("Pos: "+x+", "+y+", "+z);
                    }else{
                        return String.valueOf("Pos: null");
                    }
                }
            };
        });
    }

    @NotNull
    @Override
    protected EnergyStorageComponent<FluidTransferEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(0,0,0,-1000,-1000);
    }

    @Override
    public boolean canIncrease() {
        if(hasConnect){
            assert level != null;
            if(connectBlockPos == null){
                return false;
            }
            if(level.getBlockEntity(connectBlockPos) instanceof FluidTransferEntity connect){
                //判断输入输出
                FluidTransferEntity self = this;

                //若模式相同则返回
                //mode为true则为输入,mode为false则为输出
                if(self.mode == connect.mode){
                    return false;
                }


                FluidTransferEntity input;
                FluidTransferEntity output;
                if(self.mode){
                    input = self;
                    output = connect;
                }else{
                    input = connect;
                    output = self;
                }
                //若输入为空,则返回
                if(input.fluidStored.isEmpty()){
                    return false;
                }
                //如输出已满,则返回
                if(output.fluidStored.getFluidAmount() >= output.fluidStored.getCapacity()){
                    return false;
                }
                //判断输出的流体槽位是否为空
                boolean isEmpty = output.fluidStored.isEmpty();
                return isEmpty || self.fluidStored.getFluid().is(connect.fluidStored.getFluid().getFluid());
            }
        }
        return false;
    }

    @Override
    public Runnable onFinish() {
        return () ->{
            //判断是否已经连接
            if(this.hasConnect && this.connectBlockPos != null){
                //level为null直接返回
                assert level != null;
                //判断与其连接的方块是否为传输器
                if(level.getBlockState(connectBlockPos).getBlock() instanceof FluidTransferBlock){
                    //获取本身以及与其连接的传输器
                    FluidTransferEntity self = this;
                    FluidTransferEntity connect = (FluidTransferEntity) level.getBlockEntity(connectBlockPos);
                    //若连接为空则返回
                    if(connect == null){
                        return;
                    }
                    //判断输入输出
                    FluidTransferEntity input;
                    FluidTransferEntity output;
                    if(self.mode){
                        input = self;
                        output = connect;
                    }else{
                        input = connect;
                        output = self;
                    }

                    int amount = Math.min(input.fluidStored.getFluidAmount(),maxTransfer);
                    FluidStack stack = new FluidStack(input.fluidStored.getFluid().getFluid(), amount);
                    input.fluidStored.drainForced(stack, IFluidHandler.FluidAction.EXECUTE);
                    output.fluidStored.fill(stack, IFluidHandler.FluidAction.EXECUTE);
                }
            }
        };
    }

    @Override
    protected int getTickPower() {
        return 0;
    }

    @NotNull
    @Override
    public FluidTransferEntity getSelf() {
        return this;
    }

    public void loadSettings(Player player, CompoundTag tag) {
        if (tag.contains("connect_block_pos")) {
            int[] temp = tag.getIntArray("connect_block_pos");
            this.connectBlockPos = new BlockPos(temp[0],temp[1],temp[2]);
        }
        if (tag.contains("has_connect")) {
            this.hasConnect = tag.getBoolean("has_connect");
        }
        if(tag.contains("mode")){
            this.mode = tag.getBoolean("mode");
        }
        if(tag.contains("is_fluid_render")){
            this.isFluidRender = tag.getBoolean("is_fluid_render");
        }
        super.loadSettings(player, tag);
    }

    public void saveSettings(Player player, CompoundTag tag) {
        tag.putIntArray("connect_block_pos", new int[]{this.connectBlockPos.getX(),this.connectBlockPos.getY(),this.connectBlockPos.getZ()});
        tag.putBoolean("has_connect",this.hasConnect);
        tag.putBoolean("mode",this.mode);
        tag.putBoolean("is_fluid_render",this.isFluidRender);
        super.saveSettings(player, tag);
    }

    @Override
    public void setSendBlockPos(BlockPos pos) {
        this.connectBlockPos = pos;
    }

    @Override
    public BlockPos getSendBlockPos() {
        if(this.connectBlockPos == null){
            return null;
        }else{
            return this.connectBlockPos;
        }
    }

    @Override
    public void setValue(List<Boolean> values) {
        this.hasConnect = values.getFirst();
    }

    @Override
    public List<Boolean> getValues() {
        return List.of(hasConnect);
    }
}
