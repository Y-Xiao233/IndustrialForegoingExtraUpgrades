package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;

public class ModRecipes implements IModule {
    public static RegistryObject<RecipeSerializer<?>> INFUSER_SERIALIZER;
    public static RegistryObject<RecipeType<?>> INFUSER_TYPE;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        INFUSER_SERIALIZER = helper.registerGeneric(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey(), "infuser", () -> new GenericSerializer<>(InfuserRecipe.class, INFUSER_TYPE));
        INFUSER_TYPE = helper.registerGeneric(ForgeRegistries.RECIPE_TYPES.getRegistryKey(), "infuser", () -> RecipeType.simple(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "infuser")));
    }
}
