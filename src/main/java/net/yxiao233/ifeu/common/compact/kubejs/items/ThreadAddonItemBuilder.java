package net.yxiao233.ifeu.common.compact.kubejs.items;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yxiao233.ifeu.api.item.ModThreadAddonItem;
import net.yxiao233.ifeu.common.registry.ModItems;

public class ThreadAddonItemBuilder extends ItemBuilder {
    private int tier;
    public ThreadAddonItemBuilder(ResourceLocation i) {
        super(i);
    }
    public ThreadAddonItemBuilder setTier(int tier){
        this.tier = tier;
        return this;
    }
    @Override
    public Item createObject() {
        return new ModThreadAddonItem(this.tier, ModItems.TAB_ADDONS);
    }
}
