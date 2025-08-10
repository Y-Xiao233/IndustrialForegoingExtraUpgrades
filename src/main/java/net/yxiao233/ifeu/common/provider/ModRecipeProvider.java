package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.module.ModuleResourceProduction;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.api.IRecipeProvider;
import com.hrznstudio.titanium.block.BasicBlock;
import com.hrznstudio.titanium.recipe.generator.TitaniumRecipeProvider;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapelessRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModRecipeProvider extends TitaniumRecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
    public static final String modId = IndustrialForegoingExtraUpgrades.MODID;

    @Override
    public void register(Consumer<FinishedRecipe> consumer) {
        //Item
        ForgeRegistries.ITEMS.getEntries().forEach((reg) -> {
            //Upgrades
            if (reg.getValue() instanceof ModSpeedAddonItem speedAddonItem) {
                ((IRecipeProvider) speedAddonItem).registerRecipe(consumer);
            }else if(reg.getValue() instanceof ModEfficiencyAddonItem efficiencyAddonItem) {
                ((IRecipeProvider) efficiencyAddonItem).registerRecipe(consumer);
            }else if(reg.getValue() instanceof ModProcessingAddonItem processingAddonItem) {
                ((IRecipeProvider) processingAddonItem).registerRecipe(consumer);
            }else if(reg.getValue() instanceof IFEUAddonItem addonItem){
                ((IRecipeProvider) addonItem).registerRecipe(consumer);
            }
        });


        //Block
        ForgeRegistries.BLOCKS.getEntries().forEach((reg) -> {
            if(reg.getValue() instanceof BasicBlock){
                String s = reg.getValue().toString();
                String nameSpace = s.substring(6,s.indexOf(':'));
                if(nameSpace.equals("ifeu")){
                    ((IRecipeProvider) reg.getValue()).registerRecipe(consumer);
                }
            }
        });

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUContents.HYDROPONIC_SIMULATION_PROCESSOR.get())
                .pattern("PCP").pattern("DRD").pattern("PGP")
                .define('P', IndustrialTags.Items.PLASTIC)
                .define('C', net.minecraft.world.item.Items.COMPARATOR)
                .define('D', net.minecraft.world.item.Items.OBSERVER)
                .define('R', net.minecraft.world.item.Items.REPEATER)
                .define('G', IndustrialTags.Items.GEAR_DIAMOND)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFEUContents.LASER_LENS_SCULK.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(Items.SCULK,4)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFEUContents.LASER_LENS_DRAGON.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(IFEUContents.DRAGON_STAR.get(),4)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(IFEUContents.LASER_LENS_SCULK.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(consumer,new ResourceLocation(modId,"laser_lens0_sculk"));

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(IFEUContents.LASER_LENS_DRAGON.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(consumer,new ResourceLocation(modId,"laser_lens0_dragon"));

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUContents.WRENCH.get())
                .pattern(" AB").pattern(" CA").pattern("C  ")
                .define('A',Tags.Items.DYES_PINK)
                .define('B', IndustrialTags.Items.PLASTIC)
                .define('C',Tags.Items.RODS)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUContents.CONFIGURATION_TOOL.get())
                .pattern("ABA").pattern("CDC").pattern("AEA")
                .define('A', Items.PAPER)
                .define('B',IndustrialTags.Items.PLASTIC)
                .define('C', IFEUTags.Items.DIAMOND)
                .define('D',Tags.Items.DYES_GREEN)
                .define('E',Tags.Items.DYES_PINK)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(Items.SCULK)
                .pattern("AA")
                .pattern("AA")
                .define('A',Items.ECHO_SHARD)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUBlocks.PLATFORM_BUILDER.getLeft().get())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EFG")
                .define('A', Tags.Items.DUSTS_REDSTONE)
                .define('B', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .define('C', ModuleResourceProduction.BLOCK_BREAKER.getLeft().get())
                .define('D', IndustrialTags.Items.MACHINE_FRAME_ADVANCED)
                .define('E', IFEUItems.SPEED_ADDON_3.get())
                .define('F', IFEUItems.PROCESSING_ADDON_3.get())
                .define('G', IFEUItems.EFFICIENCY_ADDON_3.get())
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(IFEUBlocks.PRECISION_CRAFTING_TABLE.getLeft().get())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("AAA")
                .define('A', Items.CRAFTING_TABLE)
                .define('B', IndustrialTags.Items.PLASTIC)
                .define('C', IndustrialTags.Items.MACHINE_FRAME_PITY)
                .save(consumer);
    }
}
