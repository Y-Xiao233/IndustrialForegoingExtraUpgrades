package net.yxiao233.ifeu.common.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.yxiao233.ifeu.api.tree.DeferredTree;

public class IFEUPlacedFeatures {
    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        DeferredTree.DeferredTreesRegister.getAllTrees().forEach(tree ->{
            tree.registryPlacedFeature(context);
        });
    }
}
