package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.WeatherControllerEntity;
import net.yxiao233.ifeu.common.registry.ModItems;
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
        return RotationType.NONE;
    }

    @Override
    public void registerRecipe(Consumer<FinishedRecipe> consumer) {
        super.registerRecipe(consumer);
    }
}
