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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FluidCraftingTableRecipe extends SerializableRecipe {
    public static List<FluidCraftingTableRecipe> RECIPES = new ArrayList<>();
    public Ingredient.Value[] inputs;
    public FluidStack inputFluid;
    public ItemStack output;
    public FluidCraftingTableRecipe(ResourceLocation resourceLocation){
        super(resourceLocation);
    }
    public FluidCraftingTableRecipe(ResourceLocation resourceLocation, Ingredient.Value[] inputs, FluidStack inputFluid, ItemStack output) {
        super(resourceLocation);
        this.inputs = inputs;
        this.inputFluid = inputFluid;
        this.output = output;
        this.output.getItem().onCraftedBy(this.output,null,null);
        RECIPES.add(this);
    }
    public boolean matches(NonNullList<InventoryComponent<FluidCraftingTableEntity>> inputs, FluidTankComponent<FluidCraftingTableEntity> tank) {
        if (this.inputs != null && tank != null && this.inputFluid != null) {
            NonNullList<Boolean> matches = NonNullList.withSize(9, false);

            for (int i = 0; i < this.inputs.length; i++) {
                Iterator<ItemStack> iterator = this.inputs[i].getItems().iterator();

                if(iterator.hasNext()){
                    ItemStack stack = iterator.next();
                    if(stack.is(ModContents.AIR.get())){
                        matches.set(i,inputs.get(i).getStackInSlot(0).isEmpty());
                    }else{
                        matches.set(i,inputs.get(i).getStackInSlot(0).is(stack.getItem()));
                    }
                }
            }

            boolean b2 = tank.drainForced(this.inputFluid, IFluidHandler.FluidAction.SIMULATE).getAmount() == this.inputFluid.getAmount();

            return matches.stream().allMatch(i -> i) && b2;
        }else{
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
        return (GenericSerializer<? extends SerializableRecipe>) ModRecipes.FLUID_CRAFTING_TABLE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.FLUID_CRAFTING_TABLE_TYPE.get();
    }
}
