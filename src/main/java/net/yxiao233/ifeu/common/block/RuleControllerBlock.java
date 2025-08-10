package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.RuleControllerEntity;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import net.yxiao233.ifeu.common.registry.IFEUTags;
import org.jetbrains.annotations.NotNull;

public class RuleControllerBlock extends IndustrialBlock<RuleControllerEntity> {
    public RuleControllerBlock() {
        super("rule_controller", Properties.ofFullCopy(Blocks.IRON_BLOCK), RuleControllerEntity.class, IFEUItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return RuleControllerEntity::new;
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public void registerRecipe(RecipeOutput consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this)
                .pattern("ABA").pattern("CDC").pattern("ABA")
                .define('A',Items.END_CRYSTAL)
                .define('B', IFEUItems.SPEED_ADDON_6.get())
                .define('C',Items.TOTEM_OF_UNDYING)
                .define('D', IFEUTags.Items.MACHINE_FRAME_ULTIMATE)
                .save(consumer);
    }
}
