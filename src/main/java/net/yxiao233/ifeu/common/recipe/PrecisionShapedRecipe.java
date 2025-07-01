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
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.block.entity.PrecisionCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrecisionShapedRecipe extends SerializableRecipe {
    public static List<PrecisionShapedRecipe> RECIPES = new ArrayList<>();
    public Ingredient.Value[] inputs;
    public float chance;
    public ItemStack output;
    public PrecisionShapedRecipe(ResourceLocation resourceLocation){
        super(resourceLocation);
    }
    public PrecisionShapedRecipe(ResourceLocation resourceLocation, Ingredient.Value[] inputs, ItemStack output, float chance) {
        super(resourceLocation);
        this.inputs = inputs;
        this.chance = chance;
        this.output = output;
        this.output.getItem().onCraftedBy(this.output,null,null);
        RECIPES.add(this);
    }
    public boolean matches(InventoryComponent<PrecisionCraftingTableEntity> inputs) {
        if (this.inputs != null && this.chance > 0) {
            NonNullList<Boolean> matches = NonNullList.withSize(9, false);

            for (int i = 0; i < this.inputs.length; i++) {
                Iterator<ItemStack> iterator = this.inputs[i].getItems().iterator();

                if(iterator.hasNext()){
                    ItemStack stack = iterator.next();
                    if(stack.is(ModContents.AIR.get())){
                        matches.set(i,inputs.getStackInSlot(i).isEmpty());
                    }else{
                        matches.set(i,inputs.getStackInSlot(i).is(stack.getItem()));
                    }
                }
            }

            return matches.stream().allMatch(i -> i);
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
        return (GenericSerializer<? extends SerializableRecipe>) ModRecipes.PRECISION_SHAPED_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PRECISION_SHAPED_TYPE.get();
    }
}
