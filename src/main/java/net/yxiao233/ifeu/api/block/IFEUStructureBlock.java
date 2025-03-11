package net.yxiao233.ifeu.api.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.yxiao233.ifeu.api.block.entity.IFEUStructureProcessingTile;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class IFEUStructureBlock<T extends IFEUStructureProcessingTile<T>> extends IndustrialBlock<T> {
    public IFEUStructureBlock(String name, Properties properties, Class<T> tileClass, TitaniumTab group) {
        super(name, properties, tileClass, group);
    }

    @NotNull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> tooltips, TooltipFlag flag) {
        Pair<List<MutableComponent>,ChatFormatting> pair = getMaterialList(stack,tooltips);
        List<MutableComponent> list = pair.getLeft();
        ChatFormatting style = pair.getRight();

        TooltipHelper.addTooltipWhileKeyDown(TooltipHelper.KeyType.SHIFT,tooltips, () ->{
            TooltipHelper.addTooltip(tooltips,"required_material",ChatFormatting.AQUA);
            for (MutableComponent component : list) {
                TooltipHelper.addTooltip(tooltips, component.withStyle(style));
            }
        });
    }

    public abstract Pair<List<MutableComponent>,ChatFormatting> getMaterialList(ItemStack stack, List<Component> tooltips);
}
