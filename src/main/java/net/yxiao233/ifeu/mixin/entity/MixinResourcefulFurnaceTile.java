package net.yxiao233.ifeu.mixin.entity;

import com.buuz135.industrial.block.resourceproduction.tile.ResourcefulFurnaceTile;
import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResourcefulFurnaceTile.class)
public abstract class MixinResourcefulFurnaceTile extends IndustrialProcessingTile<ResourcefulFurnaceTile> {
    @Shadow private SidedInventoryComponent<ResourcefulFurnaceTile> input;

    public MixinResourcefulFurnaceTile(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> basicTileBlock, int x, int y, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, x, y, blockPos, blockState);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onTileInit(BlockPos blockPos, BlockState blockState, CallbackInfo ci){
        input.setSlotLimit(64);
    }
}
