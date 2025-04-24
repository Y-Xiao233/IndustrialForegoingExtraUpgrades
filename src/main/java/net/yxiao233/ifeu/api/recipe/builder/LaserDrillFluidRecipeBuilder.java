package net.yxiao233.ifeu.api.recipe.builder;

import com.buuz135.industrial.recipe.LaserDrillFluidRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class LaserDrillFluidRecipeBuilder extends IFEURecipeBuilder{
    private LaserDrillFluidRecipeBuilder(ItemStack output) {
        super(output);
    }

    private LaserDrillFluidRecipeBuilder(ItemStack output, String id) {
        super(output, id);
    }

    public LaserDrillFluidRecipeBuilder(FluidStack output, String id){
        super(ItemStack.EMPTY);
        this.outputFluid(output);
        this.id(id);
    }

    @Override
    public void save() {
        new LaserDrillFluidRecipe(this.getId(),this.getOutputFluid().writeToNBT(new CompoundTag()),this.getCatalyst(),this.getEntity(),this.getRarity());
    }
}
