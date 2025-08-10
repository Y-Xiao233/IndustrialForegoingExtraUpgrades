package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.block.IndustrialBlockItem;
import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.module.BlockWithTile;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.yxiao233.ifeu.api.block.IFEUBlackHoleCapacitorBlock;
import net.yxiao233.ifeu.common.block.*;

import java.util.ArrayList;
import java.util.function.Supplier;

public class IFEUBlocks implements IModule {
    public static BlockWithTile INFUSER;
    public static BlockWithTile DRAGON_STAR_GENERATOR;
    public static BlockWithTile ARCANE_DRAGON_EGG_FORGING;
    public static BlockWithTile CREATIVE_CAPACITOR;
    public static BlockWithTile RULE_CONTROLLER;
    public static BlockWithTile WEATHER_CONTROLLER;
    public static BlockWithTile TIME_CONTROLLER;
    public static BlockWithTile FLUID_CRAFTING_TABLE;
    public static BlockWithTile DRAGON_GENERATOR;
    public static BlockWithTile FLUID_TRANSFER;
    public static BlockWithTile BIG_DISSOLUTION_CHAMBER_CORE;
    public static BlockWithTile PLATFORM_BUILDER;
    public static BlockWithTile PRECISION_CRAFTING_TABLE;
//    public static BlockWithTile BLACK_HOLE_CAPACITOR_PITY;
//    public static BlockWithTile BLACK_HOLE_CAPACITOR_SIMPLE;
//    public static BlockWithTile BLACK_HOLE_CAPACITOR_ADVANCED;
//    public static BlockWithTile BLACK_HOLE_CAPACITOR_SUPREME;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        INFUSER = deferredRegistry(helper,"infuser",InfuserBlock::new);
        DRAGON_STAR_GENERATOR = deferredRegistry(helper,"dragon_star_generator",DragonStarGeneratorBlock::new);
        ARCANE_DRAGON_EGG_FORGING = deferredRegistry(helper,"arcane_dragon_egg_forging",ArcaneDragonEggForgingBlock::new);
        CREATIVE_CAPACITOR = deferredRegistry(helper,"creative_capacitor",CreativeCapacitorBlock::new);
        RULE_CONTROLLER = deferredRegistry(helper,"rule_controller", RuleControllerBlock::new);
        WEATHER_CONTROLLER = deferredRegistry(helper,"weather_controller",WeatherControllerBlock::new);
        TIME_CONTROLLER = deferredRegistry(helper,"time_controller",TimeControllerBlock::new);
        FLUID_CRAFTING_TABLE = deferredRegistry(helper,"fluid_crafting_table",FluidCraftingTableBlock::new);
        DRAGON_GENERATOR = deferredRegistry(helper,"dragon_generator",DragonGeneratorBlock::new);
        FLUID_TRANSFER = deferredRegistry(helper,"fluid_transfer",FluidTransferBlock::new);
        BIG_DISSOLUTION_CHAMBER_CORE = deferredRegistry(helper,"big_dissolution_chamber_core", BigDissolutionChamberBlock::new);
        PLATFORM_BUILDER = deferredRegistry(helper,"platform_builder",PlatformBuilderBlock::new);
        PRECISION_CRAFTING_TABLE = deferredRegistry(helper,"precision_crafting_table",PrecisionCraftingTableBlock::new);
//        BLACK_HOLE_CAPACITOR_PITY = blackHoleRegistry(helper,"black_hole_capacitor_pity",IFEURarity.PITY);
//        BLACK_HOLE_CAPACITOR_SIMPLE = blackHoleRegistry(helper,"black_hole_capacitor_simple",IFEURarity.SIMPLE);
//        BLACK_HOLE_CAPACITOR_ADVANCED = blackHoleRegistry(helper,"black_hole_capacitor_advanced",IFEURarity.ADVANCED);
//        BLACK_HOLE_CAPACITOR_SUPREME = blackHoleRegistry(helper,"black_hole_capacitor_supreme",IFEURarity.SUPREME);
    }

    private BlockWithTile deferredRegistry(DeferredRegistryHelper helper, String name, Supplier<BasicTileBlock<?>> blockSupplier){
        return helper.registerBlockWithTileItem(name,blockSupplier, (blockRegistryObject) -> () -> new IndustrialBlockItem(blockRegistryObject.get(), IFEUItems.TAB_ADDONS),null);
    }

    private BlockWithTile blackHoleRegistry(DeferredRegistryHelper helper, String name, Rarity rarity) {
        return helper.registerBlockWithTileItem(name, () -> {
            return new IFEUBlackHoleCapacitorBlock(name,rarity);
        }, (blockRegistryObject) -> {
            return () ->{
                return new IFEUBlackHoleCapacitorBlock.BlackHoleCapacitorItem(blockRegistryObject.get(),new Item.Properties().rarity(rarity),rarity, IFEUItems.TAB_ADDONS);
            };
        },null);
    }

    public static ArrayList<Block> addBlocksToCreativeModeTab(){
        ArrayList<Block> blocks = new ArrayList<>();

        blocks.add(INFUSER.getBlock());
        blocks.add(DRAGON_STAR_GENERATOR.getBlock());
        blocks.add(ARCANE_DRAGON_EGG_FORGING.getBlock());
        blocks.add(CREATIVE_CAPACITOR.getBlock());
        blocks.add(RULE_CONTROLLER.getBlock());
        blocks.add(WEATHER_CONTROLLER.getBlock());
        blocks.add(TIME_CONTROLLER.getBlock());
        blocks.add(FLUID_CRAFTING_TABLE.getBlock());
        blocks.add(DRAGON_GENERATOR.getBlock());
        blocks.add(FLUID_TRANSFER.getBlock());
        blocks.add(BIG_DISSOLUTION_CHAMBER_CORE.getBlock());
        blocks.add(PLATFORM_BUILDER.getBlock());
        blocks.add(PRECISION_CRAFTING_TABLE.getBlock());

        return blocks;
    }
}
