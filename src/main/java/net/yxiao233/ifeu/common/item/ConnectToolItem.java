package net.yxiao233.ifeu.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.yxiao233.ifeu.common.block.entity.FluidTransferEntity;
import net.yxiao233.ifeu.common.registry.ModDataComponentTypes;
import net.yxiao233.ifeu.common.utils.IntArrayBlockPosUtil;
import net.yxiao233.ifeu.common.utils.KeyDownUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.yxiao233.ifeu.common.utils.TooltipHelper.*;

public class ConnectToolItem extends Item {
    public ConnectToolItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if(!level.isClientSide()){
            Player player = context.getPlayer();
            BlockPos currentBlockPos = context.getClickedPos();
            if(player == null){
                return super.useOn(context);
            }
            ItemStack item = player.getMainHandItem();

            CompoundTag tag = new CompoundTag();
            CompoundTag emptyTag = new CompoundTag();
            CompoundTag itemTag = item.get(ModDataComponentTypes.COMPOUND_TAG);
            if(level.getBlockEntity(currentBlockPos) instanceof FluidTransferEntity){
                addPos1ToTag(item,tag,player,currentBlockPos);
                addPos2ToTag(item,tag,player,currentBlockPos);

                if(tag.contains("pos2")){
                    BlockPos blockPos1;
                    if(tag.contains("pos1")){
                        blockPos1 = getBlockPos(tag,"pos1");
                    }else{
                        blockPos1 = getBlockPos(item,"pos1");
                    }
                    BlockPos blockPos2 = getBlockPos(tag,"pos2");
                    if(blockPos1.equals(blockPos2)){
                        tag = emptyTag;
                        player.sendSystemMessage(Component.translatable("message.ifeu.connect_tool.same_pos").withStyle(ChatFormatting.RED));
                    }else{
                        FluidTransferEntity entity1 = getFluidTransferEntity(level,blockPos1);
                        FluidTransferEntity entity2 = getFluidTransferEntity(level,blockPos2);
                        if(entity1 != null && entity2 != null){
                            if(getDistance(blockPos1,blockPos2) <= getCanConnectDistance(entity1,entity2)){
                                entity1.hasConnect = true;
                                entity2.hasConnect = true;
                                entity1.connectBlockPos = blockPos2;
                                entity2.connectBlockPos = blockPos1;
                                player.sendSystemMessage(Component.translatable("message.ifeu.connect_tool.connect_success").withStyle(ChatFormatting.GOLD));
                            }else{
                                player.sendSystemMessage(Component.translatable("message.ifeu.connect_tool.beyond_distance").withStyle(ChatFormatting.RED));
                            }
                        }
                    }
                }

                if(itemTag != null){
                    if(!tag.contains("pos1") && itemTag.contains("pos1")){
                        BlockPos pos1 = getBlockPos(item,"pos1");
                        tag.putIntArray("pos1", IntArrayBlockPosUtil.BlockPosToIntArray(pos1));
                    }
                    if(!tag.contains("pos2") && itemTag.contains("pos2")){
                        BlockPos pos2 = getBlockPos(item,"pos2");
                        tag.putIntArray("pos2",IntArrayBlockPosUtil.BlockPosToIntArray(pos2));
                    }
                }

                item.set(ModDataComponentTypes.COMPOUND_TAG,tag);
            }else if(player.isShiftKeyDown()){
                player.sendSystemMessage(Component.translatable("message.ifeu.connect_tool.clear_configuration").withStyle(ChatFormatting.GREEN));
                item.set(ModDataComponentTypes.COMPOUND_TAG,emptyTag);
            }
        }
        return super.useOn(context);
    }

    private BlockPos getBlockPos(ItemStack stack, String posId){
        CompoundTag tag = stack.get(ModDataComponentTypes.COMPOUND_TAG);
        return getBlockPos(tag,posId);
    }

    private BlockPos getBlockPos(CompoundTag tag,String posId){
        if(tag == null){
            return null;
        }

        if(tag.contains(posId)){
            int[] temp = tag.getIntArray(posId);
            return new BlockPos(IntArrayBlockPosUtil.intArrayToBlockPos(temp));
        }
        return null;
    }

    private void addPos1ToTag(ItemStack item,CompoundTag tag, Player player,BlockPos pos1){
        if(!KeyDownUtil.isShiftKeyDown()){
            if(item.has(ModDataComponentTypes.COMPOUND_TAG) && !item.get(ModDataComponentTypes.COMPOUND_TAG).contains("pos1")){
                tag.putIntArray("pos1",IntArrayBlockPosUtil.BlockPosToIntArray(pos1));
                player.sendSystemMessage(Component.translatable("message.ifeu.connect_tool.pos1",new Object[]{pos1.getX(),pos1.getY(),pos1.getZ()}).withStyle(ChatFormatting.GOLD));
            }else{
                player.sendSystemMessage(Component.translatable("message.ifeu.connect_tool.has_pos1").withStyle(ChatFormatting.RED));
            }
        }
    }

    private void addPos2ToTag(ItemStack item,CompoundTag tag, Player player,BlockPos pos2){
        if(KeyDownUtil.isShiftKeyDown()){
            if(item.has(ModDataComponentTypes.COMPOUND_TAG) && item.get(ModDataComponentTypes.COMPOUND_TAG).contains("pos1")){
                tag.putIntArray("pos2",IntArrayBlockPosUtil.BlockPosToIntArray(pos2));
                player.sendSystemMessage(Component.translatable("message.ifeu.connect_tool.pos2",new Object[]{pos2.getX(),pos2.getY(),pos2.getZ()}).withStyle(ChatFormatting.GOLD));
            }else{
                player.sendSystemMessage(Component.translatable("message.ifeu.connect_tool.no_pos1").withStyle(ChatFormatting.RED));
            }
        }
    }

    private FluidTransferEntity getFluidTransferEntity(Level level, BlockPos blockPos){
        if(level.getBlockEntity(blockPos) instanceof FluidTransferEntity entity){
            return entity;
        }
        return null;
    }

    public static double getDistance(BlockPos pos1, BlockPos pos2){
        int x1 = pos1.getX();
        int y1 = pos1.getY();
        int z1 = pos1.getZ();

        int x2 = pos2.getX();
        int y2 = pos2.getY();
        int z2 = pos2.getZ();
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }

    public static int getCanConnectDistance(FluidTransferEntity entity1, FluidTransferEntity entity2){
        return Math.max(entity1.maxConnectionDistance,entity2.maxConnectionDistance);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltips, TooltipFlag flag) {
        CompoundTag tag = stack.get(ModDataComponentTypes.COMPOUND_TAG);

        addTooltipWhileKeyDown(KeyType.SHIFT,tooltips,stack,() ->{
            if(tag != null && tag.contains("pos1")){
                int[] temp = tag.getIntArray("pos1");
                addTooltip(tooltips, stack, ChatFormatting.GREEN,0,new Object[]{temp[0],temp[1],temp[2]});
            }else{
                addTooltip(tooltips,stack,ChatFormatting.RED,1);
            }

            if(tag != null && tag.contains("pos2")){
                int[] temp = tag.getIntArray("pos2");
                addTooltip(tooltips, stack, ChatFormatting.GREEN,2,new Object[]{temp[0],temp[1],temp[2]});
            }else{
                addTooltip(tooltips,stack,ChatFormatting.RED,3);
            }
        });

        addTooltipWhileKeyDown(KeyType.CONTROL,tooltips,stack,() ->{
            addTooltip(tooltips,stack,ChatFormatting.GREEN,4);
            addTooltip(tooltips,stack,ChatFormatting.GREEN,5);
            addTooltip(tooltips,stack,ChatFormatting.GREEN,6);
        });


        addTooltip(tooltips,stack,ChatFormatting.RED,7);
    }
}