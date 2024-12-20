package net.yxiao233.ifeu.common.compact.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.compact.jei.category.InfuserCategory;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.utils.JEIRegistryHelper;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private JEIRegistryHelper helper = new JEIRegistryHelper();
    private void registries(IGuiHelper guiHelper){
        helper.add(new InfuserCategory(guiHelper), ModBlocks.INFUSER.getKey());
    }
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registries(guiHelper);
        helper.getRecipeCategories().forEach(registration::addRecipeCategories);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        helper.addRecipes(manager,registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        helper.addRecipeCatalyst(registration);
    }
}
