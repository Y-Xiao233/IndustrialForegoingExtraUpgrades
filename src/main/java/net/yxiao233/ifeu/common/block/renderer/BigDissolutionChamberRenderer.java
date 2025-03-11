package net.yxiao233.ifeu.common.block.renderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.yxiao233.ifeu.api.block.renderer.IFEUStructureEntityRenderer;
import net.yxiao233.ifeu.common.block.entity.BigDissolutionChamberEntity;

public class BigDissolutionChamberRenderer extends IFEUStructureEntityRenderer<BigDissolutionChamberEntity> {
    public BigDissolutionChamberRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
}
