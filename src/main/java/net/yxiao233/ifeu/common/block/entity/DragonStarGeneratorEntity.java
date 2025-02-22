package net.yxiao233.ifeu.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.yxiao233.ifeu.api.block.entity.IFEUItemFuelGeneratorEntity;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.utils.SlotElementsUtil;
import org.jetbrains.annotations.NotNull;

public class DragonStarGeneratorEntity extends IFEUItemFuelGeneratorEntity<DragonStarGeneratorEntity> {
    public static final Item FUEL = ModContents.DRAGON_STAR.get();
    public DragonStarGeneratorEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.DRAGON_STAR_GENERATOR, blockPos, blockState);

    }

    @Override
    public boolean canIncrease() {
        return true;
    }

    @Override
    public SlotElementsUtil getSlotElements() {
        return new SlotElementsUtil()
                .setInputFilter((itemStack, integer) -> itemStack.is(FUEL));
    }

    @Override
    public Class<?> getGeneratorConfigClass() {
        return DragonStarGeneratorConfig.class;
    }

    @Override
    public ItemStack getConsumeFuel() {
        return FUEL.getDefaultInstance();
    }

    @NotNull
    @Override
    public DragonStarGeneratorEntity getSelf() {
        return this;
    }
}
