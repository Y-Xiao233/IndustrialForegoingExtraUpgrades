package net.yxiao233.ifeu.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.yxiao233.ifeu.api.recipe.ItemGeneratorSerializableRecipe;
import net.yxiao233.ifeu.common.registry.ModRecipes;

public class DragonStarGeneratorRecipe extends ItemGeneratorSerializableRecipe {
    public static final MapCodec<DragonStarGeneratorRecipe> CODEC = RecordCodecBuilder.mapCodec((in) -> {
        return in.group(ItemStack.CODEC.fieldOf("input").forGetter((o) -> {
            return o.input;
        }),Codec.INT.fieldOf("time").forGetter((o) -> {
            return o.time;
        }),Codec.INT.fieldOf("powerPerTick").forGetter((o) -> {
            return o.powerPerTick;
        })).apply(in, DragonStarGeneratorRecipe::new);
    });
    public ItemStack input;
    public int time;
    public int powerPerTick;

    public DragonStarGeneratorRecipe(ItemStack input, int progressTime, int powerPerTick) {
        super(input, progressTime, powerPerTick);
    }

    @Override
    public boolean isOnlyForPreview() {
        return true;
    }

    public static void createRecipe(RecipeOutput recipeOutput, String name, DragonStarGeneratorRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        recipeOutput.accept(rl, recipe, advancementHolder);
    }

    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu", "dragon_star_generator/" + key);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DRAGON_STAR_GENERATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DRAGON_STAR_GENERATOR_TYPE.get();
    }

}
