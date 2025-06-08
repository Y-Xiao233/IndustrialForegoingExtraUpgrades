package net.yxiao233.ifeu.api.item;

import com.hrznstudio.titanium.item.BasicItem;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SilkTouchAddonItem extends IFEUAddonItem{
    public SilkTouchAddonItem(TitaniumTab tab) {
        super("silk_touch_addon", 1, tab, new Properties().stacksTo(16));
    }

    @Override
    protected IFEUAugmentTypes setAugmentType() {
        return IFEUAugmentTypes.SILK;
    }

    @Override
    public void addTooltipDetails(@Nullable BasicItem.Key key, ItemStack stack, List<Component> tooltip, boolean advanced) {
        TooltipHelper.addTooltip(tooltip,"silk_addon_item_0", ChatFormatting.GRAY);
    }

    @Override
    public boolean hasTooltipDetails(@Nullable BasicItem.Key key) {
        return key == null;
    }
}