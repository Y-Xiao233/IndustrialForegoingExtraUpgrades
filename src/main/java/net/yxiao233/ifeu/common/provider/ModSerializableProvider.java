package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.module.ModuleTransportStorage;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
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
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
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
        new DissolutionChamberRecipe(IFEU("liquid_dragon_breath"),
                new Ingredient.Value[]{
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                },
                new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),500),200,
                new ItemStack(Items.GLASS_BOTTLE,8),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),100)
        );

        new DissolutionChamberRecipe(IFEU("laser_lens_sculk"),
                new Ingredient.Value[]{
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance())
                },new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 1000),200,
                ModContents.LASER_LENS_SCULK.get().getDefaultInstance(),FluidStack.EMPTY
        );

        new DissolutionChamberRecipe(IFEU("laser_lens_dragon"),
                new Ingredient.Value[]{
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance())
                },new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 1000),200,
                ModContents.LASER_LENS_DRAGON.get().getDefaultInstance(),FluidStack.EMPTY
        );

        new DissolutionChamberRecipe(IFEU("ultimate_machine_frame"),
                new Ingredient.Value[]{
                        tagValue(IndustrialTags.Items.PLASTIC),
                        tagValue(IndustrialTags.Items.MACHINE_FRAME_SUPREME),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()),
                        itemValue(Items.DRAGON_EGG.getDefaultInstance()),
                        tagValue(ModTags.Items.GEARS_SCULK),
                        itemValue(Items.DRAGON_EGG.getDefaultInstance())
                },new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),8000),200,
                ModContents.ULTIMATE_MACHINE_FRAME.get().asItem().getDefaultInstance(),new FluidStack(Fluids.WATER,8000)
        );


        //infuser
        new InfuserRecipe(IFEU("dragon_star"),
                Items.NETHER_STAR.getDefaultInstance(), new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(), 1000),200, ModContents.DRAGON_STAR.get().getDefaultInstance()
        );

        new InfuserRecipe(IFEU("dirt_mud"),
                Items.DIRT.getDefaultInstance(),new FluidStack(Fluids.WATER.getSource(),1000),200,Items.MUD.getDefaultInstance()
        );

        new InfuserRecipe(IFEU("mud_clay"),
                Items.MUD.getDefaultInstance(),new FluidStack(Fluids.WATER.getSource(),2000),500,Items.CLAY.getDefaultInstance()
        );

        new InfuserRecipe(IFEU("cobblestone_blackstone"),
                Items.COBBLESTONE.getDefaultInstance(),new FluidStack(Fluids.LAVA.getSource(),200),200,Items.BLACKSTONE.getDefaultInstance()
        );

        new InfuserRecipe(IFEU("sculk"),
                Items.AMETHYST_SHARD.getDefaultInstance(),new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),200),200,Items.ECHO_SHARD.getDefaultInstance()
        );


        //laser drill fluid
        new LaserDrillFluidRecipe("liquid_sculk_matter",
                new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),10).writeToNBT(new CompoundTag()),
                Ingredient.of(ModContents.LASER_LENS_SCULK.get()),
                MC("warden"),
                new LaserDrillRarity(new ResourceKey[0], new ResourceKey[0],-64,256,8)
        );
        new LaserDrillFluidRecipe("liquid_dragon_breath",
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),10).writeToNBT(new CompoundTag()),
                Ingredient.of(ModContents.LASER_LENS_DRAGON.get()),
                MC("ender_dragon"),
                new LaserDrillRarity(LaserDrillRarity.END, new ResourceKey[0],-64,256,8)
        );


        //arcane dragon egg forging
        new ArcaneDragonEggForgingRecipe(IFEU("dead_dragon_egg"),
                new ItemStack(Items.EGG,4),new FluidStack(ModuleCore.ESSENCE.getSourceFluid().get(),250),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),4000),
                200,ModContents.DEAD_DRAGON_EGG_ITEM.get().getDefaultInstance(),FluidStack.EMPTY
        );

        new ArcaneDragonEggForgingRecipe(IFEU("egg_1"),
                Items.DRAGON_EGG.getDefaultInstance(),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),100),new FluidStack(Fluids.WATER,1000),
                200,new ItemStack(Items.EGG,16),new FluidStack(ModuleCore.ESSENCE.getSourceFluid().get(), 100)
        );

        new ArcaneDragonEggForgingRecipe(IFEU("egg_2"),
                ModContents.DEAD_DRAGON_EGG_ITEM.get().getDefaultInstance(),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),100),new FluidStack(Fluids.WATER,1000),
                200,new ItemStack(Items.EGG,16),FluidStack.EMPTY
        );
        
        //shaped
            //如果配方内某个格子为空,请使用ModContents.AIR.get().getDefaultInstance(),而不是ItemStack.EMPTY
        new ShapedRecipe(IFEU("dragon_star_block"),
                new Ingredient.Value[]{
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance())
                },
                new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),8000),
                ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()
        );

        new ShapedRecipe(IFEU("dragon_star_axe"),
                new Ingredient.Value[]{
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_AXE.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())
                },
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_AXE.get().getDefaultInstance()
        );

        new ShapedRecipe(IFEU("dragon_star_hoe"),
                new Ingredient.Value[]{
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_HOE.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())
                },
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_HOE.get().getDefaultInstance()
        );


        new ShapedRecipe(IFEU("dragon_star_pickaxe"),
                new Ingredient.Value[]{
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_PICKAXE.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())
                },
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_PICKAXE.get().getDefaultInstance()
        );

        new ShapedRecipe(IFEU("dragon_star_shovel"),
                new Ingredient.Value[]{
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_SHOVEL.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())
                },
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_SHOVEL.get().getDefaultInstance()
        );


        new ShapedRecipe(IFEU("dragon_star_sword"),
                new Ingredient.Value[]{
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_SWORD.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())
                },
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_SWORD.get().getDefaultInstance()
        );

        new ShapedRecipe(IFEU("connect_tool"),
                new Ingredient.Value[]{
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        tagValue(Tags.Items.RODS),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        tagValue(Tags.Items.RODS),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())
                },
                new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),
                ModContents.CONNECT_TOOL.get().getDefaultInstance()
        );

        new ShapedRecipe(IFEU("big_dissolution_chamber_core"),
                new Ingredient.Value[]{
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(ModuleTransportStorage.BLACK_HOLE_TANK_SIMPLE.getLeft().get().asItem().getDefaultInstance()),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(ModuleCore.DISSOLUTION_CHAMBER.getLeft().get().asItem().getDefaultInstance()),
                        tagValue(ModTags.Items.MACHINE_FRAME_ULTIMATE),
                        itemValue(ModuleCore.DISSOLUTION_CHAMBER.getLeft().get().asItem().getDefaultInstance()),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(ModuleTransportStorage.BLACK_HOLE_UNIT_SIMPLE.getLeft().get().asItem().getDefaultInstance()),
                        tagValue(IndustrialTags.Items.PLASTIC)
                },
                new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),1000),
                ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get().asItem().getDefaultInstance()
        );

        new ShapedRecipe(IFEU("black_hole_capacitor_advanced"),
                new Ingredient.Value[]{
                        tagValue(IndustrialTags.Items.PLASTIC),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        tagValue(Tags.Items.ENDER_PEARLS),
                        itemValue(Items.ENDER_EYE.getDefaultInstance()),
                        tagValue(Tags.Items.ENDER_PEARLS),
                        tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                        tagValue(IndustrialTags.Items.MACHINE_FRAME_ADVANCED),
                        tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE)
                },
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModBlocks.BLACK_HOLE_CAPACITOR_ADVANCED.getLeft().get().asItem().getDefaultInstance()
        );

//        new ShapedRecipe(IFEU("black_hole_capacitor_supreme"),
//                new Ingredient.Value[]{
//                        tagValue(IndustrialTags.Items.PLASTIC),
//                        tagValue(IndustrialTags.Items.PLASTIC),
//                        tagValue(IndustrialTags.Items.PLASTIC),
//                        tagValue(Tags.Items.ENDER_PEARLS),
//                        itemValue(Items.ENDER_EYE.getDefaultInstance()),
//                        tagValue(Tags.Items.ENDER_PEARLS),
//                        tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
//                        tagValue(IndustrialTags.Items.MACHINE_FRAME_SUPREME),
//                        tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE)
//                },
//                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),2000),
//                ModBlocks.BLACK_HOLE_CAPACITOR_SUPREME.getLeft().get().asItem().getDefaultInstance()
//        );

        new ShapedRecipeBuilder(ModBlocks.BLACK_HOLE_CAPACITOR_SUPREME.getLeft().get().asItem().getDefaultInstance())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("DED")
                .define('A', IndustrialTags.Items.PLASTIC)
                .define('B', Tags.Items.ENDER_PEARLS)
                .define('C', Items.ENDER_EYE.getDefaultInstance())
                .define('D', Tags.Items.STORAGE_BLOCKS_REDSTONE)
                .define('E', IndustrialTags.Items.MACHINE_FRAME_SUPREME)
                .fluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),2000))
                .save();

        //shapeless
            //有多少输入写多少
        new ShapelessRecipe(IFEU("dragon_star_from_block"),
                new Ingredient.Value[]{
                        itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance())
                },
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),100),
                ModContents.DRAGON_STAR.get().getDefaultInstance().copyWithCount(9)
        );

        DissolutionChamberRecipe.RECIPES.forEach(dissolutionChamberRecipe -> map.put(dissolutionChamberRecipe,dissolutionChamberRecipe));
        InfuserRecipe.RECIPES.forEach(infuserRecipe -> map.put(infuserRecipe,infuserRecipe));
        LaserDrillFluidRecipe.RECIPES.forEach(laserDrillFluidRecipe -> map.put(laserDrillFluidRecipe,laserDrillFluidRecipe));
        ArcaneDragonEggForgingRecipe.RECIPES.forEach(arcaneDragonEggForgingRecipe -> map.put(arcaneDragonEggForgingRecipe,arcaneDragonEggForgingRecipe));
        ShapedRecipe.RECIPES.forEach(fluidCraftingTableRecipe -> map.put(fluidCraftingTableRecipe,fluidCraftingTableRecipe));
        ShapelessRecipe.RECIPES.forEach(shapelessRecipe -> map.put(shapelessRecipe,shapelessRecipe));
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
