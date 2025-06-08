package net.yxiao233.ifeu.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.yxiao233.ifeu.common.block.entity.FluidTransferEntity;
import net.yxiao233.ifeu.common.block.entity.PlatformBuilderEntity;
import net.yxiao233.ifeu.common.registry.ModDataComponentTypes;
import net.yxiao233.ifeu.common.utils.IntArrayBlockPosUtil;
import net.yxiao233.ifeu.common.utils.TagUtil;

import java.util.List;

import static net.yxiao233.ifeu.common.utils.TooltipHelper.*;

public class ConnectToolItem extends Item {
    public ConnectToolItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if(!level.isClientSide()) {
            Player player = context.getPlayer();
            BlockPos currentBlockPos = context.getClickedPos();
            if (player == null) {
                return super.useOn(context);
            }
            ItemStack item = player.getMainHandItem();
            initTag(item);

            CompoundTag tag = new CompoundTag();
            CompoundTag itemTag = item.get(ModDataComponentTypes.COMPOUND_TAG);

            if(itemTag != null){
                int mode = itemTag.getInt("mode");
                TagUtil.saveAllTag(tag,itemTag,List.of("mode0","mode1"));

                if(mode == 0 && level.getBlockEntity(currentBlockPos) instanceof FluidTransferEntity){
                    CompoundTag mode0 = new CompoundTag();
                    CompoundTag itemTagMode0 = TagUtil.getOrDefault(itemTag,"mode0");
                    TagUtil.saveAllBlockPos(mode0,itemTagMode0,List.of("pos1","pos2"));
                    if(!player.isShiftKeyDown()){
                        if(!TagUtil.contains(itemTagMode0,"pos1")){
                            mode0.putIntArray("pos1",IntArrayBlockPosUtil.blockPosToIntArray(currentBlockPos));
                            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.pos1",new Object[]{currentBlockPos.getX(),currentBlockPos.getY(),currentBlockPos.getZ()}).withStyle(ChatFormatting.GOLD),true);
                        }else{
                            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.has_pos1").withStyle(ChatFormatting.RED),true);
                        }
                    }else{
                        if(TagUtil.contains(mode0,"pos1")){
                            mode0.putIntArray("pos2",IntArrayBlockPosUtil.blockPosToIntArray(currentBlockPos));
                            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.pos2",new Object[]{currentBlockPos.getX(),currentBlockPos.getY(),currentBlockPos.getZ()}).withStyle(ChatFormatting.GOLD),true);
                            mergeToEntity(level,player,mode0,item);
                        }else{
                            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.no_pos1").withStyle(ChatFormatting.RED),true);
                        }
                    }
                    tag.put("mode0",mode0);
                }
                if(mode == 1){
                    CompoundTag mode1 = new CompoundTag();
                    CompoundTag itemTagMode1 = TagUtil.getOrDefault(itemTag,"mode1");
                    TagUtil.saveAllBlockPos(mode1,itemTagMode1,List.of("machine_pos","center_pos"));
                    if(player.isShiftKeyDown() && !(level.getBlockEntity(currentBlockPos) instanceof PlatformBuilderEntity)){
                        if(TagUtil.contains(mode1,"machine_pos")){
                            mode1.putIntArray("center_pos",IntArrayBlockPosUtil.blockPosToIntArray(currentBlockPos));
                            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.center_pos",new Object[]{currentBlockPos.getX(),currentBlockPos.getY(),currentBlockPos.getZ()}).withStyle(ChatFormatting.GOLD),true);
                            updateToEntity(level,player,mode1);
                        }else{
                            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.no_machine_pos").withStyle(ChatFormatting.RED),true);
                        }
                    }else{
                        mode1.putIntArray("machine_pos",IntArrayBlockPosUtil.blockPosToIntArray(currentBlockPos));
                        player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.machine_pos",new Object[]{currentBlockPos.getX(),currentBlockPos.getY(),currentBlockPos.getZ()}).withStyle(ChatFormatting.GOLD),true);
                    }
                    tag.put("mode1",mode1);
                }

                TagUtil.saveInt(tag,itemTag,"mode");
                item.set(ModDataComponentTypes.COMPOUND_TAG,tag);
            }


        }
        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide()){
            BlockHitResult hitResult = (BlockHitResult) player.pick(player.blockInteractionRange(),0.0F,false);
            BlockPos pos = hitResult.getBlockPos();
            ItemStack item = player.getMainHandItem();
            if(level.getBlockState(pos).isAir() && item.getItem() instanceof ConnectToolItem){
                initTag(item);
                CompoundTag tag = new CompoundTag();
                CompoundTag itemTag = item.get(ModDataComponentTypes.COMPOUND_TAG);
                if(itemTag != null){
                    int mode = itemTag.getInt("mode");
                    TagUtil.saveAllTag(tag,itemTag,List.of("mode0","mode1"));
                    TagUtil.saveInt(tag,itemTag,"mode");

                    if(player.isShiftKeyDown()){
                        if(mode == 1){
                            CompoundTag mode1 = TagUtil.getOrDefault(tag,"mode1");
                            if(mode1.contains("machine_pos")){
                                PlatformBuilderEntity entity = getPlatformBuilderEntity(level,IntArrayBlockPosUtil.intArrayToBlockPos(mode1.getIntArray("machine_pos")));
                                if(entity != null){
                                    entity.centerPos = entity.getBlockPos();
                                }
                            }
                        }
                        tag.put("mode" + mode,new CompoundTag());
                        player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.clear_configuration").withStyle(ChatFormatting.GREEN),true);
                        item.set(ModDataComponentTypes.COMPOUND_TAG,tag);
                        return InteractionResultHolder.success(item);
                    }
                    if(!player.isShiftKeyDown()){
                        if(mode == 0){
                            tag.putInt("mode",1);
                            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.mode_change_1").withStyle(ChatFormatting.GREEN),true);
                        }else if(mode == 1){
                            tag.putInt("mode",0);
                            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.mode_change_0").withStyle(ChatFormatting.GREEN),true);
                        }
                        item.set(ModDataComponentTypes.COMPOUND_TAG,tag);
                        return InteractionResultHolder.success(item);
                    }
                }
            }
        }
        return super.use(level, player, hand);
    }

    private void initTag(ItemStack stack){
        if(!stack.has(ModDataComponentTypes.COMPOUND_TAG) || stack.get(ModDataComponentTypes.COMPOUND_TAG) == null) {
            CompoundTag tag = new CompoundTag();
            tag.putInt("mode",0);
            tag.put("mode0",new CompoundTag());
            tag.put("mode1",new CompoundTag());
            stack.set(ModDataComponentTypes.COMPOUND_TAG,tag);
        }
    }

    private void mergeToEntity(Level level, Player player, CompoundTag tag, ItemStack item){
        if(tag.contains("pos2")){
            BlockPos blockPos1;
            if(tag.contains("pos1")){
                blockPos1 = getBlockPos(tag,"pos1");
            }else{
                blockPos1 = getBlockPos(item,"pos1");
            }
            BlockPos blockPos2 = getBlockPos(tag,"pos2");
            if(blockPos1.equals(blockPos2)){
                tag = new CompoundTag();
                player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.same_pos").withStyle(ChatFormatting.RED),true);
            }else{
                FluidTransferEntity entity1 = getFluidTransferEntity(level,blockPos1);
                FluidTransferEntity entity2 = getFluidTransferEntity(level,blockPos2);
                if(entity1 != null && entity2 != null){
                    if(getDistance(blockPos1,blockPos2) <= getCanConnectDistance(entity1,entity2)){
                        entity1.hasConnect = true;
                        entity2.hasConnect = true;
                        entity1.connectBlockPos = blockPos2;
                        entity2.connectBlockPos = blockPos1;
                        player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.connect_success").withStyle(ChatFormatting.GREEN),true);
                    }else{
                        player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.beyond_distance").withStyle(ChatFormatting.RED),true);
                    }
                }
            }
        }

    }

    private void updateToEntity(Level level, Player player, CompoundTag tag){
        if(TagUtil.contains(tag,"machine_pos") && TagUtil.contains(tag,"center_pos")){
            PlatformBuilderEntity entity = getPlatformBuilderEntity(level,IntArrayBlockPosUtil.intArrayToBlockPos(tag.getIntArray("machine_pos")));
            if(entity != null){
                entity.centerPos = IntArrayBlockPosUtil.intArrayToBlockPos(tag.getIntArray("center_pos"));
                player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.success").withStyle(ChatFormatting.GREEN),true);
            }else{
                player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.no_machine").withStyle(ChatFormatting.RED),true);
            }
        }else{
            player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.failed").withStyle(ChatFormatting.RED),true);
        }

    }

    private BlockPos getBlockPos(ItemStack stack, String posId){
        CompoundTag tag = stack.get(ModDataComponentTypes.COMPOUND_TAG);
        return getBlockPos(tag,posId);
    }

    private BlockPos getBlockPos(CompoundTag tag, String posId){
        if(tag == null){
            return null;
        }

        if(tag.contains(posId)){
            int[] temp = tag.getIntArray(posId);
            return new BlockPos(IntArrayBlockPosUtil.intArrayToBlockPos(temp));
        }
        return null;
    }

    public static int getCanConnectDistance(FluidTransferEntity entity1, FluidTransferEntity entity2){
        return Math.max(entity1.maxConnectionDistance,entity2.maxConnectionDistance);
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

    private FluidTransferEntity getFluidTransferEntity(Level level, BlockPos blockPos){
        if(level.getBlockEntity(blockPos) instanceof FluidTransferEntity entity){
            return entity;
        }
        return null;
    }

    private PlatformBuilderEntity getPlatformBuilderEntity(Level level, BlockPos blockPos){
        if(level.getBlockEntity(blockPos) instanceof PlatformBuilderEntity entity){
            return entity;
        }
        return null;
    }



    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltips, TooltipFlag flag) {
        CompoundTag tag = stack.get(ModDataComponentTypes.COMPOUND_TAG);
        initTag(stack);

        if(TagUtil.contains(tag,"mode")){
            int mode = tag.getInt("mode");
            if(mode == 0){
                addTooltip(tooltips,stack,ChatFormatting.GREEN,0);
            }else if(mode == 1){
                addTooltip(tooltips,stack,ChatFormatting.GREEN,1);
            }
            addTooltip(tooltips,stack,ChatFormatting.AQUA,99);
            CompoundTag finalTag = tag;
            addTooltipWhileKeyDown(KeyType.SHIFT,tooltips,() ->{
                if(mode == 0 && finalTag.contains("mode0")){
                    CompoundTag mode0 = finalTag.getCompound("mode0");
                    if(TagUtil.contains(mode0,"pos1")){
                        int[] temp = mode0.getIntArray("pos1");
                        addTooltip(tooltips, stack, ChatFormatting.GREEN,2,new Object[]{temp[0],temp[1],temp[2]});
                    }else{
                        addTooltip(tooltips,stack,ChatFormatting.RED,3);
                    }

                    if(TagUtil.contains(mode0,"pos2")){
                        int[] temp = mode0.getIntArray("pos2");
                        addTooltip(tooltips, stack, ChatFormatting.GREEN,4,new Object[]{temp[0],temp[1],temp[2]});
                    }else{
                        addTooltip(tooltips,stack,ChatFormatting.RED,5);
                    }
                }else if(mode == 1){
                    CompoundTag mode1 = finalTag.getCompound("mode1");

                    if(TagUtil.contains(mode1,"machine_pos")){
                        int[] temp = mode1.getIntArray("machine_pos");
                        addTooltip(tooltips, stack, ChatFormatting.GREEN,6,new Object[]{temp[0],temp[1],temp[2]});
                    }else{
                        addTooltip(tooltips,stack,ChatFormatting.RED,7);
                    }

                    if(TagUtil.contains(mode1,"center_pos")){
                        int[] temp = mode1.getIntArray("center_pos");
                        addTooltip(tooltips, stack, ChatFormatting.GREEN,8,new Object[]{temp[0],temp[1],temp[2]});
                    }else{
                        addTooltip(tooltips,stack,ChatFormatting.RED,9);
                    }
                }
            });

            addTooltipWhileKeyDown(KeyType.CONTROL,tooltips,() ->{
                if(mode == 0){
                    addTooltip(tooltips,stack,ChatFormatting.GREEN,10);
                    addTooltip(tooltips,stack,ChatFormatting.GREEN,11);
                    addTooltip(tooltips,stack,ChatFormatting.GREEN,12);
                }else if(mode == 1){
                    addTooltip(tooltips,stack,ChatFormatting.GREEN,13);
                    addTooltip(tooltips,stack,ChatFormatting.GREEN,14);
                    addTooltip(tooltips,stack,ChatFormatting.GREEN,12);
                }
            });
        }

        addTooltip(tooltips,stack,ChatFormatting.RED,98);
    }
}