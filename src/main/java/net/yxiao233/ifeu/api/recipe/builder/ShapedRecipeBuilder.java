package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.yxiao233.ifeu.common.recipe.ShapedRecipe;
import net.yxiao233.ifeu.common.registry.IFEUContents;

import java.util.List;

public class ShapedRecipeBuilder extends IFEURecipeBuilder{
    public ShapedRecipeBuilder(ItemStack output) {
        super(output);
    }
    public ShapedRecipeBuilder(ItemStack output, String id){
        super(output,id);
    }

    @Override
    public void save(RecipeOutput recipeOutput) {
        List<Ingredient> allItems = this.getStructure().stream()
                .flatMap(str ->
                        str.chars()
                                .mapToObj(c -> (char) c)
                                .map(c ->{
                                    if(this.getDefineMap().containsKey(c)){
                                        return Ingredient.of(this.getDefineMap().get(c));
                                    }else if(this.getDefineTagMap().containsKey(c)){
                                        return Ingredient.of(this.getDefineTagMap().get(c));
                                    }else{
                                        return Ingredient.of(IFEUContents.AIR.get());
                                    }
                                })
                )
                .toList();

        ShapedRecipe.createRecipe(recipeOutput,this.getId(),new ShapedRecipe(
                allItems,this.getInputFluid(),this.getOutput()
        ));
    }
}
