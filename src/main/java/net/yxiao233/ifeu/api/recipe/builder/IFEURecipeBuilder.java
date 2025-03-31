package net.yxiao233.ifeu.api.recipe.builder;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yxiao233.ifeu.common.registry.ModContents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class IFEURecipeBuilder {
    private final ArrayList<String> structure = new ArrayList<>();
    private final HashMap<Character, ItemStack> defineMap = new HashMap<>();
    private final HashMap<Character, TagKey<Item>> defineTagMap = new HashMap<>();
    private FluidStack fluid;
    private String id;
    private int time;
    private final ItemStack output;
    public IFEURecipeBuilder(ItemStack output){
        this.output = output;
    }

    public IFEURecipeBuilder(ItemStack output, String id){
        this.output = output;
        this.id = id;
    }

    public IFEURecipeBuilder id(String id){
        this.id = id;
        return this;
    }

    public IFEURecipeBuilder fluid(FluidStack fluid){
        this.fluid = fluid;
        return this;
    }

    public IFEURecipeBuilder processingTime(int time){
        this.time = time;
        return this;
    }

    public IFEURecipeBuilder pattern(String s){
        structure.add(s);
        return this;
    }

    public IFEURecipeBuilder define(char symbol, ItemStack itemStack){
        this.defineMap.put(symbol,itemStack);
        return this;
    }

    public IFEURecipeBuilder define(char symbol, TagKey<Item> itemTag){
        this.defineTagMap.put(symbol,itemTag);
        return this;
    }

    public IFEURecipeBuilder define(char symbol, DeferredHolder<Item,Item> item){
        define(symbol,item.get().getDefaultInstance());
        return this;
    }
    public abstract void save(RecipeOutput recipeOutput);

    protected ArrayList<String> getStructure() {
        if(structure.isEmpty()){
            String[] s = new String[9];
            Arrays.fill(s," ");
            return new ArrayList<>(Arrays.asList(s));
        }
        return structure;
    }

    protected FluidStack getFluid() {
        return fluid;
    }

    protected int getTime() {
        return Math.max(time, 0);
    }

    protected HashMap<Character, ItemStack> getDefineMap() {
        if(defineMap.isEmpty()){
            this.defineMap.put(' ',ModContents.AIR.get().getDefaultInstance());
            return defineMap;
        }
        return defineMap;
    }

    protected HashMap<Character, TagKey<Item>> getDefineTagMap() {
        return defineTagMap;
    }

    protected String getId() {
        if(id == null || id.isEmpty()){
            return output.getDescriptionId().substring(output.getDescriptionId().lastIndexOf(".") + 1);
        }
        return id;
    }

    public ItemStack getOutput() {
        return output;
    }
}
