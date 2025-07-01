package net.yxiao233.ifeu.api.item;

import com.hrznstudio.titanium.item.BasicItem;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModChanceAddonItem extends IFEUAddonItem{
    public boolean isCreative;
    public ModChanceAddonItem(int tier, TitaniumTab tab, boolean isCreative) {
        super("chance_addon_", tier, tab, new Properties().stacksTo(16));
        this.isCreative = isCreative;
    }
    @Override
    public IFEUAugmentTypes setAugmentType() {
        return IFEUAugmentTypes.CHANCE;
    }

    @Override
    public void addTooltipDetails(@Nullable BasicItem.Key key, ItemStack stack, List<Component> tooltip, boolean advanced) {
        TooltipHelper.addTooltip(tooltip,"chance_addon_item_0", ChatFormatting.GRAY);
        if(isCreative){
            tooltip.add(Component.translatable("tooltip.ifeu.chance_addon_item_1").withStyle(ChatFormatting.GREEN));
        }else{
            tooltip.add(Component.translatable("tooltip.ifeu.chance_addon_item_2",new Object[]{getTier() * 5}).append("%").withStyle(ChatFormatting.GREEN));
        }
    }

    @Override
    public boolean hasTooltipDetails(@Nullable BasicItem.Key key) {
        return key == null;
    }

    @Override
    public String getDescriptionId() {
        if(isCreative){
            return Component.translatable("item.ifeu.creative_chance_addon").getString();
        }
        return super.getDescriptionId();
    }
}
