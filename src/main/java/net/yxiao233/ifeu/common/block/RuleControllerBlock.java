package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.RuleControllerEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RuleControllerBlock extends IndustrialBlock<RuleControllerEntity> {
    public RuleControllerBlock() {
        super("rule_controller", Properties.copy(Blocks.IRON_BLOCK), RuleControllerEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return RuleControllerEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.NONE;
    }
}
