package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.common.Tags;
import net.yxiao233.ifeu.common.block.entity.FermenterEntity;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import net.yxiao233.ifeu.common.registry.IFEUTags;
import org.jetbrains.annotations.NotNull;

public class FermenterBlock extends IndustrialBlock<FermenterEntity> {
    public FermenterBlock() {
        super("fermenter", Properties.ofFullCopy(Blocks.IRON_BLOCK), FermenterEntity.class, IFEUItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return FermenterEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public void registerRecipe(@NotNull RecipeOutput output) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this)
                .pattern("PDP")
                .pattern("SMS")
                .pattern("ARA")
                .define('P', IndustrialTags.Items.PLASTIC)
                .define('D', IFEUTags.Items.GEARS_NETHERITE)
                .define('S', IFEUTags.Items.ROTTEN_CROPS)
                .define('A', ModuleCore.PINK_SLIME_INGOT.get())
                .define('M', IndustrialTags.Items.MACHINE_FRAME_PITY)
                .define('R', Tags.Items.CROPS)
                .save(output);
    }
}
