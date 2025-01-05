package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.yxiao233.ifeu.common.registry.ModRecipes;

import java.util.ArrayList;
import java.util.List;

public class BlockRightClickRecipe extends SerializableRecipe {
    public static List<BlockRightClickRecipe> RECIPES = new ArrayList<>();
    public Block block;
    public ItemStack handItem;
    public Block result;
    public BlockRightClickRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public BlockRightClickRecipe(ResourceLocation resourceLocation, Block block, ItemStack handItem, Block result) {
        super(resourceLocation);
        this.block = block;
        this.handItem = handItem;
        this.result = result;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }


    public boolean matches(Player player, BlockHitResult hit, Level level) {
        return level.getBlockState(hit.getBlockPos()).is(block) && player.getMainHandItem().is(handItem.getItem());
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
        return result.asItem().getDefaultInstance();
    }

    @Override
    public GenericSerializer<? extends SerializableRecipe> getSerializer() {
        return (GenericSerializer<? extends SerializableRecipe>) ModRecipes.BLOCK_RIGHT_CLICK_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.BLOCK_RIGHT_CLICK_TYPE.get();
    }
}
