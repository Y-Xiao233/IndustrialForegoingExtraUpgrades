package net.yxiao233.ifeu.api.item;

import com.hrznstudio.titanium.block.tile.MachineTile;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.item.BasicItem;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import net.yxiao233.ifeu.mixin.capability.EnergyStorageAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnergyAddonItem extends IFEUAddonItem{
    public EnergyAddonItem(int tier, TitaniumTab tab) {
        super("energy_addon_", tier, tab, new Properties().stacksTo(16));
    }

    @Override
    protected IFEUAugmentTypes setAugmentType() {
        return IFEUAugmentTypes.ENERGY;
    }

    @Override
    public void addTooltipDetails(@Nullable BasicItem.Key key, @NotNull ItemStack stack, @NotNull List<Component> tooltip, boolean advanced) {
        TooltipHelper.addTooltip(tooltip,"energy_addon_item_0",ChatFormatting.GREEN,new Object[]{Math.min((int) (Math.pow(10,(getTier() + 2) / 2D)),Integer.MAX_VALUE)});
    }

    @Override
    public boolean hasTooltipDetails(@Nullable BasicItem.Key key) {
        return key == null;
    }
}
