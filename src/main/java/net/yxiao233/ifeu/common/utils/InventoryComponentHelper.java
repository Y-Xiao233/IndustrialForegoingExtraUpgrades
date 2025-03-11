package net.yxiao233.ifeu.common.utils;

import com.hrznstudio.titanium.block.tile.MachineTile;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InventoryComponentHelper {
    public static boolean canInsertAugment(MachineTile<?> tile, ItemStack stack){
        if(tile == null || stack == null){
            return false;
        }

        if(tile.canAcceptAugment(stack)){
            SidedInventoryComponent<?> augments = tile.getAugmentInventory();
            for (int i = 0; i < augments.getSlots(); i++) {
                ItemStack itemStack = augments.getStackInSlot(i);
                if(itemStack.isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canInsertAugment(MachineTile<?> tile, Item item){
        return canInsertAugment(tile,item.getDefaultInstance());
    }

    public static int getCanInsertIndex(MachineTile<?> tile, ItemStack stack){
        if(canInsertAugment(tile,stack)){
            SidedInventoryComponent<?> augments = tile.getAugmentInventory();
            int index = 0;
            for (int i = 0; i < augments.getSlots(); i++) {
                ItemStack itemStack = augments.getStackInSlot(i);
                if(itemStack.isEmpty()){
                    index = i;
                }
            }
            return index;
        }
        return -1;
    }

    public static int getCanInsertIndex(MachineTile<?> tile, Item item){
        return getCanInsertIndex(tile,item.getDefaultInstance());
    }

    public static void insertAugment(MachineTile<?> tile, ItemStack stack){
        int index = getCanInsertIndex(tile,stack);
        if(index != -1){
            SidedInventoryComponent<?> augments = tile.getAugmentInventory();
            augments.setStackInSlot(index,stack);
        }
    }
    public static void insertAugment(MachineTile<?> tile, Item item){
        insertAugment(tile,item.getDefaultInstance());
    }

    public static int canInsetMaxCount(InventoryComponent<?> inventory, ItemStack stack){
        int stackSize = stack.getMaxStackSize();
        int slots = inventory.getSlots();
        int count = 0;

        for (int i = 0; i < slots; i++) {
            if(inventory.getStackInSlot(i).isEmpty() || inventory.getStackInSlot(i).is(stack.getItem())){
                int temp = Math.min(stackSize,inventory.getSlotLimit(i)) - inventory.getStackInSlot(i).getCount();
                count = count + temp;
            }
        }
        return count;
    }

    public static int canInsetMaxCount(InventoryComponent<?> inventory, Item item){
        int stackSize = item.getDefaultMaxStackSize();
        int slots = inventory.getSlots();
        int count = 0;

        for (int i = 0; i < slots; i++) {
            if(inventory.getStackInSlot(i).isEmpty() || inventory.getStackInSlot(i).is(item)){
                int temp = Math.min(stackSize,inventory.getSlotLimit(i)) - inventory.getStackInSlot(i).getCount();
                count = count + temp;
            }
        }
        return count;
    }
}
