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
        public static final TagKey<Block> NEEDS_DRAGON_STAR_TOOL = TagUtil.getBlockTag(new ResourceLocation("ifeu:need_dragon_star_tool"));
        public static final TagKey<Block> MACHINE_FRAME_ULTIMATE = TagUtil.getBlockTag(new ResourceLocation("ifeu:machine_frame/ultimate"));
        public static final TagKey<Block> WRENCH_PICKUP = TagUtil.getBlockTag(new ResourceLocation("ifeu:wrench_pickup"));
        private static TagKey<Block> createTag(String name){
            return BlockTags.create(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,name));
        }
    }
    public static class Items{
        public static final TagKey<Item> GEARS_NETHERITE = TagUtil.getItemTag(new ResourceLocation("forge:gears/netherite"));
        public static final TagKey<Item> GEARS = TagUtil.getItemTag(new ResourceLocation("forge:gears"));
        public static final TagKey<Item> GEARS_SCULK = TagUtil.getItemTag(new ResourceLocation("forge:gears/sculk"));
        public static final TagKey<Item> MACHINE_FRAME_ULTIMATE = TagUtil.getItemTag(new ResourceLocation("ifeu:machine_frame/ultimate"));
        public static final TagKey<Item> WRENCH = TagUtil.getItemTag(new ResourceLocation("forge:wrench"));
        public static final TagKey<Item> DIAMOND = TagUtil.getItemTag(new ResourceLocation("forge:gems/diamond"));

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,name));
        }
    }
}
