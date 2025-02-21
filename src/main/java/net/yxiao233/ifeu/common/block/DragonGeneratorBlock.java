package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.DragonGeneratorEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.registry.ModTags;
import org.jetbrains.annotations.NotNull;

public class DragonGeneratorBlock extends IndustrialBlock<DragonGeneratorEntity> {
    public DragonGeneratorBlock() {
        super("dragon_generator", Properties.ofFullCopy(Blocks.IRON_BLOCK), DragonGeneratorEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return DragonGeneratorEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }


    @Override
    public void registerRecipe(RecipeOutput consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this)
                .pattern("ACA").pattern("DFE").pattern("GBG")
                .define('A', IndustrialTags.Items.PLASTIC)
                .define('B', Blocks.FURNACE)
                .define('C', ModItems.EFFICIENCY_ADDON_6.get())
                .define('D', ModItems.PROCESSING_ADDON_6.get())
                .define('E', ModItems.SPEED_ADDON_6.get())
                .define('F', IndustrialTags.Items.MACHINE_FRAME_SUPREME)
                .define('G', ModTags.Items.GEARS_NETHERITE)
                .save(consumer);
    }
}
