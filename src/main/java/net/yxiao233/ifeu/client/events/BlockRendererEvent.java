package net.yxiao233.ifeu.client.events;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.block.renderer.IFEUStructureEntityRenderer;
import net.yxiao233.ifeu.api.block.renderer.IFEUStructureEntityRendererJS;
import net.yxiao233.ifeu.common.block.entity.BigDissolutionChamberEntity;
import net.yxiao233.ifeu.common.block.entity.DragonGeneratorEntity;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.block.entity.FluidTransferEntity;
import net.yxiao233.ifeu.common.block.renderer.DragonGeneratorRenderer;
import net.yxiao233.ifeu.common.block.renderer.FluidCraftingTableRenderer;
import net.yxiao233.ifeu.common.block.renderer.FluidTransferRenderer;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUEvents;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUStructureRenderJS;
import net.yxiao233.ifeu.common.registry.ModBlocks;

@EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlockRendererEvent {
    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer((BlockEntityType<FluidCraftingTableEntity>) ModBlocks.FLUID_CRAFTING_TABLE.type().get(), FluidCraftingTableRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<DragonGeneratorEntity>) ModBlocks.DRAGON_GENERATOR.type().get(), DragonGeneratorRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<FluidTransferEntity>) ModBlocks.FLUID_TRANSFER.type().get(), FluidTransferRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<BigDissolutionChamberEntity>) ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE.type().get(), IFEUStructureEntityRenderer::new);
    }
}
