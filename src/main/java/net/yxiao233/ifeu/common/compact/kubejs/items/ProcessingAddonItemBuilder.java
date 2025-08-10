package net.yxiao233.ifeu.common.compact.kubejs.items;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yxiao233.ifeu.api.item.ModProcessingAddonItem;
import net.yxiao233.ifeu.common.registry.IFEUItems;

public class ProcessingAddonItemBuilder extends ItemBuilder {
    private int tier;
    private int formTier;
    public ProcessingAddonItemBuilder(ResourceLocation i) {
        super(i);
    }

    public ProcessingAddonItemBuilder setTier(int tier){
        this.tier = tier;
        this.formTier = tier;
        return this;
    }

    public ProcessingAddonItemBuilder setFormTier(int tier){
        this.formTier = tier;
        return this;
    }

    @Override
    public Item createObject() {
        int formTier = this.formTier == this.tier ? this.tier : this.formTier;
        return new ModProcessingAddonItem(formTier,tier, IFEUItems.TAB_ADDONS);
    }
}