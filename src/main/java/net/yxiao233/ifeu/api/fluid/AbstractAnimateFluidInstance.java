package net.yxiao233.ifeu.api.fluid;

import com.hrznstudio.titanium.module.DeferredRegistryHelper;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.core.registries.Registries;
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
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public abstract class AbstractAnimateFluidInstance {
    private DeferredHolder<FluidType,FluidType> fluidType;
    private DeferredHolder<Fluid,Fluid> flowingFluid;
    private DeferredHolder<Fluid,Fluid> sourceFluid;
    private DeferredHolder<Item,Item> bucketFluid;
    private DeferredHolder<Block,Block> blockFluid;
    private final String fluid;

    public AbstractAnimateFluidInstance(DeferredRegistryHelper helper, String fluid, FluidType.Properties fluidTypeProperties, IClientFluidTypeExtensions renderProperties, @Nullable TitaniumTab group) {
        this.fluid = fluid;
        this.sourceFluid = helper.registerGeneric(Registries.FLUID, fluid, () -> {
            return setSource(this);
        });
        this.flowingFluid = helper.registerGeneric(Registries.FLUID, fluid + "_flowing", () -> {
            return setFlowing(this);
        });
        this.fluidType = helper.registerGeneric(NeoForgeRegistries.FLUID_TYPES.key(), fluid, () -> {
            return new FluidType(fluidTypeProperties) {
                @Override
                @SuppressWarnings("removal")
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(renderProperties);
                }
            };
        });
        this.bucketFluid = helper.registerGeneric(Registries.ITEM, fluid + "_bucket", () -> {
            BucketItem item = new BucketItem(this.sourceFluid.get(), (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1));
            if (group != null) {
                group.getTabList().add(item);
            }

            return item;
        });
        this.blockFluid = helper.registerGeneric(Registries.BLOCK, fluid, () -> {
            return new LiquidBlock((FlowingFluid)this.sourceFluid.get(), BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
        });
    }

    public abstract AbstractAnimateFluid.Source<?> setSource(AbstractAnimateFluidInstance abstractAnimateFluidInstance);
    public abstract AbstractAnimateFluid.Flowing<?> setFlowing(AbstractAnimateFluidInstance abstractAnimateFluidInstance);

    public DeferredHolder<FluidType,FluidType> getFluidType() {
        return this.fluidType;
    }

    public DeferredHolder<Fluid,Fluid> getFlowingFluid() {
        return this.flowingFluid;
    }

    public DeferredHolder<Fluid,Fluid> getSourceFluid() {
        return this.sourceFluid;
    }

    public DeferredHolder<Item,Item> getBucketFluid() {
        return this.bucketFluid;
    }

    public DeferredHolder<Block,Block> getBlockFluid() {
        return this.blockFluid;
    }

    public String getFluid() {
        return this.fluid;
    }
}
