package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.buuz135.industrial.recipe.LaserDrillFluidRecipe;
import com.buuz135.industrial.recipe.LaserDrillRarity;
import com.buuz135.industrial.utils.IndustrialTags;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.recipe.ArcaneDragonEggForgingRecipe;
import net.yxiao233.ifeu.common.recipe.ShapedRecipe;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;
import net.yxiao233.ifeu.common.recipe.ShapelessRecipe;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModFluids;
import net.yxiao233.ifeu.common.registry.ModTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModSerializableProvider{
    public static void init(RecipeOutput recipeOutput) {
        //dissolution chamber
        DissolutionChamberRecipe.createRecipe(recipeOutput,"liquid_dragon_breath", new DissolutionChamberRecipe(
                List.of(
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance())
                ),new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),500),200,
                Optional.of(new ItemStack(Items.GLASS_BOTTLE,8)), Optional.of(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),100))
        ));

        DissolutionChamberRecipe.createRecipe(recipeOutput,"laser_lens_sculk", new DissolutionChamberRecipe(
                List.of(
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance())
                ),new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 1000),200,
                Optional.of(ModContents.LASER_LENS_SCULK.get().getDefaultInstance()),Optional.empty()
        ));

        DissolutionChamberRecipe.createRecipe(recipeOutput,"laser_lens_dragon", new DissolutionChamberRecipe(
                List.of(
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance())
                ),new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 1000),200,
                Optional.of(ModContents.LASER_LENS_DRAGON.get().getDefaultInstance()),Optional.empty()
        ));

        DissolutionChamberRecipe.createRecipe(recipeOutput,"ultimate_machine_frame", new DissolutionChamberRecipe(
                List.of(
                        tagValue(IndustrialTags.Items.PLASTIC),
                        tagValue(IndustrialTags.Items.MACHINE_FRAME_SUPREME),
                        tagValue(IndustrialTags.Items.PLASTIC),
                        itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()),
                        itemValue(Items.DRAGON_EGG.getDefaultInstance()),
                        tagValue(ModTags.Items.GEARS_SCULK),
                        itemValue(Items.DRAGON_EGG.getDefaultInstance())
                ),new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),8000),200,
                Optional.of(ModContents.ULTIMATE_MACHINE_FRAME.get().asItem().getDefaultInstance()),Optional.of(new FluidStack(Fluids.WATER,8000))
        ));



        //infuser
        InfuserRecipe.createRecipe(recipeOutput,"dragon_star",new InfuserRecipe(
                Items.NETHER_STAR.getDefaultInstance(), new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(), 1000),200, ModContents.DRAGON_STAR.get().getDefaultInstance()
        ));

        InfuserRecipe.createRecipe(recipeOutput,"dirt_mud",new InfuserRecipe(
                Items.DIRT.getDefaultInstance(),new FluidStack(Fluids.WATER.getSource(),1000),200,Items.MUD.getDefaultInstance()
        ));

        InfuserRecipe.createRecipe(recipeOutput,"mud_clay",new InfuserRecipe(
                Items.MUD.getDefaultInstance(),new FluidStack(Fluids.WATER.getSource(),2000),500,Items.CLAY.getDefaultInstance()
        ));

        InfuserRecipe.createRecipe(recipeOutput,"cobblestone_blackstone",new InfuserRecipe(
                Items.COBBLESTONE.getDefaultInstance(),new FluidStack(Fluids.LAVA.getSource(),200),200,Items.BLACKSTONE.getDefaultInstance()
        ));


        //laser drill fluid
        LaserDrillFluidRecipe.createRecipe(recipeOutput,"liquid_sculk_matter","ifeu",new LaserDrillFluidRecipe(
                new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),10),
                Ingredient.of(ModContents.LASER_LENS_SCULK.get()),
                ResourceLocation.fromNamespaceAndPath("minecraft","warden"),
                new LaserDrillRarity(new LaserDrillRarity.BiomeRarity(new ArrayList<>(),new ArrayList<>()),new LaserDrillRarity.DimensionRarity(new ArrayList<>(),new ArrayList<>()),-64,256,8)
        ));

        LaserDrillFluidRecipe.createRecipe(recipeOutput,"liquid_dragon_breath","ifeu",new LaserDrillFluidRecipe(
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),10),
                Ingredient.of(ModContents.LASER_LENS_DRAGON.get()),
                ResourceLocation.fromNamespaceAndPath("minecraft","ender_dragon"),
                new LaserDrillRarity(new LaserDrillRarity.BiomeRarity(LaserDrillRarity.BiomeRarity.END,new ArrayList<>()),new LaserDrillRarity.DimensionRarity(new ArrayList<>(),new ArrayList<>()),-64,256,8)
        ));

        //arcane dragon egg forging
        ArcaneDragonEggForgingRecipe.createRecipe(recipeOutput,"dead_dragon_egg",new ArcaneDragonEggForgingRecipe(
                new ItemStack(Items.EGG,4),new FluidStack(ModuleCore.ESSENCE.getSourceFluid().get(),250),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),4000),
                200,Optional.of(ModContents.DEAD_DRAGON_EGG_ITEM.get().getDefaultInstance()),Optional.empty()
        ));

        ArcaneDragonEggForgingRecipe.createRecipe(recipeOutput,"egg_1",new ArcaneDragonEggForgingRecipe(
                Items.DRAGON_EGG.getDefaultInstance(),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),100),new FluidStack(Fluids.WATER,1000),
                200,Optional.of(new ItemStack(Items.EGG,16)),Optional.of(new FluidStack(ModuleCore.ESSENCE.getSourceFluid().get(), 100))
        ));

        ArcaneDragonEggForgingRecipe.createRecipe(recipeOutput,"egg_2",new ArcaneDragonEggForgingRecipe(
                ModContents.DEAD_DRAGON_EGG_ITEM.get().getDefaultInstance(),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),100),new FluidStack(Fluids.WATER,1000),
                200,Optional.of(new ItemStack(Items.EGG,16)),Optional.empty()
        ));

        //fluid crafting table
        //如果配方内某个格子为空,请使用ModContents.AIR.get().getDefaultInstance(),而不是ItemStack.EMPTY
        ShapedRecipe.createRecipe(recipeOutput,"dragon_star_block",new ShapedRecipe(
                List.of(
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance())

                ),new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),8000),
                ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance()
        ));

        ShapedRecipe.createRecipe(recipeOutput,"dragon_star_axe",new ShapedRecipe(
                List.of(
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_AXE.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())

                ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_AXE.get().asItem().getDefaultInstance()
        ));


        ShapedRecipe.createRecipe(recipeOutput,"dragon_star_hoe",new ShapedRecipe(
                List.of(
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_HOE.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())

                ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_HOE.get().asItem().getDefaultInstance()
        ));


        ShapedRecipe.createRecipe(recipeOutput,"dragon_star_pickaxe",new ShapedRecipe(
                List.of(
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_PICKAXE.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())

                ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_PICKAXE.get().asItem().getDefaultInstance()
        ));


        ShapedRecipe.createRecipe(recipeOutput,"dragon_star_shovel",new ShapedRecipe(
                List.of(
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_SHOVEL.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())

                ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_SHOVEL.get().asItem().getDefaultInstance()
        ));

        ShapedRecipe.createRecipe(recipeOutput,"dragon_star_sword",new ShapedRecipe(
                List.of(
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.NETHERITE_SWORD.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance()),
                        itemValue(Items.STICK.getDefaultInstance()),
                        itemValue(ModContents.AIR.get().getDefaultInstance())

                ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),
                ModContents.DRAGON_STAR_SWORD.get().asItem().getDefaultInstance()
        ));


        //shapeless
            //有多少输入写多少
        ShapelessRecipe.createRecipe(recipeOutput,"dragon_star_from_block",new ShapelessRecipe(
                List.of(
                        itemValue(ModContents.DRAGON_STAR_BLOCK.get().asItem().getDefaultInstance())
                ),
                new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),100),
                ModContents.DRAGON_STAR.get().getDefaultInstance().copyWithCount(9)
        ));
    }

    public static Ingredient tagValue(TagKey<Item> tagKey){
        return Ingredient.of(tagKey);
    }
    public static Ingredient itemValue(ItemStack itemStack){
        return Ingredient.of(itemStack);
    }
}
