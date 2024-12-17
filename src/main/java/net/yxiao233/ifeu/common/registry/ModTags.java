package net.yxiao233.ifeu.common.registry;

import com.hrznstudio.titanium.util.TagUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;

public class ModTags {
    public static class Blocks{

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,name));
        }
    }
    public static class Items{
        public static final TagKey<Item> GEARS_NETHERITE = TagUtil.getItemTag(new ResourceLocation("forge:gears/netherite"));
        public static final TagKey<Item> GEARS = TagUtil.getItemTag(new ResourceLocation("forge:gears"));
        public static final TagKey<Item> GEARS_SCULK = TagUtil.getItemTag(new ResourceLocation("forge:gears/sculk"));

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,name));
        }
    }
}
