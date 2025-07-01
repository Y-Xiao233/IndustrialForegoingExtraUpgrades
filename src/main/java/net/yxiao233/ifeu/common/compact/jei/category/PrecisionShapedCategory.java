package net.yxiao233.ifeu.common.compact.jei.category;

import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.client.screen.addon.SlotsScreenAddon;
import com.hrznstudio.titanium.client.screen.asset.DefaultAssetProvider;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.util.AssetUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.yxiao233.ifeu.api.jei.AbstractJEICategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.recipe.PrecisionShapedRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.util.Iterator;

public class PrecisionShapedCategory extends AbstractJEICategory<PrecisionShapedRecipe> {
    public static final Component TITLE = Component.translatable("jei.ifeu.precision_shaped");
    public PrecisionShapedCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.PRECISION_SHAPED, TITLE, ModBlocks.PRECISION_CRAFTING_TABLE.getLeft().get().asItem(), 160, 82);
    }

    @Override
    public RecipeType getTypeInstance() {
        return ModRecipes.PRECISION_SHAPED_TYPE.get();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PrecisionShapedRecipe recipe, IFocusGroup iFocusGroup) {
        //Input
        int x = 31;
        int y = 15;
        for (int i = 0; i < recipe.inputs.length; i++) {
            if(i % 3 == 0 && i != 0){
                x = 31;
                y += 18;
            }
            Iterator<ItemStack> iterator = recipe.inputs[i].getItems().iterator();
            if(iterator.hasNext() && iterator.next().is(ModContents.AIR.get())){
                builder.addSlot(RecipeIngredientRole.INPUT,x,y).addIngredient(VanillaTypes.ITEM_STACK,ItemStack.EMPTY);
            }else{
                builder.addSlot(RecipeIngredientRole.INPUT,x,y).addIngredients(VanillaTypes.ITEM_STACK,recipe.inputs[i].getItems().stream().toList());
            }
            x += 18;
        }
        //Output
        float chance = recipe.chance;
        if(chance >= 1){
            builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 33)
                    .addIngredient(VanillaTypes.ITEM_STACK,recipe.output)
                    .setBackground(drawSlot(1),-1,-1);
        }else{
            builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 33)
                    .addIngredient(VanillaTypes.ITEM_STACK,recipe.output)
                    .setBackground(drawSlot(chance),-1,-1)
                    .addRichTooltipCallback(addChanceTooltip(chance));
        }
    }

    @Override
    public void draw(PrecisionShapedRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        //Input
        int x = 31;
        int y = 15;
        for (int i = 0; i < recipe.inputs.length; i++) {
            if(i % 3 == 0 && i != 0){
                x = 31;
                y += 18;
            }
            SlotsScreenAddon.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER,x,y,0,0,1, integer -> Pair.of(18 * (integer % 1), 18 * (integer / 1)), integer -> ItemStack.EMPTY, true, integer -> new Color(DyeColor.LIGHT_BLUE.getFireworkColor()), integer -> true, 1);
            x += 18;
        }
        //ProgressBar
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, IAssetProvider.getAsset(DefaultAssetProvider.DEFAULT_PROVIDER, AssetTypes.PROGRESS_BAR_BACKGROUND_ARROW_HORIZONTAL), 92, 41 - 8);
    }
}
