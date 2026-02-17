package net.yxiao233.ifeu.api.block.entity;

import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.block.tile.PoweredTile;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.MultiTankComponent;
import com.hrznstudio.titanium.component.inventory.MultiInventoryComponent;
import com.hrznstudio.titanium.container.BasicAddonContainer;
import com.hrznstudio.titanium.network.locator.LocatorFactory;
import com.hrznstudio.titanium.network.locator.instance.TileEntityLocatorInstance;
import com.hrznstudio.titanium.util.FacingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.api.block.IEnumProperty;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public abstract class EnumPropertyBasicTile<T extends EnumPropertyBasicTile<T, E>, E extends Enum<E> & IEnumProperty<E>> extends PoweredTile<T>{
    private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(this::getEnergy);
    @SuppressWarnings({"unchecked","rawtypes"})
    public EnumPropertyBasicTile(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> blockWithTile, BlockPos pos, BlockState state) {
        super((BasicTileBlock)((RegistryObject) blockWithTile.getLeft()).get(), (BlockEntityType)((RegistryObject)blockWithTile.getRight()).get(), pos, state);
    }

    protected boolean isMainProperty(BlockState state){
        if(state.hasProperty(getProperty())){
            return state.getValue(getProperty()) == getDefault();
        }
        return false;
    }

    protected BlockPos getMainPropertyPos(BlockState state){
        if(state.hasProperty(getProperty())){
            Direction direction = getFacingDirection().getOpposite();
            return state.getValue(getProperty()).getMainPos(getBlockPos(),direction);
        }
        return null;
    }

    protected BlockEntity getMainPropertyEntity(BlockState state){
        BlockPos pos = getMainPropertyPos(state);
        if(pos != null){
            return getComponentWorld().getBlockEntity(pos);
        }
        return null;
    }
    protected abstract E getDefault();
    protected abstract Property<E> getProperty();

    @SuppressWarnings("unchecked")
    public EnergyStorageComponent<T> getEnergy() {
        BlockEntity entity = getMainPropertyEntity(getBlockState());
        if(entity instanceof EnumPropertyBasicTile<?,?> tile){
            return (EnergyStorageComponent<T>) tile.getEnergyStorage();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public MultiInventoryComponent<T> getItemHandler(){
        BlockEntity entity = getMainPropertyEntity(getBlockState());
        if(entity instanceof EnumPropertyBasicTile<?,?> tile){
            return (MultiInventoryComponent<T>) tile.getMultiInventoryComponent();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public MultiTankComponent<T> getFluidHandler(){
        BlockEntity entity = getMainPropertyEntity(getBlockState());
        if(entity instanceof EnumPropertyBasicTile<?,?> tile){
            return (MultiTankComponent<T>) tile.getMultiTankComponent();
        }
        return null;
    }

    @NotNull
    @Override
    public <U> LazyOptional<U> getCapability(@NotNull Capability<U> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER && this.getItemHandler() != null) {
            return this.getItemHandler().getCapabilityForSide(FacingUtil.getFacingRelative(this.getFacingDirection(), side)).cast();
        }else if(cap == ForgeCapabilities.FLUID_HANDLER && this.getFluidHandler() != null) {
            return this.getFluidHandler().getCapabilityForSide(FacingUtil.getFacingRelative(this.getFacingDirection(), side)).cast();
        }else if(cap == ForgeCapabilities.ENERGY){
            return this.lazyOptional.cast();
        }
        return LazyOptional.empty();
    }

    public void invalidateCaps() {
        super.invalidateCaps();
        this.lazyOptional.invalidate();
    }

    @Override
    public InteractionResult onActivated(@NotNull Player player, @NotNull InteractionHand hand, @NotNull Direction facing, double hitX, double hitY, double hitZ) {
        if(getFluidHandler() != null && FluidUtil.interactWithFluidHandler(player, hand, getFluidHandler().getCapabilityForSide(null).orElse(new MultiTankComponent.MultiTankCapabilityHandler<>(new ArrayList<>())))){
            return InteractionResult.SUCCESS;
        }else{
            openGui(player);
        }
        return InteractionResult.SUCCESS;
    }

    public void openGui(@NotNull Player player) {
        if (player instanceof ServerPlayer) {
            BlockEntity main = getMainPropertyEntity(getBlockState());
            if(main instanceof EnumPropertyBasicTile<?,?> tile){
                NetworkHooks.openScreen((ServerPlayer)player, tile, (buffer) -> {
                    LocatorFactory.writePacketBuffer(buffer, new TileEntityLocatorInstance(tile.worldPosition));
                });
            }
        }

    }

    @javax.annotation.Nullable
    public AbstractContainerMenu createMenu(int menu, @NotNull Inventory inventoryPlayer, @NotNull Player entityPlayer) {
        BlockEntity main = getMainPropertyEntity(getBlockState());
        if(main instanceof EnumPropertyBasicTile<?,?> tile){
            BlockPos pos = tile.worldPosition;
            return new BasicAddonContainer(this, new TileEntityLocatorInstance(pos), this.getWorldPosCallable(pos), inventoryPlayer, menu);
        }
        return null;
    }

    public ContainerLevelAccess getWorldPosCallable(BlockPos pos) {
        return this.getLevel() != null ? ContainerLevelAccess.create(this.getLevel(), pos) : ContainerLevelAccess.NULL;
    }
}
