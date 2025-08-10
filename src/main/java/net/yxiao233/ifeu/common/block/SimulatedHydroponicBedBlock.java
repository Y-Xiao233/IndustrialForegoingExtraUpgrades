package net.yxiao233.ifeu.common.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.module.ModuleAgricultureHusbandry;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yxiao233.ifeu.common.block.entity.SimulatedHydroponicBedEntity;
import net.yxiao233.ifeu.common.registry.IFEUContents;

import java.util.function.Consumer;

public class SimulatedHydroponicBedBlock extends IndustrialBlock<SimulatedHydroponicBedEntity> {

    public SimulatedHydroponicBedBlock() {
        super("simulated_hydroponic_bed", Properties.copy(Blocks.IRON_BLOCK), SimulatedHydroponicBedEntity.class, ModuleAgricultureHusbandry.TAB_AG_HUS);
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<SimulatedHydroponicBedEntity> getTileEntityFactory() {
        return SimulatedHydroponicBedEntity::new;
    }

    @Override
    public void registerRecipe(Consumer<FinishedRecipe> consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this)
                .pattern("PDP").pattern("SBS").pattern("GMG")
                .define('P', IndustrialTags.Items.PLASTIC)
                .define('D', IFEUContents.HYDROPONIC_SIMULATION_PROCESSOR.get())
                .define('G', IndustrialTags.Items.GEAR_GOLD)
                .define('S', Items.IRON_HOE)
                .define('B', ModuleCore.FERTILIZER.get())
                .define('M', IndustrialTags.Items.MACHINE_FRAME_SIMPLE)
                .save(consumer);
    }
}