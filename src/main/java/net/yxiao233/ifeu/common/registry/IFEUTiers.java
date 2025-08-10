package net.yxiao233.ifeu.common.registry;

import com.google.common.base.Suppliers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

public enum IFEUTiers implements Tier {
    DRAGON_STAR(5,4096,18.0F,7.0F,25, () -> {
        return Ingredient.of(new ItemLike[]{IFEUContents.DRAGON_STAR});
    });


    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    private IFEUTiers(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.level = pLevel;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        Objects.requireNonNull(pRepairIngredient);
        this.repairIngredient = Suppliers.memoize(pRepairIngredient::get);
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
        return IFEUTags.Blocks.NEEDS_DRAGON_STAR_TOOL;
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

    public static TagKey<Block> getTagFromVanillaTier(IFEUTiers modTiers) {
        TagKey var10000;
        switch (modTiers) {
            case DRAGON_STAR:
                var10000 = IFEUTags.Blocks.NEEDS_DRAGON_STAR_TOOL;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }
}
