package net.yxiao233.ifeu.api.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.yxiao233.ifeu.api.block.entity.IFEUStructureProcessingTile;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.common.utils.RendererProvider;

import java.util.List;

public class IFEUStructureEntityRenderer<T extends IFEUStructureProcessingTile<T>> implements BlockEntityRenderer<T> {
    private volatile MultiBlockStructure structure;
    private final int tick = 200;
    public IFEUStructureEntityRenderer(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void render(T entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        if(entity.isShouldRenderer() && entity.direction != null){
            Level level = entity.getLevel();
            if(level == null){
                return;
            }

            structure = getStructure(entity);

            Direction direction = entity.direction;
            BlockPos machinePos = entity.getBlockPos();
            boolean renderFull = entity.renderFull;
            entity.increaseTime(1);

            var poses = structure.getRenderStructure(direction,machinePos);
            RendererProvider provider = new RendererProvider(poseStack,multiBufferSource,machinePos);
            int minY = structure.getShouldRenderMinY(level,direction,machinePos);

            poses.getLeft().forEach(pair ->{
                BlockPos pos = pair.getLeft();
                BlockState state = pair.getRight().defaultBlockState();
                render(pos,state,provider,minY,!level.getBlockState(pos).is(pair.getRight()),renderFull);
            });

            poses.getRight().forEach(pair ->{
                BlockPos pos = pair.getLeft();
                List<Block> blocks = pair.getRight().getLeft();
                int index = (int) Math.floor((double) entity.getTime() / tick);
                while(index >= blocks.size()){
                    index = index - blocks.size();
                }
                if(index < 0){
                    index = 0;
                }
                BlockState state = blocks.get(index).defaultBlockState();
                render(pos,state,provider,minY,!level.getBlockState(pos).is(pair.getRight().getRight()),renderFull);
            });
        }
    }

    private void render(BlockPos pos, BlockState state, RendererProvider provider, int minY, boolean shouldRender, boolean renderFull){
        if(shouldRender){
            if(renderFull){
                provider.renderSingleBatchedGhostBlock(pos,state);
            }else if(pos.getY() != minY){
                //为了不让idea提示,没有任何实际用处
                int var0 = 0;
            }else{
                provider.renderSingleBatchedGhostBlock(pos,state);

            }
        }
    }

    public MultiBlockStructure getStructure(T entity){
        if(structure == null){
            return entity.multiBlockStructure();
        }else{
            return structure;
        }
    }
}
