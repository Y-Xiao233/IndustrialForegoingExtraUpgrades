package net.yxiao233.ifeu.api.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.hrznstudio.titanium.block.RotatableBlock;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yxiao233.ifeu.api.block.entity.IFEUBlackHoleCapacitorEntity;
import net.yxiao233.ifeu.api.capability.EnergyStorageItemStack;
import net.yxiao233.ifeu.api.loot_table.IBlockLootTableProvider;
import net.yxiao233.ifeu.client.data.loot.ModBlockLootTables;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.registry.ModRarity;
import net.yxiao233.ifeu.common.utils.BlackHoleUtil;
import net.yxiao233.ifeu.common.utils.TooltipHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public class IFEUBlackHoleCapacitorBlock extends IndustrialBlock<IFEUBlackHoleCapacitorEntity> {
    private Rarity rarity;
    public IFEUBlackHoleCapacitorBlock(String name, Rarity rarity) {
        super(name, Properties.ofFullCopy(Blocks.IRON_BLOCK), IFEUBlackHoleCapacitorEntity.class, ModItems.TAB_ADDONS);
        this.rarity = rarity;
    }

    public BlockEntityType.BlockEntitySupplier<IFEUBlackHoleCapacitorEntity> getTileEntityFactory() {
//        return (blockPos, blockState) -> {
//            return new IFEUBlackHoleCapacitorEntity(this,getRarityType(),rarity,blockPos,blockState);
//        };
        return null;
    }

//    private BlockEntityType<?> getRarityType() {
//        if (this.rarity == ModRarity.PITY) {
//            return (BlockEntityType<?>)((DeferredHolder<?,?>) ModBlocks.BLACK_HOLE_CAPACITOR_PITY.type()).get();
//        } else if (this.rarity == ModRarity.SIMPLE) {
//            return (BlockEntityType<?>)((DeferredHolder<?,?>)ModBlocks.BLACK_HOLE_CAPACITOR_SIMPLE.type()).get();
//        } else if (this.rarity == ModRarity.ADVANCED) {
//            return (BlockEntityType<?>)((DeferredHolder<?,?>) ModBlocks.BLACK_HOLE_CAPACITOR_ADVANCED.type()).get();
//        } else {
//            return this.rarity == ModRarity.SUPREME ? (BlockEntityType<?>)((DeferredHolder<?,?>)ModBlocks.BLACK_HOLE_CAPACITOR_SUPREME.type()).get() : (BlockEntityType<?>)((DeferredHolder<?,?>)ModBlocks.BLACK_HOLE_CAPACITOR_PITY.type()).get();
//        }
//    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        TooltipHelper.addTooltip(tooltip,"black_hole_capacitor_0", BlackHoleUtil.getMaxEnergyCapacityByRarity(rarity));
        TooltipHelper.addTooltip(tooltip,"black_hole_capacitor_1", ChatFormatting.GOLD,new Object[]{Objects.requireNonNull(stack.getCapability(Capabilities.EnergyStorage.ITEM)).getEnergyStored()});
    }

    public RotatableBlock.RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    public void attack(BlockState state, Level worldIn, BlockPos pos, Player player) {
    }

    public NonNullList<ItemStack> getDynamicDrops(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        return NonNullList.create();
    }

//    @Override
//    public LootTable.Builder getLootTable(ModBlockLootTables tables) {
//        CopyNbtFunction.Builder nbtBuilder = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY);
//        nbtBuilder.copy("energyStorage", "BlockEntityTag.energyStorage");
//        return tables.droppingSelfWithNbt(this, nbtBuilder);
//    }

    public static class BlackHoleTankCapabilityProvider implements ICapabilityProvider {
        private final ItemStack stack;
        private final EnergyStorageItemStack energyStorageItemStack;

        public BlackHoleTankCapabilityProvider(ItemStack stack, Rarity rarity) {
            int max = BlackHoleUtil.getMaxEnergyCapacityByRarity(rarity);
            int io = max / 10;
            this.stack = stack;
            this.energyStorageItemStack = new EnergyStorageItemStack(stack,max,io,io);
        }

        @Nullable
        @Override
        public Object getCapability(Object cap, Object side) {
            return cap != null && cap.equals(Capabilities.EnergyStorage.ITEM) ? this.energyStorageItemStack : new EnergyStorageItemStack(stack,0,0,0);
        }
    }

    public static class BlackHoleCapacitorItem extends BlockItem {
        private Rarity rarity;
        private TitaniumTab tab;

        public BlackHoleCapacitorItem(Block blockIn, Item.Properties builder, Rarity rarity, TitaniumTab tab) {
            super(blockIn, builder);
            this.rarity = rarity;
            this.tab = tab;
        }

//        @Nullable
//        @Override
//        public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
//            return new IFEUBlackHoleCapacitorBlock.BlackHoleTankCapabilityProvider(stack, this.rarity);
//        }
    }
}
