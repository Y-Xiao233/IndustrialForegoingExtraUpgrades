package net.yxiao233.ifeu.mixin.block;

import com.buuz135.industrial.block.MachineFrameBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MachineFrameBlock.MachineFrameItem.class)
public abstract class MixinMachineFrameItem extends BlockItem {
    public MixinMachineFrameItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }
    @Inject(method = "canPlace", at = @At("HEAD"), cancellable = true)
    public void canPlace(BlockPlaceContext context, BlockState state, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
