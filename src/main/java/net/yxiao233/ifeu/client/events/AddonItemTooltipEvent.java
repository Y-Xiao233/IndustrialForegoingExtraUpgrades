package net.yxiao233.ifeu.client.events;

import com.buuz135.industrial.item.addon.EfficiencyAddonItem;
import com.buuz135.industrial.item.addon.ProcessingAddonItem;
import com.buuz135.industrial.item.addon.SpeedAddonItem;
import com.buuz135.industrial.module.ModuleCore;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.common.config.machine.DragonStarGeneratorConfig;
import net.yxiao233.ifeu.common.item.ModEfficiencyAddonItem;
import net.yxiao233.ifeu.common.item.ModProcessingAddonItem;
import net.yxiao233.ifeu.common.item.ModSpeedAddonItem;
import net.yxiao233.ifeu.common.registry.ModContents;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = IndustrialForegoingExtraUpgrades.MODID, bus =  Mod.EventBusSubscriber.Bus.FORGE)
public class AddonItemTooltipEvent {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event){
        Item item = event.getItemStack().getItem();

        addAddonItemTooltip(item,event);
        addDragonStarTooltip(item,event);
    }

    public static void addAddonItemTooltip(Item item, ItemTooltipEvent event){
        if(item instanceof ProcessingAddonItem){
            float tier = 0;
            if(item == ModuleCore.PROCESSING_ADDON_1.get()){
                tier = 1;
            }else if(item == ModuleCore.PROCESSING_ADDON_2.get()){
                tier = 2;
            }else if(item instanceof ModProcessingAddonItem processingAddonItem){
                tier = processingAddonItem.tier;
            }
            float upgrade = 1 + tier;
            event.getToolTip().add(Component.translatable("item.industrialforegoing.processing").append("x" + upgrade).withStyle(ChatFormatting.GRAY));
        }else if (item instanceof SpeedAddonItem) {
            float tier = 0;
            if(item == ModuleCore.SPEED_ADDON_1.get()){
                tier = 1;
            }else if (item == ModuleCore.SPEED_ADDON_2.get()){
                tier = 2;
            }else if (item instanceof ModSpeedAddonItem speedAddonItem){
                tier = speedAddonItem.tier;
            }
            float reduction = (1.0F - 1.0F / (1.0F + tier)) * -100.0F;
            event.getToolTip().add(Component.translatable("tooltip.ifeu.cooldown_time").append(": " + Math.ceil(reduction) + "%").withStyle(ChatFormatting.GRAY));
        }else if(item instanceof EfficiencyAddonItem){
            float tier = 0;
            if(item == ModuleCore.EFFICIENCY_ADDON_1.get()){
                tier = 1;
            }else if(item == ModuleCore.EFFICIENCY_ADDON_2.get()){
                tier = 2;
            }else if(item instanceof ModEfficiencyAddonItem efficiencyAddonItem){
                tier = efficiencyAddonItem.tier;
            }
            float reduction = tier * -10;
            event.getToolTip().add(Component.translatable("tooltip.ifeu.cooldown_amount").append(": " + reduction + "%").withStyle(ChatFormatting.GRAY));
        }
    }
    public static void addDragonStarTooltip(Item item, ItemTooltipEvent event){
        if(item.getDefaultInstance().is(ModContents.DRAGON_STAR.get())){
            event.getToolTip().add(Component.translatable("tooltip.ifeu.dragon_star", DragonStarGeneratorConfig.powerPerTick).append(DragonStarGeneratorConfig.maxProgress + "tick").withStyle(ChatFormatting.AQUA));
        }
    }
}
