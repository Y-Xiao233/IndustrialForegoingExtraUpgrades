package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.utils.IndustrialTags;
import net.minecraft.core.BlockPos;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.block.entity.FluidTransferEntity;
import net.yxiao233.ifeu.common.item.ConnectToolItem;
import net.yxiao233.ifeu.common.recipe.ShapedRecipe;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModFluids;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.registry.ModTags;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FluidTransferBlock extends IndustrialBlock<FluidTransferEntity> {
    public FluidTransferBlock() {
        super("fluid_transfer", BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion(), FluidTransferEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {
        if(player.getMainHandItem().getItem() instanceof ConnectToolItem){
            return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        }
        return super.useItemOn(stack, state, worldIn, pos, player, hand, ray);
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
    public void registerRecipe(RecipeOutput consume) {
        ShapedRecipe.createRecipe(consume,"fluid_transfer",new ShapedRecipe(
                List.of(
                        Ingredient.of(IndustrialTags.Items.PLASTIC),
                        Ingredient.of(Tags.Items.GLASS_PANES),
                        Ingredient.of(IndustrialTags.Items.PLASTIC),
                        Ingredient.of(ModTags.Items.GEARS_NETHERITE),
                        Ingredient.of(ModTags.Items.MACHINE_FRAME_ULTIMATE),
                        Ingredient.of(ModTags.Items.GEARS_NETHERITE),
                        Ingredient.of(ModuleCore.RANGE_ADDONS[3].get().getDefaultInstance()),
                        Ingredient.of(Items.BUCKET.getDefaultInstance()),
                        Ingredient.of(ModuleCore.RANGE_ADDONS[3].get().getDefaultInstance())

                ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_SWORD.get().asItem().getDefaultInstance()
        ));
    }
}