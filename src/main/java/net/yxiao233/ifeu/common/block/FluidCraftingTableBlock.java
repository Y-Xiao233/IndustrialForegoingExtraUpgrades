package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FluidCraftingTableBlock extends IndustrialBlock<FluidCraftingTableEntity> {
    public FluidCraftingTableBlock() {
        super("fluid_crafting_table", Properties.copy(Blocks.IRON_BLOCK), FluidCraftingTableEntity.class, ModItems.TAB_ADDONS);
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
        super.registerRecipe(consumer);
    }
}
