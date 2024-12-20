package net.yxiao233.ifeu.common.mycelialGenerator;

import com.buuz135.industrial.block.generator.mycelial.IMycelialGeneratorType;
import com.buuz135.industrial.plugin.jei.generator.MycelialGeneratorRecipe;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.yxiao233.ifeu.common.mixin.IMycelialGeneratorTypeMixin;
import net.yxiao233.ifeu.common.registry.ModContents;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TestGeneratorType implements IMycelialGeneratorType {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public Input[] getInputs() {
        return new IMycelialGeneratorType.Input[]{Input.SLOT};
    }

    @Override
    public List<BiPredicate<ItemStack, Integer>> getSlotInputPredicates() {
        TYPES.add(this);
        return Arrays.asList((stack, integer) -> {
            return stack.getItem() == ModContents.DRAGON_STAR.get();
        });
    }

    @Override
    public List<Predicate<FluidStack>> getTankInputPredicates() {
        return new ArrayList();
    }

    @Override
    public boolean canStart(INBTSerializable<CompoundTag>[] inputs) {
        return inputs.length > 0 && inputs[0] instanceof SidedInventoryComponent && ((SidedInventoryComponent)inputs[0]).getStackInSlot(0).getCount() > 0;
    }

    @Override
    public Pair<Integer, Integer> getTimeAndPowerGeneration(INBTSerializable<CompoundTag>[] inputs) {
        if (inputs.length > 0 && inputs[0] instanceof SidedInventoryComponent && ((SidedInventoryComponent)inputs[0]).getStackInSlot(0).getCount() > 0) {
            ItemStack stack = ((SidedInventoryComponent)inputs[0]).getStackInSlot(0).copy();
            ((SidedInventoryComponent)inputs[0]).getStackInSlot(0).shrink(1);
            return this.calculate(stack);
        } else {
            return Pair.of(0, 80);
        }
    }

    @Override
    public DyeColor[] getInputColors() {
        return new DyeColor[]{DyeColor.GRAY};
    }

    @Override
    public Item getDisplay() {
        return ModContents.DRAGON_STAR.get();
    }

    @Override
    public int getSlotSize() {
        return 64;
    }

    @Override
    public List<MycelialGeneratorRecipe> getRecipes() {
        List<MycelialGeneratorRecipe> recipes = new ArrayList();
        Item[] var2 = new Item[]{ModContents.DRAGON_STAR.get()};
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Item item = var2[var4];
            ItemStack stack = new ItemStack(item);
            Pair<Integer, Integer> power = this.calculate(stack);
            recipes.add(new MycelialGeneratorRecipe(Collections.singletonList(Collections.singletonList(Ingredient.of(new ItemStack[]{stack}))), new ArrayList(), (Integer)power.getLeft(), (Integer)power.getRight()));
        }

        return recipes;
    }

    private Pair<Integer, Integer> calculate(ItemStack stack) {
        return Pair.of(2400, 4000);
    }

    @Override
    public ShapedRecipeBuilder addIngredients(ShapedRecipeBuilder recipeBuilder) {
        recipeBuilder = recipeBuilder.define('B', Blocks.WITHER_SKELETON_SKULL).define('C', Items.NETHER_STAR).define('M', com.buuz135.industrial.utils.IndustrialTags.Items.MACHINE_FRAME_SUPREME);
        return recipeBuilder;
    }
}
