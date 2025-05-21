package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.module.ModuleTransportStorage;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.buuz135.industrial.recipe.FluidExtractorRecipe;
import com.buuz135.industrial.recipe.LaserDrillFluidRecipe;
import com.buuz135.industrial.recipe.LaserDrillRarity;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.IJSONGenerator;
import com.hrznstudio.titanium.recipe.generator.IJsonFile;
import com.hrznstudio.titanium.recipe.generator.TitaniumSerializableProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.recipe.builder.IFEURecipeBuilders;
import net.yxiao233.ifeu.api.recipe.builder.ShapedRecipeBuilder;
import net.yxiao233.ifeu.common.recipe.ArcaneDragonEggForgingRecipe;
import net.yxiao233.ifeu.common.recipe.ShapedRecipe;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;
import net.yxiao233.ifeu.common.recipe.ShapelessRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModFluids;
import net.yxiao233.ifeu.common.registry.ModTags;

import java.util.Map;

public class ModSerializableProvider extends TitaniumSerializableProvider {
    public ModSerializableProvider(DataGenerator generatorIn, String modid) {
        super(generatorIn, modid);
    }

    @Override
    public void add(Map<IJsonFile, IJSONGenerator> map) {
        //dissolution chamber
        IFEURecipeBuilders.dissolutionChamberRecipe(new ItemStack(Items.GLASS_BOTTLE,8),"liquid_dragon_breath")
                .inputs(
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance())
                )
                .inputFluid(new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),500))
                .processingTime(200)
                .outputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),100))
                .save();

        IFEURecipeBuilders.dissolutionChamberRecipe(ModContents.LASER_LENS_SCULK.get().getDefaultInstance())
                .inputs(
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance())
                )
                .inputFluid(new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 1000))
                .processingTime(200)
                .outputFluid(FluidStack.EMPTY)
                .save();

        IFEURecipeBuilders.dissolutionChamberRecipe(ModContents.LASER_LENS_DRAGON.get().getDefaultInstance())
                .inputs(
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance())
                )
                .inputFluid(new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 1000))
                .processingTime(200)
                .outputFluid(FluidStack.EMPTY)
                .save();

        IFEURecipeBuilders.dissolutionChamberRecipe(ModContents.ULTIMATE_MACHINE_FRAME.get().asItem().getDefaultInstance())
                .inputs(
                        tagValue(IndustrialTags.Items.PLASTIC),
                        tagValue(IndustrialTags.Items.MACHINE_FRAME_SUPREME),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()),
                        itemValue(Items.DRAGON_EGG.getDefaultInstance()),
                        tagValue(ModTags.Items.GEARS_SCULK),
                        itemValue(Items.DRAGON_EGG.getDefaultInstance())
                )
                .inputFluid(new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),8000))
                .processingTime(200)
                .outputFluid(new FluidStack(Fluids.WATER,8000))
                .save();

        IFEURecipeBuilders.dissolutionChamberRecipe(new ItemStack(ModContents.EMPTY_NETHER_STAR.get(),4))
                .inputs(
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(Items.NETHER_STAR.getDefaultInstance())
                )
                .inputFluid(new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 4000))
                .processingTime(200)
                .outputFluid(FluidStack.EMPTY)
                .save();

        IFEURecipeBuilders.dissolutionChamberRecipe(new ItemStack(ModContents.ROUGH_DRAGON_STAR.get(),2))
                .inputs(
                        itemValue(ModContents.EMPTY_NETHER_STAR.get().getDefaultInstance()),
                        itemValue(Items.WITHER_SKELETON_SKULL.getDefaultInstance()),
                        itemValue(ModContents.EMPTY_NETHER_STAR.get().getDefaultInstance()),
                        tagValue(Tags.Items.RODS_BLAZE),
                        tagValue(Tags.Items.RODS_BLAZE),
                        tagValue(Tags.Items.STORAGE_BLOCKS_DIAMOND),
                        tagValue(Tags.Items.RODS_BLAZE),
                        tagValue(Tags.Items.STORAGE_BLOCKS_DIAMOND)
                )
                .inputFluid(new FluidStack(ModFluids.DRAGON_STAR_ESSENCE.getSourceFluid().get(), 200))
                .processingTime(200)
                .outputFluid(FluidStack.EMPTY)
                .save();

        IFEURecipeBuilders.dissolutionChamberRecipe(new ItemStack(ModContents.APPLE_CORE.get(),8),"liquid_malic_acid")
                .inputs(
                        itemValue(Items.APPLE.getDefaultInstance()),
                        itemValue(Items.APPLE.getDefaultInstance()),
                        itemValue(Items.APPLE.getDefaultInstance()),
                        itemValue(Items.APPLE.getDefaultInstance()),
                        itemValue(Items.APPLE.getDefaultInstance()),
                        itemValue(Items.APPLE.getDefaultInstance()),
                        itemValue(Items.APPLE.getDefaultInstance()),
                        itemValue(Items.APPLE.getDefaultInstance())
                )
                .inputFluid(new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 100))
                .processingTime(200)
                .outputFluid(new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 10))
                .save();

        IFEURecipeBuilders.dissolutionChamberRecipe(Items.NETHER_STAR.getDefaultInstance())
                .inputs(
                        itemValue(ModContents.ROUGH_DRAGON_STAR.get().getDefaultInstance())
                )
                .inputFluid(new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 1000))
                .processingTime(200)
                .outputFluid(FluidStack.EMPTY)
                .save();


        //infuser
        IFEURecipeBuilders.infuserRecipe(ModContents.DRAGON_STAR.get().getDefaultInstance())
                .input(Items.NETHER_STAR.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(), 1000))
                .processingTime(200)
                .save();


        IFEURecipeBuilders.infuserRecipe(Items.MUD.getDefaultInstance(),"dirt_mud")
                .input(Items.DIRT.getDefaultInstance())
                .inputFluid(new FluidStack(Fluids.WATER.getSource(),1000))
                .processingTime(200)
                .save();

        IFEURecipeBuilders.infuserRecipe(Items.CLAY.getDefaultInstance(),"mud_clay")
                .input(Items.MUD.getDefaultInstance())
                .inputFluid(new FluidStack(Fluids.WATER.getSource(),2000))
                .processingTime(500)
                .save();

        IFEURecipeBuilders.infuserRecipe(Items.BLACKSTONE.getDefaultInstance(),"cobblestone_blackstone")
                .input(Items.COBBLESTONE.getDefaultInstance())
                .inputFluid(new FluidStack(Fluids.LAVA.getSource(),200))
                .processingTime(200)
                .save();

        IFEURecipeBuilders.infuserRecipe(Items.ECHO_SHARD.getDefaultInstance(),"sculk")
                .input(Items.AMETHYST_SHARD.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),200))
                .processingTime(200)
                .save();

        IFEURecipeBuilders.infuserRecipe(Items.CRYING_OBSIDIAN.getDefaultInstance())
                .input(Items.OBSIDIAN.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),2000))
                .processingTime(200)
                .save();

        IFEURecipeBuilders.infuserRecipe(Items.DRAGON_BREATH.getDefaultInstance())
                .input(Items.GLASS_BOTTLE.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),10))
                .processingTime(200)
                .save();


        //laser drill fluid
        IFEURecipeBuilders.laserDrillFluidRecipe(new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),10),"liquid_sculk_matter")
                .catalyst(Ingredient.of(ModContents.LASER_LENS_SCULK.get()))
                .entity(MC("warden"))
                .rarity(new LaserDrillRarity(new ResourceKey[0], new ResourceKey[0],-64,256,8))
                .save();
        

        IFEURecipeBuilders.laserDrillFluidRecipe(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),10),"liquid_dragon_breath")
                .catalyst(Ingredient.of(ModContents.LASER_LENS_DRAGON.get()))
                .entity(MC("ender_dragon"))
                .rarity(new LaserDrillRarity(LaserDrillRarity.END, new ResourceKey[0],-64,256,8))
                .save();

        //fluid extractor
        IFEURecipeBuilders.fluidExtractorRecipe(new FluidStack(ModFluids.DRAGON_STAR_ESSENCE.getSourceFluid().get(),2),"dragon_star_essence")
                .inputBlock(itemValue(new ItemStack(ModContents.DRAGON_STAR_BLOCK.get())))
                .resultBlock(Blocks.AIR)
                .breakChance(0.01f)
                .isDefault(false)
                .save();

        //arcane dragon egg forging
        IFEURecipeBuilders.arcaneDragonEggForgingRecipe(ModContents.DEAD_DRAGON_EGG_ITEM.get().getDefaultInstance(),"dead_dragon_egg")
                .input(new ItemStack(Items.EGG,4))
                .inputFluids(new FluidStack(ModuleCore.ESSENCE.getSourceFluid().get(),250),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),4000))
                .outputFluid(FluidStack.EMPTY)
                .processingTime(200)
                .save();

        IFEURecipeBuilders.arcaneDragonEggForgingRecipe(new ItemStack(Items.EGG,16),"egg_1")
                .input(Items.DRAGON_EGG.getDefaultInstance())
                .inputFluids(new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),100),new FluidStack(Fluids.WATER,1000))
                .outputFluid(new FluidStack(ModuleCore.ESSENCE.getSourceFluid().get(), 100))
                .processingTime(200)
                .save();

        IFEURecipeBuilders.arcaneDragonEggForgingRecipe(new ItemStack(Items.EGG,16),"egg_2")
                .input(ModContents.DEAD_DRAGON_EGG_ITEM.get().getDefaultInstance())
                .inputFluids(new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),100),new FluidStack(Fluids.WATER,1000))
                .outputFluid(FluidStack.EMPTY)
                .processingTime(200)
                .save();
        
        //shaped
            //如果配方内某个格子为空,请使用ModContents.AIR.get().getDefaultInstance(),而不是ItemStack.EMPTY
        IFEURecipeBuilders.shapedRecipe(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModContents.DRAGON_STAR.get().getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),8000))
                .save();


        IFEURecipeBuilders.shapedRecipe(ModContents.DRAGON_STAR_AXE.get().getDefaultInstance())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" C ")
                .define('A', ModContents.DRAGON_STAR.get().getDefaultInstance())
                .define('B', Items.NETHERITE_AXE.getDefaultInstance())
                .define('C', Items.STICK.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000))
                .save();


        IFEURecipeBuilders.shapedRecipe(ModContents.DRAGON_STAR_HOE.get().getDefaultInstance())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" C ")
                .define('A', ModContents.DRAGON_STAR.get().getDefaultInstance())
                .define('B', Items.NETHERITE_HOE.getDefaultInstance())
                .define('C', Items.STICK.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000))
                .save();


        IFEURecipeBuilders.shapedRecipe(ModContents.DRAGON_STAR_PICKAXE.get().getDefaultInstance())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" C ")
                .define('A', ModContents.DRAGON_STAR.get().getDefaultInstance())
                .define('B', Items.NETHERITE_PICKAXE.getDefaultInstance())
                .define('C', Items.STICK.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000))
                .save();

        IFEURecipeBuilders.shapedRecipe(ModContents.DRAGON_STAR_SHOVEL.get().getDefaultInstance())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" C ")
                .define('A', ModContents.DRAGON_STAR.get().getDefaultInstance())
                .define('B', Items.NETHERITE_SHOVEL.getDefaultInstance())
                .define('C', Items.STICK.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000))
                .save();


        IFEURecipeBuilders.shapedRecipe(ModContents.DRAGON_STAR_SWORD.get().getDefaultInstance())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" C ")
                .define('A', ModContents.DRAGON_STAR.get().getDefaultInstance())
                .define('B', Items.NETHERITE_SWORD.getDefaultInstance())
                .define('C', Items.STICK.getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000))
                .save();

        IFEURecipeBuilders.shapedRecipe(ModContents.CONNECT_TOOL.get().getDefaultInstance())
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', ModContents.DRAGON_STAR.get().getDefaultInstance())
                .define('B', Tags.Items.RODS)
                .inputFluid(new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000))
                .save();

        IFEURecipeBuilders.shapedRecipe(ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get().asItem().getDefaultInstance())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("AEA")
                .define('A', IndustrialTags.Items.PLASTIC)
                .define('B', ModuleTransportStorage.BLACK_HOLE_TANK_SIMPLE.getLeft().get().asItem().getDefaultInstance())
                .define('C', ModuleCore.DISSOLUTION_CHAMBER.getLeft().get().asItem().getDefaultInstance())
                .define('D', ModTags.Items.MACHINE_FRAME_ULTIMATE)
                .define('E', ModuleTransportStorage.BLACK_HOLE_UNIT_SIMPLE.getLeft().get().asItem().getDefaultInstance())
                .inputFluid(new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),1000))
                .save();

        //shapeless
            //有多少输入写多少
        IFEURecipeBuilders.shapelessRecipe(ModContents.DRAGON_STAR.get().getDefaultInstance().copyWithCount(9),"dragon_star_from_block")
                .inputs(itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()))
                .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),100))
                .save();

        DissolutionChamberRecipe.RECIPES.forEach(dissolutionChamberRecipe -> map.put(dissolutionChamberRecipe,dissolutionChamberRecipe));
        InfuserRecipe.RECIPES.forEach(infuserRecipe -> map.put(infuserRecipe,infuserRecipe));
        LaserDrillFluidRecipe.RECIPES.forEach(laserDrillFluidRecipe -> map.put(laserDrillFluidRecipe,laserDrillFluidRecipe));
        ArcaneDragonEggForgingRecipe.RECIPES.forEach(arcaneDragonEggForgingRecipe -> map.put(arcaneDragonEggForgingRecipe,arcaneDragonEggForgingRecipe));
        ShapedRecipe.RECIPES.forEach(fluidCraftingTableRecipe -> map.put(fluidCraftingTableRecipe,fluidCraftingTableRecipe));
        ShapelessRecipe.RECIPES.forEach(shapelessRecipe -> map.put(shapelessRecipe,shapelessRecipe));
        FluidExtractorRecipe.RECIPES.forEach(fluidExtractorRecipe -> {
            if(!fluidExtractorRecipe.getId().getNamespace().equals("industrialforegoing")){
                map.put(fluidExtractorRecipe,fluidExtractorRecipe);
            }
        });
    }

    public Ingredient.TagValue tagValue(TagKey<Item> tagKey){
        return new Ingredient.TagValue(tagKey);
    }
    public Ingredient.ItemValue itemValue(ItemStack itemStack){
        return new Ingredient.ItemValue(itemStack);
    }
    public ResourceLocation IFEU(String path){
        return new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,path);
    }
    public ResourceLocation MC(String path){
        return new ResourceLocation("minecraft",path);
    }
}
