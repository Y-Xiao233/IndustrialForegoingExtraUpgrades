package net.yxiao233.ifeu.api.item;

import com.buuz135.industrial.item.addon.AddonItem;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.data.recipes.RecipeOutput;

public abstract class IFEUAddonItem extends AddonItem {
    private final IFEUAugmentTypes type;
    private int tier;
    public IFEUAddonItem(String name, int tier, TitaniumTab tab, Properties builder) {
        super(name, tab, builder);
        this.tier = tier;
        this.type = setAugmentType();
    }

    protected abstract IFEUAugmentTypes setAugmentType();
    public int getTier(){
        return tier;
    }
    public IFEUAugmentTypes getType(){
        return this.type;
    }

    @Override
    public void registerRecipe(RecipeOutput recipeOutput) {

    }
}
