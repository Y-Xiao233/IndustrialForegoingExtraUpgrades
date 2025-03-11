package net.yxiao233.ifeu.common.utils;

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
    public static int getAugmentIndex(InventoryComponent<?> augmentInventory, ItemStack stack){
        if(stack.getItem() instanceof IFEUAddonItem addonItem1){
            return getAugmentIndex(augmentInventory,addonItem1.getType());
        }
        return -1;
    }

    public static int getAugmentIndex(InventoryComponent<?> augmentInventory, Item item){
        return getAugmentIndex(augmentInventory,item.getDefaultInstance());
    }
    public static boolean contains(InventoryComponent<?> augmentInventory, ItemStack stack){
        return getAugmentIndex(augmentInventory,stack) != -1;
    }

    public static boolean canAccept(InventoryComponent<?> augmentInventory, ItemStack stack){
        return !contains(augmentInventory,stack);
    }

    public static int getAugmentTier(InventoryComponent<?> augmentInventory, IFEUAugmentTypes type){
        int index = getAugmentIndex(augmentInventory,type);
        return index == -1 ? 0 :((IFEUAddonItem) augmentInventory.getStackInSlot(index).getItem()).getTier();
    }
}
