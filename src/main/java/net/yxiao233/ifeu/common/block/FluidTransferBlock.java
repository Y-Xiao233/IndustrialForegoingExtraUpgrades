package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.utils.IndustrialTags;
import net.minecraft.core.BlockPos;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.item.ConnectToolItem;
import net.yxiao233.ifeu.common.recipe.ShapedRecipe;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModFluids;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.block.entity.FluidTransferEntity;
import net.yxiao233.ifeu.common.registry.ModTags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FluidTransferBlock extends IndustrialBlock<FluidTransferEntity> {
    public FluidTransferBlock() {
        super("fluid_transfer", Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), FluidTransferEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        if(player.getMainHandItem().getItem() instanceof ConnectToolItem){
            return InteractionResult.PASS;
        }
        return super.use(state, worldIn, pos, player, hand, ray);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return FluidTransferEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public void registerRecipe(Consumer<FinishedRecipe> consumer) {
        new ShapedRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"fluid_transfer"),
                new Ingredient.Value[]{
                        new Ingredient.TagValue(IndustrialTags.Items.PLASTIC),
                        new Ingredient.TagValue(Tags.Items.GLASS_PANES),
                        new Ingredient.TagValue(IndustrialTags.Items.PLASTIC),
                        new Ingredient.TagValue(ModTags.Items.GEARS_NETHERITE),
                        new Ingredient.TagValue(ModTags.Items.MACHINE_FRAME_ULTIMATE),
                        new Ingredient.TagValue(ModTags.Items.GEARS_NETHERITE),
                        new Ingredient.ItemValue(ModuleCore.RANGE_ADDONS[3].get().getDefaultInstance()),
                        new Ingredient.ItemValue(Items.BUCKET.getDefaultInstance()),
                        new Ingredient.ItemValue(ModuleCore.RANGE_ADDONS[3].get().getDefaultInstance()),
                },new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),200),
                new ItemStack(this,2)
        );
    }
}
