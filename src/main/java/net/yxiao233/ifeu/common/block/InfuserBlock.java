package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.module.ModuleCore;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.InfuserEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class InfuserBlock extends IndustrialBlock<InfuserEntity> {
    public InfuserBlock() {
        super("infuser", Properties.copy(Blocks.IRON_BLOCK), InfuserEntity.class, ModItems.TAB_ADDONS);
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
                .pattern("ABA").pattern("CDC").pattern("ABA")
                .define('A', Items.DRAGON_BREATH)
                .define('B', ModuleCore.ADVANCED.get())
                .define('C',ModuleCore.SUPREME.get())
                .define('D',Items.DRAGON_EGG)
                .save(consumer);
    }
}
