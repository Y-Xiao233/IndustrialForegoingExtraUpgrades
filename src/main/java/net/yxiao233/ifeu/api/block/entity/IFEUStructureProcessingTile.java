package net.yxiao233.ifeu.api.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.buuz135.industrial.proxy.client.IndustrialAssetProvider;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IAsset;
import com.hrznstudio.titanium.api.client.IAssetType;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.block.RotatableBlock;
import com.hrznstudio.titanium.client.screen.addon.StateButtonAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonInfo;
import com.hrznstudio.titanium.component.button.ButtonComponent;
import com.hrznstudio.titanium.module.BlockWithTile;
import com.hrznstudio.titanium.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.yxiao233.ifeu.api.components.TextureGuiComponent;
import net.yxiao233.ifeu.api.networking.BooleanValueSyncS2C;
import net.yxiao233.ifeu.api.networking.DirectionSyncS2C;
import net.yxiao233.ifeu.api.structure.IMultiBlockStructure;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.networking.packet.BooleanSyncS2CPacket;
import net.yxiao233.ifeu.common.networking.packet.DirectionSyncS2CPacket;
import net.yxiao233.ifeu.common.registry.ModTags;

import java.util.Collections;
import java.util.List;

public abstract class IFEUStructureProcessingTile<T extends IFEUStructureProcessingTile<T>> extends IndustrialProcessingTile<T> implements IMultiBlockStructure, DirectionSyncS2C, BooleanValueSyncS2C {
    public boolean hasCurrentStructure;
    @Save
    public boolean shouldRenderer = true;
    @Save
    public boolean renderFull = false;
    public Direction direction;
    private MultiBlockStructure helper;
    private int time = 0;
    public ButtonComponent structureButton;
    //base structure block tag
    public static final TagKey<Block> ENERGY = ModTags.Blocks.STORAGE_ENERGY;
    public static final TagKey<Block> FLUID = ModTags.Blocks.STORAGE_FLUID;
    public static final TagKey<Block> ITEM = ModTags.Blocks.STORAGE_ITEM;
    public static final TagKey<Block> FRAME_PITY = IndustrialTags.Blocks.MACHINE_FRAME_PITY;
    public static final TagKey<Block> FRAME_SIMPLE = IndustrialTags.Blocks.MACHINE_FRAME_SIMPLE;
    public static final TagKey<Block> FRAME_ADVANCED = IndustrialTags.Blocks.MACHINE_FRAME_ADVANCED;
    public static final TagKey<Block> FRAME_SUPREME = IndustrialTags.Blocks.MACHINE_FRAME_SUPREME;
    public static final TagKey<Block> FRAME_ULTIMATE = ModTags.Blocks.MACHINE_FRAME_ULTIMATE;
    public IFEUStructureProcessingTile(BlockWithTile basicTileBlock, int x, int y, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, x, y, blockPos, blockState);

        this.addButton(this.structureButton = (new ButtonComponent(136, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    StateButtonInfo[] buttonInfo = new StateButtonInfo[3];

                    IAssetType<IAsset> asset = IndustrialAssetProvider.BUTTON_SHOW_AREA;
                    String[] tip = new String[2];
                    ChatFormatting chatFormatting = ChatFormatting.GOLD;
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.structure.render_full_structure", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.render_full_structure_1";
                    buttonInfo[0] = new StateButtonInfo(0, asset, tip);

                    asset = IndustrialAssetProvider.BUTTON_HIDE_AREA;
                    tip = new String[2];
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.structure.render_min_structure", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.render_min_structure_1";
                    buttonInfo[1] = new StateButtonInfo(1, asset, tip);

                    asset = AssetTypes.BUTTON_SIDENESS_DISABLED;
                    tip = new String[2];
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.structure.hide_structure", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.hide_structure_1";
                    buttonInfo[2] = new StateButtonInfo(2, asset, tip);
                    return new StateButtonAddon(this, buttonInfo) {
                        public int getState() {
                            if(shouldRenderer){
                                return renderFull ? 0 : 1;
                            }
                            return 2;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            if(shouldRenderer){
                if(renderFull){
                    renderFull = false;
                }else{
                    shouldRenderer = false;
                }
            }else{
                shouldRenderer = true;
                renderFull = true;
            }
            this.markForUpdate();
        }));

        this.helper = multiBlockStructure();
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, T blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        if(!level.isClientSide()){

            boolean b = state.hasProperty(RotatableBlock.FACING_HORIZONTAL);
            Direction direction = null;
            if(b){
                this.direction = state.getValue(RotatableBlock.FACING_HORIZONTAL);
                direction = this.direction;
                PacketDistributor.sendToAllPlayers(new DirectionSyncS2CPacket(this.getBlockPos(),direction));
            }

            hasCurrentStructure = helper.checkStructure(level,direction,pos);
            PacketDistributor.sendToAllPlayers(new BooleanSyncS2CPacket(pos,List.of(hasCurrentStructure,shouldRenderer,renderFull)));
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initClient() {
        super.initClient();



        this.addGuiAddonFactory(() ->{
            return new TextureGuiComponent(setStructureFormingPosition()[0], setStructureFormingPosition()[1]) {
                @Override
                public AllGuiTextures getTexture() {
                    if(hasCurrentStructure){
                        return AllGuiTextures.TRUE;
                    }else{
                        return AllGuiTextures.FALSE;
                    }
                }

                @Override
                public Component getComponent() {
                    if(hasCurrentStructure){
                        return Component.translatable("tooltip.ifeu.structure.structural_forming");
                    }else{
                        return Component.translatable("tooltip.ifeu.structure.structural_not_forming");
                    }
                }
            }.withoutItemBackGround();
        });
    }

    public boolean isShouldRenderer(){
        if(hasCurrentStructure){
            return false;
        }
        return shouldRenderer;
    }

    public int getTime(){
        return time;
    }

    public void increaseTime(int delta){
        this.time = getTime() + delta;
    }
    public void setTime(int time){
        this.time = time;
    }
    @Override
    public void setDirectionValue(Direction value) {
        this.direction = value;
    }

    @Override
    public Direction getDirectionValue() {
        return direction;
    }

    @Override
    public void setValue(List<Boolean> values) {
        this.hasCurrentStructure = values.getFirst();
        this.shouldRenderer = values.get(1);
        this.renderFull = values.get(2);
    }

    @Override
    public List<Boolean> getValues() {
        return List.of(hasCurrentStructure,shouldRenderer,renderFull);
    }

    @Override
    public void loadSettings(Player player, CompoundTag tag) {
        if (tag.contains("shouldRenderer")) {
            this.shouldRenderer = tag.getBoolean("shouldRenderer");
        }
        if (tag.contains("renderFull")) {
            this.renderFull = tag.getBoolean("renderFull");
        }
        super.loadSettings(player, tag);
    }

    public void saveSettings(Player player, CompoundTag tag) {
        tag.putBoolean("shouldRenderer",this.shouldRenderer);
        tag.putBoolean("renderFull",this.renderFull);
        super.saveSettings(player, tag);
    }

    public abstract int[] setStructureFormingPosition();
}
