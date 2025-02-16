package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapelessRecipe extends SerializableRecipe {
    public static List<ShapelessRecipe> RECIPES = new ArrayList<>();
    public Ingredient.Value[] inputs;
    public FluidStack inputFluid;
    public ItemStack output;
    public ShapelessRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public ShapelessRecipe(ResourceLocation resourceLocation, Ingredient.Value[] inputs, FluidStack inputFluid, ItemStack output) {
        super(resourceLocation);
        this.inputs = inputs;
        this.inputFluid = inputFluid;
        this.output = output;
        this.output.getItem().onCraftedBy(this.output,null,null);
        RECIPES.add(this);
    }
    public boolean matches(InventoryComponent<FluidCraftingTableEntity> inputs, FluidTankComponent<FluidCraftingTableEntity> tank) {
        if (this.inputs != null && tank != null && this.inputFluid != null) {
            List<ItemStack> handlerItems = new ArrayList<>();

            for(int i = 0; i < inputs.getSlots(); ++i) {
                if (!inputs.getStackInSlot(i).isEmpty()) {
                    handlerItems.add(inputs.getStackInSlot(i).copy());
                }
            }

            Ingredient.Value[] var12 = this.inputs;
            int var5 = var12.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Ingredient.Value iItemList = var12[var6];
                boolean found = false;
                Iterator<ItemStack> var9 = iItemList.getItems().iterator();

                while(var9.hasNext()) {
                    ItemStack stack = var9.next();

                    int i;
                    for(i = 0; i < handlerItems.size(); ++i) {
                        if (ItemStack.isSameItem(handlerItems.get(i), stack)) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        handlerItems.remove(i);
                        break;
                    }
                }

                if (!found) {
                    return false;
                }
            }

            return handlerItems.isEmpty() && tank.drainForced(this.inputFluid, IFluidHandler.FluidAction.SIMULATE).getAmount() == this.inputFluid.getAmount();
        } else {
            return false;
        }
    }
    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public GenericSerializer<? extends SerializableRecipe> getSerializer() {
        return (GenericSerializer<? extends SerializableRecipe>) ModRecipes.SHAPELESS_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.SHAPELESS_TYPE.get();
    }
}
