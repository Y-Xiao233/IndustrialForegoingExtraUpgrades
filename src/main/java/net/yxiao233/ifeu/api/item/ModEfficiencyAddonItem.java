package net.yxiao233.ifeu.api.item;

import com.buuz135.industrial.item.addon.EfficiencyAddonItem;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.network.chat.Component;

public class ModEfficiencyAddonItem extends EfficiencyAddonItem {
    public int tier;
    public int formTier;
    public ModEfficiencyAddonItem(int tier, TitaniumTab group) {
        super(tier, group);
        this.tier = tier;
        this.formTier = tier;
    }

    public ModEfficiencyAddonItem(int formTier, int tier, TitaniumTab group) {
        super(tier, group);
        this.tier = tier;
        this.formTier = formTier;
    }

    @Override
    public String getDescriptionId() {
        String var10000 = Component.translatable("item.industrialforegoing.addon").getString();
        int tier = this.formTier== this.tier ? this.tier : this.formTier;
        return var10000 + Component.translatable("item.industrialforegoing.efficiency").getString() + "Tier " + tier + " ";
    }
}
