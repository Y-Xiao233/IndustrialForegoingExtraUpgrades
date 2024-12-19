package net.yxiao233.ifeu.common.fluid.dragon_breath;

import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.common.fluid.AbstractAnimateFluid;
import net.yxiao233.ifeu.common.fluid.AbstractAnimateFluidInstance;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class LiquidDragonBreathParticleAnimateFluidInstance extends AbstractAnimateFluidInstance {
    public LiquidDragonBreathParticleAnimateFluidInstance(DeferredRegistryHelper helper, String fluid, FluidType.Properties fluidTypeProperties, IClientFluidTypeExtensions renderProperties, @Nullable TitaniumTab group) {
        super(helper, fluid, fluidTypeProperties, renderProperties, group);
    }

    @Override
    public AbstractAnimateFluid.Source setSource(AbstractAnimateFluidInstance atfi) {
        return new LiquidDragonBreathParticleAnimateFluid.Source(this);
    }

    @Override
    public AbstractAnimateFluid.Flowing setFlowing(AbstractAnimateFluidInstance atfi) {
        return new LiquidDragonBreathParticleAnimateFluid.Flowing(this);
    }
}
