package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.hrznstudio.titanium.api.IRecipeProvider;
import com.hrznstudio.titanium.block.BasicBlock;
import com.hrznstudio.titanium.recipe.generator.TitaniumRecipeProvider;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapelessRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.common.item.ModEfficiencyAddonItem;
import net.yxiao233.ifeu.common.item.ModProcessingAddonItem;
import net.yxiao233.ifeu.common.item.ModSpeedAddonItem;
import net.yxiao233.ifeu.common.registry.ModContents;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModRecipeProvider extends TitaniumRecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void register(Consumer<FinishedRecipe> consumer) {
        //Item
        ForgeRegistries.ITEMS.getEntries().forEach((reg) -> {
            //Upgrades
            if (reg.getValue() instanceof ModSpeedAddonItem speedAddonItem) {
                ((IRecipeProvider) speedAddonItem).registerRecipe(consumer);
            } else if (reg.getValue() instanceof ModEfficiencyAddonItem efficiencyAddonItem) {
                ((IRecipeProvider) efficiencyAddonItem).registerRecipe(consumer);
            } else if (reg.getValue() instanceof ModProcessingAddonItem processingAddonItem) {
                ((IRecipeProvider) processingAddonItem).registerRecipe(consumer);
            }
        });


        //Block
        ForgeRegistries.BLOCKS.getEntries().forEach((reg) -> {
            if(reg.getValue() instanceof BasicBlock){
                String s = reg.getValue().toString();
                String nameSpace = s.substring(6,s.indexOf(':'));
                if(nameSpace.equals("ifeu")){
                    ((IRecipeProvider) reg.getValue()).registerRecipe(consumer);
                }
            }
        });


        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModContents.LASER_LENS_SCULK.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(Items.SCULK,4)
                .save(consumer);
    }
}
