package net.yxiao233.ifeu.api.block.entity;

import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.IMachine;
import com.hrznstudio.titanium.api.augment.IAugmentType;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.AssetScreenAddon;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.sideness.IFacingComponent;
import com.hrznstudio.titanium.item.AugmentWrapper;
import com.hrznstudio.titanium.module.BlockWithTile;
import com.hrznstudio.titanium.util.FacingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.yxiao233.ifeu.api.block.IEnumProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EnumPropertyMachineTile <T extends EnumPropertyMachineTile<T, E>, E extends Enum<E> & IEnumProperty<E>> extends EnumPropertyBasicTile<T,E> implements IMachine {
    @Save
    private final SidedInventoryComponent<T> augmentInventory;
    public EnumPropertyMachineTile(BlockWithTile blockWithTile, BlockPos pos, BlockState state) {
        super(blockWithTile, pos, state);
        this.addInventory(this.augmentInventory = (SidedInventoryComponent<T>) this.getAugmentFactory().create().setComponentHarness(this.getSelf()).setInputFilter((stack, integer) -> {
            return AugmentWrapper.isAugment(stack) && this.canAcceptAugment(stack);
        }));
        FacingUtil.Sideness[] values = FacingUtil.Sideness.values();

        for (FacingUtil.Sideness value : values) {
            this.augmentInventory.getFacingModes().put(value, IFacingComponent.FaceMode.NONE);
        }
    }
    @OnlyIn(Dist.CLIENT)
    public void initClient() {
        super.initClient();
        this.addGuiAddonFactory(this.getAugmentBackground());
    }
    public boolean isActive() {
        return false;
    }

    public boolean isPaused() {
        return false;
    }

    public boolean canAcceptAugment(ItemStack augment) {
        return true;
    }

    public List<ItemStack> getInstalledAugments() {
        return this.getItemStackAugments().stream().filter(AugmentWrapper::isAugment).collect(Collectors.toList());
    }

    public List<ItemStack> getInstalledAugments(IAugmentType filter) {
        return this.getItemStackAugments().stream().filter(AugmentWrapper::isAugment).filter((stack) -> {
            return AugmentWrapper.hasType(stack, filter);
        }).collect(Collectors.toList());
    }

    public boolean hasAugmentInstalled(IAugmentType augmentType) {
        return !this.getInstalledAugments(augmentType).isEmpty();
    }

    public IFactory<InventoryComponent<T>> getAugmentFactory() {
        return () -> {
            return (new SidedInventoryComponent<T>("augments", 180, 11, 4, 0)).disableFacingAddon().setColor(DyeColor.PURPLE).setSlotLimit(1).setRange(1, 4);
        };
    }

    @OnlyIn(Dist.CLIENT)
    public IFactory<? extends IScreenAddon> getAugmentBackground() {
        return () -> {
            return new AssetScreenAddon(AssetTypes.AUGMENT_BACKGROUND, 175, 4, true);
        };
    }

    private List<ItemStack> getItemStackAugments() {
        List<ItemStack> augments = new ArrayList<>();

        for(int i = 0; i < getAugmentInventory().getSlots(); ++i) {
            augments.add(getAugmentInventory().getStackInSlot(i));
        }

        return augments;
    }

    public SidedInventoryComponent<T> getWrapperAugmentInventory(){
        return this.augmentInventory;
    }
    @SuppressWarnings("unchecked")
    public SidedInventoryComponent<T> getAugmentInventory() {
        BlockEntity entity = getMainPropertyEntity(getBlockState());
        if(entity instanceof EnumPropertyMachineTile<?,?> tile){
            return (SidedInventoryComponent<T>) tile.augmentInventory;
        }
        return null;
    }
}
