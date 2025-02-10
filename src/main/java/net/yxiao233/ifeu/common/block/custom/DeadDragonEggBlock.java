package net.yxiao233.ifeu.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.utils.BlockBoxHelper;

public class DeadDragonEggBlock extends DragonEggBlock {
    public DeadDragonEggBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            return super.useItemOn(stack,state,level, pos, player, hand, hitResult);
        }

        if(stack.is(ModContents.DRAGON_STAR)){
            level.setBlock(hitResult.getBlockPos(), Blocks.DRAGON_EGG.defaultBlockState(),3);
            return ItemInteractionResult.CONSUME;
        }else{
            return super.useItemOn(stack,state,level, pos, player, hand, hitResult);
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return new BlockBoxHelper("dead_dragon_egg").getVoxelShapes();
    }
}
