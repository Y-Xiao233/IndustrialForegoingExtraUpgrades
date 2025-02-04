package net.yxiao233.ifeu.common.registry;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.block.custom.DeadDragonEggBlock;

public class ModContents {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialForegoingExtraUpgrades.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialForegoingExtraUpgrades.MODID);

    //Item
    public static final RegistryObject<Item> DRAGON_STAR = ITEMS.register("dragon_star", () -> new SimpleFoiledItem(new Item.Properties().rarity(ModRarity.LEGENDARY)));
    public static final RegistryObject<Item> LASER_LENS_SCULK = ITEMS.register("laser_lens_sculk", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> NETHERITE_GEAR = ITEMS.register("netherite_gear", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCULK_GEAR = ITEMS.register("sculk_gear", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LASER_LENS_DRAGON = ITEMS.register("laser_lens_dragon", () -> new Item(new Item.Properties().rarity(ModRarity.LEGENDARY)));

    //Tool
    public static final RegistryObject<Item> DRAGON_STAR_PICKAXE = ITEMS.register("dragon_star_pickaxe",() -> new PickaxeItem(ModTiers.DRAGON_STAR,1,-2.8F,(new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY))));
    public static final RegistryObject<Item> DRAGON_STAR_AXE = ITEMS.register("dragon_star_axe",() -> new AxeItem(ModTiers.DRAGON_STAR,5.0F,-3.0F,(new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY))));
    public static final RegistryObject<Item> DRAGON_STAR_HOE = ITEMS.register("dragon_star_hoe",() -> new HoeItem(ModTiers.DRAGON_STAR,-4,0.0F,(new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY))));
    public static final RegistryObject<Item> DRAGON_STAR_SHOVEL = ITEMS.register("dragon_star_shovel",() -> new ShovelItem(ModTiers.DRAGON_STAR,1.5F,-3.0F,(new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY))));
    public static final RegistryObject<Item> DRAGON_STAR_SWORD = ITEMS.register("dragon_star_sword",() -> new SwordItem(ModTiers.DRAGON_STAR,3,-2.4F,(new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY))));
    //Armor
//    public static final RegistryObject<Item> DRAGON_STAR_HELMET = ITEMS.register("dragon_star_helmet",() -> new ArmorItem(ModArmorMaterials.DRAGON_STAR, ArmorItem.Type.HELMET,new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY)));
//    public static final RegistryObject<Item> DRAGON_STAR_CHESTPLATE = ITEMS.register("dragon_star_chestplate",() -> new ArmorItem(ModArmorMaterials.DRAGON_STAR, ArmorItem.Type.CHESTPLATE,new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY)));
//    public static final RegistryObject<Item> DRAGON_STAR_LEGGINGS = ITEMS.register("dragon_star_leggings",() -> new ArmorItem(ModArmorMaterials.DRAGON_STAR, ArmorItem.Type.LEGGINGS,new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY)));
//    public static final RegistryObject<Item> DRAGON_STAR_BOOTS = ITEMS.register("dragon_star_boots",() -> new ArmorItem(ModArmorMaterials.DRAGON_STAR, ArmorItem.Type.BOOTS,new Item.Properties().fireResistant().rarity(ModRarity.LEGENDARY)));


    //Block
    public static final RegistryObject<Block> DEAD_DRAGON_EGG = BLOCKS.register("dead_dragon_egg", () -> new DeadDragonEggBlock(BlockBehaviour.Properties.copy(Blocks.DRAGON_EGG)));
    public static final RegistryObject<Block> ULTIMATE_MACHINE_FRAME = BLOCKS.register("ultimate_machine_frame", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> DRAGON_STAR_BLOCK = BLOCKS.register("dragon_star_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));



    //block_item
    public static final RegistryObject<BlockItem> DEAD_DRAGON_EGG_ITEM = ITEMS.register("dead_dragon_egg",() -> new BlockItem(ModContents.DEAD_DRAGON_EGG.get(),new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<BlockItem> ULTIMATE_MACHINE_FRAME_ITEM = ITEMS.register("ultimate_machine_frame", () -> new BlockItem(ModContents.ULTIMATE_MACHINE_FRAME.get(),new Item.Properties().rarity(ModRarity.MYTHOLOGICAL)));
    public static final RegistryObject<BlockItem> DRAGON_STAR_BLOCK_ITEM = ITEMS.register("dragon_star_block", () -> new BlockItem(ModContents.DRAGON_STAR_BLOCK.get(),new Item.Properties().rarity(ModRarity.LEGENDARY)));

}
