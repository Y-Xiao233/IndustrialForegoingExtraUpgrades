package net.yxiao233.ifeu.api.block.entity;

import com.buuz135.industrial.api.IMachineSettings;
import com.buuz135.industrial.item.addon.ProcessingAddonItem;
import com.buuz135.industrial.item.addon.RangeAddonItem;
import com.buuz135.industrial.proxy.client.IndustrialAssetProvider;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.augment.AugmentTypes;
import com.hrznstudio.titanium.api.filter.IFilter;
import com.hrznstudio.titanium.api.redstone.IRedstoneState;
import com.hrznstudio.titanium.block.redstone.RedstoneAction;
import com.hrznstudio.titanium.block.redstone.RedstoneManager;
import com.hrznstudio.titanium.block.redstone.RedstoneState;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.component.bundle.TankInteractionBundle;
import com.hrznstudio.titanium.component.button.RedstoneControlButtonComponent;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.sideness.IFacingComponent;
import com.hrznstudio.titanium.item.AugmentWrapper;
import com.hrznstudio.titanium.nbthandler.NBTManager;
import com.hrznstudio.titanium.util.FacingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.api.block.IEnumProperty;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public abstract class EnumPropertyIndustrialMachineTile<T extends EnumPropertyIndustrialMachineTile<T, E>, E extends Enum<E> & IEnumProperty<E>> extends EnumPropertyMachineTile<T, E>{
    private static final String settingsAddons = "MACHINE_ADDONS";
    private static final String redstoneMode = "REDSTONE_MODE";
    private static final String sidenessTank = "SIDENESS_TANK";
    private static final String sidenessInventory = "SIDENESS_INVENTORY";
    private static final String filter = "FILTER";
    @Save
    private TankInteractionBundle<EnumPropertyIndustrialMachineTile<T,E>> tankBundle;
    @Save
    private final RedstoneManager<RedstoneAction> redstoneManager;
    private final RedstoneControlButtonComponent<RedstoneAction> redstoneButton;
    private boolean tankBundleAdded;
    public EnumPropertyIndustrialMachineTile(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> blockWithTile, BlockPos pos, BlockState state) {
        super(blockWithTile, pos, state);
        this.redstoneManager = new RedstoneManager<>(RedstoneAction.IGNORE, false);
        this.tankBundleAdded = false;
        this.addButton(this.redstoneButton = new RedstoneControlButtonComponent<>(154, 84, 14, 14, () -> {
            return this.redstoneManager;
        }, () -> {
            return this;
        }));
    }

    public void addTank(@NotNull FluidTankComponent<T> tank) {
        super.addTank(tank);
        if (!this.tankBundleAdded) {
            this.addBundle(this.tankBundle = new TankInteractionBundle<>(() -> {
                return this.getCapability(ForgeCapabilities.FLUID_HANDLER);
            }, 175, 94, this, 10));
            this.tankBundleAdded = true;
        }
    }

    public boolean canAcceptAugment(ItemStack augment) {
        if (AugmentWrapper.hasType(augment, AugmentTypes.SPEED)) {
            return !this.hasAugmentInstalled(AugmentTypes.SPEED);
        } else if (AugmentWrapper.hasType(augment, AugmentTypes.EFFICIENCY)) {
            return !this.hasAugmentInstalled(AugmentTypes.EFFICIENCY);
        } else if (AugmentWrapper.hasType(augment, ProcessingAddonItem.PROCESSING)) {
            return !this.hasAugmentInstalled(ProcessingAddonItem.PROCESSING);
        } else if (AugmentWrapper.hasType(augment, RangeAddonItem.RANGE)) {
            return !this.hasAugmentInstalled(RangeAddonItem.RANGE);
        } else {
            return false;
        }
    }

    public RedstoneManager<RedstoneAction> getRedstoneManager() {
        BlockEntity entity = getMainPropertyEntity(getBlockState());
        if(entity instanceof EnumPropertyIndustrialMachineTile<?,?> tile){
            return tile.redstoneManager;
        }
        return null;
    }

    public IRedstoneState getEnvironmentValue(boolean strongPower, Direction direction) {
        if (strongPower) {
            if (direction == null) {
                return getComponentWorld().hasNeighborSignal(this.worldPosition) ? RedstoneState.ON : RedstoneState.OFF;
            } else {
                return getComponentWorld().hasSignal(this.worldPosition, direction) ? RedstoneState.ON : RedstoneState.OFF;
            }
        } else {
            return getComponentWorld().getBestNeighborSignal(this.worldPosition) > 0 ? RedstoneState.ON : RedstoneState.OFF;
        }
    }

    public void onNeighborChanged(@NotNull Block blockIn, @NotNull BlockPos fromPos) {
        super.onNeighborChanged(blockIn, fromPos);
        this.redstoneManager.setLastRedstoneState(this.getEnvironmentValue(false, (Direction)null).isReceivingRedstone());
    }

    public IAssetProvider getAssetProvider() {
        return IndustrialAssetProvider.INSTANCE;
    }

    @SuppressWarnings("all")
    public void loadSettings(Player player, CompoundTag tag) {
        if (tag.contains(settingsAddons)) {
            List<ItemStack> stacks = IMachineSettings.readInventory(tag.getCompound(settingsAddons));
            Iterator<ItemStack> var4 = stacks.iterator();

            label108:
            while(true) {
                while(true) {
                    ItemStack stack;
                    do {
                        if (!var4.hasNext()) {
                            break label108;
                        }

                        stack = var4.next();
                    } while(!this.canAcceptAugment(stack));

                    for (ItemStack stackPlayer : player.getInventory().items) {
                        if (ItemStack.isSameItem(stack, stackPlayer)) {
                            ItemStack copiedStack = ItemHandlerHelper.copyStackWithSize(stackPlayer, 1);
                            if (ItemHandlerHelper.insertItem(this.getAugmentInventory(), copiedStack, false).isEmpty()) {
                                stackPlayer.shrink(1);
                            }
                            break;
                        }
                    }
                }
            }
        }

        if (tag.contains(redstoneMode)) {
            NBTManager.getInstance().readTileEntity(this, tag.getCompound(redstoneMode));
        }

        Iterator var9;
        CompoundTag intermediateTag;
        Iterator var16;
        String allKey;
        if (tag.contains(sidenessInventory)) {
            var9 = this.getMultiInventoryComponent().getInventoryHandlers().iterator();

            label88:
            while(true) {
                SidedInventoryComponent sided;
                do {
                    InventoryComponent inventoryHandler;
                    do {
                        if (!var9.hasNext()) {
                            break label88;
                        }

                        inventoryHandler = (InventoryComponent)var9.next();
                    } while(!(inventoryHandler instanceof SidedInventoryComponent));

                    sided = (SidedInventoryComponent)inventoryHandler;
                } while(!tag.getCompound(sidenessInventory).contains(sided.getName()));

                intermediateTag = tag.getCompound(sidenessInventory).getCompound(sided.getName());
                var16 = intermediateTag.getAllKeys().iterator();

                while(var16.hasNext()) {
                    allKey = (String)var16.next();
                    sided.getFacingModes().put(FacingUtil.Sideness.valueOf(allKey), IFacingComponent.FaceMode.valueOf(intermediateTag.getString(allKey)));
                }
            }
        }

        if (tag.contains(sidenessTank)) {
            var9 = this.getMultiTankComponent().getTanks().iterator();

            label68:
            while(true) {
                SidedFluidTankComponent sided;
                do {
                    FluidTankComponent fluidTankComponent;
                    do {
                        if (!var9.hasNext()) {
                            break label68;
                        }

                        fluidTankComponent = (FluidTankComponent)var9.next();
                    } while(!(fluidTankComponent instanceof SidedFluidTankComponent));

                    sided = (SidedFluidTankComponent)fluidTankComponent;
                } while(!tag.getCompound(sidenessTank).contains(sided.getName()));

                intermediateTag = tag.getCompound(sidenessTank).getCompound(sided.getName());
                var16 = intermediateTag.getAllKeys().iterator();

                while(var16.hasNext()) {
                    allKey = (String)var16.next();
                    sided.getFacingModes().put(FacingUtil.Sideness.valueOf(allKey), IFacingComponent.FaceMode.valueOf(intermediateTag.getString(allKey)));
                }
            }
        }

        if (tag.contains(filter)) {
            var9 = this.getMultiFilterComponent().getFilters().iterator();

            while(var9.hasNext()) {
                IFilter iFilter = (IFilter)var9.next();
                if (tag.getCompound(filter).contains(iFilter.getName())) {
                    iFilter.deserializeNBT(tag.getCompound(filter).getCompound(iFilter.getName()));
                }
            }
        }

        this.markForUpdate();
    }

    @SuppressWarnings("all")
    public void saveSettings(Player player, CompoundTag tag) {
        tag.put(settingsAddons, IMachineSettings.writeInventory(this.getAugmentInventory()));
        tag.put(redstoneMode, NBTManager.getInstance().writeTileEntityObject(this, this.redstoneManager, new CompoundTag()));
        CompoundTag sideTankTag;
        Iterator var4;
        CompoundTag intermediateTag;
        Iterator var8;
        FacingUtil.Sideness facing;
        if (this.getMultiInventoryComponent() != null) {
            sideTankTag = new CompoundTag();
            var4 = this.getMultiInventoryComponent().getInventoryHandlers().iterator();

            label69:
            while(true) {
                InventoryComponent inventoryHandler;
                do {
                    if (!var4.hasNext()) {
                        tag.put(sidenessInventory, sideTankTag);
                        break label69;
                    }

                    inventoryHandler = (InventoryComponent)var4.next();
                } while(!(inventoryHandler instanceof SidedInventoryComponent));

                SidedInventoryComponent<T> sided = (SidedInventoryComponent)inventoryHandler;
                intermediateTag = new CompoundTag();
                var8 = sided.getFacingModes().keySet().iterator();

                while(var8.hasNext()) {
                    facing = (FacingUtil.Sideness)var8.next();
                    intermediateTag.putString(facing.name(), ((IFacingComponent.FaceMode)sided.getFacingModes().get(facing)).name());
                }

                sideTankTag.put(sided.getName(), intermediateTag);
            }
        }

        if (this.getMultiTankComponent() != null) {
            sideTankTag = new CompoundTag();
            var4 = this.getMultiTankComponent().getTanks().iterator();

            label51:
            while(true) {
                FluidTankComponent fluidTankComponent;
                do {
                    if (!var4.hasNext()) {
                        tag.put(sidenessTank, sideTankTag);
                        break label51;
                    }

                    fluidTankComponent = (FluidTankComponent)var4.next();
                } while(!(fluidTankComponent instanceof SidedFluidTankComponent));

                SidedFluidTankComponent<T> sided = (SidedFluidTankComponent)fluidTankComponent;
                intermediateTag = new CompoundTag();
                var8 = sided.getFacingModes().keySet().iterator();

                while(var8.hasNext()) {
                    facing = (FacingUtil.Sideness)var8.next();
                    intermediateTag.putString(facing.name(), ((IFacingComponent.FaceMode)sided.getFacingModes().get(facing)).name());
                }

                sideTankTag.put(sided.getName(), intermediateTag);
            }
        }

        if (this.getMultiFilterComponent() != null) {
            sideTankTag = new CompoundTag();
            var4 = this.getMultiFilterComponent().getFilters().iterator();

            while(var4.hasNext()) {
                IFilter iFilter = (IFilter)var4.next();
                sideTankTag.put(iFilter.getName(), iFilter.serializeNBT());
            }

            tag.put(filter, sideTankTag);
        }

    }
}
