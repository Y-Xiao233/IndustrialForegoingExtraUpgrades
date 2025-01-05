package net.yxiao233.ifeu.client.data;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.utils.IndustrialTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.GEARS_NETHERITE).add(ModContents.NETHERITE_GEAR.get());
        tag(ModTags.Items.GEARS_SCULK).add(ModContents.SCULK_GEAR.get());
        tag(IndustrialTags.Items.GEAR_DIAMOND).add(ModuleCore.DIAMOND_GEAR.get());
        tag(IndustrialTags.Items.GEAR_GOLD).add(ModuleCore.GOLD_GEAR.get());
        tag(IndustrialTags.Items.GEAR_IRON).add(ModuleCore.IRON_GEAR.get());

        tag(ModTags.Items.GEARS)
                .addTag(IndustrialTags.Items.GEAR_IRON)
                .addTag(IndustrialTags.Items.GEAR_GOLD)
                .addTag(IndustrialTags.Items.GEAR_DIAMOND)
                .addTag(ModTags.Items.GEARS_NETHERITE)
                .addTag(ModTags.Items.GEARS_SCULK);

        tag(ItemTags.PICKAXES)
                .add(ModContents.DRAGON_STAR_PICKAXE.get());

        tag(ItemTags.AXES)
                .add(ModContents.DRAGON_STAR_AXE.get());

        tag(ItemTags.SHOVELS)
                .add(ModContents.DRAGON_STAR_SHOVEL.get());

        tag(ItemTags.SWORDS)
                .add(ModContents.DRAGON_STAR_SWORD.get());

        tag(ItemTags.HOES)
                .add(ModContents.DRAGON_STAR_HOE.get());
    }
}
