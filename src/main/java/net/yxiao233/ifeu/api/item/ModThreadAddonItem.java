package net.yxiao233.ifeu.api.item;

import com.hrznstudio.titanium.item.BasicItem;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ModThreadAddonItem extends IFEUAddonItem {
    public ModThreadAddonItem(int tier, TitaniumTab tab) {
        super("thread_addon_", tier, tab, new Properties().stacksTo(16));
    }
    @Override
    public IFEUAugmentTypes setAugmentType() {
        return IFEUAugmentTypes.THREAD;
    }

    @Override
    public void addTooltipDetails(@Nullable BasicItem.Key key, ItemStack stack, List<Component> tooltip, boolean advanced) {
        TooltipHelper.addTooltip(tooltip,"thread_addon_item_0", ChatFormatting.GRAY);
        TooltipHelper.addTooltip(tooltip,"thread_addon_item_1",ChatFormatting.GREEN,new Object[]{getTier() * 4});
    }

    @Override
    public boolean hasTooltipDetails(@Nullable BasicItem.Key key) {
        return key == null;
    }
}
