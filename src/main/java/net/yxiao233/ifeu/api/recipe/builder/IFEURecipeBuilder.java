package net.yxiao233.ifeu.api.recipe.builder;

import com.buuz135.industrial.recipe.LaserDrillRarity;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.registry.ModContents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class IFEURecipeBuilder {
    private final ArrayList<String> structure = new ArrayList<>();
    private final HashMap<Character, ItemStack> defineMap = new HashMap<>();
    private final HashMap<Character, TagKey<Item>> defineTagMap = new HashMap<>();
    private Ingredient.Value[] inputs;
    private ItemStack input;
    private FluidStack inputFluid;
    private FluidStack[] inputFluids;
    private Block result;
    private ResourceLocation entity;
    private float breakChance;
    private Ingredient.Value inputBlock;
    private Ingredient catalyst;
    private LaserDrillRarity[] rarity;
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

    public IFEURecipeBuilder inputs(Ingredient.Value... inputs){
        this.inputs = inputs;
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

    public IFEURecipeBuilder resultBlock(Block result){
        this.result = result;
        return this;
    }

    public IFEURecipeBuilder entity(ResourceLocation entityLocation){
        this.entity = entityLocation;
        return this;
    }

    public IFEURecipeBuilder rarity(LaserDrillRarity... rarity){
        this.rarity = rarity;
        return this;
    }

    public IFEURecipeBuilder input(ItemStack stack){
        this.input = stack;
        return this;
    }

    public IFEURecipeBuilder inputBlock(Ingredient.Value value){
        this.inputBlock = value;
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

    public IFEURecipeBuilder define(char symbol, RegistryObject<Item> item){
        define(symbol,item.get().getDefaultInstance());
        return this;
    }
    public abstract void save();
    public void save(String id){
        this.id = id;
        this.save();
    }

    public void save(ResourceLocation location){
        this.id = location.getPath();
        this.nameSpace = location.getNamespace();
        this.save();
    }

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

    protected Ingredient.Value[] getInputs(){
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

    protected LaserDrillRarity[] getRarity(){
        return rarity;
    }

    protected ResourceLocation getLocation(){
        return new ResourceLocation(getNameSpace(),getId());
    }
    protected Ingredient.Value getInputBlock(){
        return this.inputBlock;
    }
    protected Block getResult(){
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