package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.api.IRecipeProvider;
import com.hrznstudio.titanium.block.BasicBlock;
import com.hrznstudio.titanium.recipe.generator.TitaniumRecipeProvider;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapelessRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
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
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModTags;

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

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.HYDROPONIC_SIMULATION_PROCESSOR.get())
                .pattern("PCP").pattern("DRD").pattern("PGP")
                .define('P', IndustrialTags.Items.PLASTIC)
                .define('C', net.minecraft.world.item.Items.COMPARATOR)
                .define('D', net.minecraft.world.item.Items.OBSERVER)
                .define('R', net.minecraft.world.item.Items.REPEATER)
                .define('G', IndustrialTags.Items.GEAR_DIAMOND)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModContents.LASER_LENS_SCULK.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(Items.SCULK,4)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModContents.LASER_LENS_DRAGON.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(ModContents.DRAGON_STAR.get(),4)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(ModContents.LASER_LENS_SCULK.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(consumer,new ResourceLocation(modId,"laser_lens0_sculk"));

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(ModContents.LASER_LENS_DRAGON.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(consumer,new ResourceLocation(modId,"laser_lens0_dragon"));

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.NETHERITE_GEAR.get())
                .pattern(" A ").pattern("A A").pattern(" A ")
                .define('A',Tags.Items.INGOTS_NETHERITE)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.SCULK_GEAR.get())
                .pattern(" A ").pattern("ABA").pattern(" A ")
                .define('A',Items.SCULK)
                .define('B', ModTags.Items.GEARS_NETHERITE)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.WRENCH.get())
                .pattern(" AB").pattern(" CA").pattern("C  ")
                .define('A',Tags.Items.DYES_PINK)
                .define('B', IndustrialTags.Items.PLASTIC)
                .define('C',Tags.Items.RODS)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.CONFIGURATION_TOOL.get())
                .pattern("ABA").pattern("CDC").pattern("AEA")
                .define('A', Items.PAPER)
                .define('B',IndustrialTags.Items.PLASTIC)
                .define('C',ModTags.Items.DIAMOND)
                .define('D',Tags.Items.DYES_GREEN)
                .define('E',Tags.Items.DYES_PINK)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(Items.SCULK)
                .pattern("AA")
                .pattern("AA")
                .define('A',Items.ECHO_SHARD)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModBlocks.BLACK_HOLE_CAPACITOR_PITY.getLeft().get())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("DED")
                .define('A',IndustrialTags.Items.PLASTIC)
                .define('B',Items.ENDER_EYE)
                .define('C',Tags.Items.ENDER_PEARLS)
                .define('D',Tags.Items.STORAGE_BLOCKS_REDSTONE)
                .define('E',IndustrialTags.Items.MACHINE_FRAME_PITY)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModBlocks.BLACK_HOLE_CAPACITOR_SIMPLE.getLeft().get())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("DED")
                .define('A',IndustrialTags.Items.PLASTIC)
                .define('B',Items.ENDER_EYE)
                .define('C',Tags.Items.ENDER_PEARLS)
                .define('D',Tags.Items.STORAGE_BLOCKS_REDSTONE)
                .define('E',IndustrialTags.Items.MACHINE_FRAME_SIMPLE)
                .save(consumer);
    }
}
