package net.yxiao233.ifeu.common.compact.kubejs.items;

import com.buuz135.industrial.item.addon.RangeAddonItem;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yxiao233.ifeu.common.registry.IFEUItems;

public class RangeAddonItemBuilder extends ItemBuilder {
    private int tier;
    public RangeAddonItemBuilder(ResourceLocation id) {
        super(id);
    }
    public RangeAddonItemBuilder setTier(int tier){
        this.tier = tier;
        return this;
    }

    @Override
    public Item createObject() {
        return new RangeAddonItem(tier, IFEUItems.TAB_ADDONS);
    }
}
