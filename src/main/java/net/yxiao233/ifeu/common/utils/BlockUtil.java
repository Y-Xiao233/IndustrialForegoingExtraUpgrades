package net.yxiao233.ifeu.common.utils;

import com.buuz135.industrial.utils.IFFakePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockUtil {
    private final Level level;
    private BlockPos pos;
    private static String uuid = "d28b7061-fb12-4064-90fb-7e02b95c72a5";
    public BlockUtil(Level level, BlockPos pos){
        this.level = level;
        this.pos = pos;
    }

    public void updatePos(BlockPos pos){
        this.pos = pos;
    }
    public void destroy(boolean dropBlock){
        destroy(level,pos,dropBlock);
    }

    public void destroyWithTool(ItemStack tool){
        destroyWithTool(level,pos,tool);
    }

    public void silkDestroy(ToolType type){
        silkDestroy(level,pos,type);
    }

    public  void silkDestroy(){
        silkDestroy(level,pos);
    }


    public static void destroy(Level level, BlockPos pos, boolean dropBlock){
        level.destroyBlock(pos,dropBlock);
    }

    public static void destroyWithTool(Level level, BlockPos pos,ItemStack tool){
        IFFakePlayer fakePlayer = new IFFakePlayer((ServerLevel) level,uuid);
        fakePlayer.setGameMode(GameType.SURVIVAL);
        fakePlayer.setItemInHand(InteractionHand.MAIN_HAND,tool);


        BlockState state = level.getBlockState(pos);
        Block.dropResources(state,level,pos,null,fakePlayer,tool);
        destroy(level,pos,false);
    }

    public static void silkDestroy(Level level, BlockPos pos, ToolType type){
        ItemStack tool = type.getTool();
        tool.enchant(level.registryAccess().holderOrThrow(Enchantments.SILK_TOUCH),1);
        destroyWithTool(level,pos,tool);
    }

    public static void silkDestroy(Level level, BlockPos pos){
        silkDestroy(level,pos,ToolType.PICKAXE);
    }

    public enum ToolType{
        PICKAXE(Items.NETHERITE_PICKAXE),
        AXE(Items.NETHERITE_AXE),
        SHOVEL(Items.NETHERITE_SHOVEL),
        HOE(Items.NETHERITE_HOE),
        SWORD(Items.NETHERITE_SWORD);
        private final ItemStack tool;

        ToolType(Item tool){
            this.tool = new ItemStack(tool);
        }

        public ItemStack getTool() {
            return tool;
        }
    }
}
