package net.yxiao233.ifeu.common.registry;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

public class ModContents {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialForegoingExtraUpgrades.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialForegoingExtraUpgrades.MODID);
    //FluidContents
        //Liquid Dragon Breath
    public static final RegistryObject<LiquidBlock> LIQUID_DRAGON_BREATH_BLOCK = BLOCKS.register("liquid_dragon_breath_block",() -> new LiquidBlock(ModFluids.SOURCE_LIQUID_DRAGON_BREATH, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<Item> LIQUID_DRAGON_BREATH_BUCKET = ITEMS.register("liquid_dragon_breath_bucket", () -> new BucketItem(ModFluids.SOURCE_LIQUID_DRAGON_BREATH,
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

        //Liquid Sculk
    public static final RegistryObject<LiquidBlock> LIQUID_SCULK_MATTER_BLOCK = BLOCKS.register("liquid_sculk_matter_block",() -> new LiquidBlock(ModFluids.SOURCE_LIQUID_SCULK_MATTER, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<Item> LIQUID_SCULK_MATTER_BUCKET = ITEMS.register("liquid_sculk_matter_bucket", () -> new BucketItem(ModFluids.SOURCE_LIQUID_SCULK_MATTER,
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    //Item
    public static final RegistryObject<Item> DRAGON_STAR = ITEMS.register("dragon_star", () -> new SimpleFoiledItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LASER_LENS_SCULK = ITEMS.register("laser_lens_sculk", () -> new Item(new Item.Properties()));
}
