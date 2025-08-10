package net.yxiao233.ifeu.common.compact.jei;

import com.buuz135.industrial.plugin.jei.IndustrialRecipeTypes;
import com.hrznstudio.titanium.container.BasicAddonContainer;
import com.hrznstudio.titanium.util.RecipeUtil;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.compact.jei.category.*;
import net.yxiao233.ifeu.common.config.machine.DragonGeneratorConfig;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.*;
import net.yxiao233.ifeu.common.registry.*;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;
import net.yxiao233.ifeu.common.utils.JEIUtil;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        JEIUtil util = new JEIUtil(jeiRuntime);
        util.removeItemStack(IFEUContents.AIR);
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper =  registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new InfuserCategory(guiHelper));
        registration.addRecipeCategories(new ArcaneDragonEggForgingCategory(guiHelper));
        registration.addRecipeCategories(new BlockRightClickCategory(guiHelper));
        registration.addRecipeCategories(new DragonStarGeneratorCategory(guiHelper));
        registration.addRecipeCategories(new ShapedCategory(guiHelper));
        registration.addRecipeCategories(new ShapelessCategory(guiHelper));
        registration.addRecipeCategories(new DragonGeneratorCategory(guiHelper));
        registration.addRecipeCategories(new StructureInfoCategory(guiHelper));
        registration.addRecipeCategories(new PrecisionShapedCategory(guiHelper));
        registration.addRecipeCategories(new PrecisionShapelessCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addInfuserCompactRecipes(registration);
        addBlockRightClickRecipes(registration);
        addDragonStarGeneratorRecipe(registration);
        addDragonGeneratorRecipe(registration);
        addStructureInfoRecipe(registration);

        Level level = Minecraft.getInstance().level;
        registration.addRecipes(ModRecipeType.INFUSER, RecipeUtil.getRecipes(level,(RecipeType<InfuserRecipe>) IFEURecipes.INFUSER_TYPE.get()));
        registration.addRecipes(ModRecipeType.ARCANE_DRAGON_EGG_FORGING, RecipeUtil.getRecipes(level,(RecipeType<ArcaneDragonEggForgingRecipe>) IFEURecipes.ARCANE_DRAGON_EGG_FORGING_TYPE.get()));
        registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK, RecipeUtil.getRecipes(level,(RecipeType<BlockRightClickRecipe>) IFEURecipes.BLOCK_RIGHT_CLICK_TYPE.get()));
        registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR, RecipeUtil.getRecipes(level,(RecipeType<DragonStarGeneratorRecipe>) IFEURecipes.DRAGON_STAR_GENERATOR_TYPE.get()));
        registration.addRecipes(ModRecipeType.SHAPED, RecipeUtil.getRecipes(level,(RecipeType<ShapedRecipe>) IFEURecipes.SHAPED_TYPE.get()));
        registration.addRecipes(ModRecipeType.SHAPELESS, RecipeUtil.getRecipes(level,(RecipeType<ShapelessRecipe>) IFEURecipes.SHAPELESS_TYPE.get()));
        registration.addRecipes(ModRecipeType.PRECISION_SHAPED,RecipeUtil.getRecipes(level,(RecipeType<PrecisionShapedRecipe>) IFEURecipes.PRECISION_SHAPED_TYPE.get()));
        registration.addRecipes(ModRecipeType.PRECISION_SHAPELESS,RecipeUtil.getRecipes(level,(RecipeType<PrecisionShapelessRecipe>) IFEURecipes.PRECISION_SHAPELESS_TYPE.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(IFEUBlocks.INFUSER.getLeft().get(),ModRecipeType.INFUSER);
        registration.addRecipeCatalyst(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get(),ModRecipeType.ARCANE_DRAGON_EGG_FORGING);
        registration.addRecipeCatalyst(Blocks.DRAGON_EGG,ModRecipeType.BLOCK_RIGHT_CLICK);
        registration.addRecipeCatalyst(IFEUBlocks.DRAGON_STAR_GENERATOR.getLeft().get(),ModRecipeType.DRAGON_STAR_GENERATOR);
        registration.addRecipeCatalyst(IFEUBlocks.FLUID_CRAFTING_TABLE.getLeft().get(),ModRecipeType.SHAPED);
        registration.addRecipeCatalyst(IFEUBlocks.FLUID_CRAFTING_TABLE.getLeft().get(),ModRecipeType.SHAPELESS);
        registration.addRecipeCatalyst(IFEUBlocks.DRAGON_GENERATOR.getLeft().get(),ModRecipeType.DRAGON_GENERATOR);
        registration.addRecipeCatalyst(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get(),IndustrialRecipeTypes.DISSOLUTION);
        registration.addRecipeCatalyst(IFEUBlocks.PRECISION_CRAFTING_TABLE.getLeft().get(),ModRecipeType.PRECISION_SHAPED);
        registration.addRecipeCatalyst(IFEUBlocks.PRECISION_CRAFTING_TABLE.getLeft().get(),ModRecipeType.PRECISION_SHAPELESS);

        List<Item> items = new ArrayList<>();
        IFEUMultiBlockStructures.values.forEach((id,structure) ->{
            if(!items.contains(structure.getMachine())){
                registration.addRecipeCatalyst(structure.getMachine(),ModRecipeType.STRUCTURE);
            }
            items.add(structure.getMachine());
        });
    }


    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.SHAPED,4,9,16,36);
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.SHAPELESS,4,9,16,36);
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.PRECISION_SHAPED,4,9,14,36);
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.PRECISION_SHAPELESS,4,9,14,36);
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null, IndustrialRecipeTypes.DISSOLUTION,4,8,17,36);
    }

    private void addInfuserCompactRecipes(IRecipeRegistration registration){
        List<InfuserRecipe> infuserRecipes = new ArrayList<>();
        ForgeRegistries.ITEMS.getEntries().forEach(reg ->{
            if(reg.getValue() instanceof MobBucketItem || reg.getValue().getDefaultInstance().is(Items.BUCKET)){
                return;
            }
            if(reg.getValue() instanceof BucketItem bucketItem){
                FluidStack fluidStack = new FluidStack(bucketItem.getFluid(),1000);
                if(!fluidStack.isEmpty()){
                    String raw = bucketItem.getDescriptionId();
                    int firstPoint = raw.indexOf('.');
                    int lastPoint = raw.lastIndexOf('.');
                    String nameSpace = raw.substring(firstPoint+1,lastPoint);
                    String path = raw.substring(lastPoint+1,raw.length());

                    ResourceLocation resourceLocation = new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"infuser/"+nameSpace+"/fill_"+path);
                    infuserRecipes.add(new InfuserRecipe(resourceLocation,Items.BUCKET.getDefaultInstance(),fluidStack,200,bucketItem.getDefaultInstance()));
                }
            }
        });
        registration.addRecipes(ModRecipeType.INFUSER,infuserRecipes);
    }
    private void addBlockRightClickRecipes(IRecipeRegistration registration){
        List<BlockRightClickRecipe> blockRightClickRecipes = new ArrayList<>();
        blockRightClickRecipes.add(new BlockRightClickRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"block_right_click"), IFEUContents.DEAD_DRAGON_EGG.get(), IFEUContents.DRAGON_STAR.get().getDefaultInstance(),Blocks.DRAGON_EGG));
        registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK,blockRightClickRecipes);
    }
    private void addDragonStarGeneratorRecipe(IRecipeRegistration registration){
        List<DragonStarGeneratorRecipe> dragonStarGeneratorCategories = new ArrayList<>();
        dragonStarGeneratorCategories.add(new DragonStarGeneratorRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"dragon_star"), IFEUContents.DRAGON_STAR.get().getDefaultInstance(), DragonStarGeneratorConfig.maxProgress,DragonStarGeneratorConfig.powerPerTick));
        registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR,dragonStarGeneratorCategories);
    }

    private void addDragonGeneratorRecipe(IRecipeRegistration registration){
        List<DragonGeneratorRecipe> dragonGeneratorRecipes = new ArrayList<>();
        dragonGeneratorRecipes.add(new DragonGeneratorRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"liquid_dragon_breath"),new FluidStack(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000), DragonGeneratorConfig.maxProgress,DragonGeneratorConfig.powerPerTick));
        registration.addRecipes(ModRecipeType.DRAGON_GENERATOR,dragonGeneratorRecipes);
    }

    private void addStructureInfoRecipe(IRecipeRegistration registration){
        List<StructureInfoRecipe> structureRecipes = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        IFEUMultiBlockStructures.values.forEach((id,structure) ->{
            if(!items.contains(structure.getMachine())){
                structureRecipes.add(new StructureInfoRecipe(id,structure));
            }
            items.add(structure.getMachine());
        });
        registration.addRecipes(ModRecipeType.STRUCTURE,structureRecipes);
    }
}
