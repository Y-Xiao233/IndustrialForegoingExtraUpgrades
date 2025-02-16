package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.recipe.serializer.CodecRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.recipe.*;

public class ModRecipes implements IModule {
    public static DeferredHolder<RecipeSerializer<?>,RecipeSerializer<?>> INFUSER_SERIALIZER;
    public static DeferredHolder<RecipeType<?>,RecipeType<?>> INFUSER_TYPE;
    public static DeferredHolder<RecipeSerializer<?>,RecipeSerializer<?>> ARCANE_DRAGON_EGG_FORGING_SERIALIZER;
    public static DeferredHolder<RecipeType<?>,RecipeType<?>> ARCANE_DRAGON_EGG_FORGING_TYPE;
    public static DeferredHolder<RecipeSerializer<?>,RecipeSerializer<?>> BLOCK_RIGHT_CLICK_SERIALIZER;
    public static DeferredHolder<RecipeType<?>,RecipeType<?>> BLOCK_RIGHT_CLICK_TYPE;
    public static DeferredHolder<RecipeSerializer<?>,RecipeSerializer<?>> DRAGON_STAR_GENERATOR_SERIALIZER;
    public static DeferredHolder<RecipeType<?>,RecipeType<?>> DRAGON_STAR_GENERATOR_TYPE;
    public static DeferredHolder<RecipeSerializer<?>,RecipeSerializer<?>> SHAPED_SERIALIZER;
    public static DeferredHolder<RecipeType<?>,RecipeType<?>> SHAPED_TYPE;
    public static DeferredHolder<RecipeSerializer<?>,RecipeSerializer<?>> SHAPELESS_SERIALIZER;
    public static DeferredHolder<RecipeType<?>,RecipeType<?>> SHAPELESS_TYPE;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        INFUSER_SERIALIZER = helper.registerGeneric(Registries.RECIPE_SERIALIZER, "infuser", () -> new CodecRecipeSerializer<>(InfuserRecipe.class, INFUSER_TYPE,InfuserRecipe.CODEC));
        INFUSER_TYPE = helper.registerGeneric(Registries.RECIPE_TYPE, "infuser", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "infuser")));


        ARCANE_DRAGON_EGG_FORGING_SERIALIZER = helper.registerGeneric(Registries.RECIPE_SERIALIZER, "arcane_dragon_egg_forging", () -> new CodecRecipeSerializer<>(ArcaneDragonEggForgingRecipe.class, ARCANE_DRAGON_EGG_FORGING_TYPE,ArcaneDragonEggForgingRecipe.CODEC));
        ARCANE_DRAGON_EGG_FORGING_TYPE = helper.registerGeneric(Registries.RECIPE_TYPE, "arcane_dragon_egg_forging", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "arcane_dragon_egg_forging")));

        BLOCK_RIGHT_CLICK_SERIALIZER = helper.registerGeneric(Registries.RECIPE_SERIALIZER, "block_right_click", () -> new CodecRecipeSerializer<>(BlockRightClickRecipe.class, BLOCK_RIGHT_CLICK_TYPE,BlockRightClickRecipe.CODEC));
        BLOCK_RIGHT_CLICK_TYPE = helper.registerGeneric(Registries.RECIPE_TYPE, "block_right_click", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "block_right_click")));

        DRAGON_STAR_GENERATOR_SERIALIZER = helper.registerGeneric(Registries.RECIPE_SERIALIZER, "dragon_star_generator", () -> new CodecRecipeSerializer<>(DragonStarGeneratorRecipe.class, DRAGON_STAR_GENERATOR_TYPE, DragonStarGeneratorRecipe.CODEC));
        DRAGON_STAR_GENERATOR_TYPE = helper.registerGeneric(Registries.RECIPE_TYPE, "dragon_star_generator", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "dragon_star_generator")));

        SHAPED_SERIALIZER = helper.registerGeneric(Registries.RECIPE_SERIALIZER, "shaped", () -> new CodecRecipeSerializer<>(ShapedRecipe.class, SHAPED_TYPE, ShapedRecipe.CODEC));
        SHAPED_TYPE = helper.registerGeneric(Registries.RECIPE_TYPE, "shaped", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "shaped")));

        SHAPELESS_SERIALIZER = helper.registerGeneric(Registries.RECIPE_SERIALIZER, "shapeless", () -> new CodecRecipeSerializer<>(ShapelessRecipe.class, SHAPELESS_TYPE,ShapelessRecipe.CODEC));
        SHAPELESS_TYPE = helper.registerGeneric(Registries.RECIPE_TYPE, "shapeless", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "shapeless")));
    }
}
