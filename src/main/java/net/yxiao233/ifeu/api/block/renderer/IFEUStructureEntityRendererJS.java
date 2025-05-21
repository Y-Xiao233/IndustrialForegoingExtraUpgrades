package net.yxiao233.ifeu.api.block.renderer;

import com.hrznstudio.titanium.block.RotatableBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.common.utils.RendererProvider;

import java.util.List;

public class IFEUStructureEntityRendererJS implements BlockEntityRenderer<BlockEntity> {
    private final MultiBlockStructure structure;
    private final int tick = 200;
    private final Item item;
    private int time = 0;
    public IFEUStructureEntityRendererJS(BlockEntityRendererProvider.Context context, MultiBlockStructure structure, Item item) {
        this.structure = structure;
        this.item = item;
    }
    @Override
    public void render(BlockEntity entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        boolean shouldRender = false;
        if(item == null || item.getDefaultInstance().is(Items.AIR) || item.getDefaultInstance().isEmpty()){
            shouldRender = true;
        }else{
            Player player = Minecraft.getInstance().player;
            if(player != null && (player.getMainHandItem().is(item) || player.getOffhandItem().is(item))){
                shouldRender = true;
            }
        }

        if(!shouldRender){
            return;
        }

        BlockState blockState = entity.getBlockState();
        boolean hasDirection = blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING);
        boolean hasSpecialDirection = blockState.hasProperty(RotatableBlock.FACING_HORIZONTAL);
        Direction direction = null;
        if(hasDirection){
            direction = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
        }else if(hasSpecialDirection){
            direction = blockState.getValue(RotatableBlock.FACING_HORIZONTAL);
        }
        if(direction != null){
            Level level = entity.getLevel();
            if(level == null){
                return;
            }
            BlockPos machinePos = entity.getBlockPos();
            time ++;

            var poses = structure.getRenderStructure(direction,machinePos);
            RendererProvider provider = new RendererProvider(poseStack,multiBufferSource,machinePos);
            int minY = structure.getShouldRenderMinY(level,direction,machinePos);

            poses.getLeft().forEach(pair ->{
                BlockPos pos = pair.getLeft();
                BlockState state = pair.getRight().defaultBlockState();
                render(pos,state,provider,minY,!level.getBlockState(pos).is(pair.getRight()));
            });

            poses.getRight().forEach(pair ->{
                BlockPos pos = pair.getLeft();
                List<Block> blocks = pair.getRight().getLeft();
                int index = (int) Math.floor((double) time / tick);
                while(index >= blocks.size()){
                    index = index - blocks.size();
                }
                if(index < 0){
                    index = 0;
                }
                BlockState state = blocks.get(index).defaultBlockState();
                render(pos,state,provider,minY,!level.getBlockState(pos).is(pair.getRight().getRight()));
            });
        }
    }

    private void render(BlockPos pos, BlockState state, RendererProvider provider, int minY, boolean shouldRender){
        if(shouldRender){
            if(pos.getY() != minY){
                //为了不让idea提示,没有任何实际用处
                int var0 = 0;
            }else{
                provider.renderSingleBatchedGhostBlock(pos,state);
            }
        }
    }
}
