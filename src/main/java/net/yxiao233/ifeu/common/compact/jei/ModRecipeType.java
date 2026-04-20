package net.yxiao233.ifeu.common.compact.jei;

import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.compact.jei.category.FermenterCategory;
import net.yxiao233.ifeu.common.compact.jei.category.SaucepanCategory;
import net.yxiao233.ifeu.common.recipe.*;

public class ModRecipeType {
    private static final String nameSpace = IndustrialForegoingExtraUpgrades.MODID;
    public static RecipeType<RecipeHolder<InfuserRecipe>> INFUSER = RecipeType.createRecipeHolderType(defaultLocation("infuser"));
    public static RecipeType<RecipeHolder<ArcaneDragonEggForgingRecipe>> ARCANE_DRAGON_EGG_FORGING = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(nameSpace,"arcane_dragon_egg_forging"));
    public static RecipeType<RecipeHolder<BlockRightClickRecipe>> BLOCK_RIGHT_CLICK = RecipeType.createRecipeHolderType(defaultLocation("block_right_click"));
    public static RecipeType<RecipeHolder<DragonStarGeneratorRecipe>> DRAGON_STAR_GENERATOR = RecipeType.createRecipeHolderType(defaultLocation("dragon_star_generator"));
    public static RecipeType<RecipeHolder<ShapedRecipe>> SHAPED = RecipeType.createRecipeHolderType(defaultLocation("shaped"));
    public static RecipeType<RecipeHolder<ShapelessRecipe>> SHAPELESS = RecipeType.createRecipeHolderType(defaultLocation("shapeless"));
    public static RecipeType<RecipeHolder<DragonGeneratorRecipe>> DRAGON_GENERATOR = RecipeType.createRecipeHolderType(defaultLocation("dragon_generator"));
    public static RecipeType<RecipeHolder<PrecisionShapedRecipe>> PRECISION_SHAPED = RecipeType.createRecipeHolderType(defaultLocation("precision_shaped"));
    public static RecipeType<RecipeHolder<PrecisionShapelessRecipe>> PRECISION_SHAPELESS = RecipeType.createRecipeHolderType(defaultLocation("precision_shapeless"));
    public static RecipeType<SaucepanCategory.SaucepanRecipeWrapper> SAUCEPAN = RecipeType.create(nameSpace,"saucepan",SaucepanCategory.SaucepanRecipeWrapper.class);
    public static RecipeType<FermenterCategory.FermenterRecipeWrapper> FERMENTER = RecipeType.create(nameSpace,"fermenter", FermenterCategory.FermenterRecipeWrapper.class);
    private static ResourceLocation defaultLocation(String path){
        return ResourceLocation.fromNamespaceAndPath(nameSpace,path);
    }
}
