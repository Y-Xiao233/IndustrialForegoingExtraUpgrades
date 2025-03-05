package net.yxiao233.ifeu.common.item;

import com.buuz135.industrial.item.addon.EfficiencyAddonItem;
import com.buuz135.industrial.item.addon.ProcessingAddonItem;
import com.buuz135.industrial.item.addon.RangeAddonItem;
import com.buuz135.industrial.item.addon.SpeedAddonItem;
import com.hrznstudio.titanium.block.tile.ActiveTile;
import com.hrznstudio.titanium.block.tile.MachineTile;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.sideness.IFacingComponent;
import com.hrznstudio.titanium.util.FacingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.common.networking.ModNetWorking;
import net.yxiao233.ifeu.common.networking.packet.ConfigurationToolItemKeyDownSyncC2SPacket;
import net.yxiao233.ifeu.common.utils.InventoryComponentHelper;
import net.yxiao233.ifeu.common.utils.KeyDownUtil;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ConfigurationToolItem extends Item {
    public static boolean isCtrlDown = false;
    public ConfigurationToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if(level.isClientSide()){
            isCtrlDown = KeyDownUtil.isCtrlKeyDown();
            ModNetWorking.sendToServer(new ConfigurationToolItemKeyDownSyncC2SPacket(isCtrlDown));
        }


        if(!level.isClientSide()){
            Player player = context.getPlayer();
            if(player == null){
                return InteractionResult.PASS;
            }
            if(context.getHand() != InteractionHand.MAIN_HAND){
                return InteractionResult.PASS;
            }
            if(player.isShiftKeyDown() && !isCtrlDown){
                return addConfigToTag(context);
            }else if(player.isShiftKeyDown() && isCtrlDown){
                return applyConfig(context);
            }
        }
        return super.useOn(context);
    }


    private InteractionResult addConfigToTag(UseOnContext context){
        //base
        Level level = context.getLevel();
        BlockPos clickedBlockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(clickedBlockPos);
        Block block = blockState.getBlock();
        ItemStack stack = context.getItemInHand();
        CompoundTag emptyTag = new CompoundTag();
        CompoundTag tag = new CompoundTag();
        //inventory
        AtomicReference<NonNullList<HashMap<FacingUtil.Sideness, IFacingComponent.FaceMode>>> invFacings = new AtomicReference<>(NonNullList.create());
        AtomicReference<NonNullList<String>> invConfigName = new AtomicReference<>(NonNullList.create());
        AtomicBoolean hasInvConfig = new AtomicBoolean(false);

        CompoundTag invFacingsTag = new CompoundTag();

        //fluid
        AtomicReference<NonNullList<HashMap<FacingUtil.Sideness, IFacingComponent.FaceMode>>> fluidFacings = new AtomicReference<>(NonNullList.create());
        AtomicReference<NonNullList<String>> fluidConfigName = new AtomicReference<>(NonNullList.create());
        AtomicBoolean hasFluidConfig = new AtomicBoolean(false);

        CompoundTag fluidFacingsTag = new CompoundTag();

        //augments
        CompoundTag augmentsTag = new CompoundTag();
        AtomicBoolean hasAugments = new AtomicBoolean(false);


        if(blockState.hasBlockEntity()){
            BlockEntity blockEntity = level.getBlockEntity(clickedBlockPos);
            if(blockEntity instanceof ActiveTile<?> activeTile){
                //inv
                if(activeTile.getMultiInventoryComponent() != null){
                    activeTile.getMultiInventoryComponent().getInventoryHandlers().forEach(component ->{
                        if(component instanceof SidedInventoryComponent<?> side){
                            if(!side.getName().equals("augments")){
                                hasInvConfig.set(true);
                                invFacings.get().add(side.getFacingModes());
                                invConfigName.get().add(side.getName());
                            }
                        }
                    });
                }

                //fluid
                if(activeTile.getMultiTankComponent() != null){
                    activeTile.getMultiTankComponent().getTanks().forEach(component -> {
                        if(component instanceof SidedFluidTankComponent<?> side){
                            hasFluidConfig.set(true);
                            fluidFacings.get().add(side.getFacingModes());
                            fluidConfigName.get().add(side.getName());
                        }
                    });
                }
            }

            //augments
            if(blockEntity instanceof MachineTile<?> machineTile){
                SidedInventoryComponent<?> invComponent = machineTile.getAugmentInventory();
                for (int i = 0; i < invComponent.getSlots(); i++) {
                    ItemStack aug = invComponent.getStackInSlot(i);
                    if(!aug.isEmpty() && aug.getTag() != null){
                        hasAugments.set(true);
                        augmentsTag.put(String.valueOf(ForgeRegistries.ITEMS.getKey(aug.getItem())),aug.getTag());
                    }
                }
            }
        }else{
            return InteractionResult.PASS;
        }

        //inv
        if(hasInvConfig.get()){
            if(invFacings.get().isEmpty()){
                stack.setTag(emptyTag);
                return InteractionResult.FAIL;
            }else{
                for (int i = 0; i < invFacings.get().size(); i++) {
                    Iterator<FacingUtil.Sideness> iterator = invFacings.get().get(i).keySet().iterator();
                    CompoundTag facingTag = new CompoundTag();
                    while (iterator.hasNext()) {
                        FacingUtil.Sideness facing = iterator.next();
                        facingTag.putString(facing.name(),invFacings.get().get(i).get(facing).name());
                    }
                    invFacingsTag.put(invConfigName.get().get(i),facingTag);
                }
                tag.put("invFacingModes",invFacingsTag);
            }
        }

        //fluid
        if(hasFluidConfig.get()){
            if(fluidFacings.get().isEmpty()){
                stack.setTag(emptyTag);
                return InteractionResult.FAIL;
            }else{
                for (int i = 0; i < fluidFacings.get().size(); i++) {
                    Iterator<FacingUtil.Sideness> iterator = fluidFacings.get().get(i).keySet().iterator();
                    CompoundTag facingTag = new CompoundTag();
                    while (iterator.hasNext()){
                        FacingUtil.Sideness facing = iterator.next();
                        facingTag.putString(facing.name(),fluidFacings.get().get(i).get(facing).name());
                    }
                    fluidFacingsTag.put(fluidConfigName.get().get(i),facingTag);
                }
                tag.put("fluidFacingModes",fluidFacingsTag);
            }
        }

        //augments
        if(hasAugments.get()){
            if(augmentsTag.isEmpty()){
                stack.setTag(emptyTag);
                return InteractionResult.FAIL;
            }else{
                tag.put("augments",augmentsTag);
            }
        }

        tag.putString("machine_type",block.getDescriptionId());

        stack.setTag(tag);
        context.getPlayer().displayClientMessage(Component.translatable("message.ifeu.configuration_tool.copy").withStyle(ChatFormatting.GOLD),true);
        return InteractionResult.SUCCESS;
    }

    private InteractionResult applyConfig(UseOnContext context) {
        //base
        Level level = context.getLevel();
        BlockPos clickedBlockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(clickedBlockPos);
        Block block = blockState.getBlock();
        ItemStack stack = context.getItemInHand();
        CompoundTag itemTag = stack.getTag();
        Player player = context.getPlayer();
        if(player == null){
            return InteractionResult.PASS;
        }
        //inv
        AtomicReference<HashMap<String,HashMap<FacingUtil.Sideness, IFacingComponent.FaceMode>>> invFacings = new AtomicReference<>(new HashMap<>());

        //fluid
        AtomicReference<HashMap<String,HashMap<FacingUtil.Sideness, IFacingComponent.FaceMode>>> fluidFacings = new AtomicReference<>(new HashMap<>());

        //augments
        ArrayList<CompoundTag> augmentsTag = new ArrayList<>();
        AtomicReference<NonNullList<Item>> augItem = new AtomicReference<>(NonNullList.create());

        if (itemTag != null) {
            boolean hasFluidConfig = itemTag.contains("fluidFacingModes");
            boolean hasInvConfig = itemTag.contains("invFacingModes");
            boolean hasAugments = itemTag.contains("augments");
            if (!itemTag.contains("machine_type")) {
                return InteractionResult.FAIL;
            }

            if (block.getDescriptionId().equals(itemTag.getString("machine_type"))) {

                //inv
                if(hasInvConfig){
                    CompoundTag facingModes = itemTag.getCompound("invFacingModes");
                    Iterator<String> facingsKeyIterator = facingModes.getAllKeys().iterator();

                    while (facingsKeyIterator.hasNext()) {
                        String key = facingsKeyIterator.next();
                        CompoundTag facingMode = facingModes.getCompound(key);

                        Iterator<String> iterator = facingMode.getAllKeys().iterator();

                        if(!key.equals("augments")){

                            HashMap<FacingUtil.Sideness, IFacingComponent.FaceMode> map = new HashMap<>();
                            while (iterator.hasNext()) {
                                String face = iterator.next();
                                map.put(FacingUtil.Sideness.valueOf(face), IFacingComponent.FaceMode.valueOf(facingMode.getString(face)));
                            }
                            invFacings.get().put(key,map);
                        }
                    }
                }

                //fluid
                if(hasFluidConfig){
                    CompoundTag facingModes = itemTag.getCompound("fluidFacingModes");
                    Iterator<String> facingsKeyIterator = facingModes.getAllKeys().iterator();

                    while (facingsKeyIterator.hasNext()) {
                        String key = facingsKeyIterator.next();
                        CompoundTag facingMode = facingModes.getCompound(key);

                        Iterator<String> iterator = facingMode.getAllKeys().iterator();
                        HashMap<FacingUtil.Sideness, IFacingComponent.FaceMode> map = new HashMap<>();
                        while (iterator.hasNext()) {
                            String face = iterator.next();
                            map.put(FacingUtil.Sideness.valueOf(face), IFacingComponent.FaceMode.valueOf(facingMode.getString(face)));
                        }
                        fluidFacings.get().put(key,map);
                    }
                }

                //augments
                if(hasAugments){
                    CompoundTag tempAugmentsTag = itemTag.getCompound("augments");

                    Iterator<String> iterator = tempAugmentsTag.getAllKeys().iterator();

                    while (iterator.hasNext()){
                        String key = iterator.next();
                        Item aug = ForgeRegistries.ITEMS.getValue(new ResourceLocation(key));
                        if(aug != null){
                            CompoundTag addonItemTag = tempAugmentsTag.getCompound(key);
                            if(!addonItemTag.isEmpty()){
                                augmentsTag.add(addonItemTag);
                                augItem.get().add(aug);
                            }else{
                                augmentsTag.add(new CompoundTag());
                                augItem.get().add(aug);
                            }
                        }
                    }
                }

                if (blockState.hasBlockEntity()) {
                    BlockEntity blockEntity = level.getBlockEntity(clickedBlockPos);
                    if (blockEntity instanceof ActiveTile<?> activeTile && !activeTile.isClient()) {
                        //inv
                        if(activeTile.getMultiInventoryComponent() != null && hasInvConfig){
                            activeTile.getMultiInventoryComponent().getInventoryHandlers().forEach(component -> {
                                if (component instanceof SidedInventoryComponent<?> side) {
                                    if(side.getName().equals("augments")){
                                        return;
                                    }
                                    side.getFacingModes().clear();
                                    side.getFacingModes().putAll(invFacings.get().get(side.getName()));
                                }
                            });
                        }

                        //fluid
                        if(activeTile.getMultiTankComponent() != null && hasFluidConfig){
                            activeTile.getMultiTankComponent().getTanks().forEach(component -> {
                                if (component instanceof SidedFluidTankComponent<?> side) {
                                    side.getFacingModes().clear();
                                    side.getFacingModes().putAll(fluidFacings.get().get(side.getName()));
                                }
                            });
                        }

                        //update
                        activeTile.markForUpdate();
                        activeTile.markComponentDirty();
                    }

                    //augments
                    if(hasAugments && blockEntity instanceof MachineTile<?> machineTile){
                        Inventory inventory = player.getInventory();
                        insertAugment(augItem.get(),augmentsTag,inventory,machineTile,SpeedAddonItem.class);
                        insertAugment(augItem.get(),augmentsTag,inventory,machineTile,RangeAddonItem.class);
                        insertAugment(augItem.get(),augmentsTag,inventory,machineTile,EfficiencyAddonItem.class);
                        insertAugment(augItem.get(),augmentsTag,inventory,machineTile,ProcessingAddonItem.class);
                    }

                    player.displayClientMessage(Component.translatable("message.ifeu.configuration_tool.paste").withStyle(ChatFormatting.GOLD),true);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.PASS;
                }
            }else{
                player.displayClientMessage(Component.translatable("message.ifeu.configuration_tool.different_type").withStyle(ChatFormatting.RED),true);
            }
        }
        return InteractionResult.PASS;
    }

    private void insertAugment(NonNullList<Item> augItems, ArrayList<CompoundTag> augmentsTag, Inventory playerInventory, MachineTile<?> machineTile, Class<?> clazz){
        for (int i = 0; i < augItems.size(); i++) {
            if(clazz.isAssignableFrom(augItems.get(i).getClass())){
                Item item = augItems.get(i);
                ItemStack stack = new ItemStack(item);
                stack.setTag(augmentsTag.get(i));
                if(playerInventory.findSlotMatchingItem(stack) != -1 && InventoryComponentHelper.canInsertAugment(machineTile,stack)){
                    int index = playerInventory.findSlotMatchingItem(stack);
                    playerInventory.getItem(index).setCount(playerInventory.getItem(index).getCount() - 1);
                    InventoryComponentHelper.insertAugment(machineTile,stack);
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide()){
            BlockHitResult blockHitResult = (BlockHitResult) player.pick(player.getBlockReach(), 0.0F, false);
            if(player.isShiftKeyDown() && level.getBlockState(blockHitResult.getBlockPos()).isAir()){
                player.getMainHandItem().setTag(new CompoundTag());
                player.displayClientMessage(Component.translatable("message.ifeu.connect_tool.clear_configuration").withStyle(ChatFormatting.GREEN),true);
                return InteractionResultHolder.success(player.getMainHandItem());
            }
        }
        return super.use(level, player, hand);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltips, TooltipFlag flag) {
        TooltipHelper.addTooltipWhileKeyDown(TooltipHelper.KeyType.SHIFT,tooltips,stack,() ->{
            TooltipHelper.addTooltip(tooltips,stack, ChatFormatting.GREEN,0);
            TooltipHelper.addTooltip(tooltips,stack, ChatFormatting.GOLD,1);
            TooltipHelper.addTooltip(tooltips,stack,ChatFormatting.RED,2);
        });

        CompoundTag tag = stack.getTag();
        if(tag == null || !tag.contains("machine_type")){
            TooltipHelper.addTooltip(tooltips,stack,ChatFormatting.RED,3,new Object[]{"null"});
        }else{
            String s = tag.getString("machine_type");
            TooltipHelper.addTooltip(tooltips,stack,ChatFormatting.AQUA,3,new Object[]{Component.translatable(s)});
        }
    }
}