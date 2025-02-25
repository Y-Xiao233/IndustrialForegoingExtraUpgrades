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
        public static final TagKey<Block> NEEDS_DRAGON_STAR_TOOL = TagUtil.getBlockTag(ResourceLocation.parse("ifeu:need_dragon_star_tool"));
        public static final TagKey<Block> MACHINE_FRAME_ULTIMATE = TagUtil.getBlockTag(ResourceLocation.parse("ifeu:machine_frame/ultimate"));
        public static final TagKey<Block> WRENCH_PICKUP = TagUtil.getBlockTag(ResourceLocation.parse("ifeu:wrench_pickup"));
        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,name));
        }
    }
    public static class Items{
        public static final TagKey<Item> GEARS_NETHERITE = TagUtil.getItemTag(ResourceLocation.parse("c:gears/netherite"));
        public static final TagKey<Item> GEARS = TagUtil.getItemTag(ResourceLocation.parse("c:gears"));
        public static final TagKey<Item> GEARS_SCULK = TagUtil.getItemTag(ResourceLocation.parse("c:gears/sculk"));
        public static final TagKey<Item> MACHINE_FRAME_ULTIMATE = TagUtil.getItemTag(ResourceLocation.parse("ifeu:machine_frame/ultimate"));
        public static final TagKey<Item> WRENCH = TagUtil.getItemTag(ResourceLocation.parse("c:wrench"));

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,name));
        }
    }
}
