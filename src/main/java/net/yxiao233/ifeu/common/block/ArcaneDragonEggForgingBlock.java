package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.common.Tags;
import net.yxiao233.ifeu.common.block.entity.ArcaneDragonEggForgingEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

public class ArcaneDragonEggForgingBlock extends IndustrialBlock<ArcaneDragonEggForgingEntity> {
    public ArcaneDragonEggForgingBlock() {
        super("arcane_dragon_egg_forging", Properties.ofFullCopy(Blocks.IRON_BLOCK), ArcaneDragonEggForgingEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return ArcaneDragonEggForgingEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public void registerRecipe(RecipeOutput consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this)
                .pattern("ABA").pattern("BDB").pattern("ABA")
                .define('A', Tags.Items.CHESTS)
                .define('D', IndustrialTags.Items.MACHINE_FRAME_SUPREME)
                .define('B',Items.SCULK)
                .save(consumer);
    }
}
