package net.yxiao233.ifeu.common.utils;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.yxiao233.ifeu.common.compact.jei.category.AbstractJEICategory;

import java.util.ArrayList;
import java.util.List;

public class JEIRegistryHelper {
    private ArrayList<IRecipeCategory<?>> recipeCategories = new ArrayList<>();
    private ArrayList<Item> recipeCatalysts = new ArrayList<>();
    public JEIRegistryHelper(){}
    public void add(IRecipeCategory<?> recipeCategory,Item item){
        this.recipeCategories.add(recipeCategory);
        this.recipeCatalysts.add(item);
    }
    public ArrayList<IRecipeCategory<?>> getRecipeCategories() {
        return recipeCategories;
    }
    public <T extends Recipe<C>, C extends Container> void addRecipes(RecipeManager manager, IRecipeRegistration registration){
        this.getRecipeCategories().forEach(abstractJEICategory -> {
            AbstractJEICategory<T> jeiCategory = (AbstractJEICategory<T>) abstractJEICategory;
            net.minecraft.world.item.crafting.RecipeType<T> typeInstance = jeiCategory.getTypeInstance();
            RecipeType<T> recipeType = jeiCategory.getRecipeType();

            addRecipes2(manager,registration,recipeType,typeInstance);
        });
    }
    public <T extends Recipe<?>> void addRecipeCatalyst(IRecipeCatalystRegistration registration){
        for (int i = 0; i < this.getRecipeCategories().size(); i++) {
            AbstractJEICategory<T> jeiCategory = (AbstractJEICategory<T>) this.getRecipeCategories().get(i);
            RecipeType<T> recipeType = jeiCategory.getRecipeType();
            ItemStack itemStack = this.recipeCatalysts.get(i).getDefaultInstance();
            registration.addRecipeCatalyst(itemStack,recipeType);
        }
    }
    private <C extends Container, T extends Recipe<C>> List<T> addRecipes0(RecipeManager recipeManager, net.minecraft.world.item.crafting.RecipeType<T> pRecipeType){
        return recipeManager.getAllRecipesFor(pRecipeType);
    }

    private <T> void addRecipes1(IRecipeRegistration registration, mezz.jei.api.recipe.RecipeType<T> var1, List<T> var2) {
        registration.addRecipes(var1,var2);
    }

    private <T extends Recipe<C>, C extends Container> void addRecipes2(RecipeManager manager, IRecipeRegistration registration, RecipeType<T> recipeType, net.minecraft.world.item.crafting.RecipeType<T> typeInstance) {
        addRecipes1(registration, recipeType, addRecipes0(manager, typeInstance));
    }
}
