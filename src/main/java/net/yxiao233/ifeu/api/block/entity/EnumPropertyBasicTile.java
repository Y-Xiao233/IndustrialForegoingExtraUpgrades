package net.yxiao233.ifeu.api.block.entity;

import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.block.tile.PoweredTile;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.MultiTankComponent;
import com.hrznstudio.titanium.component.inventory.MultiInventoryComponent;
import com.hrznstudio.titanium.container.BasicAddonContainer;
import com.hrznstudio.titanium.module.BlockWithTile;
import com.hrznstudio.titanium.network.locator.LocatorFactory;
import com.hrznstudio.titanium.network.locator.instance.TileEntityLocatorInstance;
import com.hrznstudio.titanium.util.FacingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.yxiao233.ifeu.api.block.IEnumProperty;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class EnumPropertyBasicTile <T extends EnumPropertyBasicTile<T, E>, E extends Enum<E> & IEnumProperty<E>> extends PoweredTile<T> {
    @SuppressWarnings({"unchecked","rawtypes"})
    public EnumPropertyBasicTile(BlockWithTile blockWithTile, BlockPos pos, BlockState state) {
        super((BasicTileBlock)blockWithTile.getBlock(), blockWithTile.type().get(), pos, state);
    }

    protected boolean isMainProperty(BlockState state){
        if(state.hasProperty(getProperty())){
            return state.getValue(getProperty()) == getDefault();
        }
        return false;
    }

    protected BlockPos getMainPropertyPos(BlockState state){
        if(level != null && state.hasProperty(getProperty())){
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
    public MultiInventoryComponent<T> getItemComponent(){
        BlockEntity entity = getMainPropertyEntity(getBlockState());
        if(entity instanceof EnumPropertyBasicTile<?,?> tile){
            return (MultiInventoryComponent<T>) tile.getMultiInventoryComponent();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public MultiTankComponent<T> getFluidComponent(){
        BlockEntity entity = getMainPropertyEntity(getBlockState());
        if(entity instanceof EnumPropertyBasicTile<?,?> tile){
            return (MultiTankComponent<T>) tile.getMultiTankComponent();
        }
        return null;
    }

    @Override
    public IFluidHandler getFluidHandler(@Nullable Direction direction) {
        return getFluidComponent() == null ? null : getFluidComponent().getCapabilityForSide(FacingUtil.getFacingRelative(this.getFacingDirection(), direction)).orElse(null);
    }

    @Override
    public IItemHandler getItemHandler(@Nullable Direction direction) {
        return getItemComponent() == null ? null : getItemComponent().getCapabilityForSide(FacingUtil.getFacingRelative(this.getFacingDirection(), direction)).orElse(null);
    }

    @Override
    public ItemInteractionResult onActivated(@NotNull Player player, @NotNull InteractionHand hand, @NotNull Direction facing, double hitX, double hitY, double hitZ) {
        if(getFluidHandler(facing) != null && FluidUtil.interactWithFluidHandler(player, hand, getFluidHandler(facing))){
            return ItemInteractionResult.SUCCESS;
        }else{
            openGui(player);
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    public void openGui(@NotNull Player player) {
        if(player instanceof ServerPlayer serverPlayer) {
            BlockEntity main = getMainPropertyEntity(getBlockState());
            if(main instanceof EnumPropertyBasicTile<?,?> tile){
                serverPlayer.openMenu(tile, (buffer) -> {
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
            return new BasicAddonContainer(tile, new TileEntityLocatorInstance(pos), this.getWorldPosCallable(pos), inventoryPlayer, menu);
        }
        return null;
    }

    public ContainerLevelAccess getWorldPosCallable(BlockPos pos) {
        return this.getLevel() != null ? ContainerLevelAccess.create(this.getLevel(), pos) : ContainerLevelAccess.NULL;
    }
}
