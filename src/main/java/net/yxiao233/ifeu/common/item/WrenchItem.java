package net.yxiao233.ifeu.common.item;

import com.hrznstudio.titanium.block.tile.BasicTile;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yxiao233.ifeu.common.registry.ModTags;
import net.yxiao233.ifeu.common.utils.TooltipHelper;

import java.util.List;

public class WrenchItem extends Item {
    public WrenchItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        if(!level.isClientSide()){
            if(player != null && player.isShiftKeyDown()){
                if(level.getBlockEntity(pos) instanceof BasicTile<?> tile){
                    BlockState blockState = tile.getBlockState();
                    Block block = blockState.getBlock();
                    Inventory inventory = player.getInventory();
                    if(!block.canEntityDestroy(blockState,level,pos,player)){
                        return InteractionResult.FAIL;
                    }
                    level.destroyBlock(pos,false);
                    if(inventory.getFreeSlot() != -1 || inventory.contains(block.asItem().getDefaultInstance())){
                        inventory.add(block.asItem().getDefaultInstance());
                    }else{
                        block.playerDestroy(level,player,pos,blockState,tile,block.asItem().getDefaultInstance());
                    }
                    return InteractionResult.SUCCESS;
                }else if(level.getBlockState(pos).getTags().toList().contains(ModTags.Blocks.WRENCH_PICKUP)){
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();
                    Inventory inventory = player.getInventory();
                    BlockEntity entity = null;
                    if(state.hasBlockEntity()){
                        entity = level.getBlockEntity(pos);
                    }
                    if(!block.canEntityDestroy(state,level,pos,player)){
                        return InteractionResult.FAIL;
                    }
                    level.destroyBlock(pos,false);
                    if(inventory.getFreeSlot() != -1 || inventory.contains(block.asItem().getDefaultInstance())){
                        inventory.add(block.asItem().getDefaultInstance());
                    }else{
                        if(entity != null){
                            block.playerDestroy(level,player,pos,state,entity,block.asItem().getDefaultInstance());
                        }else{
                            ItemEntity itemEntity = new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),block.asItem().getDefaultInstance());
                            level.addFreshEntity(itemEntity);
                        }

                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.useOn(context);
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltips, TooltipFlag flag) {
        TooltipHelper.addTooltip(tooltips,stack, ChatFormatting.GOLD,0);
        TooltipHelper.addTooltipWhileKeyDown(TooltipHelper.KeyType.SHIFT,tooltips,() ->{
            TooltipHelper.addTooltip(tooltips,stack,ChatFormatting.GREEN,1);
        });
    }
}
