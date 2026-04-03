package net.yxiao233.ifeu.common.compact.jei;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.plugin.jei.IndustrialRecipeTypes;
import com.hrznstudio.titanium.container.BasicAddonContainer;
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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.block.entity.FermenterEntity;
import net.yxiao233.ifeu.common.block.entity.SaucepanEntity;
import net.yxiao233.ifeu.common.compact.jei.category.*;
import net.yxiao233.ifeu.common.config.machine.DragonGeneratorConfig;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.recipe.*;
import net.yxiao233.ifeu.common.registry.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,"jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, Collections.singletonList(new ItemStack(IFEUContents.AIR.get())));
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
        registration.addRecipeCategories(new PrecisionShapedCategory(guiHelper));
        registration.addRecipeCategories(new PrecisionShapelessCategory(guiHelper));
        registration.addRecipeCategories(new SaucepanCategory(guiHelper));
        registration.addRecipeCategories(new FermenterCategory(guiHelper));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        Level level = Minecraft.getInstance().level;
        if(level == null){
            return;
        }


        addInfuserCompactRecipes(registration);
        addBlockRightClickRecipes(registration);
        addDragonStarGenerator(registration);
        addDragonGeneratorRecipe(registration);
        addSaucepanRecipe(registration);
        addFermenterRecipe(registration);

        {
            List<RecipeHolder<InfuserRecipe>> recipes = level.getRecipeManager().getAllRecipesFor((RecipeType<InfuserRecipe>) IFEURecipes.INFUSER_TYPE.get()).stream().toList();
            registration.addRecipes(ModRecipeType.INFUSER, recipes);
        }
        {
            List<RecipeHolder<ArcaneDragonEggForgingRecipe>> recipes = level.getRecipeManager().getAllRecipesFor((RecipeType<ArcaneDragonEggForgingRecipe>) IFEURecipes.ARCANE_DRAGON_EGG_FORGING_TYPE.get()).stream().toList();
            registration.addRecipes(ModRecipeType.ARCANE_DRAGON_EGG_FORGING, recipes);
        }
        {
            List<RecipeHolder<BlockRightClickRecipe>> recipes = level.getRecipeManager().getAllRecipesFor((RecipeType<BlockRightClickRecipe>) IFEURecipes.BLOCK_RIGHT_CLICK_TYPE.get()).stream().toList();
            registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK, recipes);
        }
        {
            List<RecipeHolder<DragonStarGeneratorRecipe>> recipes = level.getRecipeManager().getAllRecipesFor((RecipeType<DragonStarGeneratorRecipe>) IFEURecipes.DRAGON_STAR_GENERATOR_TYPE.get()).stream().toList();
            registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR, recipes);
        }
        {
            List<RecipeHolder<ShapedRecipe>> recipes = level.getRecipeManager().getAllRecipesFor((RecipeType<ShapedRecipe>) IFEURecipes.SHAPED_TYPE.get()).stream().toList();
            registration.addRecipes(ModRecipeType.SHAPED, recipes);
        }
        {
            List<RecipeHolder<ShapelessRecipe>> recipes = level.getRecipeManager().getAllRecipesFor((RecipeType<ShapelessRecipe>) IFEURecipes.SHAPELESS_TYPE.get()).stream().toList();
            registration.addRecipes(ModRecipeType.SHAPELESS, recipes);
        }
        {
            List<RecipeHolder<PrecisionShapedRecipe>> recipes = level.getRecipeManager().getAllRecipesFor((RecipeType<PrecisionShapedRecipe>) IFEURecipes.PRECISION_SHAPED_TYPE.get()).stream().toList();
            registration.addRecipes(ModRecipeType.PRECISION_SHAPED, recipes);
        }
        {
            List<RecipeHolder<PrecisionShapelessRecipe>> recipes = level.getRecipeManager().getAllRecipesFor((RecipeType<PrecisionShapelessRecipe>) IFEURecipes.PRECISION_SHAPELESS_TYPE.get()).stream().toList();
            registration.addRecipes(ModRecipeType.PRECISION_SHAPELESS, recipes);
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(IFEUBlocks.INFUSER,ModRecipeType.INFUSER);
        registration.addRecipeCatalyst(IFEUBlocks.ARCANE_DRAGON_EGG_FORGING,ModRecipeType.ARCANE_DRAGON_EGG_FORGING);
        registration.addRecipeCatalyst(Blocks.DRAGON_EGG,ModRecipeType.BLOCK_RIGHT_CLICK);
        registration.addRecipeCatalyst(IFEUBlocks.DRAGON_STAR_GENERATOR,ModRecipeType.DRAGON_STAR_GENERATOR);
        registration.addRecipeCatalyst(IFEUBlocks.FLUID_CRAFTING_TABLE,ModRecipeType.SHAPED);
        registration.addRecipeCatalyst(IFEUBlocks.FLUID_CRAFTING_TABLE,ModRecipeType.SHAPELESS);
        registration.addRecipeCatalyst(IFEUBlocks.DRAGON_GENERATOR,ModRecipeType.DRAGON_GENERATOR);
        registration.addRecipeCatalyst(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE,IndustrialRecipeTypes.DISSOLUTION);
        registration.addRecipeCatalyst(IFEUBlocks.PRECISION_CRAFTING_TABLE.getBlock(),ModRecipeType.PRECISION_SHAPED);
        registration.addRecipeCatalyst(IFEUBlocks.PRECISION_CRAFTING_TABLE.getBlock(),ModRecipeType.PRECISION_SHAPELESS);
        registration.addRecipeCatalyst(IFEUBlocks.SAUCEPAN.asItem(),ModRecipeType.SAUCEPAN);
        registration.addRecipeCatalyst(IFEUBlocks.FERMENTER.asItem(),ModRecipeType.FERMENTER);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.SHAPED,4,9,16,36);
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.SHAPELESS,4,9,16,36);
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null, IndustrialRecipeTypes.DISSOLUTION,4,8,17,36);
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.PRECISION_SHAPED,4,9,14,36);
        registration.addRecipeTransferHandler(BasicAddonContainer.class,null,ModRecipeType.PRECISION_SHAPELESS,4,9,14,36);
    }

    private void addInfuserCompactRecipes(IRecipeRegistration registration){
        List<RecipeHolder<InfuserRecipe>> infuserRecipes = new ArrayList<>();
        BuiltInRegistries.ITEM.stream().forEach(reg ->{
            if(reg instanceof MobBucketItem || reg.getDefaultInstance().is(Items.BUCKET)){
                return;
            }
            if(reg instanceof BucketItem bucketItem){
                FluidStack fluidStack = new FluidStack(bucketItem.content,1000);
                if(!fluidStack.isEmpty()){
                    infuserRecipes.add(new RecipeHolder<>(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,"infuser/" + BuiltInRegistries.ITEM.getKey(bucketItem).getNamespace() + "/" + BuiltInRegistries.ITEM.getKey(bucketItem).getPath()),new InfuserRecipe(Items.BUCKET.getDefaultInstance(),fluidStack,200,bucketItem.getDefaultInstance())));
                }
            }
        });
        registration.addRecipes(ModRecipeType.INFUSER,infuserRecipes);
    }
    private void addBlockRightClickRecipes(IRecipeRegistration registration){
        List<RecipeHolder<BlockRightClickRecipe>> blockRightClickRecipes = new ArrayList<>();
        blockRightClickRecipes.add(new RecipeHolder<>(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,"block_right_click/" + BuiltInRegistries.BLOCK.getKey(Blocks.DRAGON_EGG).getPath()),new BlockRightClickRecipe(IFEUContents.DRAGON_STAR.get().getDefaultInstance(), IFEUContents.DEAD_DRAGON_EGG.get(),Blocks.DRAGON_EGG)));
        registration.addRecipes(ModRecipeType.BLOCK_RIGHT_CLICK,blockRightClickRecipes);
    }
    private void addDragonStarGenerator(IRecipeRegistration registration){
        List<RecipeHolder<DragonStarGeneratorRecipe>> dragonStarGeneratorCategories = new ArrayList<>();
        dragonStarGeneratorCategories.add(new RecipeHolder<>(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,"dragon_star_generator/" + BuiltInRegistries.ITEM.getKey(IFEUContents.DRAGON_STAR.get()).getPath()),new DragonStarGeneratorRecipe(IFEUContents.DRAGON_STAR.get().getDefaultInstance(), DragonStarGeneratorConfig.maxProgress,DragonStarGeneratorConfig.powerPerTick)));
        registration.addRecipes(ModRecipeType.DRAGON_STAR_GENERATOR,dragonStarGeneratorCategories);
    }

    private void addDragonGeneratorRecipe(IRecipeRegistration registration){
        List<RecipeHolder<DragonGeneratorRecipe>> dragonGeneratorRecipes = new ArrayList<>();
        dragonGeneratorRecipes.add(new RecipeHolder<>(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,"dragon_generator/" + BuiltInRegistries.FLUID.getKey(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get()).getPath()),new DragonGeneratorRecipe(new FluidStack(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000), DragonGeneratorConfig.maxProgress,DragonGeneratorConfig.powerPerTick)));
        registration.addRecipes(ModRecipeType.DRAGON_GENERATOR,dragonGeneratorRecipes);
    }

    public static void addSaucepanRecipe(IRecipeRegistration registration) {
        List<SaucepanCategory.SaucepanRecipeWrapper> recipes = new ArrayList<>();
        List<TagKey<Item>> tags = List.copyOf(SaucepanEntity.VALID);

        tags.forEach(tag ->{
            recipes.add(new SaucepanCategory.SaucepanRecipeWrapper(tag,new FluidStack(ModuleCore.MEAT.getSourceFluid().get(),80)));
        });

        registration.addRecipes(ModRecipeType.SAUCEPAN,recipes);
    }

    public static void addFermenterRecipe(IRecipeRegistration registration) {
        List<FermenterCategory.FermenterRecipeWrapper> recipes = new ArrayList<>();
        List<TagKey<Item>> catalysts = List.of(FermenterEntity.CATALYST);

        recipes.add(new FermenterCategory.FermenterRecipeWrapper(Tags.Items.CROPS,catalysts.getFirst(),new FluidStack(ModuleCore.SLUDGE.getSourceFluid().get(),80)));
        recipes.add(new FermenterCategory.FermenterRecipeWrapper(IFEUTags.Items.ROTTEN_CROPS,catalysts.getFirst(),new FluidStack(ModuleCore.SLUDGE.getSourceFluid().get(),400)));

        registration.addRecipes(ModRecipeType.FERMENTER,recipes);
    }
}
