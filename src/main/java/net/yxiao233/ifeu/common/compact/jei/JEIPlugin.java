package net.yxiao233.ifeu.common.compact.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.compact.jei.category.ArcaneDragonEggForgingCategory;
import net.yxiao233.ifeu.common.compact.jei.category.BlockRightClickCategory;
import net.yxiao233.ifeu.common.compact.jei.category.DragonStarGeneratorCategory;
import net.yxiao233.ifeu.common.compact.jei.category.InfuserCategory;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.BlockRightClickRecipe;
import net.yxiao233.ifeu.common.recipe.DragonStarGeneratorRecipe;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModTags;
import net.yxiao233.ifeu.common.utils.JEIRegistryHelper;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private JEIRegistryHelper helper = new JEIRegistryHelper();
    private void registries(IGuiHelper guiHelper){
        helper.add(new InfuserCategory(guiHelper), ModBlocks.INFUSER.getLeft());
        helper.add(new ArcaneDragonEggForgingCategory(guiHelper), ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft());
        helper.add(new BlockRightClickCategory(guiHelper), Blocks.DRAGON_EGG);
        helper.add(new DragonStarGeneratorCategory(guiHelper),ModBlocks.DRAGON_STAR_GENERATOR.getLeft());
    }
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"jei_plugin");
    }


    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registries(guiHelper);
        helper.getRecipeCategories().forEach(registration::addRecipeCategories);

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        addInfuserCompactRecipes(registration);
        addBlockRightClickRecipes(registration);
        addDragonStarGenerator(registration);

        helper.addRecipes(manager,registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        helper.addRecipeCatalyst(registration);
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
        blockRightClickRecipes.add(new BlockRightClickRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"block_right_click"), ModContents.DEAD_DRAGON_EGG.get(),Items.NETHER_STAR.getDefaultInstance(),Blocks.DRAGON_EGG));
        registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK,blockRightClickRecipes);
    }
    private void addDragonStarGenerator(IRecipeRegistration registration){
        List<DragonStarGeneratorRecipe> dragonStarGeneratorCategories = new ArrayList<>();
        dragonStarGeneratorCategories.add(new DragonStarGeneratorRecipe(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,"dragon_star"),ModContents.DRAGON_STAR.get().getDefaultInstance(), DragonStarGeneratorConfig.maxProgress,DragonStarGeneratorConfig.powerPerTick));
        registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR,dragonStarGeneratorCategories);
    }
}
