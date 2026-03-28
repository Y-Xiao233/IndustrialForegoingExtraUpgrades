package net.yxiao233.ifeu.common.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.yxiao233.ifeu.api.tree.DeferredTree;

public class IFEUBiomeModifier {
    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        DeferredTree.DeferredTreesRegister.getAllTrees().forEach(tree ->{
            tree.registryBiomeModifier(context);
        });
    }
}
