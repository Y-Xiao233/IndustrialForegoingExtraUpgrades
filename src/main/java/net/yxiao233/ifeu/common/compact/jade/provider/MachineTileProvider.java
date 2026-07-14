package net.yxiao233.ifeu.common.compact.jade.provider;

import com.hrznstudio.titanium.block.tile.MachineTile;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec2;
import net.yxiao233.ifeu.common.compact.jade.IFEUJadeIds;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

import java.util.ArrayList;
import java.util.List;

public class MachineTileProvider implements IBlockComponentProvider {
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
        List<IElement> elements = new ArrayList<>();
        BlockEntity blockEntity = accessor.getBlockEntity();
        int x = 0;
        if(blockEntity instanceof MachineTile<?> machine){
            SidedInventoryComponent<?> augmentInventory = machine.getAugmentInventory();
            for (int i = 0; i < augmentInventory.getSlots(); i++) {
                ItemStack stack = augmentInventory.getStackInSlot(i);
                if(!stack.isEmpty()){
                    elements.add(item(stack, x));
                    x += 6;
                }
            }
            tooltip.add(elements);
        }
    }

    public IElement item(ItemStack stack, int x) {
        try {
            return IElementHelper.get()
                    .item(stack, 0.55f)
                    .size(new Vec2(10, 10))
                    .translate(new Vec2(x, -2))
                    .message(null);

        } catch (NullPointerException e) {
            return IElementHelper.get()
                    .item(new ItemStack(Items.BARRIER), 0.55f)
                    .size(new Vec2(10, 10))
                    .translate(new Vec2(x, -2))
                    .message(null);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return IFEUJadeIds.MACHINE_TILE;
    }
}
