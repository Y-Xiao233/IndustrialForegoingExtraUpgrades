package net.yxiao233.ifeu.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import net.yxiao233.ifeu.common.utils.BlockBoxHelper;

public class DeadDragonEggBlock extends DragonEggBlock {
    public DeadDragonEggBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(level.isClientSide()){
            return super.use(state,level, pos, player, hand, hit);
        }

        if(player.getMainHandItem().is(IFEUContents.DRAGON_STAR.get())){
            level.setBlock(hit.getBlockPos(), Blocks.DRAGON_EGG.defaultBlockState(),3);
            return InteractionResult.CONSUME;
        }else{
            return super.use(state, level, pos, player, hand, hit);
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return new BlockBoxHelper("ifeu","dead_dragon_egg").getVoxelShapes();
    }
}
