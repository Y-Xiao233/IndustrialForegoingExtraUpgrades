package net.yxiao233.ifeu.api.recipe.builder;

import com.buuz135.industrial.recipe.LaserDrillRarity;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.ModContents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class IFEURecipeBuilder {
    private final ArrayList<String> structure = new ArrayList<>();
    private final HashMap<Character, ItemStack> defineMap = new HashMap<>();
    private final HashMap<Character, TagKey<Item>> defineTagMap = new HashMap<>();
    private List<Ingredient> inputs;
    private ItemStack input;
    private FluidStack inputFluid;
    private FluidStack[] inputFluids;
    private BlockState result;
    private ResourceLocation entity;
    private float breakChance;
    private Ingredient inputBlock;
    private Ingredient catalyst;
    private List<LaserDrillRarity> rarity;
    private FluidStack outputFluid;
    private String id;
    private String nameSpace;
    private boolean isDefaultRecipe;
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

    public IFEURecipeBuilder inputFluid(FluidStack fluid){
        this.inputFluid = fluid;
        return this;
    }

    public IFEURecipeBuilder inputFluids(FluidStack... inputFluids){
        this.inputFluids = inputFluids;
        return this;
    }

    public IFEURecipeBuilder outputFluid(FluidStack fluid){
        this.outputFluid = fluid;
        return this;
    }

    public IFEURecipeBuilder inputs(Ingredient... inputs){
        this.inputs = List.of(inputs);
        return this;
    }

    public IFEURecipeBuilder catalyst(Ingredient catalyst){
        this.catalyst = catalyst;
        return this;
    }

    public IFEURecipeBuilder isDefault(boolean isDefault){
        this.isDefaultRecipe = isDefault;
        return this;
    }

    public IFEURecipeBuilder breakChance(float breakChance){
        this.breakChance = breakChance;
        return this;
    }

    public IFEURecipeBuilder resultBlockState(BlockState result){
        this.result = result;
        return this;
    }

    public IFEURecipeBuilder entity(ResourceLocation entityLocation){
        this.entity = entityLocation;
        return this;
    }

    public IFEURecipeBuilder rarity(LaserDrillRarity... rarity){
        this.rarity = List.of(rarity);
        return this;
    }

    public IFEURecipeBuilder input(ItemStack stack){
        this.input = stack;
        return this;
    }

    public IFEURecipeBuilder inputBlock(Ingredient input){
        this.inputBlock = input;
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
    public abstract void save(RecipeOutput output);

    protected ArrayList<String> getStructure() {
        if(structure.isEmpty()){
            String[] s = new String[9];
            Arrays.fill(s," ");
            return new ArrayList<>(Arrays.asList(s));
        }
        return structure;
    }

    protected FluidStack getInputFluid() {
        return inputFluid;
    }
    protected FluidStack getOutputFluid() {
        return outputFluid;
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

    protected String getNameSpace(){
        if(nameSpace == null || nameSpace.isEmpty()){
            return IndustrialForegoingExtraUpgrades.MODID;
        }
        return nameSpace;
    }

    protected List<Ingredient> getInputs(){
        return this.inputs;
    }
    protected ItemStack getInput(){
        return input;
    }

    protected ResourceLocation getEntity(){
        return this.entity;
    }

    protected Ingredient getCatalyst(){
        return catalyst;
    }

    protected List<LaserDrillRarity> getRarity(){
        return rarity;
    }

    protected ResourceLocation getLocation(){
        return ResourceLocation.fromNamespaceAndPath(getNameSpace(),getId());
    }
    protected Ingredient getInputBlock(){
        return this.inputBlock;
    }
    protected BlockState getResult(){
        return this.result;
    }
    protected float getBreakChance(){
        return this.breakChance;
    }

    protected boolean isDefault(){
        return this.isDefaultRecipe;
    }

    protected FluidStack[] getInputFluids(){
        return this.inputFluids;
    }

    public ItemStack getOutput() {
        return output;
    }
}