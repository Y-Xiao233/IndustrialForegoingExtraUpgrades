package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.buuz135.industrial.utils.IndustrialTags;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class FluidCraftingTableBlock extends IndustrialBlock<FluidCraftingTableEntity> {
    public FluidCraftingTableBlock() {
        super("fluid_crafting_table", Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.GLASS).noOcclusion(), FluidCraftingTableEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return FluidCraftingTableEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }


    @Override
    public void registerRecipe(RecipeOutput consumer) {
        DissolutionChamberRecipe.createRecipe(consumer,"fluid_crafting_table", new DissolutionChamberRecipe(
                List.of(
                        Ingredient.of(Tags.Items.GLASS_BLOCKS),
                        Ingredient.of(IndustrialTags.Items.MACHINE_FRAME_ADVANCED),
                        Ingredient.of(Tags.Items.GLASS_BLOCKS),
                        Ingredient.of(Tags.Items.GLASS_BLOCKS),
                        Ingredient.of(Tags.Items.GLASS_BLOCKS),
                        Ingredient.of(Tags.Items.GLASS_BLOCKS),
                        Ingredient.of(IndustrialTags.Items.GEAR_DIAMOND),
                        Ingredient.of(Tags.Items.GLASS_BLOCKS)
                ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(), 2000),200,
                Optional.of(this.asBlock().asItem().getDefaultInstance()),Optional.empty()
        ));
    }
}
