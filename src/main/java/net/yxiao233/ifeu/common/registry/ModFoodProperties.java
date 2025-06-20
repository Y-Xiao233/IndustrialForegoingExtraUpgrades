package net.yxiao233.ifeu.common.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties APPLE_CORE = new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(1)
            .saturationModifier(0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION,10*20,10),1.0f)
            .build();
}
