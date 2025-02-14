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
import net.yxiao233.ifeu.common.compact.jei.AbstractJEICategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.config.machine.InfuserConfig;
import net.yxiao233.ifeu.common.recipe.ArcaneDragonEggForgingRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArcaneDragonEggForgingCategory extends AbstractJEICategory<ArcaneDragonEggForgingRecipe> {
    public static final Component TITLE = Component.translatable("block.ifeu.arcane_dragon_egg_forging");
    private final IDrawable bigTank_input1;
    private final IDrawable bigTank_input2;
    private final IDrawable bigTank_output;
    public ArcaneDragonEggForgingCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.ARCANE_DRAGON_EGG_FORGING, TITLE, ModBlocks.ARCANE_DRAGON_EGG_FORGING.getLeft().get().asItem(), 160, 82);

        this.bigTank_input1 = helper.createDrawable(DefaultAssetProvider.DEFAULT_LOCATION, 180, 4, 12, 50);
        this.bigTank_input2 = helper.createDrawable(DefaultAssetProvider.DEFAULT_LOCATION, 180, 4, 12, 50);

        this.bigTank_output = helper.createDrawable(DefaultAssetProvider.DEFAULT_LOCATION, 180, 4, 12, 50);
    }

    @Override
    public RecipeType getTypeInstance() {
        return ModRecipes.ARCANE_DRAGON_EGG_FORGING_TYPE.get();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ArcaneDragonEggForgingRecipe recipe, IFocusGroup iFocusGroup) {
        //Input
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 33).addIngredient(VanillaTypes.ITEM_STACK,recipe.input);
        //InputFluid1
        if(recipe.inputFluid1 != null && !recipe.inputFluid1.isEmpty()){
            builder.addSlot(RecipeIngredientRole.INPUT, 24 + 3, 12 + 3).setFluidRenderer(InfuserConfig.maxInputTankSize >= 1000 ? InfuserConfig.maxInputTankSize : 1000,false,12,50).setOverlay(bigTank_input1,0,0).addIngredient(ForgeTypes.FLUID_STACK, recipe.inputFluid1);
        }
        //InputFluid2
        if(recipe.inputFluid2 != null && !recipe.inputFluid2.isEmpty()){
            builder.addSlot(RecipeIngredientRole.INPUT, 44 + 3, 12 + 3).setFluidRenderer(InfuserConfig.maxInputTankSize >= 1000 ? InfuserConfig.maxInputTankSize : 1000,false,12,50).setOverlay(bigTank_input2,0,0).addIngredient(ForgeTypes.FLUID_STACK, recipe.inputFluid2);
        }
        //Output
        if(!recipe.output.isEmpty()){
            ItemStack stack = recipe.output;
            stack.getItem().onCraftedBy(stack,null,null);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 16).addIngredient(VanillaTypes.ITEM_STACK,stack);
        }
        //OutputFluid
        if (recipe.outputFluid != null && !recipe.outputFluid.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 142, 17).setFluidRenderer(1000L, false, 12, 50).setOverlay(this.bigTank_output, 0, 0).addIngredient(ForgeTypes.FLUID_STACK, recipe.outputFluid);
        }
    }

    @Override
    public void draw(ArcaneDragonEggForgingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        //Background
        EnergyBarScreenAddon.drawBackground(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 0, 12, 0, 0);
        //Input
        SlotsScreenAddon.drawAsset(guiGraphics,Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER,66,33,0,0,1, integer -> Pair.of(18 * (integer % 1), 18 * (integer / 1)), integer -> ItemStack.EMPTY, true, integer -> new Color(DyeColor.LIGHT_BLUE.getFireworkColor()), integer -> true, 1);
        //Output
        SlotsScreenAddon.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 119, 16, 0, 0, 3, integer -> Pair.of(18 * (integer % 1), 18 * (integer / 1)), integer -> ItemStack.EMPTY, true, integer -> new Color(DyeColor.ORANGE.getFireworkColor()), integer -> true, 1);
        //InputFluid1
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER.getAsset(AssetTypes.TANK_NORMAL), 24, 12);
        //InputFluid2
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER.getAsset(AssetTypes.TANK_NORMAL), 44, 12);
        //OutputFluid
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER.getAsset(AssetTypes.TANK_NORMAL), 139, 14);
        //ProgressBar
        AssetUtil.drawAsset(guiGraphics, Minecraft.getInstance().screen, IAssetProvider.getAsset(DefaultAssetProvider.DEFAULT_PROVIDER, AssetTypes.PROGRESS_BAR_BACKGROUND_ARROW_HORIZONTAL), 92, 41 - 8);
        //EnergyBar
        int consumed = recipe.processingTime * InfuserConfig.powerPerTick;
        EnergyBarScreenAddon.drawForeground(guiGraphics, Minecraft.getInstance().screen, DefaultAssetProvider.DEFAULT_PROVIDER, 0, 12, 0, 0, consumed, (int) Math.max(50000, Math.ceil(consumed)));
    }

    @Override
    public List<Component> getTooltipStrings(ArcaneDragonEggForgingRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        Rectangle rec = DefaultAssetProvider.DEFAULT_PROVIDER.getAsset(AssetTypes.ENERGY_BACKGROUND).getArea();
        if (new Rectangle(0, 12, rec.width, rec.height).contains(mouseX, mouseY)) {
            int consumed = recipe.processingTime * 60;
            return EnergyBarScreenAddon.getTooltip(consumed, (int) Math.max(50000, Math.ceil(consumed)));
        }
        return new ArrayList<>();
    }
}
