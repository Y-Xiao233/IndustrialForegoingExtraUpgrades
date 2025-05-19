package net.yxiao233.ifeu.common.compact.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.yxiao233.ifeu.api.jei.AbstractJEICategory;
import net.yxiao233.ifeu.common.compact.jei.ModRecipeType;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.recipe.StructureInfoRecipe;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import net.yxiao233.ifeu.common.utils.TooltipCallBackHelper;

import java.util.ArrayList;
import java.util.List;

public class StructureInfoCategory extends AbstractJEICategory<StructureInfoRecipe> {
    public static final Component TITLE = Component.translatable("jei.ifeu.structure");
    public StructureInfoCategory(IGuiHelper helper) {
        super(helper, ModRecipeType.STRUCTURE, TITLE, ModContents.BLUEPRINT.get(), 160, 140);
    }
    @Override
    public RecipeType getTypeInstance() {
        return ModRecipes.STRUCTURE_TYPE.get();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, StructureInfoRecipe recipe, IFocusGroup iFocusGroup) {
        ItemStack machine = recipe.getStructure().getMachine().getDefaultInstance();
        builder.addSlot(RecipeIngredientRole.OUTPUT,71,1).addIngredient(VanillaTypes.ITEM_STACK, machine).addRichTooltipCallback(
                addText("jei.ifeu.structure_core",ChatFormatting.GREEN)
        );
        var pair = recipe.getStructure().getStructure().materialListForJei();
        int stackSize = pair.getLeft().size();
        int tagSize = pair.getRight().size();
        int size = stackSize + tagSize;
        int x = 1,y = 41;

        for (int i = 0; i < size; i++) {
            if(i < stackSize) {
                builder.addSlot(RecipeIngredientRole.INPUT, x, y).addIngredient(VanillaTypes.ITEM_STACK,pair.getLeft().get(i));
            }else{
                List<ItemStack> tagStacks = new ArrayList<>();
                int finalI = i;
                BuiltInRegistries.BLOCK.forEach(reg -> {
                    if(reg.defaultBlockState().is(pair.getRight().get(finalI - stackSize).getLeft())){
                        tagStacks.add(new ItemStack(reg.asItem(),pair.getRight().get(finalI - stackSize).getRight()));
                    }
                });
                builder.addSlot(RecipeIngredientRole.INPUT,x,y).addIngredients(VanillaTypes.ITEM_STACK,tagStacks).addRichTooltipCallback(
                        addText(
                                new TooltipCallBackHelper("jei.ifeu.block_tag",ChatFormatting.GRAY),
                                new TooltipCallBackHelper(Component.literal("#" + pair.getRight().get(finalI - stackSize).getLeft().location().toString()).withStyle(ChatFormatting.GRAY))
                        )
                );
            }
            x += 20;
            if(x >= 160){
                y += 20;
                x = 1;
            }
        }
    }
    @Override
    public void draw(StructureInfoRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("jei.ifeu.structure_list"), 0, 30, 16777215);
        AllGuiTextures.BASIC_SLOT.render(guiGraphics,70,0);

        var pair = recipe.getStructure().getStructure().materialListForJei();
        int stackSize = pair.getLeft().size();
        int tagSize = pair.getRight().size();
        int size = stackSize + tagSize;
        int x = 0,y = 40;

        for (int i = 0; i < size; i++) {
            AllGuiTextures.BASIC_SLOT.render(guiGraphics,x,y);
            x += 20;
            if(x >= 160){
                y += 20;
                x = 0;
            }
        }
    }
}
