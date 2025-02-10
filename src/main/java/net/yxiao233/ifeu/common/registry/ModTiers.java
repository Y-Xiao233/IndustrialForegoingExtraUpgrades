package net.yxiao233.ifeu.common.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    DRAGON_STAR(5,4096,18.0F,7.0F,25, () -> {
        return Ingredient.of(ModContents.DRAGON_STAR.get());
    });


    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModTiers(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier pRepairIngredient) {
        this.level = pLevel;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        this.repairIngredient = new LazyLoadedValue(pRepairIngredient);
    }
    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return ModTags.Blocks.NEEDS_DRAGON_STAR_TOOL;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }

    public @Nullable TagKey<Block> getTag() {
        return getTagFromVanillaTier(this);
    }

    public static TagKey<Block> getTagFromVanillaTier(ModTiers modTiers) {
        TagKey var10000;
        switch (modTiers) {
            case DRAGON_STAR:
                var10000 = ModTags.Blocks.NEEDS_DRAGON_STAR_TOOL;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }
}
