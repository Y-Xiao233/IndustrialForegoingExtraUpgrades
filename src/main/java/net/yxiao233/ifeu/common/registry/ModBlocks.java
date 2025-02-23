package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.block.IndustrialBlockItem;
import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.module.BlockWithTile;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.yxiao233.ifeu.common.block.*;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlocks implements IModule {
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
    }

    private BlockWithTile deferredRegistry(DeferredRegistryHelper helper, String name, Supplier<BasicTileBlock<?>> blockSupplier){
        return helper.registerBlockWithTileItem(name,blockSupplier, (blockRegistryObject) -> () -> new IndustrialBlockItem(blockRegistryObject.get(),ModItems.TAB_ADDONS),null);
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

        return blocks;
    }
}
