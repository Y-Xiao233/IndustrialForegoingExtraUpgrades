package net.yxiao233.ifeu.api.tree;

import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.*;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.block.FlammableRotatedPillarBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("unused")

public class DeferredTree {
    private final DeferredBlock<Block> logBlock;
    private final DeferredItem<BlockItem> logItem;
    private final DeferredBlock<Block> strippedLogBlock;
    private final DeferredItem<BlockItem> strippedLogItem;
    private final DeferredBlock<Block> woodBlock;
    private final DeferredItem<BlockItem> woodItem;
    private final DeferredBlock<Block> strippedWoodBlock;
    private final DeferredItem<BlockItem> strippedWoodItem;
    private final DeferredBlock<Block> planksBlock;
    private final DeferredItem<BlockItem> planksItem;
    private final DeferredBlock<Block> leavesBlock;
    private final DeferredItem<BlockItem> leavesItem;
    private final DeferredBlock<Block> saplingBlock;
    private final DeferredItem<BlockItem> saplingItem;
    private final ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey;
    private final ResourceKey<PlacedFeature> placedFeatureResourceKey;
    private final ResourceKey<BiomeModifier> biomeModifierResourceKey;
    private final List<ResourceKey<Biome>> biomes;
    private TrunkPlacer trunkPlacer;
    private FoliagePlacer foliagePlacer;
    private FeatureSize featureSize;
    private final int logBaseHeight;
    private final int logFirstRandomHeight;
    private final int logSecondRandomHeight;
    private final int leavesRadius;
    private final int leavesOffset;
    private final int leavesHeight;
    private final int limit;
    private final int lowerSize;
    private final int upperSize;
    private DeferredTree(DeferredBlock<Block> logBlock, DeferredItem<BlockItem> logItem,
                         DeferredBlock<Block> strippedLogBlock, DeferredItem<BlockItem> strippedLogItem,
                         DeferredBlock<Block> woodBlock, DeferredItem<BlockItem> woodItem,
                         DeferredBlock<Block> strippedWoodBlock, DeferredItem<BlockItem> strippedWoodItem,
                         DeferredBlock<Block> planksBlock, DeferredItem<BlockItem> planksItem,
                         DeferredBlock<Block> leavesBlock, DeferredItem<BlockItem> leavesItem,
                         DeferredBlock<Block> saplingBlock, DeferredItem<BlockItem> saplingItem,
                         ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey,ResourceKey<PlacedFeature> placedFeatureResourceKey,ResourceKey<BiomeModifier> biomeModifierResourceKey,
                         List<ResourceKey<Biome>> biomes,
                         int logBaseHeight, int logFirstRandomHeight, int logSecondRandomHeight,
                         int leavesRadius, int leavesOffset, int leavesHeight,
                         int limit, int lowerSize, int upperSize){
        this.logBlock = logBlock;
        this.logItem = logItem;
        this.strippedLogBlock = strippedLogBlock;
        this.strippedLogItem = strippedLogItem;
        this.woodBlock = woodBlock;
        this.woodItem = woodItem;
        this.strippedWoodBlock = strippedWoodBlock;
        this.strippedWoodItem = strippedWoodItem;
        this.planksBlock = planksBlock;
        this.planksItem = planksItem;
        this.leavesBlock = leavesBlock;
        this.leavesItem = leavesItem;
        this.saplingBlock = saplingBlock;
        this.saplingItem = saplingItem;
        this.configuredFeatureResourceKey = configuredFeatureResourceKey;
        this.placedFeatureResourceKey = placedFeatureResourceKey;
        this.biomeModifierResourceKey = biomeModifierResourceKey;
        this.biomes = biomes;
        this.logBaseHeight = logBaseHeight;
        this.logFirstRandomHeight = logFirstRandomHeight;
        this.logSecondRandomHeight = logSecondRandomHeight;
        this.leavesRadius = leavesRadius;
        this.leavesOffset = leavesOffset;
        this.leavesHeight = leavesHeight;
        this.limit = limit;
        this.lowerSize = lowerSize;
        this.upperSize = upperSize;
    }

    public DeferredBlock<Block> getLogBlock() {
        return logBlock;
    }

    public DeferredItem<BlockItem> getLogItem() {
        return logItem;
    }

    public DeferredBlock<Block> getStrippedLogBlock() {
        return strippedLogBlock;
    }

    public DeferredItem<BlockItem> getStrippedLogItem() {
        return strippedLogItem;
    }

    public DeferredBlock<Block> getWoodBlock() {
        return woodBlock;
    }

    public DeferredItem<BlockItem> getWoodItem() {
        return woodItem;
    }

    public DeferredBlock<Block> getStrippedWoodBlock() {
        return strippedWoodBlock;
    }

    public DeferredItem<BlockItem> getStrippedWoodItem() {
        return strippedWoodItem;
    }

    public DeferredBlock<Block> getPlanksBlock() {
        return planksBlock;
    }

    public DeferredItem<BlockItem> getPlanksItem() {
        return planksItem;
    }

    public DeferredBlock<Block> getLeavesBlock() {
        return leavesBlock;
    }

    public DeferredItem<BlockItem> getLeavesItem() {
        return leavesItem;
    }

    public DeferredBlock<Block> getSaplingBlock() {
        return saplingBlock;
    }

    public DeferredItem<BlockItem> getSaplingItem() {
        return saplingItem;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeatureResourceKey() {
        return configuredFeatureResourceKey;
    }

    public ResourceKey<PlacedFeature> getPlacedFeatureResourceKey() {
        return placedFeatureResourceKey;
    }

    public ResourceKey<BiomeModifier> getBiomeModifierResourceKey() {
        return biomeModifierResourceKey;
    }

    public List<DeferredBlock<Block>> getAllBlocks(){
        return List.of(logBlock,strippedLogBlock,woodBlock,strippedWoodBlock,planksBlock,leavesBlock,saplingBlock);
    }

    public List<DeferredBlock<Block>> getAllBlocksWithoutLeavesBlock(){
        return List.of(logBlock,strippedLogBlock,woodBlock,strippedWoodBlock,planksBlock,saplingBlock);
    }

    public DeferredTree withCustomTrunkPlacer(TrunkPlacer trunkPlacer){
        this.trunkPlacer = trunkPlacer;
        return this;
    }

    public DeferredTree withCustomFoliagePlacer(FoliagePlacer foliagePlacer){
        this.foliagePlacer = foliagePlacer;
        return this;
    }

    public DeferredTree withCustomFeatureSize(FeatureSize featureSize){
        this.featureSize = featureSize;
        return this;
    }

    public void registryConfiguredFeature(BootstrapContext<ConfiguredFeature<?,?>> context){
        TrunkPlacer trunkPlacer = new ForkingTrunkPlacer(logBaseHeight,logFirstRandomHeight,logSecondRandomHeight);
        FoliagePlacer foliagePlacer = new BlobFoliagePlacer(ConstantInt.of(leavesRadius),ConstantInt.of(leavesOffset),leavesHeight);
        FeatureSize featureSize = new TwoLayersFeatureSize(limit,lowerSize,upperSize);
        if(this.trunkPlacer != null){
            trunkPlacer = this.trunkPlacer;
        }
        if(this.foliagePlacer != null){
            foliagePlacer = this.foliagePlacer;
        }
        if(this.featureSize != null){
            featureSize = this.featureSize;
        }
        context.register(configuredFeatureResourceKey,new ConfiguredFeature<>(Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(logBlock.get()),
                trunkPlacer,
                BlockStateProvider.simple(leavesBlock.get()),
                foliagePlacer,
                featureSize
        ).build()));
    }

    public void registryPlacedFeature(BootstrapContext<PlacedFeature> context){
        var lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(placedFeatureResourceKey,new PlacedFeature(lookup.getOrThrow(configuredFeatureResourceKey), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f,2),saplingBlock.get())));
    }

    public void registryBiomeModifier(BootstrapContext<BiomeModifier> context){
        if(biomes != null && !biomes.isEmpty()){
            List<Holder<Biome>> holders = new ArrayList<>();
            biomes.forEach(biomeResourceKey -> holders.add(context.lookup(Registries.BIOME).getOrThrow(biomeResourceKey)));

            context.register(biomeModifierResourceKey,new BiomeModifiers.AddFeaturesBiomeModifier(
                    HolderSet.direct(holders),HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureResourceKey)), GenerationStep.Decoration.VEGETAL_DECORATION
            ));
        }
    }

    public static class DeferredTreesRegister{
        private static final HashMap<List<String>, DeferredTree> treeMap = new HashMap<>();
        private static final HashMap<List<String>, DeferredTree> logMap = new HashMap<>();
        private static final HashMap<List<String>, DeferredTree> woodMap = new HashMap<>();
        private final DeferredRegister.Items items;
        private final DeferredRegister.Blocks blocks;
        private DeferredTreesRegister(DeferredRegister.Items item, DeferredRegister.Blocks blocks){
            this.items = item;
            this.blocks = blocks;
        }
        public static DeferredTreesRegister create(DeferredRegister.Items item, DeferredRegister.Blocks blocks){
            return new DeferredTreesRegister(item,blocks);
        }

        public DeferredTree registry(String name, List<ResourceKey<Biome>> biomes){
            return registry(name,biomes,4,4,3,2,3,3,1,0,2);
        }

        public DeferredTree registry(String name){
            return registry(name,null,4,4,3,2,3,3,1,0,2);
        }

        public DeferredTree registryOf(String name, DeferredTree other, @Nullable List<ResourceKey<Biome>> biomes){
            return registryOf(name,other,biomes,1,0,2);
        }

        public DeferredTree registryOf(String name, DeferredTree other, @Nullable List<ResourceKey<Biome>> biomes, int limit, int lowerSize, int upperSize){
            return registryOf(name,other,biomes,4,4,3,2,3,3,limit,lowerSize,upperSize);
        }
        public DeferredTree registryOf(String name, DeferredTree other, @Nullable List<ResourceKey<Biome>> biomes, int logBaseHeight, int logFirstRandomHeight, int logSecondRandomHeight, int leavesRadius, int leavesOffset, int leavesHeight, int limit, int lowerSize, int upperSize){
            ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey = registryKey(Registries.CONFIGURED_FEATURE,name);
            ResourceKey<PlacedFeature> featureResourceKey = registryKey(Registries.PLACED_FEATURE,name);
            ResourceKey<BiomeModifier> biomeModifierResourceKey = registryKey(NeoForgeRegistries.Keys.BIOME_MODIFIERS, "add_tree_" + name);

            DeferredBlock<Block> saplingBlock = blocks.register(name + "_sapling", () -> new SaplingBlock(new TreeGrower(IndustrialForegoingExtraUpgrades.MODID + ":" + name, Optional.empty(), Optional.of(configuredFeatureResourceKey), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
            DeferredItem<BlockItem> saplingItem = items.register(name + "_sapling", () -> new BlockItem(saplingBlock.get(), new Item.Properties()));

            DeferredTree tree = new DeferredTree(other.logBlock,other.logItem,
                    other.strippedLogBlock,other.strippedLogItem,
                    other.woodBlock,other.woodItem,
                    other.strippedWoodBlock,other.strippedWoodItem,
                    other.planksBlock,other.planksItem,
                    other.leavesBlock,other.leavesItem,
                    saplingBlock,saplingItem,
                    configuredFeatureResourceKey,featureResourceKey,biomeModifierResourceKey,
                    biomes,logBaseHeight,logFirstRandomHeight,logSecondRandomHeight,
                    leavesRadius,leavesOffset,leavesHeight,
                    limit,lowerSize,upperSize
            );
            putToMap(name,tree);
            return tree;
        }

        public DeferredTree registry(String name, @Nullable List<ResourceKey<Biome>> biomes, int logBaseHeight, int logFirstRandomHeight, int logSecondRandomHeight, int leavesRadius, int leavesOffset, int leavesHeight, int limit, int lowerSize, int upperSize){
            DeferredBlock<Block> logBlock = blocks.register(name + "_log", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
            DeferredItem<BlockItem> logItem = items.register(name + "_log", () -> new BlockItem(logBlock.get(),new Item.Properties()));

            DeferredBlock<Block> strippedLogBlock = blocks.register("stripped_" + name + "_log", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
            DeferredItem<BlockItem> strippedLogItem = items.register("stripped_" + name + "_log", () -> new BlockItem(strippedLogBlock.get(),new Item.Properties()));

            DeferredBlock<Block> woodBlock = blocks.register(name + "_wood", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
            DeferredItem<BlockItem> woodItem = items.register(name + "_wood", () -> new BlockItem(woodBlock.get(),new Item.Properties()));

            DeferredBlock<Block> strippedWoodBlock = blocks.register("stripped_" + name + "_wood", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
            DeferredItem<BlockItem> strippedWoodItem = items.register("stripped_" + name + "_wood", () -> new BlockItem(strippedWoodBlock.get(),new Item.Properties()));

            DeferredBlock<Block> planksBlock = blocks.register(name + "_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                    return 5;
                }
            });
            DeferredItem<BlockItem> planksItem = items.register(name + "_planks",() -> new BlockItem(planksBlock.get(), new Item.Properties()));

            DeferredBlock<Block> leavesBlock = blocks.register(name + "_leaves", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
                    return 30;
                }
            });

            ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey = registryKey(Registries.CONFIGURED_FEATURE,name);
            ResourceKey<PlacedFeature> featureResourceKey = registryKey(Registries.PLACED_FEATURE,name);
            ResourceKey<BiomeModifier> biomeModifierResourceKey = registryKey(NeoForgeRegistries.Keys.BIOME_MODIFIERS, "add_tree_" + name);

            DeferredItem<BlockItem> leavesItem = items.register(name + "_leaves", () -> new BlockItem(leavesBlock.get(), new Item.Properties()));

            DeferredBlock<Block> saplingBlock = blocks.register(name + "_sapling", () -> new SaplingBlock(new TreeGrower(IndustrialForegoingExtraUpgrades.MODID + ":" + name, Optional.empty(), Optional.of(configuredFeatureResourceKey), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
            DeferredItem<BlockItem> saplingItem = items.register(name + "_sapling", () -> new BlockItem(saplingBlock.get(), new Item.Properties()));

            DeferredTree tree = new DeferredTree(logBlock, logItem,
                    strippedLogBlock, strippedLogItem,
                    woodBlock, woodItem,
                    strippedWoodBlock, strippedWoodItem,
                    planksBlock,planksItem,
                    leavesBlock,leavesItem,
                    saplingBlock,saplingItem,
                    configuredFeatureResourceKey,featureResourceKey,biomeModifierResourceKey,
                    biomes,
                    logBaseHeight,logFirstRandomHeight,logSecondRandomHeight,
                    leavesRadius,leavesOffset,leavesHeight,
                    limit,lowerSize,upperSize

            );
            putToMap(name,tree);
            return tree;
        }

        private static void putToMap(String name, DeferredTree tree){
            treeMap.put(List.of(name + "_log","stripped_" + name + "_log",name + "_wood","stripped_" + name + "_wood",name + "_planks",name + "_leaves",name + "_sapling"),tree);
            logMap.put(List.of(name + "_log","stripped_" + name + "_log"),tree);
            woodMap.put(List.of(name + "_wood","stripped_" + name + "_wood"),tree);
        }

        private static <T> ResourceKey<T> registryKey(ResourceKey<Registry<T>> resourceKey, String name){
            return ResourceKey.create(resourceKey, ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID, name));
        }


        public static DeferredTree get(String name){
            AtomicReference<DeferredTree> deferredTree = new AtomicReference<>(null);
            AtomicBoolean find = new AtomicBoolean(false);
            treeMap.forEach((list, tree) ->{
                if(list.contains(name) && !find.get()){
                    deferredTree.set(tree);
                    find.set(true);
                }
            });

            return deferredTree.get();
        }

        public static DeferredTree get(Block block){
            return get(BuiltInRegistries.BLOCK.getKey(block).getPath());
        }

        public static List<DeferredTree> getAllTrees(){
            return treeMap.values().stream().toList();
        }

        public static BlockState onToolModified(BlockState state, EnumProperty<Direction.Axis> axis){
            AtomicReference<BlockState> newState = new AtomicReference<>(null);
            AtomicBoolean find = new AtomicBoolean(false);
            String name = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
            if(name.contains("log") && !name.contains("stripped")){
                logMap.forEach((list, tree) ->{
                    if(list.contains(name) && !find.get()){
                        newState.set(tree.getStrippedLogBlock().get().defaultBlockState().setValue(axis, state.getValue(axis)));
                        find.set(true);
                    }
                });
            }
            if(name.contains("wood") && !name.contains("stripped")){
                woodMap.forEach((list, tree) ->{
                    if(list.contains(name) && !find.get()){
                        newState.set(tree.getStrippedWoodBlock().get().defaultBlockState().setValue(axis, state.getValue(axis)));
                        find.set(true);
                    }
                });
            }

            return newState.get();
        }
    }
}
