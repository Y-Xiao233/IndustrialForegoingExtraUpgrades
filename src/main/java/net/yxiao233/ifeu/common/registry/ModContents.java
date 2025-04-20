package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.utils.CustomRarity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.block.DeadDragonEggBlock;
import net.yxiao233.ifeu.common.item.ConfigurationToolItem;
import net.yxiao233.ifeu.common.item.ConnectToolItem;
import net.yxiao233.ifeu.common.item.WrenchItem;

public class ModContents {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IndustrialForegoingExtraUpgrades.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(IndustrialForegoingExtraUpgrades.MODID);

    //Item
    public static final DeferredItem<Item> DRAGON_STAR = ITEMS.register("dragon_star", () -> new Item(new Item.Properties().rarity(CustomRarity.SUPREME.getValue())));
    public static final DeferredItem<Item> LASER_LENS_SCULK = ITEMS.register("laser_lens_sculk", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_GEAR = ITEMS.register("netherite_gear", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCULK_GEAR = ITEMS.register("sculk_gear", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LASER_LENS_DRAGON = ITEMS.register("laser_lens_dragon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CONNECT_TOOL = ITEMS.register("connect_tool", () -> new ConnectToolItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WRENCH = ITEMS.register("wrench", () -> new WrenchItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> CONFIGURATION_TOOL = ITEMS.register("configuration_tool", () -> new  ConfigurationToolItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ROUGH_DRAGON_STAR = ITEMS.register("rough_dragon_star", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> EMPTY_NETHER_STAR = ITEMS.register("empty_nether_star", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> APPLE_CORE = ITEMS.register("apple_core", () -> new Item(new Item.Properties().food(ModFoodProperties.APPLE_CORE)));

    //Tool
    public static final DeferredItem<Item> DRAGON_STAR_PICKAXE = ITEMS.register("dragon_star_pickaxe",() -> new PickaxeItem(ModTiers.DRAGON_STAR,(new Item.Properties().attributes(PickaxeItem.createAttributes(ModTiers.DRAGON_STAR,1,-2.8F)).fireResistant().rarity(CustomRarity.SUPREME.getValue()))));
    public static final DeferredItem<Item> DRAGON_STAR_AXE = ITEMS.register("dragon_star_axe",() -> new AxeItem(ModTiers.DRAGON_STAR,(new Item.Properties().fireResistant().attributes(AxeItem.createAttributes(ModTiers.DRAGON_STAR,5.0F,-3.0F)).rarity(CustomRarity.SUPREME.getValue()))));
    public static final DeferredItem<Item> DRAGON_STAR_HOE = ITEMS.register("dragon_star_hoe",() -> new HoeItem(ModTiers.DRAGON_STAR,(new Item.Properties().fireResistant().attributes(HoeItem.createAttributes(ModTiers.DRAGON_STAR,-4,0.0F)).rarity(CustomRarity.SUPREME.getValue()))));
    public static final DeferredItem<Item> DRAGON_STAR_SHOVEL = ITEMS.register("dragon_star_shovel",() -> new ShovelItem(ModTiers.DRAGON_STAR,(new Item.Properties().fireResistant().attributes(ShovelItem.createAttributes(ModTiers.DRAGON_STAR,1.5F,-3.0F)).rarity(CustomRarity.SUPREME.getValue()))));
    public static final DeferredItem<Item> DRAGON_STAR_SWORD = ITEMS.register("dragon_star_sword",() -> new SwordItem(ModTiers.DRAGON_STAR,(new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(ModTiers.DRAGON_STAR,3,-2.4F)).rarity(CustomRarity.SUPREME.getValue()))));

    //Block
    public static final DeferredBlock<Block> DEAD_DRAGON_EGG = BLOCKS.register("dead_dragon_egg", () -> new DeadDragonEggBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DRAGON_EGG)));
    public static final DeferredBlock<Block> ULTIMATE_MACHINE_FRAME = BLOCKS.register("ultimate_machine_frame", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> DRAGON_STAR_BLOCK = BLOCKS.register("dragon_star_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));



    //block_item
    public static final DeferredItem<BlockItem> DEAD_DRAGON_EGG_ITEM = ITEMS.register("dead_dragon_egg",() -> new BlockItem(ModContents.DEAD_DRAGON_EGG.get(),new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<BlockItem> ULTIMATE_MACHINE_FRAME_ITEM = ITEMS.register("ultimate_machine_frame", () -> new BlockItem(ModContents.ULTIMATE_MACHINE_FRAME.get(),new Item.Properties().rarity(CustomRarity.SUPREME.getValue())));
    public static final DeferredItem<BlockItem> DRAGON_STAR_BLOCK_ITEM = ITEMS.register("dragon_star_block", () -> new BlockItem(ModContents.DRAGON_STAR_BLOCK.get(),new Item.Properties().rarity(CustomRarity.SUPREME.getValue())));


    //for recipe as "minecraft:air"
    public static final DeferredItem<Item> AIR = ITEMS.register("air", () -> new Item(new Item.Properties()));

}
