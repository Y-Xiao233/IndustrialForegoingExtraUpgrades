package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.module.ModuleResourceProduction;
import com.buuz135.industrial.utils.IndustrialTags;
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
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.ModEfficiencyAddonItem;
import net.yxiao233.ifeu.api.item.ModProcessingAddonItem;
import net.yxiao233.ifeu.api.item.ModSpeedAddonItem;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import net.yxiao233.ifeu.common.registry.IFEUItems;
import net.yxiao233.ifeu.common.registry.IFEUTags;

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
            if(reg instanceof ModSpeedAddonItem speedAddonItem) {
                ((IRecipeProvider) speedAddonItem).registerRecipe(recipeOutput);
            }else if(reg instanceof ModEfficiencyAddonItem efficiencyAddonItem) {
                ((IRecipeProvider) efficiencyAddonItem).registerRecipe(recipeOutput);
            }else if(reg instanceof ModProcessingAddonItem processingAddonItem) {
                ((IRecipeProvider) processingAddonItem).registerRecipe(recipeOutput);
            }else if(reg instanceof IFEUAddonItem addonItem){
                ((IRecipeProvider) addonItem).registerRecipe(recipeOutput);
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


        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFEUContents.LASER_LENS_SCULK.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(Items.SCULK,4)
                .save(recipeOutput);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFEUContents.LASER_LENS_DRAGON.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(IFEUContents.DRAGON_STAR.get(),4)
                .save(recipeOutput);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(IFEUContents.LASER_LENS_SCULK.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(modId,"laser_lens0_sculk"));

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(IFEUContents.LASER_LENS_DRAGON.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(modId,"laser_lens0_dragon"));

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUContents.WRENCH.get())
                .pattern(" AB").pattern(" CA").pattern("C  ")
                .define('A',Tags.Items.DYES_PINK)
                .define('B', IndustrialTags.Items.PLASTIC)
                .define('C',Tags.Items.RODS)
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUContents.CONFIGURATION_TOOL.get())
                .pattern("ABA").pattern("CDC").pattern("AEA")
                .define('A', Items.PAPER)
                .define('B',IndustrialTags.Items.PLASTIC)
                .define('C', IFEUTags.Items.DIAMOND)
                .define('D',Tags.Items.DYES_GREEN)
                .define('E',Tags.Items.DYES_PINK)
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(Items.SCULK)
                .pattern("AA")
                .pattern("AA")
                .define('A',Items.ECHO_SHARD)
                .save(recipeOutput);

//        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUBlocks.BLACK_HOLE_CAPACITOR_PITY.getLeft().get())
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("DED")
//                .define('A',IndustrialTags.Items.PLASTIC)
//                .define('B',Items.ENDER_EYE)
//                .define('C',Tags.Items.ENDER_PEARLS)
//                .define('D',Tags.Items.STORAGE_BLOCKS_REDSTONE)
//                .define('E',IndustrialTags.Items.MACHINE_FRAME_PITY)
//                .save(consumer);
//
//        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUBlocks.BLACK_HOLE_CAPACITOR_SIMPLE.getLeft().get())
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("DED")
//                .define('A',IndustrialTags.Items.PLASTIC)
//                .define('B',Items.ENDER_EYE)
//                .define('C',Tags.Items.ENDER_PEARLS)
//                .define('D',Tags.Items.STORAGE_BLOCKS_REDSTONE)
//                .define('E',IndustrialTags.Items.MACHINE_FRAME_SIMPLE)
//                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUBlocks.PLATFORM_BUILDER.asItem())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EFG")
                .define('A', Tags.Items.DUSTS_REDSTONE)
                .define('B', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .define('C', ModuleResourceProduction.BLOCK_BREAKER.asItem())
                .define('D', IndustrialTags.Items.MACHINE_FRAME_ADVANCED)
                .define('E', IFEUItems.SPEED_ADDON_3.get())
                .define('F', IFEUItems.PROCESSING_ADDON_3.get())
                .define('G', IFEUItems.EFFICIENCY_ADDON_3.get())
                .save(recipeOutput);

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUBlocks.PRECISION_CRAFTING_TABLE.asItem())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("AAA")
                .define('A', Items.CRAFTING_TABLE)
                .define('B', IndustrialTags.Items.PLASTIC)
                .define('C', IndustrialTags.Items.MACHINE_FRAME_PITY)
                .save(recipeOutput);


        ModSerializableProvider.init(recipeOutput);
    }
}
