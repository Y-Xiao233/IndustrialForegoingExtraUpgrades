package net.yxiao233.ifeu.common.event;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.block.entity.EnumPropertyBasicTile;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryCapabilitiesHandler {
    @SubscribeEvent
    public static void onRegister(RegisterCapabilitiesEvent event){
        registryEnumPropertyTile(event, IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.type());
    }

    private static void registryEnumPropertyTile(RegisterCapabilitiesEvent event ,Holder<BlockEntityType<?>> type){
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, (BlockEntityType<?>)type.value(), (object, context) -> {
            if (object instanceof EnumPropertyBasicTile<?,?> powered) {
                return powered.getEnergy();
            } else {
                return null;
            }
        });
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, (BlockEntityType<?>)type.value(), (object, context) -> {
            if (object instanceof EnumPropertyBasicTile<?,?> tile) {
                return tile.getFluidHandler(context);
            } else {
                return null;
            }
        });
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, (BlockEntityType<?>)type.value(), (object, context) -> {
            if (object instanceof EnumPropertyBasicTile<?,?> tile) {
                return tile.getItemHandler(context);
            } else {
                return null;
            }
        });
    }
}
