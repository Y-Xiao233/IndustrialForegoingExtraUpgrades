package net.yxiao233.ifeu.api.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.common.utils.RendererHelper;
import net.yxiao233.ifeu.api.block.entity.IFEUStructureProcessingTile;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class IFEUStructureEntityRenderer<T extends IFEUStructureProcessingTile<T>> implements BlockEntityRenderer<T> {
    private int[] index = new int[2560];
    private int tick = 200;
    private int time = 0;
    public IFEUStructureEntityRenderer(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void render(T entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int i1) {
        if(entity.shouldRenderer && entity.direction != null){
            //次数递增,用于渲染TagBlock
            time ++;
            //获取level,若level为空直接返回
            Level level = entity.getLevel();
            if(level == null){
                return;
            }
            BlockPos entityPos = entity.getBlockPos();


            //获取结构
            Pair<List<Pair<BlockPos,Block>>,List<Pair<BlockPos, TagKey<Block>>>> list = entity.multiBlockStructure().parseStructure(entity.multiBlockStructure().getStructure(), entity.direction,entity.getBlockPos());

            //当不是渲染完整结构时,计算block最低层y值
            AtomicInteger minBlockY = new AtomicInteger(list.getLeft().get(0).getLeft().getY());
            AtomicInteger minTagBlockY = new AtomicInteger(list.getRight().get(0).getLeft().getY());
            if(!entity.renderFull){
                list.getLeft().forEach(pair ->{
                    //如果该位置的方块已经存在且相同,则不进行计算
                    if(!entity.getLevel().getBlockState(pair.getLeft()).is(pair.getRight())){
                        minBlockY.set(Math.min(pair.getLeft().getY(),minBlockY.get()));
                    }
                });
                //计算blockTag最低层y值
                list.getRight().forEach(pair ->{
                    //如果该位置的方块已经存在且相同,则不进行计算
                    if(!entity.getLevel().getBlockState(pair.getLeft()).is(pair.getRight())){
                        minTagBlockY.set(Math.min(pair.getLeft().getY(),minTagBlockY.get()));
                    }
                });
            }

            //对两个最低y进行比较,取其中最小的
            int minY = Math.min(minBlockY.get(),minTagBlockY.get());

            //对最低层进行渲染,若该层所有方块都已经存在则会自动渲染下一层的方块
            list.getLeft().forEach(pair ->{
                //若方块不相同或其y坐标为当前最低y坐标
                if(!entity.getLevel().getBlockState(pair.getLeft()).is(pair.getRight())){
                    BlockPos pos = pair.getLeft();
                    BlockState state = pair.getRight().defaultBlockState();
                    if(entity.renderFull){
                        RendererHelper.renderSingleBatchedGhostBlock(poseStack,multiBufferSource,entityPos,pos,state);
                    }else if(pair.getLeft().getY() != minY){
                        //为了不让idea提示,没有任何实际用处
                        int var0 = 0;
                    }else{
                        RendererHelper.renderSingleBatchedGhostBlock(poseStack,multiBufferSource,entityPos,pos,state);
                    }
                }
            });

            List<List<Block>> lists = new ArrayList<>();
            List<Integer> maxIndex = new ArrayList<>();
            list.getRight().forEach(pair ->{
                List<Block> temp = getBlocksFromTagKey(pair.getRight());
                lists.add(temp);
                maxIndex.add(temp.size());
            });


            //遍历列表
            for (int m = 0; m < list.getRight().size(); m++) {
                //若次数大于当前设置tick,则递增所有方块列表索引
                if(time >= tick){
                    for (int k = 0; k < index.length; k++) {
                        index[k] ++;
                    }
                    time = 0;
                }

                //若当前索引超过最大索引,设置为0
                if(index[m] >= maxIndex.get(m)){
                    index[m] = 0;
                }
                BlockPos pos = list.getRight().get(m).getLeft();
                BlockState state = lists.get(m).get(index[m]).defaultBlockState();
                //若该位置已经有对应方块或其y坐标不是最小y坐标 则直接进入下一次循环,否则渲染该方块
                if(entity.getLevel().getBlockState(pos).is(list.getRight().get(m).getRight())){
                    continue;
                }
                if(entity.renderFull){
                    RendererHelper.renderSingleBatchedGhostBlock(poseStack,multiBufferSource,entityPos,pos,state);
                    continue;
                }else if(list.getRight().get(m).getLeft().getY() != minY){
                    continue;
                }


                RendererHelper.renderSingleBatchedGhostBlock(poseStack,multiBufferSource,entityPos,pos,state);
            }
        }
    }

    //遍历注册表来获取当前BlockTag对应的方块列表
    public List<Block> getBlocksFromTagKey(TagKey<Block> blockTag){
        List<Block> list = new ArrayList<>();
        ForgeRegistries.BLOCKS.getEntries().forEach(reg ->{
            if(reg.getValue().defaultBlockState().is(blockTag)){
                list.add(reg.getValue());
            }
        });

        return list;
    }

    private int getLightLevel(Level level, BlockPos blockPos){
        int bLight = level.getBrightness(LightLayer.BLOCK,blockPos);
        int sLight = level.getBrightness(LightLayer.SKY,blockPos);
        return LightTexture.pack(bLight,sLight);
    }
}
