package net.yxiao233.ifeu.client.events;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.block.entity.DragonGeneratorEntity;
import net.yxiao233.ifeu.common.block.entity.FluidCraftingTableEntity;
import net.yxiao233.ifeu.common.block.entity.FluidTransferEntity;
import net.yxiao233.ifeu.common.block.renderer.DragonGeneratorRenderer;
import net.yxiao233.ifeu.common.block.renderer.FluidCraftingTableRenderer;
import net.yxiao233.ifeu.common.block.renderer.FluidTransferRenderer;
import net.yxiao233.ifeu.common.registry.ModBlocks;

@Mod.EventBusSubscriber(modid = IndustrialForegoingExtraUpgrades.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class BlockRendererEvent {
    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer((BlockEntityType<FluidCraftingTableEntity>) ModBlocks.FLUID_CRAFTING_TABLE.getRight().get(),FluidCraftingTableRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<DragonGeneratorEntity>) ModBlocks.DRAGON_GENERATOR.getRight().get(), DragonGeneratorRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<FluidTransferEntity>) ModBlocks.FLUID_TRANSFER.getRight().get(), FluidTransferRenderer::new);
    }
}