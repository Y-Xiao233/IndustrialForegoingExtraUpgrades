package net.yxiao233.ifeu.api.block.entity;

import com.hrznstudio.titanium.module.BlockWithTile;
import com.hrznstudio.titanium.util.RecipeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yxiao233.ifeu.api.recipe.ItemGeneratorSerializableRecipe;
import net.yxiao233.ifeu.common.utils.SlotElementsUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class IFEUItemFuelJsonGeneratorEntity<T extends IFEUItemFuelJsonGeneratorEntity<T>> extends IFEUItemFuelGeneratorEntity<T> {
    private ItemGeneratorSerializableRecipe recipe;
    public IFEUItemFuelJsonGeneratorEntity(BlockWithTile blockWithTile, BlockPos blockPos, BlockState blockState) {
        super(blockWithTile, blockPos, blockState);
    }

    public abstract <R extends ItemGeneratorSerializableRecipe> RecipeType<R> setRecipeType();

    @Override
    public SlotElementsUtil getSlotElements() {
        List<ItemGeneratorSerializableRecipe> list = RecipeUtil.getRecipes(this.level,setRecipeType());
        List<ItemStack> stacks = new ArrayList<>(list.size());

        list.forEach(recipe -> stacks.add(recipe.input));

        return new SlotElementsUtil()
                .setInputFilter((stack, integer) -> stacks.contains(stack));
    }


    @Override
    public ItemStack getConsumeFuel() {
        return ItemStack.EMPTY;
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, T blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        checkForRecipe();
    }

    @Override
    public int consumeFuel() {
        if(recipe != null){
            ItemStack stack = recipe.input;
            if(input.getStackInSlot(0).getCount() >= stack.getCount()){
                input.extractItem(0,stack.getCount(), false);
                return recipe.progressTime;
            }
        }else{
            checkForRecipe();
        }
        return 0;
    }

    @Override
    public boolean canStart() {
        if (recipe != null) {
            return input.getStackInSlot(0).getCount() >= recipe.input.getCount() && this.getEnergyStorage().getEnergyStored() < this.getEnergyStorage().getMaxEnergyStored();
        } else {
            checkForRecipe();
            return false;
        }
    }

    private void checkForRecipe(){
        if(isServer()){
            if(recipe != null && recipe.matches(input,this.getEnergyStorage())){
                return;
            }

            recipe = RecipeUtil.getRecipes(this.level,setRecipeType()).stream().filter(recipe -> recipe.matches(input,this.getEnergyStorage())).findFirst().orElse(null);
        }
    }

    @Override
    public void setChanged() {
        super.setChanged();
        checkForRecipe();
    }

    @Override
    public void setLevel(Level p_155231_) {
        super.setLevel(p_155231_);
        checkForRecipe();
    }
}