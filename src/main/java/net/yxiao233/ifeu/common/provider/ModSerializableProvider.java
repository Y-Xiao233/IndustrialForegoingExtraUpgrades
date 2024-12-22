package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.buuz135.industrial.recipe.LaserDrillFluidRecipe;
import com.buuz135.industrial.recipe.LaserDrillRarity;
import com.hrznstudio.titanium.recipe.generator.IJSONGenerator;
import com.hrznstudio.titanium.recipe.generator.IJsonFile;
import com.hrznstudio.titanium.recipe.generator.TitaniumSerializableProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.recipe.InfuserRecipe;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModFluids;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ModSerializableProvider extends TitaniumSerializableProvider {
    private final String modId = IndustrialForegoingExtraUpgrades.MODID;
    public ModSerializableProvider(DataGenerator generatorIn, String modid) {
        super(generatorIn, modid);
    }

    @Override
    public void add(Map<IJsonFile, IJSONGenerator> map) {
        new InfuserRecipe(new ResourceLocation(modId,"dragon_star"),
                Items.NETHER_STAR.getDefaultInstance(), new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(), 1000),200, ModContents.DRAGON_STAR.get().getDefaultInstance());

        new DissolutionChamberRecipe(new ResourceLocation(modId,"liquid_dragon_breath"),
                new Ingredient.Value[]{
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                        itemValue(Items.DRAGON_BREATH.getDefaultInstance()),
                },
                new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),500),200,
                new ItemStack(Items.GLASS_BOTTLE,8),new FluidStack(ModFluids.LIQUID_DRAGON_BREATH.getSourceFluid().get(),1000)
        );

        new LaserDrillFluidRecipe("liquid_sculk_matter",
                new FluidStack(ModFluids.LIQUID_SCULK_MATTER.getSourceFluid().get(),10).writeToNBT(new CompoundTag()),
                Ingredient.of(ModContents.LASER_LENS_SCULK.get()),
                new ResourceLocation("minecraft","warden"),
                new LaserDrillRarity(new ResourceKey[0], new ResourceKey[0],-64,256,8)
        );

        new DissolutionChamberRecipe(new ResourceLocation(modId,"laser_lens_sculk"),
                new Ingredient.Value[]{
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        tagValue(Tags.Items.GLASS_PANES_COLORLESS),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance()),
                        itemValue(Items.SCULK.getDefaultInstance())
                },new FluidStack(ModuleCore.ETHER.getSourceFluid().get(), 1000),200,
                ModContents.LASER_LENS_SCULK.get().getDefaultInstance(),FluidStack.EMPTY);


        DissolutionChamberRecipe.RECIPES.forEach(dissolutionChamberRecipe -> map.put(dissolutionChamberRecipe,dissolutionChamberRecipe));
        InfuserRecipe.RECIPES.forEach(infuserRecipe -> map.put(infuserRecipe,infuserRecipe));
        LaserDrillFluidRecipe.RECIPES.forEach(laserDrillFluidRecipe -> map.put(laserDrillFluidRecipe,laserDrillFluidRecipe));
    }

    public Ingredient.TagValue tagValue(TagKey<Item> tagKey){
        return new Ingredient.TagValue(tagKey);
    }
    public Ingredient.ItemValue itemValue(ItemStack itemStack){
        return new Ingredient.ItemValue(itemStack);
    }
}
