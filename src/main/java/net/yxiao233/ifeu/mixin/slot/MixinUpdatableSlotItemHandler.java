package net.yxiao233.ifeu.mixin.slot;

import com.hrznstudio.titanium.container.addon.UpdatableSlotItemHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(UpdatableSlotItemHandler.class)
public class MixinUpdatableSlotItemHandler extends SlotItemHandler {

    public MixinUpdatableSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return true;
    }
}
