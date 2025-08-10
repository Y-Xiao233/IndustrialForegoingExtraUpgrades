package net.yxiao233.ifeu.client.events;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.block.renderer.IFEUStructureEntityRenderer;
import net.yxiao233.ifeu.api.block.renderer.PlatformBuilderRenderer;
import net.yxiao233.ifeu.common.block.entity.*;
import net.yxiao233.ifeu.common.block.renderer.DragonGeneratorRenderer;
import net.yxiao233.ifeu.common.block.renderer.FluidCraftingTableRenderer;
import net.yxiao233.ifeu.common.block.renderer.FluidTransferRenderer;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;

@Mod.EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class BlockRendererEvent {
    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer((BlockEntityType<FluidCraftingTableEntity>) IFEUBlocks.FLUID_CRAFTING_TABLE.getRight().get(),FluidCraftingTableRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<DragonGeneratorEntity>) IFEUBlocks.DRAGON_GENERATOR.getRight().get(), DragonGeneratorRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<FluidTransferEntity>) IFEUBlocks.FLUID_TRANSFER.getRight().get(), FluidTransferRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<BigDissolutionChamberEntity>) IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getRight().get(), IFEUStructureEntityRenderer::new);

        event.registerBlockEntityRenderer((BlockEntityType<PlatformBuilderEntity>) IFEUBlocks.PLATFORM_BUILDER.getRight().get(), PlatformBuilderRenderer::new);
    }
}