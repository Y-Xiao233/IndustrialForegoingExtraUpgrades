package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.DragonStarGeneratorEntity;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import net.yxiao233.ifeu.common.registry.IFEUTags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class DragonStarGeneratorBlock extends IndustrialBlock<DragonStarGeneratorEntity> {
    public DragonStarGeneratorBlock() {
        super("dragon_star_generator", Properties.copy(Blocks.IRON_BLOCK), DragonStarGeneratorEntity.class, IFEUItems.TAB_ADDONS);
    }
    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return DragonStarGeneratorEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public void registerRecipe(Consumer<FinishedRecipe> consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this)
                .pattern("ACA").pattern("DFE").pattern("GBG")
                .define('A', IndustrialTags.Items.PLASTIC)
                .define('B', Blocks.FURNACE)
                .define('C', IFEUItems.EFFICIENCY_ADDON_6.get())
                .define('D', IFEUItems.PROCESSING_ADDON_6.get())
                .define('E', IFEUItems.SPEED_ADDON_6.get())
                .define('F', IFEUTags.Items.MACHINE_FRAME_ULTIMATE)
                .define('G', IFEUTags.Items.GEARS_SCULK)
                .save(consumer);
    }
}
