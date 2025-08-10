package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.yxiao233.ifeu.common.block.entity.PrecisionCraftingTableEntity;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEURecipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PrecisionShapelessRecipe implements Recipe<RecipeInput> {
    public static final MapCodec<PrecisionShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec((in) -> {
        return in.group(Ingredient.CODEC.listOf(0,9).fieldOf("inputs").forGetter((o) -> {
            return o.inputs;
        }), ItemStack.CODEC.fieldOf("output").forGetter((o) -> {
            return o.output;
        }), Codec.FLOAT.fieldOf("chance").forGetter((o) -> {
            return o.chance;
        })).apply(in, PrecisionShapelessRecipe::new);
    });
    public List<Ingredient> inputs;
    public float chance;
    public ItemStack output;
    public PrecisionShapelessRecipe(List<Ingredient> inputs, ItemStack output, float chance){
        this.inputs = inputs;
        this.output = output;
        this.chance = chance;
    }
    public static void createRecipe(RecipeOutput recipeOutput, String name, PrecisionShapelessRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        recipeOutput.accept(rl, recipe, advancementHolder);
    }
    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu","precision_shapeless/" + key);
    }
    public boolean matches(InventoryComponent<PrecisionCraftingTableEntity> inputs) {
        if (this.inputs != null && chance > 0) {
            List<ItemStack> handlerItems = new ArrayList<>();

            for(int i = 0; i < inputs.getSlots(); ++i) {
                if (!inputs.getStackInSlot(i).isEmpty()) {
                    handlerItems.add(inputs.getStackInSlot(i).copy());
                }
            }

            List<Ingredient> var12 = this.inputs;
            int var5 = var12.size();

            for(int var6 = 0; var6 < var5; ++var6) {
                Ingredient iItemList = var12.get(var6);
                boolean found = false;
                Iterator<ItemStack> var9 = Arrays.stream(iItemList.getItems()).iterator();

                while(var9.hasNext()) {
                    ItemStack stack = var9.next();

                    int i;
                    for(i = 0; i < handlerItems.size(); ++i) {
                        if (ItemStack.isSameItem(handlerItems.get(i), stack)) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        handlerItems.remove(i);
                        break;
                    }
                }

                if (!found) {
                    return false;
                }
            }

            return handlerItems.isEmpty();
        } else {
            return false;
        }
    }
    @Override
    public boolean matches(RecipeInput p_346065_, Level p_345375_) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeInput p_345149_, HolderLookup.Provider p_346030_) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_336125_) {
        return this.output.copy();
    }

    @Override
    public ItemStack getToastSymbol() {
        return IFEUBlocks.PRECISION_CRAFTING_TABLE.asItem().getDefaultInstance();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return IFEURecipes.PRECISION_SHAPELESS_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return IFEURecipes.PRECISION_SHAPELESS_TYPE.get();
    }
}
