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
}
