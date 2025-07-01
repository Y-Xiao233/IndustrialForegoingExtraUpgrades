package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.module.IModule;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.item.*;
import net.yxiao233.ifeu.api.recipe.builder.IFEURecipeBuilders;
import net.yxiao233.ifeu.common.utils.EnchantUtil;

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
    public static RegistryObject<Item> THREAD_ADDON_1;
    public static RegistryObject<Item> THREAD_ADDON_2;
    public static RegistryObject<Item> THREAD_ADDON_3;
    public static RegistryObject<Item> THREAD_ADDON_4;
    public static RegistryObject<Item> THREAD_ADDON_5;
    public static RegistryObject<Item> THREAD_ADDON_6;
    public static RegistryObject<Item> APPLE_ADDON_1;
    public static RegistryObject<Item> APPLE_ADDON_2;
    public static RegistryObject<Item> APPLE_ADDON_3;
    public static RegistryObject<Item> APPLE_ADDON_4;
    public static RegistryObject<Item> APPLE_ADDON_5;
    public static RegistryObject<Item> APPLE_ADDON_6;
    public static RegistryObject<Item> SILK_ADDON;
    public static RegistryObject<Item> HEAL_ADDON_1;
    public static RegistryObject<Item> HEAL_ADDON_2;
    public static RegistryObject<Item> HEAL_ADDON_3;
    public static RegistryObject<Item> HEAL_ADDON_4;
    public static RegistryObject<Item> HEAL_ADDON_5;
    public static RegistryObject<Item> HEAL_ADDON_6;
    public static RegistryObject<Item> CHANCE_ADDON_1;
    public static RegistryObject<Item> CHANCE_ADDON_2;
    public static RegistryObject<Item> CHANCE_ADDON_3;
    public static RegistryObject<Item> CHANCE_ADDON_4;
    public static RegistryObject<Item> CHANCE_ADDON_5;
    public static RegistryObject<Item> CHANCE_ADDON_6;
    public static RegistryObject<Item> CHANCE_ADDON_CREATIVE;
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
                        tagValue(ModTags.Items.GEARS_NETHERITE),
                        tagValue(ModTags.Items.GEARS_NETHERITE),
                        itemValue(Items.SUGAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        SPEED_ADDON_4 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "speed_addon_4", () -> new ModSpeedAddonItem(4,TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(ModTags.Items.GEARS_SCULK),
                        tagValue(ModTags.Items.GEARS_SCULK),
                        itemValue(Items.SUGAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        SPEED_ADDON_5 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "speed_addon_5", () -> new ModSpeedAddonItem(5,TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        SPEED_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "speed_addon_6", () -> new ModSpeedAddonItem(6,TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this), new Ingredient.Value[] {
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.DUSTS_REDSTONE),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance()),
                        itemValue(Items.SUGAR.getDefaultInstance())
                },
                        new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),2000),200,
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
                        tagValue(ModTags.Items.GEARS_NETHERITE),
                        tagValue(ModTags.Items.GEARS_NETHERITE),
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
                        tagValue(ModTags.Items.GEARS_SCULK),
                        tagValue(ModTags.Items.GEARS_SCULK),
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
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
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
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance()),
                        itemValue(Items.BLAZE_ROD.getDefaultInstance())
                },
                        new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),2000),200,
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
                        tagValue(ModTags.Items.GEARS_NETHERITE),
                        tagValue(ModTags.Items.GEARS_NETHERITE),
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
                        tagValue(ModTags.Items.GEARS_SCULK),
                        tagValue(ModTags.Items.GEARS_SCULK),
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
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
                        itemValue(Items.NETHER_STAR.getDefaultInstance()),
                        itemValue(Items.FURNACE.getDefaultInstance()),
                        itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                },
                        new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY);
            }
        });

        PROCESSING_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "processing_addon_6", () -> new ModProcessingAddonItem(6, TAB_ADDONS) {
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[] {
                            tagValue(Tags.Items.DUSTS_REDSTONE),
                            tagValue(Tags.Items.DUSTS_REDSTONE),
                            tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                            tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                            itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                            itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                            itemValue(Items.FURNACE.getDefaultInstance()),
                            itemValue(Items.CRAFTING_TABLE.getDefaultInstance())
                        },
                        new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        THREAD_ADDON_1 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"thread_addon_1", () -> new ModThreadAddonItem(1,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[] {
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        },
                        new FluidStack(ModuleCore.LATEX.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        THREAD_ADDON_2 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"thread_addon_2", () -> new ModThreadAddonItem(2,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[] {
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        },
                        new FluidStack(ModuleCore.LATEX.getSourceFluid().get(),2000),200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        THREAD_ADDON_3 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"thread_addon_3", () -> new ModThreadAddonItem(3,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[] {
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        THREAD_ADDON_4 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"thread_addon_4", () -> new ModThreadAddonItem(4,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[] {
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        },
                        new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000),200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        THREAD_ADDON_5 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"thread_addon_5", () -> new ModThreadAddonItem(5,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[] {
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.NETHER_STARS),
                                tagValue(Tags.Items.NETHER_STARS),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        },
                        new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        THREAD_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"thread_addon_6", () -> new ModThreadAddonItem(6,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[] {
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.ECHO_SHARD.getDefaultInstance()),
                                tagValue(Tags.Items.INGOTS_NETHERITE)
                        },
                        new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000),200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });


        APPLE_ADDON_1 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "apple_addon_1", () -> new ModAppleAddonItem(1,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[]{
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                tagValue(IndustrialTags.Items.GEAR_GOLD),
                                itemValue(ModContents.APPLE_CORE.get().getDefaultInstance()),
                                itemValue(ModContents.APPLE_CORE.get().getDefaultInstance())
                        },
                        new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 10), 200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        APPLE_ADDON_2 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "apple_addon_2", () -> new ModAppleAddonItem(2,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[]{
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                itemValue(ModItems.APPLE_ADDON_1.get().getDefaultInstance()),
                                itemValue(ModContents.APPLE_CORE.get().getDefaultInstance())
                        },
                        new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 100), 200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        APPLE_ADDON_3 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "apple_addon_3", () -> new ModAppleAddonItem(3,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[]{
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                itemValue(ModItems.APPLE_ADDON_2.get().getDefaultInstance()),
                                itemValue(ModContents.APPLE_CORE.get().getDefaultInstance())
                        },
                        new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 1000), 200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        APPLE_ADDON_4 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "apple_addon_4", () -> new ModAppleAddonItem(4,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[]{
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                itemValue(ModItems.APPLE_ADDON_3.get().getDefaultInstance()),
                                itemValue(ModContents.APPLE_CORE.get().getDefaultInstance())
                        },
                        new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 2000), 200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        APPLE_ADDON_5 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "apple_addon_5", () -> new ModAppleAddonItem(5,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[]{
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(ModItems.APPLE_ADDON_4.get().getDefaultInstance()),
                                itemValue(ModContents.APPLE_CORE.get().getDefaultInstance())
                        },
                        new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 4000), 200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });
        APPLE_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "apple_addon_6", () -> new ModAppleAddonItem(6,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                new DissolutionChamberRecipe(ForgeRegistries.ITEMS.getKey(this),
                        new Ingredient.Value[]{
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(ModItems.APPLE_ADDON_5.get().getDefaultInstance()),
                                itemValue(ModContents.APPLE_CORE.get().getDefaultInstance())
                        },
                        new FluidStack(ModFluids.LIQUID_MALIC_ACID.getSourceFluid().get(), 8000), 200,
                        new ItemStack(this),FluidStack.EMPTY
                );
            }
        });

        SILK_ADDON = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "silk_addon", () -> new SilkTouchAddonItem(TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(ModItems.SILK_ADDON.get().getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                itemValue(Items.DIAMOND_PICKAXE.getDefaultInstance()),
                                itemValue(Items.BOOK.getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save();
            }
        });


        HEAL_ADDON_1 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "heal_addon_1", () -> new HealAddonItem(1,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
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
                        .save();
            }
        });
        HEAL_ADDON_2 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "heal_addon_2", () -> new HealAddonItem(2,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND),
                                itemValue(Items.GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(ModItems.HEAL_ADDON_1.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.LATEX.getSourceFluid().get(),2000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save();
            }
        });
        HEAL_ADDON_3 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "heal_addon_3", () -> new HealAddonItem(3,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                tagValue(ModTags.Items.GEARS_NETHERITE),
                                itemValue(Items.GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(ModItems.HEAL_ADDON_2.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save();
            }
        });
        HEAL_ADDON_4 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "heal_addon_4", () -> new HealAddonItem(4,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                tagValue(ModTags.Items.GEARS_SCULK),
                                itemValue(Items.GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(ModItems.HEAL_ADDON_3.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.PINK_SLIME.getSourceFluid().get(),2000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save();
            }
        });
        HEAL_ADDON_5 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "heal_addon_5", () -> new HealAddonItem(5,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance()),
                                itemValue(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(ModItems.HEAL_ADDON_4.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModuleCore.ETHER.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save();
            }
        });
        HEAL_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), "heal_addon_6", () -> new HealAddonItem(6,TAB_ADDONS){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.dissolutionChamberRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.DUSTS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance()),
                                itemValue(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance()),
                                itemValue(ModItems.HEAL_ADDON_5.get().getDefaultInstance())
                        )
                        .inputFluid(new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000))
                        .processingTime(200)
                        .outputFluid(FluidStack.EMPTY)
                        .save();
            }
        });

        CHANCE_ADDON_1 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"chance_addon_1", () -> new ModChanceAddonItem(1,TAB_ADDONS,false){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                tagValue(IndustrialTags.Items.GEAR_GOLD)
                        )
                        .chance(0.6F)
                        .save();
            }
        });
        CHANCE_ADDON_2 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"chance_addon_2", () -> new ModChanceAddonItem(2,TAB_ADDONS,false){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                tagValue(IndustrialTags.Items.GEAR_DIAMOND)
                        )
                        .chance(0.5F)
                        .save();
            }
        });
        CHANCE_ADDON_3 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"chance_addon_3", () -> new ModChanceAddonItem(3,TAB_ADDONS,false){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                tagValue(ModTags.Items.GEARS_NETHERITE)
                        )
                        .chance(0.4F)
                        .save();
            }
        });
        CHANCE_ADDON_4 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"chance_addon_4", () -> new ModChanceAddonItem(4,TAB_ADDONS,false){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                tagValue(ModTags.Items.GEARS_SCULK)
                        )
                        .chance(0.3F)
                        .save();
            }
        });
        CHANCE_ADDON_5 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"chance_addon_5", () -> new ModChanceAddonItem(5,TAB_ADDONS,false){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                itemValue(Items.NETHER_STAR.getDefaultInstance())
                        )
                        .chance(0.2F)
                        .save();
            }
        });
        CHANCE_ADDON_6 = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"chance_addon_6", () -> new ModChanceAddonItem(6,TAB_ADDONS,false){
            @Override
            public void registerRecipe(Consumer<FinishedRecipe> consumer) {
                IFEURecipeBuilders.precisionShapelessRecipe(this.getDefaultInstance())
                        .inputs(
                                tagValue(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                                tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                                itemValue(Items.ENCHANTED_BOOK.getDefaultInstance()),
                                itemValue(ModContents.DRAGON_STAR.get().getDefaultInstance())
                        )
                        .chance(0.1F)
                        .save();
            }
        });
        CHANCE_ADDON_CREATIVE = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(),"creative_chance_addon", () -> new ModChanceAddonItem(Integer.MAX_VALUE,TAB_ADDONS,true));
    }

    public Ingredient.TagValue tagValue(TagKey<Item> tagKey){
        return new Ingredient.TagValue(tagKey);
    }
    public Ingredient.ItemValue itemValue(ItemStack itemStack){
        return new Ingredient.ItemValue(itemStack);
    }
}
