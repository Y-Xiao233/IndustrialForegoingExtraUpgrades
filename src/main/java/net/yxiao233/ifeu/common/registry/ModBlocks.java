package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.block.IndustrialBlockItem;
import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.common.block.*;
import net.yxiao233.ifeu.common.block.FluidTransferBlock;
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
    }

    private Pair<RegistryObject<Block>,RegistryObject<BlockEntityType<?>>> deferredRegistry(DeferredRegistryHelper helper, String name, Supplier<BasicTileBlock<?>> blockSupplier){
        return helper.registerBlockWithTileItem(name,blockSupplier, (blockRegistryObject) -> () -> new IndustrialBlockItem(blockRegistryObject.get(),ModItems.TAB_ADDONS),null);
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

        return blocks;
    }
}
