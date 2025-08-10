package net.yxiao233.ifeu.common.compact.kubejs.items;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yxiao233.ifeu.api.item.ModChanceAddonItem;
import net.yxiao233.ifeu.common.registry.IFEUItems;

public class ChanceAddonItemBuilder extends ItemBuilder {
    private int tier;
    public ChanceAddonItemBuilder(ResourceLocation i) {
        super(i);
    }
    public ChanceAddonItemBuilder setTier(int tier){
        this.tier = tier;
        return this;
    }
    @Override
    public Item createObject() {
        if(this.tier > 20){
            this.tier = 20;
        }
        return new ModChanceAddonItem(this.tier, IFEUItems.TAB_ADDONS,false);
    }
}
