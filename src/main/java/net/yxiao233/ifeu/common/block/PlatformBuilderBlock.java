package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.hrznstudio.titanium.block.RotatableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yxiao233.ifeu.common.block.entity.PlatformBuilderEntity;
import net.yxiao233.ifeu.common.item.ConnectToolItem;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import net.yxiao233.ifeu.common.utils.BlockBoxHelper;
import org.jetbrains.annotations.NotNull;

public class PlatformBuilderBlock extends IndustrialBlock<PlatformBuilderEntity> {
    public PlatformBuilderBlock() {
        super("platform_builder", BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK), PlatformBuilderEntity.class, IFEUItems.TAB_ADDONS);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        if(player.getMainHandItem().getItem() instanceof ConnectToolItem){
            return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        }
        return super.useItemOn(stack, state, worldIn, pos, player, hand, ray);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return PlatformBuilderEntity::new;
    }

    @NotNull
    @Override
    public RotatableBlock.RotationType getRotationType() {
        return RotatableBlock.RotationType.NONE;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return new BlockBoxHelper("platform_builder").getVoxelShapes();
    }
}
