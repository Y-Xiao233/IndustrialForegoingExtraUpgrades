package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.hrznstudio.titanium.util.TagUtil;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.common.Tags;
import net.yxiao233.ifeu.common.block.entity.SaucepanEntity;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import net.yxiao233.ifeu.common.registry.IFEUTags;
import org.jetbrains.annotations.NotNull;

public class SaucepanBlock extends IndustrialBlock<SaucepanEntity> {
    public SaucepanBlock() {
        super("saucepan", Properties.ofFullCopy(Blocks.IRON_BLOCK), SaucepanEntity.class, IFEUItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return SaucepanEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
         return RotationType.FOUR_WAY;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void registerRecipe(@NotNull RecipeOutput output) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this)
                .pattern("PDP")
                .pattern("SMS")
                .pattern("ARA")
                .define('P', IndustrialTags.Items.PLASTIC)
                .define('D', IFEUTags.Items.GEARS_NETHERITE)
                .define('S', Tags.Items.SLIMEBALLS)
                .define('A', Items.BRICK)
                .define('M', IndustrialTags.Items.MACHINE_FRAME_PITY)
                .define('R', ItemTags.MEAT)
                .save(output);
    }
}
