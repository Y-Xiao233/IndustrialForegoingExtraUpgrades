package net.yxiao233.ifeu.mixin.jei;

import com.buuz135.industrial.block.core.tile.DissolutionChamberTile;
import com.buuz135.industrial.plugin.jei.category.DissolutionChamberCategory;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DissolutionChamberCategory.class)
public abstract class MixinDissolutionChamberCategory implements IRecipeCategory<DissolutionChamberRecipe> {
    @Shadow @Final private IDrawable smallTank;
    @Shadow @Final private IDrawable bigTank;
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DissolutionChamberRecipe recipe, IFocusGroup iFocusGroup) {
        for(int i = 0; i < 8; ++i) {
            if (i < recipe.input.length) {
                builder.addSlot(RecipeIngredientRole.INPUT, 24 + (Integer) DissolutionChamberTile.getSlotPos(i).getLeft(), 11 + (Integer)DissolutionChamberTile.getSlotPos(i).getRight()).addIngredients(VanillaTypes.ITEM_STACK, recipe.input[i].getItems().stream().toList());
            }
        }

        if (recipe.inputFluid != null && !recipe.inputFluid.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 48, 35).setFluidRenderer(1000L, false, 12, 13).setOverlay(this.smallTank, 0, 0).addIngredient(ForgeTypes.FLUID_STACK, recipe.inputFluid);
        }

        if (!recipe.output.isEmpty()) {
            ItemStack stack = recipe.output;
            stack.getItem().onCraftedBy(stack, (Level)null, (Player)null);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 16).addIngredient(VanillaTypes.ITEM_STACK, stack);
        }

        if (recipe.outputFluid != null && !recipe.outputFluid.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 142, 17).setFluidRenderer(1000L, false, 12, 50).setOverlay(this.bigTank, 0, 0).addIngredient(ForgeTypes.FLUID_STACK, recipe.outputFluid);
        }
    }
}
