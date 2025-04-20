package net.yxiao233.ifeu.mixin.entity;

import com.buuz135.industrial.block.agriculturehusbandry.tile.HydroponicBedTile;
import com.buuz135.industrial.block.tile.IndustrialWorkingTile;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(HydroponicBedTile.class)
public abstract class MixinHydroponicBedTile extends IndustrialWorkingTile<HydroponicBedTile>{
    public MixinHydroponicBedTile(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> basicTileBlock, int estimatedPower, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, estimatedPower, blockPos, blockState);
    }

    @Inject(
            method = "tryToHarvestAndReplant",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void onForEachDrops(Level level, BlockPos up, BlockState state, IItemHandler output, ProgressBarComponent<?> etherBuffer, IndustrialWorkingTile<?> tile, CallbackInfoReturnable<Boolean> cir, Optional optional, List<ItemStack> drops) {
        AtomicInteger apple = new AtomicInteger(0);
        List<ItemStack> list = new ArrayList<>();
        int tier = AugmentInventoryHelper.getAugmentTier(tile.getAugmentInventory(), IFEUAugmentTypes.APPLE);
        drops.forEach(stack -> {
            if(!stack.is(Items.APPLE)){
                list.add(stack);
            }else{
                apple.set(apple.get() + 1);
            }
        });

        if(apple.get() != 0){
            int times = tier == 0 ? 1 : tier;
            list.add(new ItemStack(Items.APPLE,apple.get() * times));
        }

        list.forEach(stack ->{
            ItemHandlerHelper.insertItem(output, stack, false);
        });
    }

    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem){
            return AugmentInventoryHelper.canAccept(this.getAugmentInventory(),augment);
        }
        return super.canAcceptAugment(augment);
    }
}
