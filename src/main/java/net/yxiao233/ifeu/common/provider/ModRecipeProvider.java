package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.hrznstudio.titanium.api.IRecipeProvider;
import com.hrznstudio.titanium.block.BasicBlock;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapelessRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.item.ModEfficiencyAddonItem;
import net.yxiao233.ifeu.common.item.ModProcessingAddonItem;
import net.yxiao233.ifeu.common.item.ModSpeedAddonItem;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModRecipeProvider extends VanillaRecipeProvider {
    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput,completableFuture);
    }
    public static final String modId = IndustrialForegoingExtraUpgrades.MODID;

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        BuiltInRegistries.ITEM.stream().forEach(reg ->{
            //Upgrades
            if (reg instanceof ModSpeedAddonItem speedAddonItem) {
                ((IRecipeProvider) speedAddonItem).registerRecipe(recipeOutput);
            } else if (reg instanceof ModEfficiencyAddonItem efficiencyAddonItem) {
                ((IRecipeProvider) efficiencyAddonItem).registerRecipe(recipeOutput);
            } else if (reg instanceof ModProcessingAddonItem processingAddonItem) {
                ((IRecipeProvider) processingAddonItem).registerRecipe(recipeOutput);
            }
        });

        BuiltInRegistries.BLOCK.stream().forEach((reg) -> {
            if(reg instanceof BasicBlock){
                String s = reg.toString();
                String nameSpace = s.substring(6,s.indexOf(':'));
                if(nameSpace.equals("ifeu")){
                    ((IRecipeProvider) reg).registerRecipe(recipeOutput);
                }
            }
        });


        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModContents.LASER_LENS_SCULK.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(Items.SCULK,4)
                .save(recipeOutput);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModContents.LASER_LENS_DRAGON.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(ModContents.DRAGON_STAR.get(),4)
                .save(recipeOutput);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(ModContents.LASER_LENS_SCULK.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(modId,"laser_lens0_sculk"));

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(ModContents.LASER_LENS_DRAGON.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(modId,"laser_lens0_dragon"));

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.NETHERITE_GEAR.get())
                .pattern(" A ").pattern("A A").pattern(" A ")
                .define('A',Tags.Items.INGOTS_NETHERITE)
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.SCULK_GEAR.get())
                .pattern(" A ").pattern("ABA").pattern(" A ")
                .define('A',Items.SCULK)
                .define('B', ModTags.Items.GEARS_NETHERITE)
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_SWORD.get())
                .pattern(" A ").pattern(" B ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_SWORD)
                .define('C', Tags.Items.RODS)
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_PICKAXE)
                .define('C', Tags.Items.RODS)
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_AXE)
                .define('C', Tags.Items.RODS)
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_SHOVEL)
                .define('C', Tags.Items.RODS)
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_HOE)
                .define('C', Tags.Items.RODS)
                .save(recipeOutput);



        ModSerializableProvider.init(recipeOutput);
    }
}
