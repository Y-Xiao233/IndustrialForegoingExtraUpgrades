package net.yxiao233.ifeu.api.components;

import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IAssetType;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.block.tile.BasicTile;
import com.hrznstudio.titanium.client.screen.addon.LockableOverlayAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonInfo;
import com.hrznstudio.titanium.component.IComponentBundle;
import com.hrznstudio.titanium.component.IComponentHandler;
import com.hrznstudio.titanium.component.IComponentHarness;
import com.hrznstudio.titanium.component.bundle.LockableInventoryBundle;
import com.hrznstudio.titanium.component.button.ButtonComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.container.addon.IContainerAddon;
import com.hrznstudio.titanium.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.config.machine.BigDissolutionChamberConfig;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class IFEULockableInventoryBundle<T extends BasicTile & IComponentHarness> implements IComponentBundle, INBTSerializable<CompoundTag> {
    private InventoryComponent<T> inventory;
    private InventoryComponent<T> augmentInventory;
    private BiPredicate<ItemStack, Integer> cachedFilter;
    private ButtonComponent buttonAddon;
    private T componentHarness;
    private ItemStack[] filter;
    private int lockPosX;
    private int lockPosY;
    private boolean isLocked;

    public IFEULockableInventoryBundle(InventoryComponent<T> augmentInventory,T componentHarness, final InventoryComponent<T> inventory, int lockPosX, int lockPosY, boolean isLocked) {
        this.componentHarness = componentHarness;
        this.inventory = inventory;
        this.cachedFilter = inventory.getInsertPredicate();
        this.filter = new ItemStack[this.inventory.getSlots()];
        Arrays.fill(this.filter, ItemStack.EMPTY);
        this.lockPosX = lockPosX;
        this.lockPosY = lockPosY;
        this.isLocked = isLocked;
        this.buttonAddon = (new ButtonComponent(lockPosX, lockPosY, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    ButtonComponent var10003 = IFEULockableInventoryBundle.this.buttonAddon;
                    StateButtonInfo[] var10004 = new StateButtonInfo[2];
                    IAssetType var10010 = AssetTypes.BUTTON_UNLOCKED;
                    String[] var10011 = new String[1];
                    String var10014 = String.valueOf(ChatFormatting.GOLD);
                    var10011[0] = var10014 + LangUtil.getString("tooltip.titanium.locks", new Object[0]) + String.valueOf(ChatFormatting.WHITE) + " " + LangUtil.getString("tooltip.titanium.facing_handler." + inventory.getName().toLowerCase(), new Object[0]);
                    var10004[0] = new StateButtonInfo(0, var10010, var10011);
                    var10010 = AssetTypes.BUTTON_LOCKED;
                    var10011 = new String[1];
                    var10014 = String.valueOf(ChatFormatting.GOLD);
                    var10011[0] = var10014 + LangUtil.getString("tooltip.titanium.unlocks", new Object[0]) + String.valueOf(ChatFormatting.WHITE) + " " + LangUtil.getString("tooltip.titanium.facing_handler." + inventory.getName().toLowerCase(), new Object[0]);
                    var10004[1] = new StateButtonInfo(1, var10010, var10011);
                    return new StateButtonAddon(var10003, var10004) {
                        public int getState() {
                            return IFEULockableInventoryBundle.this.isLocked ? 1 : 0;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.isLocked = !this.isLocked;

            for(int i = 0; i < this.inventory.getSlots(); ++i) {
                this.filter[i] = this.inventory.getStackInSlot(i).copy();
            }

            this.updateFilter();
            this.componentHarness.syncObject(this);
        });
    }

    @Nonnull
    @OnlyIn(Dist.CLIENT)
    public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
        return this.inventory instanceof SidedInventoryComponent ? Collections.singletonList(() -> {
            return new LockableOverlayAddon((SidedInventoryComponent)this.inventory, this.lockPosX, this.lockPosY);
        }) : Collections.emptyList();
    }

    public void accept(IComponentHandler... handler) {
        IComponentHandler[] var2 = handler;
        int var3 = handler.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            IComponentHandler iComponentHandler = var2[var4];
            iComponentHandler.add(new Object[]{this.inventory, this.buttonAddon});
        }

    }

    @Nonnull
    public List<IFactory<? extends IContainerAddon>> getContainerAddons() {
        return Collections.emptyList();
    }

    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.put("Inventory", this.inventory.serializeNBT(provider));
        compoundNBT.putBoolean("Locked", this.isLocked);
        ListTag nbt = new ListTag();
        ItemStack[] var4 = this.filter;
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            ItemStack stack = var4[var6];
            nbt.add(stack.saveOptional(provider));
        }

        compoundNBT.put("Filter", nbt);
        return compoundNBT;
    }

    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.inventory.deserializeNBT(provider, nbt.getCompound("Inventory"));
        this.isLocked = nbt.getBoolean("Locked");
        ListTag list = (ListTag)nbt.get("Filter");
        this.filter = new ItemStack[list.size()];
        Arrays.fill(this.filter, ItemStack.EMPTY);

        for(int i = 0; i < list.size(); ++i) {
            this.filter[i] = ItemStack.parseOptional(provider, list.getCompound(i));
        }

        this.updateFilter();
    }

    private void updateFilter() {
        if (this.isLocked) {
            this.inventory.setInputFilter((stack, integer) -> {
                boolean bound = integer < this.filter.length;
                boolean isEmpty = this.filter[integer].isEmpty();
                boolean same = ItemStack.isSameItemSameComponents(this.filter[integer], stack);
                int count = this.inventory.getStackInSlot(integer).getCount();
                int max = this.filter[integer].getCount();
                if(BigDissolutionChamberConfig.inputRule == 1){
                    max = AugmentInventoryHelper.getAugmentTier(this.augmentInventory, IFEUAugmentTypes.THREAD) * 4 + BigDissolutionChamberConfig.maxThread;
                }
                boolean canInsert = count + stack.getCount() <= max;
                return bound && !isEmpty && same && canInsert;
            });
        } else {
            Arrays.fill(this.filter, ItemStack.EMPTY);
            this.inventory.setInputFilter(this.cachedFilter);
        }

        for(int i = 0; i < this.filter.length; ++i) {
            this.inventory.setSlotToItemStackRender(i, this.filter[i]);
        }

    }

    public InventoryComponent<T> getInventory() {
        return this.inventory;
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public ItemStack[] getFilter() {
        return this.filter;
    }

    public void setFilter(ItemStack[] filter) {
        this.filter = filter;
    }

    public void setLocked(boolean locked) {
        this.isLocked = locked;
    }

    public InventoryComponent<T> getAugmentInventory() {
        return augmentInventory;
    }
}
