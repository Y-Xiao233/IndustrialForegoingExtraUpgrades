package net.yxiao233.ifeu.common.compact.jei;

import com.hrznstudio.titanium.container.BasicAddonContainer;
import com.hrznstudio.titanium.util.RecipeUtil;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.compact.jei.category.*;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.*;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper =  registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new InfuserCategory(guiHelper));
        registration.addRecipeCategories(new ArcaneDragonEggForgingCategory(guiHelper));
        registration.addRecipeCategories(new BlockRightClickCategory(guiHelper));
        registration.addRecipeCategories(new DragonStarGeneratorCategory(guiHelper));
        registration.addRecipeCategories(new FluidCraftingTableCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addInfuserCompactRecipes(registration);
        addBlockRightClickRecipes(registration);
        addDragonStarGeneratorRecipe(registration);

        Level level = Minecraft.getInstance().level;
        registration.addRecipes(ModRecipeType.INFUSER, RecipeUtil.getRecipes(level,(RecipeType<InfuserRecipe>) ModRecipes.INFUSER_TYPE.get()));
        registration.addRecipes(ModRecipeType.ARCANE_DRAGON_EGG_FORGING, RecipeUtil.getRecipes(level,(RecipeType<ArcaneDragonEggForgingRecipe>) ModRecipes.ARCANE_DRAGON_EGG_FORGING_TYPE.get()));
        registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK, RecipeUtil.getRecipes(level,(RecipeType<BlockRightClickRecipe>) ModRecipes.BLOCK_RIGHT_CLICK_TYPE.get()));
        registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR, RecipeUtil.getRecipes(level,(RecipeType<DragonStarGeneratorRecipe>) ModRecipes.DRAGON_STAR_GENERATOR_TYPE.get()));
        registration.addRecipes(ModRecipeType.FLUID_CRAFTING_TABLE, RecipeUtil.getRecipes(level,(RecipeType<FluidCraftingTableRecipe>) ModRecipes.FLUID_CRAFTING_TABLE_TYPE.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlocks.INFUSER.getLeft().get().asItem().getDefaultInstance(),ModRecipeType.INFUSER);
        registration.addRecipeCatalyst(ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get().asItem().getDefaultInstance(),ModRecipeType.ARCANE_DRAGON_EGG_FORGING);
        registration.addRecipeCatalyst(Blocks.DRAGON_EGG.asItem().getDefaultInstance(),ModRecipeType.BLOCK_RIGHT_CLICK);
        registration.addRecipeCatalyst(ModBlocks.DRAGON_STAR_GENERATOR.getLeft().get().asItem().getDefaultInstance(),ModRecipeType.DRAGON_STAR_GENERATOR);
        registration.addRecipeCatalyst(ModBlocks.FLUID_CRAFTING_TABLE.getLeft().get().asItem().getDefaultInstance(),ModRecipeType.FLUID_CRAFTING_TABLE);
    }


    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.FLUID_CRAFTING_TABLE,4,9,16,36);
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
        blockRightClickRecipes.add(new BlockRightClickRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"block_right_click"), ModContents.DEAD_DRAGON_EGG.get(),ModContents.DRAGON_STAR.get().getDefaultInstance(),Blocks.DRAGON_EGG));
        registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK,blockRightClickRecipes);
    }
    private void addDragonStarGeneratorRecipe(IRecipeRegistration registration){
        List<DragonStarGeneratorRecipe> dragonStarGeneratorCategories = new ArrayList<>();
        dragonStarGeneratorCategories.add(new DragonStarGeneratorRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"dragon_star"),ModContents.DRAGON_STAR.get().getDefaultInstance(), DragonStarGeneratorConfig.maxProgress,DragonStarGeneratorConfig.powerPerTick));
        registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR,dragonStarGeneratorCategories);
    }
}
