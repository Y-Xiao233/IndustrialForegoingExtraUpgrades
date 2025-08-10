package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.yxiao233.ifeu.common.registry.IFEURecipes;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;

public class StructureInfoRecipe extends SerializableRecipe {
    public IFEUMultiBlockStructures structure;
    public StructureInfoRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public StructureInfoRecipe(ResourceLocation resourceLocation, IFEUMultiBlockStructures structure) {
        super(resourceLocation);
        this.structure = structure;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return true;
    }

    public IFEUMultiBlockStructures getStructure(){
        return this.structure;
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
        return (GenericSerializer<? extends SerializableRecipe>) IFEURecipes.STRUCTURE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return IFEURecipes.STRUCTURE_TYPE.get();
    }
}
