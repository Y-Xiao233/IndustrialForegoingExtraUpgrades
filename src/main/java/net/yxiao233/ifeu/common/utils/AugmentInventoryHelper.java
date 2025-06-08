package net.yxiao233.ifeu.common.utils;

import com.hrznstudio.titanium.block.tile.MachineTile;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;

public class AugmentInventoryHelper {

    public static int getAugmentIndex(InventoryComponent<?> augmentInventory, IFEUAugmentTypes type){
        for (int i = 0; i < augmentInventory.getSlots(); i++) {
            if(augmentInventory.getStackInSlot(i).getItem() instanceof IFEUAddonItem addonItem){
                if(addonItem.getType() == type){
                    return i;
                }
            }
        }
        return -1;
    }

    public static int getAugmentIndex(MachineTile<?> tile, IFEUAugmentTypes type){
        return getAugmentIndex(tile.getAugmentInventory(),type);
    }

    public static int getAugmentIndex(InventoryComponent<?> augmentInventory, ItemStack stack){
        if(stack.getItem() instanceof IFEUAddonItem addonItem1){
            return getAugmentIndex(augmentInventory,addonItem1.getType());
        }
        return -1;
    }

    public static int getAugmentIndex(MachineTile<?> tile, ItemStack stack){
        return getAugmentIndex(tile.getAugmentInventory(),stack);
    }

    public static int getAugmentIndex(InventoryComponent<?> augmentInventory, Item item){
        return getAugmentIndex(augmentInventory,item.getDefaultInstance());
    }

    public static int getAugmentIndex(MachineTile<?> tile, Item item){
        return getAugmentIndex(tile.getAugmentInventory(),item);
    }
    public static boolean contains(InventoryComponent<?> augmentInventory, ItemStack stack){
        return getAugmentIndex(augmentInventory,stack) != -1;
    }

    public static boolean contains(MachineTile<?> tile, IFEUAugmentTypes type){
        return getAugmentIndex(tile,type) != -1;
    }

    public static boolean contains(MachineTile<?> tile, ItemStack stack){
        return contains(tile.getAugmentInventory(),stack);
    }

    public static boolean canAccept(InventoryComponent<?> augmentInventory, ItemStack stack){
        return !contains(augmentInventory,stack);
    }

    public static boolean canAccept(MachineTile<?> tile, ItemStack stack){
        return canAccept(tile.getAugmentInventory(),stack);
    }

    public static int getAugmentTier(InventoryComponent<?> augmentInventory, IFEUAugmentTypes type){
        int index = getAugmentIndex(augmentInventory,type);
        return index == -1 ? 0 :((IFEUAddonItem) augmentInventory.getStackInSlot(index).getItem()).getTier();
    }

    public static int getAugmentTier(MachineTile<?> tile, IFEUAugmentTypes type){
        return getAugmentTier(tile.getAugmentInventory(),type);
    }
}
