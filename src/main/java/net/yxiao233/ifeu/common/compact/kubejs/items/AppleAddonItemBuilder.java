package net.yxiao233.ifeu.common.compact.kubejs.items;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yxiao233.ifeu.api.item.ModAppleAddonItem;
import net.yxiao233.ifeu.common.registry.ModItems;

public class AppleAddonItemBuilder extends ItemBuilder {
    private int tier;
    public AppleAddonItemBuilder(ResourceLocation i) {
        super(i);
    }
    public AppleAddonItemBuilder setTier(int tier){
        this.tier = tier;
        return this;
    }
    @Override
    public Item createObject() {
        return new ModAppleAddonItem(this.tier, ModItems.TAB_ADDONS);
    }
}
