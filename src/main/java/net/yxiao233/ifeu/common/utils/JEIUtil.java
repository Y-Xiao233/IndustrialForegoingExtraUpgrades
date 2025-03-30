package net.yxiao233.ifeu.common.utils;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.List;

public class JEIUtil {
    private final  IJeiRuntime jeiRuntime;
    public JEIUtil(IJeiRuntime jeiRuntime){
        this.jeiRuntime = jeiRuntime;
    }
    public void removeItemStack(ItemStack stack){
        jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, Collections.singletonList(stack));
    }

    public void removeItemStack(RegistryObject<Item> reg){
        this.removeItemStack(reg.get().getDefaultInstance());
    }

    public void removeItemStack(Item item){
        this.removeItemStack(item.getDefaultInstance());
    }

    public <T extends Item> void removeItemStacks(List<T> items){
        items.forEach(this::removeItemStack);
    }
}
