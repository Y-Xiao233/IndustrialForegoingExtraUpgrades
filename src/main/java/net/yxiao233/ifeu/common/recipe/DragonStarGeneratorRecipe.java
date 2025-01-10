package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.yxiao233.ifeu.common.registry.ModRecipes;

import java.util.ArrayList;
import java.util.List;

public class DragonStarGeneratorRecipe extends SerializableRecipe {
    public static List<DragonStarGeneratorRecipe> RECIPES = new ArrayList<>();
    public ItemStack input;
    public int time;
    public int powerPerTick;
    public DragonStarGeneratorRecipe(ResourceLocation resourceLocation, ItemStack input, int time, int powerPerTick) {
        super(resourceLocation);
        this.input = input;
        this.time = time;
        this.powerPerTick = powerPerTick;
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
        return null;
    }

    @Override
    public GenericSerializer<? extends SerializableRecipe> getSerializer() {
        return (GenericSerializer<? extends SerializableRecipe>) ModRecipes.DRAGON_STAR_GENERATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DRAGON_STAR_GENERATOR_TYPE.get();
    }
}
