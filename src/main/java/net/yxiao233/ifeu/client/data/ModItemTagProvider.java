package net.yxiao233.ifeu.client.data;

import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.utils.IndustrialTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.yxiao233.ifeu.api.tree.DeferredTree;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import net.yxiao233.ifeu.common.registry.IFEUTags;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    private final List<ResourceLocation> generated = new ArrayList<>();
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(IFEUTags.Items.GEARS_NETHERITE).add(IFEUContents.NETHERITE_GEAR.get());
        tag(IFEUTags.Items.GEARS_SCULK).add(IFEUContents.SCULK_GEAR.get());
        tag(IndustrialTags.Items.GEAR_DIAMOND).add(ModuleCore.DIAMOND_GEAR.get());
        tag(IndustrialTags.Items.GEAR_GOLD).add(ModuleCore.GOLD_GEAR.get());
        tag(IndustrialTags.Items.GEAR_IRON).add(ModuleCore.IRON_GEAR.get());
        tag(IFEUTags.Items.MACHINE_FRAME_ULTIMATE).add(IFEUContents.ULTIMATE_MACHINE_FRAME.get().asItem());

        tag(IFEUTags.Items.GEARS)
                .addTag(IndustrialTags.Items.GEAR_IRON)
                .addTag(IndustrialTags.Items.GEAR_GOLD)
                .addTag(IndustrialTags.Items.GEAR_DIAMOND)
                .addTag(IFEUTags.Items.GEARS_NETHERITE)
                .addTag(IFEUTags.Items.GEARS_SCULK);

        tag(ItemTags.PICKAXES)
                .add(IFEUContents.DRAGON_STAR_PICKAXE.get());

        tag(ItemTags.AXES)
                .add(IFEUContents.DRAGON_STAR_AXE.get());

        tag(ItemTags.SHOVELS)
                .add(IFEUContents.DRAGON_STAR_SHOVEL.get());

        tag(ItemTags.SWORDS)
                .add(IFEUContents.DRAGON_STAR_SWORD.get());

        tag(ItemTags.HOES)
                .add(IFEUContents.DRAGON_STAR_HOE.get());

        tag(IFEUTags.Items.WRENCH)
                .add(IFEUContents.WRENCH.get());

        tag(IFEUTags.Items.ROTTEN_CROPS)
                .add(Items.POISONOUS_POTATO);


        DeferredTree.DeferredTreesRegister.getAllTrees().forEach(tree ->{
            if(shouldGenerated(tree.getLogItem().get())){
                this.tag(ItemTags.LOGS_THAT_BURN).add(tree.getLogItem().get());
                generated(tree.getLogItem().get());
            }
            if(shouldGenerated(tree.getStrippedLogItem().get())){
                this.tag(ItemTags.LOGS_THAT_BURN).add(tree.getStrippedLogItem().get());
                generated(tree.getStrippedLogItem().get());
            }
            if(shouldGenerated(tree.getWoodItem().get())){
                this.tag(ItemTags.LOGS_THAT_BURN).add(tree.getWoodItem().get());
                generated(tree.getWoodItem().get());
            }
            if(shouldGenerated(tree.getStrippedWoodItem().get())){
                this.tag(ItemTags.LOGS_THAT_BURN).add(tree.getStrippedWoodItem().get());
                generated(tree.getStrippedWoodItem().get());
            }
            if(shouldGenerated(tree.getPlanksItem().get())){
                this.tag(ItemTags.PLANKS).add(tree.getPlanksItem().get());
                generated(tree.getPlanksItem().get());
            }
        });
    }

    private boolean shouldGenerated(Item item){
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(item);
        return !generated.contains(location);
    }

    private void generated(Item item){
        generated.add(BuiltInRegistries.ITEM.getKey(item));
    }
}
