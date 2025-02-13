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
import net.yxiao233.ifeu.common.recipe.*;

public class ModRecipes implements IModule {
    public static RegistryObject<RecipeSerializer<?>> INFUSER_SERIALIZER;
    public static RegistryObject<RecipeType<?>> INFUSER_TYPE;
    public static RegistryObject<RecipeSerializer<?>> ARCANE_DRAGON_EGG_FORGING_SERIALIZER;
    public static RegistryObject<RecipeType<?>> ARCANE_DRAGON_EGG_FORGING_TYPE;
    public static RegistryObject<RecipeSerializer<?>> BLOCK_RIGHT_CLICK_SERIALIZER;
    public static RegistryObject<RecipeType<?>> BLOCK_RIGHT_CLICK_TYPE;
    public static RegistryObject<RecipeSerializer<?>> DRAGON_STAR_GENERATOR_SERIALIZER;
    public static RegistryObject<RecipeType<?>> DRAGON_STAR_GENERATOR_TYPE;
    public static RegistryObject<RecipeSerializer<?>> FLUID_CRAFTING_TABLE_SERIALIZER;
    public static RegistryObject<RecipeType<?>> FLUID_CRAFTING_TABLE_TYPE;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        INFUSER_SERIALIZER = helper.registerGeneric(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey(), "infuser", () -> new GenericSerializer<>(InfuserRecipe.class, INFUSER_TYPE));
        INFUSER_TYPE = helper.registerGeneric(ForgeRegistries.RECIPE_TYPES.getRegistryKey(), "infuser", () -> RecipeType.simple(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "infuser")));


        ARCANE_DRAGON_EGG_FORGING_SERIALIZER = helper.registerGeneric(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey(), "arcane_dragon_egg_forging", () -> new GenericSerializer<>(ArcaneDragonEggForgingRecipe.class, ARCANE_DRAGON_EGG_FORGING_TYPE));
        ARCANE_DRAGON_EGG_FORGING_TYPE = helper.registerGeneric(ForgeRegistries.RECIPE_TYPES.getRegistryKey(), "arcane_dragon_egg_forging", () -> RecipeType.simple(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "arcane_dragon_egg_forging")));

        BLOCK_RIGHT_CLICK_SERIALIZER = helper.registerGeneric(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey(), "block_right_click", () -> new GenericSerializer<>(BlockRightClickRecipe.class, BLOCK_RIGHT_CLICK_TYPE));
        BLOCK_RIGHT_CLICK_TYPE = helper.registerGeneric(ForgeRegistries.RECIPE_TYPES.getRegistryKey(), "block_right_click", () -> RecipeType.simple(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "block_right_click")));

        DRAGON_STAR_GENERATOR_SERIALIZER = helper.registerGeneric(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey(), "dragon_star_generator", () -> new GenericSerializer<>(DragonStarGeneratorRecipe.class, DRAGON_STAR_GENERATOR_TYPE));
        DRAGON_STAR_GENERATOR_TYPE = helper.registerGeneric(ForgeRegistries.RECIPE_TYPES.getRegistryKey(), "dragon_star_generator", () -> RecipeType.simple(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "dragon_star_generator")));

        FLUID_CRAFTING_TABLE_SERIALIZER = helper.registerGeneric(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey(), "fluid_crafting_table", () -> new GenericSerializer<>(FluidCraftingTableRecipe.class, FLUID_CRAFTING_TABLE_TYPE));
        FLUID_CRAFTING_TABLE_TYPE = helper.registerGeneric(ForgeRegistries.RECIPE_TYPES.getRegistryKey(), "fluid_crafting_table", () -> RecipeType.simple(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "fluid_crafting_table")));
    }
}
