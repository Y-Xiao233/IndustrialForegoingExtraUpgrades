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
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.yxiao233.ifeu.common.registry.ModRecipes;

public class BlockRightClickRecipe implements Recipe<CraftingInput> {
    public static final MapCodec<BlockRightClickRecipe> CODEC = RecordCodecBuilder.mapCodec((in) -> {
        return in.group(ItemStack.CODEC.fieldOf("handItem").forGetter((o) -> {
            return o.handItem;
        }), Block.CODEC.fieldOf("block").forGetter((o) -> {
            return o.block;
        }), Block.CODEC.fieldOf("result").forGetter((o) -> {
            return o.result;
        })).apply(in, BlockRightClickRecipe::new);
    });
    public Block block;
    public ItemStack handItem;
    public Block result;
    public BlockRightClickRecipe() {
    }

    public BlockRightClickRecipe(ItemStack handItem, Block block, Block result) {
        this.block = block;
        this.handItem = handItem;
        this.result = result;
    }

    public static void createRecipe(RecipeOutput recipeOutput, String name, BlockRightClickRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        recipeOutput.accept(rl, recipe, advancementHolder);
    }

    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu", "block_right/" + key);
    }

    public boolean matches(Player player, BlockHitResult hit, Level level) {
        return level.getBlockState(hit.getBlockPos()).is(block) && player.getMainHandItem().is(handItem.getItem());
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
        return this.result.asItem().getDefaultInstance();
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
