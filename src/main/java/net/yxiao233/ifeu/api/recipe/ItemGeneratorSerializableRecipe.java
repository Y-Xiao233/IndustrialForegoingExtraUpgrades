package net.yxiao233.ifeu.api.recipe;

import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemGeneratorSerializableRecipe extends SerializableRecipe {
    public static List<ItemGeneratorSerializableRecipe> RECIPES = new ArrayList<>();
    public ItemStack input;
    public int progressTime;
    public int powerPerTick;

    private ItemGeneratorSerializableRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }
    public ItemGeneratorSerializableRecipe(ResourceLocation resourceLocation, ItemStack input, int progressTime, int powerPerTick){
        super(resourceLocation);
        this.input = input;
        this.progressTime = progressTime;
        this.powerPerTick = powerPerTick;
        RECIPES.add(this);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    public boolean matches(IItemHandler itemHandler, EnergyStorageComponent<?> energyStorageComponent) {
        return itemHandler.getStackInSlot(0).is(input.getItem()) && energyStorageComponent.getEnergyStored() <= energyStorageComponent.getMaxEnergyStored();
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
        return ItemStack.EMPTY;
    }

    @Override
    public GenericSerializer<? extends SerializableRecipe> getSerializer() {
        return (GenericSerializer<? extends SerializableRecipe>) getRecipeSerializer();
    }

    public abstract boolean isOnlyForPreview();
    public abstract RecipeSerializer<?> getRecipeSerializer();
}
