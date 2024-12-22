package net.yxiao233.ifeu.common.registry;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

public class ModContents {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialForegoingExtraUpgrades.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialForegoingExtraUpgrades.MODID);

    //Item
    public static final RegistryObject<Item> DRAGON_STAR = ITEMS.register("dragon_star", () -> new SimpleFoiledItem(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> LASER_LENS_SCULK = ITEMS.register("laser_lens_sculk", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_GEAR = ITEMS.register("netherite_gear", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCULK_GEAR = ITEMS.register("sculk_gear", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_STAR_PICKAXE = ITEMS.register("dragon_star_pickaxe",() -> new PickaxeItem(ModTiers.DRAGON_STAR,1,-2.8F,(new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> DRAGON_STAR_AXE = ITEMS.register("dragon_star_axe",() -> new AxeItem(ModTiers.DRAGON_STAR,5.0F,-3.0F,(new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> DRAGON_STAR_HOE = ITEMS.register("dragon_star_hoe",() -> new HoeItem(ModTiers.DRAGON_STAR,-4,0.0F,(new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> DRAGON_STAR_SHOVEL = ITEMS.register("dragon_star_shovel",() -> new ShovelItem(ModTiers.DRAGON_STAR,1.5F,-3.0F,(new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
    public static final RegistryObject<Item> DRAGON_STAR_SWORD = ITEMS.register("dragon_star_sword",() -> new SwordItem(ModTiers.DRAGON_STAR,3,-2.4F,(new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
}
