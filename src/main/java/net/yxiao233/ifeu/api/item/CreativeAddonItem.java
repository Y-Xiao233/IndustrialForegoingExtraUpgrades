package net.yxiao233.ifeu.api.item;

import com.hrznstudio.titanium.item.BasicItem;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreativeAddonItem extends IFEUAddonItem{
    public CreativeAddonItem(TitaniumTab tab) {
        super("creative_addon", 1, tab, new Properties().stacksTo(16));
    }

    @Override
    protected IFEUAugmentTypes setAugmentType() {
        return IFEUAugmentTypes.CREATIVE;
    }

    @Override
    public void addTooltipDetails(@Nullable BasicItem.Key key, @NotNull ItemStack stack, @NotNull List<Component> tooltip, boolean advanced) {
        TooltipHelper.addTooltip(tooltip,"creative_addon_item_0", ChatFormatting.GREEN);
    }

    @Override
    public boolean hasTooltipDetails(@Nullable BasicItem.Key key) {
        return key == null;
    }
}
