package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.WeatherControllerEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.registry.ModTags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class WeatherControllerBlock extends IndustrialBlock<WeatherControllerEntity> {
    public WeatherControllerBlock() {
        super("weather_controller", Properties.copy(Blocks.IRON_BLOCK), WeatherControllerEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return WeatherControllerEntity::new;
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
                .define('A', Items.END_CRYSTAL)
                .define('B',ModItems.PROCESSING_ADDON_6.get())
                .define('C',Items.TOTEM_OF_UNDYING)
                .define('D', ModTags.Items.MACHINE_FRAME_ULTIMATE)
                .save(consumer);
    }
}
