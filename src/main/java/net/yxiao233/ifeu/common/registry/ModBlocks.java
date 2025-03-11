package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.block.IndustrialBlockItem;
import com.buuz135.industrial.module.IModule;
import com.buuz135.industrial.module.ModuleCore;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.api.block.IFEUBlackHoleCapacitorBlock;
import net.yxiao233.ifeu.common.block.*;
import net.yxiao233.ifeu.common.block.FluidTransferBlock;
import net.yxiao233.ifeu.common.block.BigDissolutionChamberBlock;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlocks implements IModule {
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> INFUSER;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> DRAGON_STAR_GENERATOR;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> ARCANE_DRAGON_EGG_FORGING;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> CREATIVE_CAPACITOR;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> RULE_CONTROLLER;
    public static Pair<RegistryObject<Block>,RegistryObject<BlockEntityType<?>>> WEATHER_CONTROLLER;
    public static Pair<RegistryObject<Block>,RegistryObject<BlockEntityType<?>>> TIME_CONTROLLER;
    public static Pair<RegistryObject<Block>,RegistryObject<BlockEntityType<?>>> FLUID_CRAFTING_TABLE;
    public static Pair<RegistryObject<Block>,RegistryObject<BlockEntityType<?>>> DRAGON_GENERATOR;
    public static Pair<RegistryObject<Block>,RegistryObject<BlockEntityType<?>>> FLUID_TRANSFER;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> BIG_DISSOLUTION_CHAMBER_CORE;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> BLACK_HOLE_CAPACITOR_PITY;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> BLACK_HOLE_CAPACITOR_SIMPLE;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> BLACK_HOLE_CAPACITOR_ADVANCED;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> BLACK_HOLE_CAPACITOR_SUPREME;
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
        FLUID_TRANSFER = deferredRegistry(helper,"fluid_transfer", FluidTransferBlock::new);
        BIG_DISSOLUTION_CHAMBER_CORE = deferredRegistry(helper,"big_dissolution_chamber_core", BigDissolutionChamberBlock::new);
        BLACK_HOLE_CAPACITOR_PITY = blackHoleRegistry(helper,"black_hole_capacitor_pity",ModuleCore.PITY_RARITY);
        BLACK_HOLE_CAPACITOR_SIMPLE = blackHoleRegistry(helper,"black_hole_capacitor_simple",ModuleCore.SIMPLE_RARITY);
        BLACK_HOLE_CAPACITOR_ADVANCED = blackHoleRegistry(helper,"black_hole_capacitor_advanced",ModuleCore.ADVANCED_RARITY);
        BLACK_HOLE_CAPACITOR_SUPREME = blackHoleRegistry(helper,"black_hole_capacitor_supreme",ModuleCore.SUPREME_RARITY);
    }

    private Pair<RegistryObject<Block>,RegistryObject<BlockEntityType<?>>> deferredRegistry(DeferredRegistryHelper helper, String name, Supplier<BasicTileBlock<?>> blockSupplier){
        return helper.registerBlockWithTileItem(name,blockSupplier, (blockRegistryObject) -> () -> new IndustrialBlockItem(blockRegistryObject.get(),ModItems.TAB_ADDONS),null);
    }

    private Pair<RegistryObject<Block>,RegistryObject<BlockEntityType<?>>> blackHoleRegistry(DeferredRegistryHelper helper, String name, Rarity rarity) {
        return helper.registerBlockWithTileItem(name, () -> {
            return new IFEUBlackHoleCapacitorBlock(name,rarity);
        }, (blockRegistryObject) -> {
            return () ->{
                return new IFEUBlackHoleCapacitorBlock.BlackHoleCapacitorItem(blockRegistryObject.get(),new Item.Properties().rarity(rarity),rarity,ModItems.TAB_ADDONS);
            };
        },null);
    }

    public static ArrayList<RegistryObject<Block>> addBlocksToCreativeModeTab(){
        ArrayList<RegistryObject<Block>> blocks = new ArrayList<>();

        blocks.add(INFUSER.getLeft());
        blocks.add(DRAGON_STAR_GENERATOR.getLeft());
        blocks.add(ARCANE_DRAGON_EGG_FORGING.getLeft());
        blocks.add(CREATIVE_CAPACITOR.getLeft());
        blocks.add(RULE_CONTROLLER.getLeft());
        blocks.add(WEATHER_CONTROLLER.getLeft());
        blocks.add(TIME_CONTROLLER.getLeft());
        blocks.add(FLUID_CRAFTING_TABLE.getLeft());
        blocks.add(DRAGON_GENERATOR.getLeft());
        blocks.add(FLUID_TRANSFER.getLeft());
        blocks.add(BIG_DISSOLUTION_CHAMBER_CORE.getLeft());
        blocks.add(BLACK_HOLE_CAPACITOR_PITY.getLeft());
        blocks.add(BLACK_HOLE_CAPACITOR_SIMPLE.getLeft());
        blocks.add(BLACK_HOLE_CAPACITOR_ADVANCED.getLeft());
        blocks.add(BLACK_HOLE_CAPACITOR_SUPREME.getLeft());

        return blocks;
    }
}
