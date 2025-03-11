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
import net.yxiao233.ifeu.api.item.ModEfficiencyAddonItem;
import net.yxiao233.ifeu.api.item.ModProcessingAddonItem;
import net.yxiao233.ifeu.api.item.ModSpeedAddonItem;
import net.yxiao233.ifeu.api.item.ModThreadAddonItem;

import java.util.List;
import java.util.Optional;

public class ModItems implements IModule {
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
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                itemValue(Items.SUGAR.getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance())
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        SPEED_ADDON_4 = helper.registerGeneric(Registries.ITEM, "speed_addon_4", () -> new ModSpeedAddonItem(4,6, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer, "speed_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                itemValue(Items.SUGAR.getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance())
                        ), new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 2000), 200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        SPEED_ADDON_5 = helper.registerGeneric(Registries.ITEM, "speed_addon_5", () -> new ModSpeedAddonItem(5,20, TAB_ADDONS) {
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
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });

        SPEED_ADDON_6 = helper.registerGeneric(Registries.ITEM, "speed_addon_6", () -> new ModSpeedAddonItem(6,100, TAB_ADDONS) {
            @Override
            public void registerRecipe(RecipeOutput consumer) {
                DissolutionChamberRecipe.createRecipe(consumer,"speed_addon_" + this.formTier,
                        new DissolutionChamberRecipe(List.of(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance()),
                                itemValue(Items.SUGAR.getDefaultInstance())
                        ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
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
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
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
                                tagValue(ModTags.Items.GEARS_SCULK),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance())
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
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
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
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
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                                itemValue(Items.BLAZE_ROD.getDefaultInstance())
                        ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
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
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
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
                                tagValue(ModTags.Items.GEARS_SCULK),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                itemValue(Items.FURNACE.getDefaultInstance()),
                                itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
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
                        ),new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
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
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.FURNACE.getDefaultInstance()),
                                itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                        ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
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
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
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
                                tagValue(ModTags.Items.GEARS_SCULK),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        ),new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
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
                        ),new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),2000),200,
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
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        ),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
                                Optional.of(new ItemStack(this)), Optional.empty())
                );
            }
        });
    }

    public Ingredient tagValue(TagKey<Item> tagKey){
        return Ingredient.of(tagKey);
    }
    public Ingredient itemValue(ItemStack itemStack){
        return Ingredient.of(itemStack);
    }
}
