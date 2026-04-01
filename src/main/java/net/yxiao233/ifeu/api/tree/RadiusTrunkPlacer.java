package net.yxiao233.ifeu.api.tree;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class RadiusTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<RadiusTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.intRange(0,Integer.MAX_VALUE).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("radius").forGetter(placer -> placer.radius)
            ).apply(instance, RadiusTrunkPlacer::new)
    );

    private final int radius;
    public RadiusTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, int radius) {
        super(baseHeight, heightRandA, heightRandB);
        this.radius = radius;
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return IFEUContents.RADIUS_TRUNK_PLACER_TYPE.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader reader, @NotNull BiConsumer<BlockPos, BlockState> consumer, @NotNull RandomSource randomSource, int height, @NotNull BlockPos pos, @NotNull TreeConfiguration configuration) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int i = 0; i < height; i++) {
            this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, 0, i, 0);
            if (i < height - 1) {
                for(int w1 = 1; w1 < radius; w1 ++){
                    for(int w2 = 1; w2 < radius; w2 ++){
                        this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, 0, i, w2);
                        this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, 0, i, -w2);
                        this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, w1, i, 0);
                        this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, -w1, i, 0);
                        this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, w1, i, w2);
                        this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, -w1, i, -w2);
                        this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, w1, i, -w2);
                        this.placeLogIfFreeWithOffset(reader, consumer, randomSource, blockpos$mutableblockpos, configuration, pos, -w1, i, w2);
                    }
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, true));
    }

    private static int sinc2(final double x, final double z, final int y) {
        final double pi = Math.PI, pi2 = pi / 1.5;
        double r;
        r = Math.sqrt((r = (x / pi)) * r + (r = (z / pi)) * r) * pi / 180;
        if (r == 0) return y;
        return (int) Math.round(y * (((Math.sin(r) / r) + (Math.sin(r * pi2) / (r * pi2))) / 2));
    }

    private void placeLogIfFreeWithOffset(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> consumer, RandomSource randomSource, BlockPos.MutableBlockPos mutableBlockPos, TreeConfiguration configuration, BlockPos blockPos, int xOffset, int yOffset, int zOffset) {
        mutableBlockPos.setWithOffset(blockPos, xOffset, yOffset, zOffset);
        this.placeLogIfFree(reader, consumer, randomSource, mutableBlockPos, configuration);
    }
}
