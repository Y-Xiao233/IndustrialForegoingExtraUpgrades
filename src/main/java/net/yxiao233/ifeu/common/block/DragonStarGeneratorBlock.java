package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.DragonStarGeneratorEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.registry.ModTags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class DragonStarGeneratorBlock extends IndustrialBlock<DragonStarGeneratorEntity> {
    public DragonStarGeneratorBlock() {
        super("dragon_star_generator", Properties.copy(Blocks.IRON_BLOCK), DragonStarGeneratorEntity.class, ModItems.TAB_ADDONS);
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
                .pattern("ABA").pattern("CDC").pattern("ECE")
                .define('A', IndustrialTags.Items.PLASTIC)
                .define('B',Blocks.FURNACE)
                .define('C', Items.DRAGON_BREATH)
                .define('D',IndustrialTags.Items.MACHINE_FRAME_SUPREME)
                .define('E', ModTags.Items.GEARS_SCULK)
                .save(consumer);
    }
}
