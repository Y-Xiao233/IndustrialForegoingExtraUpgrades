package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.block.IndustrialBlockItem;
import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.common.block.DragonStarGeneratorBlock;
import net.yxiao233.ifeu.common.block.InfuserBlock;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;

public class ModBlocks implements IModule {
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> INFUSER;
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> DRAGON_STAR_GENERATOR;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        INFUSER = helper.registerBlockWithTileItem("infuser", InfuserBlock::new,(blockRegistryObject) -> () -> new IndustrialBlockItem(blockRegistryObject.get(), ModItems.TAB_ADDONS), null);
        DRAGON_STAR_GENERATOR = helper.registerBlockWithTileItem("dragon_star_generator", DragonStarGeneratorBlock::new,(blockRegistryObject) -> () -> new IndustrialBlockItem(blockRegistryObject.get(), ModItems.TAB_ADDONS),null);
    }

    public static ArrayList<RegistryObject<Block>> addBlocksToCreativeModeTab(){
        ArrayList<RegistryObject<Block>> blocks = new ArrayList<>();

        blocks.add(INFUSER.getLeft());
        blocks.add(DRAGON_STAR_GENERATOR.getLeft());

        return blocks;
    }
}
