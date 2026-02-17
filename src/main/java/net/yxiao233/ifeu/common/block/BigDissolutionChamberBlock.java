package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yxiao233.ifeu.api.block.EnumPropertyTileBlock;
import net.yxiao233.ifeu.common.block.entity.BigDissolutionChamberEntity;
import net.yxiao233.ifeu.common.config.machine.BigDissolutionChamberConfig;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import net.yxiao233.ifeu.common.state.BigDissolutionChamberStructure;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BigDissolutionChamberBlock extends EnumPropertyTileBlock<BigDissolutionChamberEntity, BigDissolutionChamberStructure> {
    public static final EnumProperty<BigDissolutionChamberStructure> structure = EnumProperty.create("big_dissolution_chamber_structure", BigDissolutionChamberStructure.class);
    public BigDissolutionChamberBlock() {
        super("structure", Properties.ofFullCopy(Blocks.IRON_BLOCK), BigDissolutionChamberEntity.class, IFEUItems.TAB_ADDONS);
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        if(state.getValue(structure) == BigDissolutionChamberStructure.M){
            return RenderShape.MODEL;
        }
        return RenderShape.INVISIBLE;
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<?> getTileEntityFactory() {
        return BigDissolutionChamberEntity::new;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltips, @NotNull TooltipFlag flag) {
        TooltipHelper.addTooltip(tooltips,stack,ChatFormatting.GRAY,1,new Object[]{BigDissolutionChamberConfig.maxThread});
        super.appendHoverText(stack, context, tooltips, flag);
    }

    @Override
    protected EnumProperty<BigDissolutionChamberStructure> setProperty() {
        return structure;
    }

    @Override
    protected BigDissolutionChamberStructure getDefault() {
        return BigDissolutionChamberStructure.CORE;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Block.box(0,0,0,15.99,15.99,15.99);
    }
}
