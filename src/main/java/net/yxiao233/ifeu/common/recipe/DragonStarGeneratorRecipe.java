package net.yxiao233.ifeu.common.recipe;

import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
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
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.items.IItemHandler;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;

public class DragonStarGeneratorRecipe implements Recipe<CraftingInput> {
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
    public DragonStarGeneratorRecipe(){}
    public DragonStarGeneratorRecipe(ItemStack input, int time, int powerPerTick) {
        this.input = input;
        this.time = time;
        this.powerPerTick = powerPerTick;
    }

    public static void createRecipe(RecipeOutput recipeOutput, String name, DragonStarGeneratorRecipe recipe) {
        ResourceLocation rl = generateRL(name);
        AdvancementHolder advancementHolder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(rl)).rewards(AdvancementRewards.Builder.recipe(rl)).requirements(AdvancementRequirements.Strategy.OR).build(rl);
        recipeOutput.accept(rl, recipe, advancementHolder);
    }

    public static ResourceLocation generateRL(String key) {
        return ResourceLocation.fromNamespaceAndPath("ifeu", "dragon_star_generator/" + key);
    }


    public boolean matches(IItemHandler itemHandler, EnergyStorageComponent<?> energyStorageComponent) {
        return itemHandler.getStackInSlot(0).is(input.getItem()) && energyStorageComponent.getEnergyStored() <= energyStorageComponent.getMaxEnergyStored();
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
        return null;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.DRAGON_STAR_GENERATOR);
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
