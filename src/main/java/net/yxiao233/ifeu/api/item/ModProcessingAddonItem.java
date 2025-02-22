package net.yxiao233.ifeu.api.item;

import com.buuz135.industrial.item.addon.ProcessingAddonItem;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.network.chat.Component;

public class ModProcessingAddonItem extends ProcessingAddonItem {
    public int tier;
    public int formTier;
    public ModProcessingAddonItem(int tier, TitaniumTab group) {
        super(tier, group);
        this.tier = tier;
        this.formTier = tier;
    }

    public ModProcessingAddonItem(int formTier, int tier, TitaniumTab group) {
        super(tier, group);
        this.tier = tier;
        this.formTier = formTier;
    }

    @Override
    public String getDescriptionId() {
        String var10000 = Component.translatable("item.industrialforegoing.addon").getString();
        int tier = this.formTier == this.tier ? this.tier : this.formTier;
        return var10000 + Component.translatable("item.industrialforegoing.processing").getString() + "Tier " + tier + " ";
    }
}
