package net.yxiao233.ifeu.common.registry;

import com.buuz135.industrial.block.IndustrialBlockItem;
import com.buuz135.industrial.module.IModule;
import com.hrznstudio.titanium.fluid.ClientFluidTypeExtensions;
import com.hrznstudio.titanium.fluid.TitaniumFluidInstance;
import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.block.InfuserBlock;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;

public class ModBlocks implements IModule {
    public static Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> INFUSER;
    @Override
    public void generateFeatures(DeferredRegistryHelper helper) {
        INFUSER = helper.registerBlockWithTileItem("infuser", InfuserBlock::new,(blockRegistryObject) -> () -> new IndustrialBlockItem(blockRegistryObject.get(), ModItems.TAB_ADDONS), null);
    }

    public static ArrayList<RegistryObject<Block>> addBlocksToCreativeModeTab(){
        ArrayList<RegistryObject<Block>> blocks = new ArrayList<>();

        blocks.add(INFUSER.getLeft());

        return blocks;
    }
}
