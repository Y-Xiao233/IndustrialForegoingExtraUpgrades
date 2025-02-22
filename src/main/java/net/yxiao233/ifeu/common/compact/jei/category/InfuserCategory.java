package net.yxiao233.ifeu.common.compact.jei.category;

import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.client.screen.addon.EnergyBarScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.SlotsScreenAddon;
import com.hrznstudio.titanium.client.screen.asset.DefaultAssetProvider;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.util.AssetUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
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
import net.yxiao233.ifeu.common.config.machine.InfuserConfig;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;

public class InfuserCategory extends AbstractJEICategory<InfuserRecipe> {
    public static final Component TITLE = Component.translatable("block.ifeu.infuser");
    private final IDrawable bigTank;
    public InfuserCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.INFUSER,TITLE,ModBlocks.INFUSER.getKey().get().asItem(), 160, 82);
        this.bigTank = helper.createDrawable(DefaultAssetProvider.DEFAULT_LOCATION, 177 + 3, 1 + 3, 12, 50);
    }

    @Override
    public RecipeType getTypeInstance() {
        return ModRecipes.INFUSER_TYPE.get();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InfuserRecipe recipe, IFocusGroup iFocusGroup) {
        //Input
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 33).addIngredient(VanillaTypes.ITEM_STACK,recipe.input);
        //InputFluid
        if(recipe.inputFluid != null && !recipe.inputFluid.isEmpty()){
            builder.addSlot(RecipeIngredientRole.INPUT, 44 + 3, 12 + 3).setFluidRenderer(InfuserConfig.maxInputTankSize >= 1000 ? InfuserConfig.maxInputTankSize : 1000,false,12,50).setOverlay(bigTank,0,0).addIngredient(ForgeTypes.FLUID_STACK, recipe.inputFluid);
        }
        //Output
        if(!recipe.output.isEmpty()){
            ItemStack stack = recipe.output;
            stack.getItem().onCraftedBy(stack,null,null);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 16).addIngredient(VanillaTypes.ITEM_STACK,stack);
        }
    }

    @Override
    public void draw(InfuserRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        //Background
        EnergyBarScreenAddon.drawBackground(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 0, 12, 0, 0);
        //Input
        SlotsScreenAddon.drawAsset(guiGraphics,Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER,66,33,0,0,1,integer -> Pair.of(18 * (integer % 1), 18 * (integer / 1)), integer -> ItemStack.EMPTY, true, integer -> new Color(DyeColor.LIGHT_BLUE.getFireworkColor()), integer -> true, 1);
        //Output
        SlotsScreenAddon.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 119, 16, 0, 0, 3, integer -> Pair.of(18 * (integer % 1), 18 * (integer / 1)), integer -> ItemStack.EMPTY, true, integer -> new Color(DyeColor.ORANGE.getFireworkColor()), integer -> true, 1);
        //InputFluid
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER.getAsset(AssetTypes.TANK_NORMAL), 44, 12);
        //ProgressBar
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, IAssetProvider.getAsset(DefaultAssetProvider.DEFAULT_PROVIDER, AssetTypes.PROGRESS_BAR_BACKGROUND_ARROW_HORIZONTAL), 92, 41 - 8);
        //EnergyBar
        int consumed = recipe.processingTime * InfuserConfig.powerPerTick;
        EnergyBarScreenAddon.drawForeground(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 0, 12, 0, 0, consumed, (int) Math.max(50000, Math.ceil(consumed)));
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, InfuserRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        int consumed = recipe.processingTime * 60;
        addEnergyBarTooltip(tooltip,mouseX,mouseY,consumed,(int) Math.max(50000, Math.ceil(consumed)));
    }
}
