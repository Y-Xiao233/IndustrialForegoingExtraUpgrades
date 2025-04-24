package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.yxiao233.ifeu.common.recipe.ShapedRecipe;
import net.yxiao233.ifeu.common.registry.ModContents;

import java.util.function.IntFunction;

public class ShapedRecipeBuilder extends IFEURecipeBuilder{
    public ShapedRecipeBuilder(ItemStack output) {
        super(output);
    }
    public ShapedRecipeBuilder(ItemStack output, String id){
        super(output,id);
    }

    @Override
    public void save() {
        Ingredient.Value[] allItems = this.getStructure().stream()
                .flatMap(str ->
                        str.chars()
                                .mapToObj(c -> (char) c)
                                .map(c ->{
                                    if(this.getDefineMap().containsKey(c)){
                                        return new Ingredient.ItemValue(this.getDefineMap().get(c));
                                    }else if(this.getDefineTagMap().containsKey(c)){
                                        return new Ingredient.TagValue(this.getDefineTagMap().get(c));
                                    }else{
                                        return new Ingredient.ItemValue(ModContents.AIR.get().getDefaultInstance());
                                    }
                                })
                )
                .toArray(Ingredient.Value[]::new);

        new ShapedRecipe(this.getLocation(),allItems,this.getInputFluid(),this.getOutput());
    }
}