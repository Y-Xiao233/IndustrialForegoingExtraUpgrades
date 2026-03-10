package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialWorkingTile;
import com.buuz135.industrial.module.ModuleCore;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.ProgressBarScreenAddon;
import com.hrznstudio.titanium.component.bundle.LockableInventoryBundle;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.yxiao233.ifeu.common.config.machine.FermenterConfig;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUTags;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FermenterEntity extends IndustrialWorkingTile<FermenterEntity> {
    //TODO mixin workingTile to make it can achieve creative addon function
    public static List<TagKey<Item>> VALID = List.of(Tags.Items.CROPS, IFEUTags.Items.ROTTEN_CROPS);
    public static TagKey<Item> CATALYST = Tags.Items.MUSHROOMS;
    private int getMaxProgress;
    private int getPowerPerOperation;
    @Save
    private SidedFluidTankComponent<FermenterEntity> sludge;
    @Save
    private SidedFluidTankComponent<FermenterEntity> water;
    @Save
    private LockableInventoryBundle<FermenterEntity> input;
    @Save
    private SidedInventoryComponent<FermenterEntity> catalyst;
    @Save
    private ProgressBarComponent<FermenterEntity> bar;

    @SuppressWarnings("unchecked")
    public FermenterEntity(BlockPos blockPos, BlockState blockState) {
        super(IFEUBlocks.FERMENTER, FermenterConfig.powerPerOperation, blockPos, blockState);

        this.addTank(this.water = (SidedFluidTankComponent<FermenterEntity>)(new SidedFluidTankComponent<>("water", FermenterConfig.maxWaterTankStorage, 45, 20, 0))
                .setColor(DyeColor.CYAN).setComponentHarness(this)
                .setTankAction(FluidTankComponent.Action.FILL)
                .setValidator((fluidStack) -> {
                    return fluidStack.getFluid().equals(Fluids.WATER);
                })
        );

        this.addInventory(this.catalyst = (SidedInventoryComponent<FermenterEntity>) new SidedInventoryComponent<FermenterEntity>("catalyst",87,80,1,1).setColor(DyeColor.CYAN).setInputFilter((stack, integer) -> {
            return stack.is(CATALYST);
        }).setOutputFilter((stack, integer) -> {
            return false;
        }).setComponentHarness(this.getSelf()));
        this.addBundle(this.input = new LockableInventoryBundle<>(this, (new SidedInventoryComponent<FermenterEntity>("input", 69, 22, 9, 2)).setColor(DyeColor.BLUE).setRange(3,3).setInputFilter((stack, integer) -> {
            return this.canInsert(integer, stack);
        }).setOutputFilter((stack, integer) -> {
            return false;
        }).setComponentHarness(this), 136, 84, false));
        this.addTank(this.sludge = (SidedFluidTankComponent<FermenterEntity>)(new SidedFluidTankComponent<>("sludge", FermenterConfig.maxSludgeTankStorage, 128, 20, 3)).setColor(DyeColor.PURPLE).setComponentHarness(this).setTankAction(FluidTankComponent.Action.DRAIN).setValidator((fluidStack) -> {
            return fluidStack.getFluid().isSame(ModuleCore.SLUDGE.getSourceFluid().get());
        }));
        this.addProgressBar(this.bar = (new ProgressBarComponent<FermenterEntity>(150, 20, FermenterConfig.maxProgress) {
            @OnlyIn(Dist.CLIENT)
            public @NotNull List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    return new ProgressBarScreenAddon<>(FermenterEntity.this.bar.getPosX(), FermenterEntity.this.bar.getPosY(), this) {
                        public List<Component> getTooltipLines() {
                            Component[] var10000 = new Component[1];
                            String var10003 = String.valueOf(ChatFormatting.GOLD);
                            var10000[0] = Component.literal(var10003 + Component.translatable("text.industrialforegoing.display.efficiency_2").getString() + String.valueOf(ChatFormatting.WHITE) + (int) ((double) FermenterEntity.this.getEfficiency() / 9.0 * 100.0) + String.valueOf(ChatFormatting.DARK_AQUA) + "%");
                            return Arrays.asList(var10000);
                        }
                    };
                });
            }
        }).setColor(DyeColor.YELLOW).setCanIncrease((tileEntity) -> {
            return true;
        }).setOnTickWork(() -> {
            this.bar.setProgress((int)((double)this.getEfficiency() / 9.0 * 100.0));
        }).setCanReset((tileEntity) -> {
            return this.getEfficiency() != 9;
        }).setComponentHarness(this));

        this.getMaxProgress = FermenterConfig.maxProgress;
        this.getPowerPerOperation = FermenterConfig.powerPerOperation;
    }

    @Override
    public IndustrialWorkingTile<FermenterEntity>.WorkAction work() {
        if (this.hasEnergy(this.getPowerPerOperation)) {
            int efficiency = this.getEfficiency();
            if (efficiency <= 0) {
                return new IndustrialWorkingTile<FermenterEntity>.WorkAction(1.0F, 0);
            }
            if(catalyst.getStackInSlot(0).isEmpty()){
                return new WorkAction(1.0F,0);
            }

            int fluidAmount = ((efficiency - 1) * 40 + 93) * efficiency + 2;
            if (this.water.getFluidAmount() >= fluidAmount && this.sludge.getCapacity() - this.sludge.getFluidAmount() >= fluidAmount) {
                this.water.drainForced(fluidAmount, IFluidHandler.FluidAction.EXECUTE);
                this.sludge.fillForced(new FluidStack(ModuleCore.SLUDGE.getSourceFluid().get(), fluidAmount), IFluidHandler.FluidAction.EXECUTE);

                for(int i = 0; i < this.input.getInventory().getSlots(); ++i) {
                    this.input.getInventory().getStackInSlot(i).shrink(1);
                }

                return new IndustrialWorkingTile<FermenterEntity>.WorkAction(1.0F, this.getPowerPerOperation);
            }
        }

        return new IndustrialWorkingTile<FermenterEntity>.WorkAction(1.0F, 0);
    }

    @NotNull
    @Override
    public FermenterEntity getSelf() {
        return this;
    }

    private boolean canInsert(int slot, ItemStack stack) {
        int foundSlot = -1;

        for(int i = 0; i < this.input.getInventory().getSlots(); ++i) {
            if (ItemStack.isSameItem(this.input.getInventory().getStackInSlot(i), stack)) {
                foundSlot = i;
            }
        }

        for(TagKey<Item> itemTag : List.copyOf(VALID)) {
            if (stack.is(itemTag) && (foundSlot == -1 || this.input.getInventory().getStackInSlot(foundSlot).getCount() + stack.getCount() <= this.input.getInventory().getStackInSlot(foundSlot).getMaxStackSize() && slot == foundSlot)) {
                return true;
            }
        }
        return false;
    }

    private int getEfficiency() {
        int slots = 0;

        for(int i = 0; i < this.input.getInventory().getSlots(); ++i) {
            if (!this.input.getInventory().getStackInSlot(i).isEmpty()) {
                if(this.input.getInventory().getStackInSlot(i).is(IFEUTags.Items.ROTTEN_CROPS)){
                    slots += 4;
                }
                ++slots;
            }
        }

        return slots;
    }

    @Override
    protected @NotNull EnergyStorageComponent<FermenterEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(FermenterConfig.maxStoredPower, 10, 20);
    }

    @Override
    public int getMaxProgress() {
        return this.getMaxProgress;
    }

    @Override
    public void loadSettings(Player player, CompoundTag tag) {
        if (tag.contains("FE_locked")) {
            this.input.setLocked(tag.getBoolean("FE_locked"));
        }

        String spFilter;
        if (tag.contains("FE_filter")) {
            for(Iterator<String> iterator = tag.getCompound("FE_filter").getAllKeys().iterator(); iterator.hasNext(); this.input.getFilter()[Integer.parseInt(spFilter)] = ItemStack.parseOptional(this.level.registryAccess(), tag.getCompound("FE_filter").getCompound(spFilter))) {
                spFilter = iterator.next();
            }
        }

        super.loadSettings(player, tag);
    }

    public void saveSettings(Player player, CompoundTag tag) {
        tag.putBoolean("FE_locked", this.input.isLocked());
        CompoundTag filterTag = new CompoundTag();

        for(int i = 0; i < this.input.getFilter().length; ++i) {
            filterTag.put("" + i, this.input.getFilter()[i].saveOptional(this.level.registryAccess()));
        }

        tag.put("FE_filter", filterTag);
        super.saveSettings(player, tag);
    }
}
