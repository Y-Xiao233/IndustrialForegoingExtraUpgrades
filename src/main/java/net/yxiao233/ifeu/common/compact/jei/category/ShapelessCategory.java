package net.yxiao233.ifeu.common.compact.jei.category;

import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.client.screen.addon.SlotsScreenAddon;
import com.hrznstudio.titanium.client.screen.asset.DefaultAssetProvider;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.util.AssetUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
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
import net.yxiao233.ifeu.common.config.machine.FluidCraftingTableConfig;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.recipe.ShapelessRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.util.Arrays;

public class ShapelessCategory extends AbstractJEICategory<ShapelessRecipe> {
    public static final Component TITLE = Component.translatable("jei.ifeu.fluid_shapeless");
    private final IDrawable bigTank_input1;
    public ShapelessCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.SHAPELESS, TITLE, ModBlocks.FLUID_CRAFTING_TABLE.asItem(),160, 82);
        this.bigTank_input1 = helper.createDrawable(DefaultAssetProvider.DEFAULT_LOCATION, 180, 4, 12, 50);
    }

    @Override
    public RecipeType getTypeInstance() {
        return ModRecipes.SHAPELESS_TYPE.get();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ShapelessRecipe recipe, IFocusGroup iFocusGroup) {
        //Input
        int x = 31;
        int y = 15;
        for (int i = 0; i < 9; i++) {
            if(i % 3 == 0 && i != 0){
                x = 31;
                y += 18;
            }
            if(i < recipe.inputs.size()){
                builder.addSlot(RecipeIngredientRole.INPUT,x,y).addIngredients(VanillaTypes.ITEM_STACK, Arrays.asList(recipe.inputs.get(i).getItems()));
            }else{
                builder.addSlot(RecipeIngredientRole.INPUT,x,y).addIngredient(VanillaTypes.ITEM_STACK, ItemStack.EMPTY);
            }
            x += 18;
        }

        //InputFluid
        if(recipe.inputFluid != null && !recipe.inputFluid.isEmpty()){
            builder.addSlot(RecipeIngredientRole.CATALYST, 9 + 3, 12 + 3)
                    .setFluidRenderer(FluidCraftingTableConfig.maxInputTankSize >= 1000 ? FluidCraftingTableConfig.maxInputTankSize : 1000,false,12,50)
                    .setOverlay(bigTank_input1,0,0).addIngredient(NeoForgeTypes.FLUID_STACK, recipe.inputFluid);
        }
        //Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 33)
                .addIngredient(VanillaTypes.ITEM_STACK,recipe.output);
    }

    @Override
    public void draw(ShapelessRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        //Input
        int x = 31;
        int y = 15;
        for (int i = 0; i < 9; i++) {
            if(i % 3 == 0 && i != 0){
                x = 31;
                y += 18;
            }
            SlotsScreenAddon.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER,x,y,0,0,1, integer -> Pair.of(18 * (integer % 1), 18 * (integer / 1)), integer -> ItemStack.EMPTY, true, integer -> new Color(DyeColor.LIGHT_BLUE.getFireworkColor()), integer -> true, 1);
            x += 18;
        }
        //InputFluid
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER.getAsset(AssetTypes.TANK_NORMAL), 9, 12);
        //Output
        SlotsScreenAddon.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 119, 33, 0, 0, 1, integer -> Pair.of(18 * (integer % 1), 18 * (integer / 1)), integer -> ItemStack.EMPTY, true, integer -> new Color(DyeColor.ORANGE.getFireworkColor()), integer -> true, 1);
        //ProgressBar
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, IAssetProvider.getAsset(DefaultAssetProvider.DEFAULT_PROVIDER, AssetTypes.PROGRESS_BAR_BACKGROUND_ARROW_HORIZONTAL), 92, 41 - 8);
        //Shapeless icon
        drawTextureWithTooltip(guiGraphics, AllGuiTextures.SHAPELESS,Component.translatable("jei.ifeu.shapeless"),119,17,mouseX,mouseY);
    }
}
