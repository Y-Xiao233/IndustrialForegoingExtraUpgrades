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
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import net.yxiao233.ifeu.api.recipe.FluidGeneratorSerializableRecipe;
import net.yxiao233.ifeu.common.registry.IFEURecipes;

public class DragonGeneratorRecipe extends FluidGeneratorSerializableRecipe {
    public static final MapCodec<DragonGeneratorRecipe> CODEC = RecordCodecBuilder.mapCodec((in) -> {
        return in.group(FluidStack.CODEC.fieldOf("input").forGetter((o) -> {
            return o.inputFluid;
        }), Codec.INT.fieldOf("progressTime").forGetter((o) -> {
            return o.progressTime;
        }),Codec.INT.fieldOf("powerPerTick").forGetter((o) -> {
            return o.powerPerTick;
        })).apply(in, DragonGeneratorRecipe::new);
    });

    public DragonGeneratorRecipe(FluidStack inputFluid, int progressTime, int powerPerTick) {
        super(inputFluid, progressTime, powerPerTick);
    }

    public static void createRecipe(RecipeOutput recipeOutput, String name, DragonStarGeneratorRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        recipeOutput.accept(rl, recipe, advancementHolder);
    }

    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu", "dragon_generator/" + key);
    }
    @Override
    public boolean isOnlyForPreview() {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return IFEURecipes.DRAGON_GENERATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return IFEURecipes.DRAGON_GENERATOR_TYPE.get();
    }
}
