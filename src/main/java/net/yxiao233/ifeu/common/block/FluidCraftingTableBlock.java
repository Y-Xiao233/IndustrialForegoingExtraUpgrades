package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.core.BlockPos;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModFluids;
import net.yxiao233.ifeu.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FluidCraftingTableBlock extends IndustrialBlock<FluidCraftingTableEntity> {
    public FluidCraftingTableBlock() {
        super("fluid_crafting_table", Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.GLASS).noOcclusion(), FluidCraftingTableEntity.class, ModItems.TAB_ADDONS);
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
    public void registerRecipe(Consumer<FinishedRecipe> consumer) {
        new DissolutionChamberRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"fluid_crafting_table"),
                new Ingredient.Value[]{
                        new Ingredient.TagValue(Tags.Items.GLASS),
                        new Ingredient.TagValue(IndustrialTags.Items.MACHINE_FRAME_ADVANCED),
                        new Ingredient.TagValue(Tags.Items.GLASS),
                        new Ingredient.TagValue(Tags.Items.GLASS),
                        new Ingredient.TagValue(Tags.Items.GLASS),
                        new Ingredient.TagValue(Tags.Items.GLASS),
                        new Ingredient.TagValue(IndustrialTags.Items.GEAR_DIAMOND),
                        new Ingredient.TagValue(Tags.Items.GLASS)
                },new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000),200,
                this.asBlock().asItem().getDefaultInstance(),FluidStack.EMPTY
        );
    }
}
