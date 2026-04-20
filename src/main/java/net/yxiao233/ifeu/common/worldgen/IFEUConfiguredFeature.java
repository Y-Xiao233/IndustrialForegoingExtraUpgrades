package net.yxiao233.ifeu.common.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.yxiao233.ifeu.api.tree.DeferredTree;

public class IFEUConfiguredFeature {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context){
        DeferredTree.DeferredTreesRegister.getAllTrees().forEach(tree ->{
            tree.registryConfiguredFeature(context);
        });
    }
}
