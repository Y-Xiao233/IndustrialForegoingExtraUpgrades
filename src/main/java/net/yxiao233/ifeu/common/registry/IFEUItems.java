package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.module.IModule;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.item.*;
import net.yxiao233.ifeu.api.recipe.builder.IFEURecipeBuilders;

import java.util.List;
import java.util.Optional;

public class IFEUItems implements IModule {
    public static TitaniumTab TAB_ADDONS = new TitaniumTab(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, "extra_upgrades"));
    public static DeferredHolder<Item,Item> SPEED_ADDON_3;
    public static DeferredHolder<Item,Item> SPEED_ADDON_4;
    public static DeferredHolder<Item,Item> SPEED_ADDON_5;
    public static DeferredHolder<Item,Item> SPEED_ADDON_6;
    public static DeferredHolder<Item,Item> EFFICIENCY_ADDON_3;
    public static DeferredHolder<Item,Item> EFFICIENCY_ADDON_4;
    public static DeferredHolder<Item,Item> EFFICIENCY_ADDON_5;
    public static DeferredHolder<Item,Item> EFFICIENCY_ADDON_6;
    public static DeferredHolder<Item,Item> PROCESSING_ADDON_3;
    public static DeferredHolder<Item,Item> PROCESSING_ADDON_4;
    public static DeferredHolder<Item,Item> PROCESSING_ADDON_5;
    public static DeferredHolder<Item,Item> PROCESSING_ADDON_6;
    public static DeferredHolder<Item,Item> THREAD_ADDON_1;
    public static DeferredHolder<Item,Item> THREAD_ADDON_2;
    public static DeferredHolder<Item,Item> THREAD_ADDON_3;
    public static DeferredHolder<Item,Item> THREAD_ADDON_4;
    public static DeferredHolder<Item,Item> THREAD_ADDON_5;
    public static DeferredHolder<Item,Item> THREAD_ADDON_6;
    public static DeferredHolder<Item,Item> APPLE_ADDON_1;
    public static DeferredHolder<Item,Item> APPLE_ADDON_2;
    public static DeferredHolder<Item,Item> APPLE_ADDON_3;
    public static DeferredHolder<Item,Item> APPLE_ADDON_4;
    public static DeferredHolder<Item,Item> APPLE_ADDON_5;
    public static DeferredHolder<Item,Item> APPLE_ADDON_6;
    public static DeferredHolder<Item,Item> SILK_ADDON;
    public static DeferredHolder<Item,Item> HEAL_ADDON_1;
    public static DeferredHolder<Item,Item> HEAL_ADDON_2;
    public static DeferredHolder<Item,Item> HEAL_ADDON_3;
    public static DeferredHolder<Item,Item> HEAL_ADDON_4;
    public static DeferredHolder<Item,Item> HEAL_ADDON_5;
    public static DeferredHolder<Item,Item> HEAL_ADDON_6;
    public static DeferredHolder<Item,Item> CHANCE_ADDON_1;
    public static DeferredHolder<Item,Item> CHANCE_ADDON_2;
    public static DeferredHolder<Item,Item> CHANCE_ADDON_3;
    public static DeferredHolder<Item,Item> CHANCE_ADDON_4;
    public static DeferredHolder<Item,Item> CHANCE_ADDON_5;
    public static DeferredHolder<Item,Item> CHANCE_ADDON_6;
    public static DeferredHolder<Item,Item> CHANCE_ADDON_CREATIVE;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        //Speed Addon
        SPEED_ADDON_3 = helper.registerGeneric(Registries.ITEM, "speed_addon_3", () -> new ModSpeedAddonItem(3, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"speed_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                itemValue(Items.SUGAR.getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance())
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        SPEED_ADDON_4 = helper.registerGeneric(Registries.ITEM, "speed_addon_4", () -> new ModSpeedAddonItem(4, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer, "speed_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                itemValue(Items.SUGAR.getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance())
                        ), new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(), 2000), 200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        SPEED_ADDON_5 = helper.registerGeneric(Registries.ITEM, "speed_addon_5", () -> new ModSpeedAddonItem(5, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"speed_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance())
                        ),new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        SPEED_ADDON_6 = helper.registerGeneric(Registries.ITEM, "speed_addon_6", () -> new ModSpeedAddonItem(6, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"speed_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        //Efficiency Addon
        EFFICIENCY_ADDON_3 = helper.registerGeneric(Registries.ITEM, "efficiency_addon_3", () -> new ModEfficiencyAddonItem(3, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"efficiency_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance())
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        EFFICIENCY_ADDON_4 = helper.registerGeneric(Registries.ITEM, "efficiency_addon_4", () -> new ModEfficiencyAddonItem(4, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"efficiency_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance())
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        EFFICIENCY_ADDON_5 = helper.registerGeneric(Registries.ITEM, "efficiency_addon_5", () -> new ModEfficiencyAddonItem(5, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"efficiency_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance())
                        ),new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        EFFICIENCY_ADDON_6 = helper.registerGeneric(Registries.ITEM, "efficiency_addon_6", () -> new ModEfficiencyAddonItem(6, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"efficiency_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });


        //Processing Addon
        PROCESSING_ADDON_3 = helper.registerGeneric(Registries.ITEM, "processing_addon_3", () -> new ModProcessingAddonItem(3, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"processing_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                itemValue(Items.FURNACE.getDefaultInstance()),
                                itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        PROCESSING_ADDON_4 = helper.registerGeneric(Registries.ITEM, "processing_addon_4", () -> new ModProcessingAddonItem(4, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"processing_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                itemValue(Items.FURNACE.getDefaultInstance()),
                                itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        PROCESSING_ADDON_5 = helper.registerGeneric(Registries.ITEM, "processing_addon_5", () -> new ModProcessingAddonItem(5, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"processing_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.FURNACE.getDefaultInstance()),
                                itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                        ),new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        PROCESSING_ADDON_6 = helper.registerGeneric(Registries.ITEM, "processing_addon_6", () -> new ModProcessingAddonItem(6, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"processing_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.FURNACE.getDefaultInstance()),
                                itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        THREAD_ADDON_1 = helper.registerGeneric(Registries.ITEM,"thread_addon_1", () -> new ModThreadAddonItem(1,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"thread_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        ),new FluidStack(ModuleCore.LATEX.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        THREAD_ADDON_2 = helper.registerGeneric(Registries.ITEM,"thread_addon_2", () -> new ModThreadAddonItem(2,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"thread_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        ),new FluidStack(ModuleCore.LATEX.getSourceFluid().get(),2000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        THREAD_ADDON_3 = helper.registerGeneric(Registries.ITEM,"thread_addon_3", () -> new ModThreadAddonItem(3,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"thread_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        THREAD_ADDON_4 = helper.registerGeneric(Registries.ITEM,"thread_addon_4", () -> new ModThreadAddonItem(4,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"thread_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        THREAD_ADDON_5 = helper.registerGeneric(Registries.ITEM,"thread_addon_5", () -> new ModThreadAddonItem(5,TAB_ADDONS){

            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"thread_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.NETHER_STARS),
                                tagValue(Tags.Items.NETHER_STARS),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        ),new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        THREAD_ADDON_6 = helper.registerGeneric(Registries.ITEM,"thread_addon_6", () -> new ModThreadAddonItem(6,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"thread_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        ),new FluidStack(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });


        APPLE_ADDON_1 = helper.registerGeneric(Registries.ITEM, "apple_addon_1", () -> new ModAppleAddonItem(1,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer, "apple_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                itemValue(IFEUContents.APPLE_CORE.get().getDefaultInstance()),
                                itemValue(IFEUContents.APPLE_CORE.get().getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 10), 200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        APPLE_ADDON_2 = helper.registerGeneric(Registries.ITEM, "apple_addon_2", () -> new ModAppleAddonItem(2,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer, "apple_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                itemValue(IFEUItems.APPLE_ADDON_1.get().getDefaultInstance()),
                                itemValue(IFEUContents.APPLE_CORE.get().getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 100), 200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        APPLE_ADDON_3 = helper.registerGeneric(Registries.ITEM, "apple_addon_3", () -> new ModAppleAddonItem(3,TAB_ADDONS){

            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer, "apple_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                itemValue(IFEUItems.APPLE_ADDON_2.get().getDefaultInstance()),
                                itemValue(IFEUContents.APPLE_CORE.get().getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 1000), 200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        APPLE_ADDON_4 = helper.registerGeneric(Registries.ITEM, "apple_addon_4", () -> new ModAppleAddonItem(4,TAB_ADDONS){

            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer, "apple_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                itemValue(IFEUItems.APPLE_ADDON_3.get().getDefaultInstance()),
                                itemValue(IFEUContents.APPLE_CORE.get().getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 2000), 200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        APPLE_ADDON_5 = helper.registerGeneric(Registries.ITEM, "apple_addon_5", () -> new ModAppleAddonItem(5,TAB_ADDONS){

            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer, "apple_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(IFEUItems.APPLE_ADDON_4.get().getDefaultInstance()),
                                itemValue(IFEUContents.APPLE_CORE.get().getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 4000), 200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });
        APPLE_ADDON_6 = helper.registerGeneric(Registries.ITEM, "apple_addon_6", () -> new ModAppleAddonItem(6,TAB_ADDONS){

            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer, "apple_addon_" + this.getTier(),
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(IFEUItems.APPLE_ADDON_5.get().getDefaultInstance()),
                                itemValue(IFEUContents.APPLE_CORE.get().getDefaultInstance())
                        ),new FluidStack(IFEUFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 8000), 200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        SILK_ADDON = helper.registerGeneric(Registries.ITEM, "silk_addon", () -> new SilkTouchAddonItem(TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(IFEUItems.SILK_ADDON.get().getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                itemValue(Items.DIAMOND_PICKAXE.getDefaultInstance()),
                                itemValue(Items.BOOK.getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save(consumer);
            }
        });

        HEAL_ADDON_1 = helper.registerGeneric(Registries.ITEM, "heal_addon_1", () -> new HealAddonItem(1,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                itemValue(Items.GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(Items.GOLDEN_CARROT.getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.LATEX.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save(consumer);
            }
        });
        HEAL_ADDON_2 = helper.registerGeneric(Registries.ITEM, "heal_addon_2", () -> new HealAddonItem(2,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                itemValue(Items.GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(IFEUItems.HEAL_ADDON_1.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.LATEX.getSourceFluid().get(),2000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save(consumer);
            }
        });
        HEAL_ADDON_3 = helper.registerGeneric(Registries.ITEM, "heal_addon_3", () -> new HealAddonItem(3,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE),
                                itemValue(Items.GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(IFEUItems.HEAL_ADDON_2.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save(consumer);
            }
        });
        HEAL_ADDON_4 = helper.registerGeneric(Registries.ITEM, "heal_addon_4", () -> new HealAddonItem(4,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                tagValue(IFEUTags.Items.GEARS_SCULK),
                                itemValue(Items.GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(IFEUItems.HEAL_ADDON_3.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save(consumer);
            }
        });
        HEAL_ADDON_5 = helper.registerGeneric(Registries.ITEM, "heal_addon_5", () -> new HealAddonItem(5,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(IFEUItems.HEAL_ADDON_4.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save(consumer);
            }
        });
        HEAL_ADDON_6 = helper.registerGeneric(Registries.ITEM, "heal_addon_6", () -> new HealAddonItem(6,TAB_ADDONS){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(IFEUItems.HEAL_ADDON_5.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(IFEUFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save(consumer);
            }
        });
        CHANCE_ADDON_1 = helper.registerGeneric(Registries.ITEM,"chance_addon_1", () -> new ModChanceAddonItem(1,TAB_ADDONS,false){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                tagValue(IndustrialTags.Items.GEAR_GOLD)
                        )
                        .chance(0.6F)
                        .save(consumer);
            }
        });
        CHANCE_ADDON_2 = helper.registerGeneric(Registries.ITEM,"chance_addon_2", () -> new ModChanceAddonItem(2,TAB_ADDONS,false){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND)
                        )
                        .chance(0.5F)
                        .save(consumer);
            }
        });
        CHANCE_ADDON_3 = helper.registerGeneric(Registries.ITEM,"chance_addon_3", () -> new ModChanceAddonItem(3,TAB_ADDONS,false){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                tagValue(IFEUTags.Items.GEARS_NETHERITE)
                        )
                        .chance(0.4F)
                        .save(consumer);
            }
        });
        CHANCE_ADDON_4 = helper.registerGeneric(Registries.ITEM,"chance_addon_4", () -> new ModChanceAddonItem(4,TAB_ADDONS,false){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                tagValue(IFEUTags.Items.GEARS_SCULK)
                        )
                        .chance(0.3F)
                        .save(consumer);
            }
        });
        CHANCE_ADDON_5 = helper.registerGeneric(Registries.ITEM,"chance_addon_5", () -> new ModChanceAddonItem(5,TAB_ADDONS,false){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance())
                        )
                        .chance(0.2F)
                        .save(consumer);
            }
        });
        CHANCE_ADDON_6 = helper.registerGeneric(Registries.ITEM,"chance_addon_6", () -> new ModChanceAddonItem(6,TAB_ADDONS,false){
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                itemValue(IFEUContents.DRAGON_STAR.get().getDefaultInstance())
                        )
                        .chance(0.1F)
                        .save(consumer);
            }
        });
        CHANCE_ADDON_CREATIVE = helper.registerGeneric(Registries.ITEM,"creative_chance_addon", () -> new ModChanceAddonItem(Integer.MAX_VALUE,TAB_ADDONS,true));
    }

    public Ingredient tagValue(TagKey<Item> tagKey){
        return Ingredient.of(tagKey);
    }
    public Ingredient itemValue(ItemStack itemStack){
        return Ingredient.of(itemStack);
    }
}
