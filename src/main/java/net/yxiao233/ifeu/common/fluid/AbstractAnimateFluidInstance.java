package net.yxiao233.ifeu.common.fluid;

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

import javax.annotation.Nullable;
import java.util.function.Consumer;

public abstract class AbstractAnimateFluidInstance {
    private RegistryObject<FluidType> fluidType;
    private RegistryObject<Fluid> flowingFluid;
    private RegistryObject<Fluid> sourceFluid;
    private RegistryObject<Item> bucketFluid;
    private RegistryObject<Block> blockFluid;
    private final String fluid;

    public AbstractAnimateFluidInstance(DeferredRegistryHelper helper, String fluid, FluidType.Properties fluidTypeProperties, IClientFluidTypeExtensions renderProperties, @Nullable TitaniumTab group) {
        this.fluid = fluid;
        this.sourceFluid = helper.registerGeneric(ForgeRegistries.FLUIDS.getRegistryKey(), fluid, () -> {
            return setSource(this);
        });
        this.flowingFluid = helper.registerGeneric(ForgeRegistries.FLUIDS.getRegistryKey(), fluid + "_flowing", () -> {
            return setFlowing(this);
        });
        this.fluidType = helper.registerGeneric(ForgeRegistries.Keys.FLUID_TYPES, fluid, () -> {
            return new FluidType(fluidTypeProperties) {
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(renderProperties);
                }
            };
        });
        this.bucketFluid = helper.registerGeneric(ForgeRegistries.ITEMS.getRegistryKey(), fluid + "_bucket", () -> {
            BucketItem item = new BucketItem(this.sourceFluid, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1));
            if (group != null) {
                group.getTabList().add(item);
            }

            return item;
        });
        this.blockFluid = helper.registerGeneric(ForgeRegistries.BLOCKS.getRegistryKey(), fluid, () -> {
            return new LiquidBlock(() -> {
                return (FlowingFluid)this.sourceFluid.get();
            }, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
        });
    }

    public abstract AbstractAnimateFluid.Source<?> setSource(AbstractAnimateFluidInstance abstractAnimateFluidInstance);
    public abstract AbstractAnimateFluid.Flowing<?> setFlowing(AbstractAnimateFluidInstance abstractAnimateFluidInstance);

    public RegistryObject<FluidType> getFluidType() {
        return this.fluidType;
    }

    public RegistryObject<Fluid> getFlowingFluid() {
        return this.flowingFluid;
    }

    public RegistryObject<Fluid> getSourceFluid() {
        return this.sourceFluid;
    }

    public RegistryObject<Item> getBucketFluid() {
        return this.bucketFluid;
    }

    public RegistryObject<Block> getBlockFluid() {
        return this.blockFluid;
    }

    public String getFluid() {
        return this.fluid;
    }
}
