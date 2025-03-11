package net.yxiao233.ifeu.api.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.module.ModuleTransportStorage;
import com.hrznstudio.titanium.block.RotatableBlock;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.api.block.entity.IFEUBlackHoleCapacitorEntity;
import net.yxiao233.ifeu.api.capability.EnergyStorageItemStack;
import net.yxiao233.ifeu.api.loot_table.IBlockLootTableProvider;
import net.yxiao233.ifeu.client.data.loot.ModBlockLootTables;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModItems;
import net.yxiao233.ifeu.common.utils.BlackHoleUtil;
import net.yxiao233.ifeu.common.utils.TooltipHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class IFEUBlackHoleCapacitorBlock extends IndustrialBlock<IFEUBlackHoleCapacitorEntity> implements IBlockLootTableProvider {
    private Rarity rarity;
    public IFEUBlackHoleCapacitorBlock(String name, Rarity rarity) {
        super(name, Properties.copy(Blocks.IRON_BLOCK), IFEUBlackHoleCapacitorEntity.class, ModItems.TAB_ADDONS);
        this.rarity = rarity;
    }

    public BlockEntityType.BlockEntitySupplier<IFEUBlackHoleCapacitorEntity> getTileEntityFactory() {
        return (blockPos, blockState) -> {
            return new IFEUBlackHoleCapacitorEntity(this,getRarityType(),rarity,blockPos,blockState);
        };
    }

    private BlockEntityType<?> getRarityType() {
        if (this.rarity == ModuleCore.PITY_RARITY) {
            return (BlockEntityType<?>)((RegistryObject<?>)ModBlocks.BLACK_HOLE_CAPACITOR_PITY.getRight()).get();
        } else if (this.rarity == ModuleCore.SIMPLE_RARITY) {
            return (BlockEntityType<?>)((RegistryObject<?>)ModBlocks.BLACK_HOLE_CAPACITOR_SIMPLE.getRight()).get();
        } else if (this.rarity == ModuleCore.ADVANCED_RARITY) {
            return (BlockEntityType<?>)((RegistryObject<?>) ModBlocks.BLACK_HOLE_CAPACITOR_ADVANCED.getRight()).get();
        } else {
            return this.rarity == ModuleCore.SUPREME_RARITY ? (BlockEntityType<?>)((RegistryObject<?>)ModBlocks.BLACK_HOLE_CAPACITOR_SUPREME.getRight()).get() : (BlockEntityType<?>)((RegistryObject<?>)ModBlocks.BLACK_HOLE_CAPACITOR_PITY.getRight()).get();
        }
    }

    public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        TooltipHelper.addTooltip(tooltip,"black_hole_capacitor_0", BlackHoleUtil.getMaxEnergyCapacityByRarity(rarity));
        stack.getCapability(ForgeCapabilities.ENERGY).ifPresent((energyStorage) -> {
            TooltipHelper.addTooltip(tooltip,"black_hole_capacitor_1",ChatFormatting.GOLD,new Object[]{energyStorage.getEnergyStored()});
        });
    }

    public RotatableBlock.RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    public void attack(BlockState state, Level worldIn, BlockPos pos, Player player) {
    }

    public NonNullList<ItemStack> getDynamicDrops(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        return NonNullList.create();
    }

    @Override
    public LootTable.Builder getLootTable(ModBlockLootTables tables) {
        CopyNbtFunction.Builder nbtBuilder = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY);
        nbtBuilder.copy("energyStorage", "BlockEntityTag.energyStorage");
        return tables.droppingSelfWithNbt(this, nbtBuilder);
    }

    public static class BlackHoleTankCapabilityProvider implements ICapabilityProvider {
        private final ItemStack stack;
        private LazyOptional<net.yxiao233.ifeu.api.capability.EnergyStorageItemStack> energyStorageItemStack;

        public BlackHoleTankCapabilityProvider(ItemStack stack, Rarity rarity) {
            int max = BlackHoleUtil.getMaxEnergyCapacityByRarity(rarity);
            int io = max / 10;
            this.stack = stack;
            this.energyStorageItemStack = LazyOptional.of(() -> {
                return new EnergyStorageItemStack(stack,max,io,io);
            });
        }

        @Nonnull
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap != null && cap.equals(ForgeCapabilities.ENERGY) ? this.energyStorageItemStack.cast() : LazyOptional.empty();
        }
    }

    public static class BlackHoleCapacitorItem extends BlockItem{
        private Rarity rarity;
        private TitaniumTab tab;

        public BlackHoleCapacitorItem(Block blockIn, Item.Properties builder, Rarity rarity, TitaniumTab tab) {
            super(blockIn, builder);
            this.rarity = rarity;
            this.tab = tab;
        }

        @Nullable
        @Override
        public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
            return new IFEUBlackHoleCapacitorBlock.BlackHoleTankCapabilityProvider(stack, this.rarity);
        }
    }
}
