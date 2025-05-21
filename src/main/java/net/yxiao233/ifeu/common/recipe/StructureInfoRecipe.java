package net.yxiao233.ifeu.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;

public class StructureInfoRecipe implements Recipe<CraftingInput> {
    public static final MapCodec<StructureInfoRecipe> CODEC = RecordCodecBuilder.mapCodec((in) -> {
        return in.group(IFEUMultiBlockStructures.CODEC.fieldOf("structure").forGetter((o) -> {
            return o.structure;
        })).apply(in, StructureInfoRecipe::new);
    });
    public IFEUMultiBlockStructures structure;

    public StructureInfoRecipe(IFEUMultiBlockStructures structure) {
        this.structure = structure;
    }

    public static void createRecipe(RecipeOutput recipeOutput, String name, BlockRightClickRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        recipeOutput.accept(rl, recipe, advancementHolder);
    }

    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu", "structure_info/" + key);
    }

    public boolean matches(Player player, BlockHitResult hit, Level level) {
        return true;
    }


    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModContents.BLUEPRINT.asItem());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.BLOCK_RIGHT_CLICK_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.BLOCK_RIGHT_CLICK_TYPE.get();
    }
}
