package net.yxiao233.ifeu.common.item;

import com.hrznstudio.titanium.block.tile.BasicTile;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WrenchItem extends Item {
    public WrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        if(!level.isClientSide()){
            if(player != null && player.isShiftKeyDown()){
                BlockState blockState = null;
                Block block = null;
                Inventory inventory = player.getInventory();
                BlockEntity entity = null;

                if(level.getBlockEntity(pos) instanceof BasicTile<?> tile){
                    blockState = tile.getBlockState();
                    block = blockState.getBlock();
                    entity = tile;
                    if(!block.canEntityDestroy(blockState,level,pos,player)){
                        return InteractionResult.FAIL;
                    }
                }else if(level.getBlockState(pos).getTags().toList().contains(ModTags.Blocks.WRENCH_PICKUP)){
                    blockState = level.getBlockState(pos);
                    block = blockState.getBlock();
                    if(blockState.hasBlockEntity()){
                        entity = level.getBlockEntity(pos);
                    }
                    if(!block.canEntityDestroy(blockState,level,pos,player)){
                        return InteractionResult.FAIL;
                    }
                }

                if(block == null){
                    return InteractionResult.FAIL;
                }

                if(block.canEntityDestroy(blockState,level,pos,player)){
                    level.destroyBlock(pos,false);
                    addToInventory(level,blockState,pos,entity,player,player.getUseItem(),inventory);
                    return InteractionResult.SUCCESS;
                }else{
                    return InteractionResult.FAIL;
                }
            }
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltips, TooltipFlag flag) {
        TooltipHelper.addTooltip(tooltips,stack, ChatFormatting.GOLD,0);
        TooltipHelper.addTooltipWhileKeyDown(TooltipHelper.KeyType.SHIFT,tooltips,() ->{
            TooltipHelper.addTooltip(tooltips,stack,ChatFormatting.GREEN,1);
        });
    }

    public void addToInventory(Level level, BlockState state, BlockPos pos, BlockEntity entity, Player player, ItemStack tool, Inventory playerInventory){
        List<ItemStack> list = Block.getDrops(state,(ServerLevel) level,pos,entity,player,tool);

        list.forEach(itemStack ->{
            if(playerInventory.getFreeSlot() != -1 || playerInventory.contains(itemStack)){
                playerInventory.add(itemStack);
            }else{
                dropContents(level,pos,itemStack);
            }
        });
    }

    public void dropContents(Level level, BlockPos pos,ItemStack stack){
        double d0 = (double) EntityType.ITEM.getHeight() / 2.0;
        double d1 = (double)pos.getX() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25);
        double d2 = (double)pos.getY() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25) - d0;
        double d3 = (double)pos.getZ() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25);
        ItemEntity item = new ItemEntity(level, d1, d2, d3, stack);
        level.addFreshEntity(item);
    }
}
