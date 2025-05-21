package net.yxiao233.ifeu.common.compact.jei;

import com.buuz135.industrial.plugin.jei.IndustrialRecipeTypes;
import com.hrznstudio.titanium.container.BasicAddonContainer;
import com.hrznstudio.titanium.util.RecipeUtil;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.compact.jei.category.*;
import net.yxiao233.ifeu.common.config.machine.DragonGeneratorConfig;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.*;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModFluids;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,"jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, Collections.singletonList(new ItemStack(ModContents.AIR.get())));
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
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addInfuserCompactRecipes(registration);
        addBlockRightClickRecipes(registration);
        addDragonStarGenerator(registration);
        addDragonGeneratorRecipe(registration);
        addStructureInfoRecipe(registration);

        Level level = Minecraft.getInstance().level;
        registration.addRecipes(ModRecipeType.INFUSER, RecipeUtil.getRecipes(level,(RecipeType<InfuserRecipe>) ModRecipes.INFUSER_TYPE.get()));
        registration.addRecipes(ModRecipeType.ARCANE_DRAGON_EGG_FORGING, RecipeUtil.getRecipes(level,(RecipeType<ArcaneDragonEggForgingRecipe>) ModRecipes.ARCANE_DRAGON_EGG_FORGING_TYPE.get()));
        registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK, RecipeUtil.getRecipes(level,(RecipeType<BlockRightClickRecipe>) ModRecipes.BLOCK_RIGHT_CLICK_TYPE.get()));
        registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR, RecipeUtil.getRecipes(level,(RecipeType<DragonStarGeneratorRecipe>) ModRecipes.DRAGON_STAR_GENERATOR_TYPE.get()));
        registration.addRecipes(ModRecipeType.SHAPED, RecipeUtil.getRecipes(level,(RecipeType<ShapedRecipe>) ModRecipes.SHAPED_TYPE.get()));
        registration.addRecipes(ModRecipeType.SHAPELESS, RecipeUtil.getRecipes(level,(RecipeType<ShapelessRecipe>) ModRecipes.SHAPELESS_TYPE.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlocks.INFUSER,ModRecipeType.INFUSER);
        registration.addRecipeCatalyst(ModBlocks.ARCANE_DRAGON_EGG_FORGING,ModRecipeType.ARCANE_DRAGON_EGG_FORGING);
        registration.addRecipeCatalyst(Blocks.DRAGON_EGG,ModRecipeType.BLOCK_RIGHT_CLICK);
        registration.addRecipeCatalyst(ModBlocks.DRAGON_STAR_GENERATOR,ModRecipeType.DRAGON_STAR_GENERATOR);
        registration.addRecipeCatalyst(ModBlocks.FLUID_CRAFTING_TABLE,ModRecipeType.SHAPED);
        registration.addRecipeCatalyst(ModBlocks.FLUID_CRAFTING_TABLE,ModRecipeType.SHAPELESS);
        registration.addRecipeCatalyst(ModBlocks.DRAGON_GENERATOR,ModRecipeType.DRAGON_GENERATOR);
        registration.addRecipeCatalyst(ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE,IndustrialRecipeTypes.DISSOLUTION);

        List<Item> items = new ArrayList<>();
        IFEUMultiBlockStructures.values.forEach((id, structure) ->{
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
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null, IndustrialRecipeTypes.DISSOLUTION,4,8,17,36);
    }

    private void addInfuserCompactRecipes(IRecipeRegistration registration){
        List<InfuserRecipe> infuserRecipes = new ArrayList<>();
        BuiltInRegistries.ITEM.stream().forEach(reg ->{
            if(reg instanceof MobBucketItem || reg.getDefaultInstance().is(Items.BUCKET)){
                return;
            }
            if(reg instanceof BucketItem bucketItem){
                FluidStack fluidStack = new FluidStack(bucketItem.content,1000);
                if(!fluidStack.isEmpty()){
                    infuserRecipes.add(new InfuserRecipe(Items.BUCKET.getDefaultInstance(),fluidStack,200,bucketItem.getDefaultInstance()));
                }
            }
        });
        registration.addRecipes(ModRecipeType.INFUSER,infuserRecipes);
    }
    private void addBlockRightClickRecipes(IRecipeRegistration registration){
        List<BlockRightClickRecipe> blockRightClickRecipes = new ArrayList<>();
        blockRightClickRecipes.add(new BlockRightClickRecipe(ModContents.DRAGON_STAR.get().getDefaultInstance(),ModContents.DEAD_DRAGON_EGG.get(),Blocks.DRAGON_EGG));
        registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK,blockRightClickRecipes);
    }
    private void addDragonStarGenerator(IRecipeRegistration registration){
        List<DragonStarGeneratorRecipe> dragonStarGeneratorCategories = new ArrayList<>();
        dragonStarGeneratorCategories.add(new DragonStarGeneratorRecipe(ModContents.DRAGON_STAR.get().getDefaultInstance(), DragonStarGeneratorConfig.maxProgress,DragonStarGeneratorConfig.powerPerTick));
        registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR,dragonStarGeneratorCategories);
    }

    private void addDragonGeneratorRecipe(IRecipeRegistration registration){
        List<DragonGeneratorRecipe> dragonGeneratorRecipes = new ArrayList<>();
        dragonGeneratorRecipes.add(new DragonGeneratorRecipe(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000), DragonGeneratorConfig.maxProgress,DragonGeneratorConfig.powerPerTick));
        registration.addRecipes(ModRecipeType.DRAGON_GENERATOR,dragonGeneratorRecipes);
    }

    private void addStructureInfoRecipe(IRecipeRegistration registration){
        List<StructureInfoRecipe> structureRecipes = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        IFEUMultiBlockStructures.values.forEach((id,structure) ->{
            if(!items.contains(structure.getMachine())){
                structureRecipes.add(new StructureInfoRecipe(structure));
            }
            items.add(structure.getMachine());
        });
        registration.addRecipes(ModRecipeType.STRUCTURE,structureRecipes);
    }
}
