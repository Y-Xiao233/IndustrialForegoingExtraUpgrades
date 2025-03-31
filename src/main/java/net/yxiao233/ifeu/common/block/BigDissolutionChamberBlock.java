package net.yxiao233.ifeu.common.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.api.block.IFEUStructureBlock;
import net.yxiao233.ifeu.common.block.entity.BigDissolutionChamberEntity;
import net.yxiao233.ifeu.common.config.machine.BigDissolutionChamberConfig;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class BigDissolutionChamberBlock extends IFEUStructureBlock<BigDissolutionChamberEntity> {
    public BigDissolutionChamberBlock() {
        super("structure", Properties.ofFullCopy(Blocks.IRON_BLOCK), BigDissolutionChamberEntity.class, ModItems.TAB_ADDONS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return BigDissolutionChamberEntity::new;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltips, TooltipFlag flag) {
        TooltipHelper.addTooltip(tooltips,stack,ChatFormatting.GRAY,1,new Object[]{BigDissolutionChamberConfig.maxThread});
        super.appendHoverText(stack, context, tooltips, flag);
    }

    @Override
    public Pair<List<MutableComponent>, ChatFormatting> getMaterialList(ItemStack stack, List<Component> tooltips) {
        return Pair.of(IFEUMultiBlockStructures.BIG_DISSOLUTION_CHAMBER.getStructure().getMaterialList(),ChatFormatting.GREEN);
    }
}
