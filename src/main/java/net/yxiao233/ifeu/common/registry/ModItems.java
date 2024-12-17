package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.module.IModule;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.item.ModEfficiencyAddonItem;
import net.yxiao233.ifeu.common.item.ModProcessingAddonItem;
import net.yxiao233.ifeu.common.item.ModSpeedAddonItem;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;

import java.util.function.Consumer;

public class ModItems implements IModule {
    public static TitaniumTab TAB_ADDONS = new TitaniumTab(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID, "extra_upgrades"));
    public static RegistryObject<Item> SPEED_ADDON_3;
    public static RegistryObject<Item> SPEED_ADDON_4;
    public static RegistryObject<Item> SPEED_ADDON_5;
    public static RegistryObject<Item> SPEED_ADDON_6;
    public static RegistryObject<Item> EFFICIENCY_ADDON_3;
    public static RegistryObject<Item> EFFICIENCY_ADDON_4;
    public static RegistryObject<Item> EFFICIENCY_ADDON_5;
    public static RegistryObject<Item> EFFICIENCY_ADDON_6;
    public static RegistryObject<Item> PROCESSING_ADDON_3;
    public static RegistryObject<Item> PROCESSING_ADDON_4;
    public static RegistryObject<Item> PROCESSING_ADDON_5;
    public static RegistryObject<Item> PROCESSING_ADDON_6;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        //Speed Addon
        SPEED_ADDON_3 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "speed_addon_3", () -> new ModSpeedAddonItem(3, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModuleCore.SPEED_ADDON_2.get().getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        SPEED_ADDON_4 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "speed_addon_4", () -> new ModSpeedAddonItem(4,6, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.SPEED_ADDON_3.get().getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),2000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        SPEED_ADDON_5 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "speed_addon_5", () -> new ModSpeedAddonItem(5,20, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.SPEED_ADDON_4.get().getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        SPEED_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "speed_addon_6", () -> new ModSpeedAddonItem(6,100, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.SPEED_ADDON_5.get().getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),2000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        //Efficiency Addon
        EFFICIENCY_ADDON_3 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "efficiency_addon_3", () -> new ModEfficiencyAddonItem(3, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModuleCore.EFFICIENCY_ADDON_2.get().getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        EFFICIENCY_ADDON_4 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "efficiency_addon_4", () -> new ModEfficiencyAddonItem(4, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.EFFICIENCY_ADDON_3.get().getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        EFFICIENCY_ADDON_5 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "efficiency_addon_5", () -> new ModEfficiencyAddonItem(5, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.EFFICIENCY_ADDON_4.get().getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        EFFICIENCY_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "efficiency_addon_6", () -> new ModEfficiencyAddonItem(6, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.EFFICIENCY_ADDON_5.get().getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),2000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });


        //Processing Addon
        PROCESSING_ADDON_3 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "processing_addon_3", () -> new ModProcessingAddonItem(3, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModuleCore.PROCESSING_ADDON_2.get().getDefaultInstance()),
                        itemValue(Items.FURNACE.getDefaultInstance()),
                        itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        PROCESSING_ADDON_4 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "processing_addon_4", () -> new ModProcessingAddonItem(4, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.PROCESSING_ADDON_3.get().getDefaultInstance()),
                        itemValue(Items.FURNACE.getDefaultInstance()),
                        itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        PROCESSING_ADDON_5 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "processing_addon_5", () -> new ModProcessingAddonItem(5, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.PROCESSING_ADDON_4.get().getDefaultInstance()),
                        itemValue(Items.FURNACE.getDefaultInstance()),
                        itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        PROCESSING_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "processing_addon_6", () -> new ModProcessingAddonItem(6, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GEMS_DIAMOND),
                        itemValue(ModItems.PROCESSING_ADDON_5.get().getDefaultInstance()),
                        itemValue(Items.FURNACE.getDefaultInstance()),
                        itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });
    }

    public Ingredient.TagValue tagValue(TagKey<Item> tagKey){
        return new Ingredient.TagValue(tagKey);
    }
    public Ingredient.ItemValue itemValue(ItemStack itemStack){
        return new Ingredient.ItemValue(itemStack);
    }
}
