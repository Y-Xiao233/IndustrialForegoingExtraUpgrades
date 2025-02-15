package net.yxiao233.ifeu.client.events;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.block.renderer.FluidCraftingTableRenderer;
import net.yxiao233.ifeu.common.registry.ModBlocks;

@EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlockRendererEvent {
    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer((BlockEntityType<FluidCraftingTableEntity>) ModBlocks.FLUID_CRAFTING_TABLE.type().get(), FluidCraftingTableRenderer::new);
    }
}
