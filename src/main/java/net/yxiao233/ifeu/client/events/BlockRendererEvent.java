package net.yxiao233.ifeu.client.events;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.block.renderer.IFEUStructureEntityRenderer;
import net.yxiao233.ifeu.api.block.renderer.PlatformBuilderRenderer;
import net.yxiao233.ifeu.common.block.entity.*;
import net.yxiao233.ifeu.common.block.renderer.DragonGeneratorRenderer;
import net.yxiao233.ifeu.common.block.renderer.FluidCraftingTableRenderer;
import net.yxiao233.ifeu.common.block.renderer.FluidTransferRenderer;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;

@EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlockRendererEvent {
    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer((BlockEntityType<FluidCraftingTableEntity>) IFEUBlocks.FLUID_CRAFTING_TABLE.type().get(), FluidCraftingTableRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<DragonGeneratorEntity>) IFEUBlocks.DRAGON_GENERATOR.type().get(), DragonGeneratorRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<FluidTransferEntity>) IFEUBlocks.FLUID_TRANSFER.type().get(), FluidTransferRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<BigDissolutionChamberEntity>) IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.type().get(), IFEUStructureEntityRenderer::new);

        event.registerBlockEntityRenderer((BlockEntityType<PlatformBuilderEntity>) IFEUBlocks.PLATFORM_BUILDER.type().get(), PlatformBuilderRenderer::new);
    }
}
