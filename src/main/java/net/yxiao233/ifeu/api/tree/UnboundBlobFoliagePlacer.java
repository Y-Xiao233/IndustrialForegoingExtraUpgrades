package net.yxiao233.ifeu.api.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import org.jetbrains.annotations.NotNull;

public class UnboundBlobFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<UnboundBlobFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    IntProvider.codec(0, Integer.MAX_VALUE).fieldOf("radius").forGetter(placer -> placer.radius),
                    IntProvider.codec(0, Integer.MAX_VALUE).fieldOf("offset").forGetter(placer -> placer.offset),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("height").forGetter(placer -> placer.height)
            ).apply(instance, UnboundBlobFoliagePlacer::new)

    );
    public UnboundBlobFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset,height);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return IFEUContents.UNBOUND_BLOB_FOLIAGE_PLACER_TYPE.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader reader, FoliagePlacer.@NotNull FoliageSetter foliageSetter, @NotNull RandomSource randomSource, @NotNull TreeConfiguration configuration, int trunkHeight, FoliagePlacer.@NotNull FoliageAttachment foliageAttachment, int foliageHeight, int radius, int offset) {
        for (int i = offset; i >= offset - foliageHeight; i--) {
            int j = Math.max(radius + foliageAttachment.radiusOffset() - radius / 2 + 1 - i / 2, 0);
            this.placeLeavesRow(reader, foliageSetter, randomSource, configuration, foliageAttachment.pos(), j, i, foliageAttachment.doubleTrunk());
        }
    }
}
