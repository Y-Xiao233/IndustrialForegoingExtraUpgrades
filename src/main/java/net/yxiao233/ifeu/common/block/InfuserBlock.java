package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.InfuserEntity;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class InfuserBlock extends IndustrialBlock<InfuserEntity> {
    public InfuserBlock() {
        super("infuser", Properties.copy(Blocks.IRON_BLOCK), InfuserEntity.class, IFEUItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return InfuserEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public void registerRecipe(Consumer<FinishedRecipe> consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this)
                .pattern("ABA").pattern("BDB").pattern("ABA")
                .define('A', Items.BUCKET)
                .define('B', IndustrialTags.Items.MACHINE_FRAME_PITY)
                .define('D',Items.DRAGON_EGG)
                .save(consumer);
    }
}
