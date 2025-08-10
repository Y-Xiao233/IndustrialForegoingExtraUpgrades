package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.config.machine.core.DissolutionChamberConfig;
import com.buuz135.industrial.module.ModuleCore;
import com.buuz135.industrial.recipe.DissolutionChamberRecipe;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.component.bundle.LockableInventoryBundle;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.util.RecipeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.yxiao233.ifeu.api.block.entity.IFEUStructureProcessingTile;
import net.yxiao233.ifeu.api.components.IFEULockableInventoryBundle;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.api.item.ModAppleAddonItem;
import net.yxiao233.ifeu.common.config.machine.BigDissolutionChamberConfig;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.common.structure.IFEUMultiBlockStructures;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class BigDissolutionChamberEntity extends IFEUStructureProcessingTile<BigDissolutionChamberEntity> {
    private int maxProgress;
    private int powerPerTick;
    private int defaultMaxThread;
    @Save
    private IFEULockableInventoryBundle<BigDissolutionChamberEntity> input;
    @Save
    private SidedFluidTankComponent<BigDissolutionChamberEntity> inputFluid;
    @Save
    private SidedInventoryComponent<BigDissolutionChamberEntity> output;
    @Save
    private SidedFluidTankComponent<BigDissolutionChamberEntity> outputFluid;
    private DissolutionChamberRecipe currentRecipe;
    public BigDissolutionChamberEntity(BlockPos blockPos, BlockState blockState) {
        super(IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE, 102, 41, blockPos, blockState);

        int slotSpacing = 22;
        this.addBundle(this.input = new IFEULockableInventoryBundle<>(this.getAugmentInventory(),this, (new SidedInventoryComponent<BigDissolutionChamberEntity>("input", 34, 19, 8, 0))
                .setColor(DyeColor.LIGHT_BLUE)
                .setSlotPosition(BigDissolutionChamberEntity::getSlotPos)
                .setOutputFilter((stack, integer) -> false)
                .setComponentHarness(this)
                .setInputFilter((stack, integer) -> !this.canIncrease())
                .setOnSlotChanged((stack, integer) -> this.checkForRecipe()), 100, 64, false));

        this.addTank(this.inputFluid = (SidedFluidTankComponent<BigDissolutionChamberEntity>)(new SidedFluidTankComponent<BigDissolutionChamberEntity>("input_fluid", BigDissolutionChamberConfig.maxInputTankSize, 33 + slotSpacing, 18 + slotSpacing, 1))
                .setColor(DyeColor.LIME)
                .setTankType(FluidTankComponent.Type.SMALL)
                .setComponentHarness(this)
                .setOnContentChange(this::checkForRecipe));
        this.addInventory(this.output = (SidedInventoryComponent<BigDissolutionChamberEntity>)(new SidedInventoryComponent<BigDissolutionChamberEntity>("output", 129, 22, 3, 2))
                .setColor(DyeColor.ORANGE)
                .setRange(1, 3)
                .setInputFilter((stack, integer) -> false)
                .setComponentHarness(this));
        this.addTank(this.outputFluid = (SidedFluidTankComponent<BigDissolutionChamberEntity>)(new SidedFluidTankComponent<BigDissolutionChamberEntity>("output_fluid", BigDissolutionChamberConfig.maxOutputTankSize, 149, 20, 3))
                .setColor(DyeColor.MAGENTA)
                .setComponentHarness(this)
                .setTankAction(FluidTankComponent.Action.DRAIN));

        this.maxProgress = BigDissolutionChamberConfig.maxProgress;
        this.powerPerTick = BigDissolutionChamberConfig.powerPerTick;
        this.defaultMaxThread = BigDissolutionChamberConfig.maxThread;
    }

    private void checkForRecipe() {
        if (this.isServer()) {
            if(!hasCurrentStructure){
                this.currentRecipe = null;
                return;
            }
            if (this.currentRecipe != null && this.currentRecipe.matches(this.input.getInventory(), this.inputFluid)) {
                return;
            }

            this.currentRecipe = RecipeUtil.getRecipes(this.level, (RecipeType<DissolutionChamberRecipe>) ModuleCore.DISSOLUTION_TYPE.get()).stream().filter(recipe -> recipe.matches(this.input.getInventory(), this.inputFluid)).findFirst().orElse(null);
        }

    }


    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem item){
            if(item instanceof ModAppleAddonItem){
                return false;
            }
            return AugmentInventoryHelper.canAccept(this.getAugmentInventory(),augment);
        }
        return super.canAcceptAugment(augment);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        this.checkForRecipe();
    }

    public void setLevel(Level level) {
        super.setLevel(level);
        this.checkForRecipe();
    }

    @Override
    public boolean canIncrease() {
        return hasCurrentStructure && this.currentRecipe != null && ItemHandlerHelper.insertItem(this.output, this.currentRecipe.output.copy(), true).isEmpty() && (this.currentRecipe.outputFluid == null || this.outputFluid.fillForced(this.currentRecipe.outputFluid.copy(), IFluidHandler.FluidAction.SIMULATE) == this.currentRecipe.outputFluid.getAmount());
    }

    public Runnable onFinish() {
        return () -> {
            if (this.currentRecipe != null) {
                DissolutionChamberRecipe dissolutionChamberRecipe = this.currentRecipe;


                int thread = Math.min(getCurThread(dissolutionChamberRecipe),getMaxOutputThread());


                this.inputFluid.drainForced(dissolutionChamberRecipe.inputFluid.getAmount() * thread, IFluidHandler.FluidAction.EXECUTE);

                for(int i = 0; i < this.input.getInventory().getSlots(); ++i) {
                    this.input.getInventory().getStackInSlot(i).shrink(thread);
                }

                if (dissolutionChamberRecipe.outputFluid != null && !dissolutionChamberRecipe.outputFluid.isEmpty()) {
                    this.outputFluid.fillForced(new FluidStack(dissolutionChamberRecipe.outputFluid.getFluid(),dissolutionChamberRecipe.outputFluid.getAmount() * thread), IFluidHandler.FluidAction.EXECUTE);
                }

                ItemStack outputStack = dissolutionChamberRecipe.output.copy();
                outputStack.getItem().onCraftedBy(outputStack, this.level, (Player)null);
                ItemHandlerHelper.insertItem(this.output, new ItemStack(outputStack.getItem(),outputStack.getCount() * thread), false);
                this.checkForRecipe();
            }

        };
    }

    public int getCurThread(DissolutionChamberRecipe recipe){
        int thread = getMaxOutputThread();
        for (int i = 0; i < this.input.getInventory().getSlots(); i++) {
            if(!this.input.getInventory().getStackInSlot(i).isEmpty()){
                thread = Math.min(this.input.getInventory().getStackInSlot(i).getCount(),thread);
            }
        }

        int fluidAmount = this.inputFluid.getFluid().getAmount();
        int recipeNeededFluid = recipe.inputFluid.getAmount();
        thread = recipeNeededFluid * thread <= fluidAmount ? thread : (int) fluidAmount / recipeNeededFluid;

        if(recipe.outputFluid != null){
            int remainingOutputCapacity = this.outputFluid.getCapacity() - this.outputFluid.getFluidAmount();
            int outputFluidAmount = recipe.outputFluid.getAmount();
            thread = outputFluidAmount * thread <= remainingOutputCapacity ? thread : (int) remainingOutputCapacity / outputFluidAmount;
        }

        return thread;
    }

    public int getMaxOutputThread(){
        int tier = AugmentInventoryHelper.getAugmentTier(this.getAugmentInventory(), IFEUAugmentTypes.THREAD);
        return tier * 4 + defaultMaxThread;
    }

    protected EnergyStorageComponent<BigDissolutionChamberEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(DissolutionChamberConfig.maxStoredPower, 10, 20);
    }


    @Override
    protected int getTickPower() {
        return this.powerPerTick;
    }
    public int getMaxProgress() {
        return this.currentRecipe != null ? this.currentRecipe.processingTime : this.maxProgress;
    }

    public static Pair<Integer, Integer> getSlotPos(int slot) {
        int slotSpacing = 22;
        int offset = 2;
        switch (slot) {
            case 1:
                return Pair.of(Integer.valueOf(slotSpacing), -offset);
            case 2:
                return Pair.of(slotSpacing * 2, 0);
            case 3:
                return Pair.of(-offset, Integer.valueOf(slotSpacing));
            case 4:
                return Pair.of(slotSpacing * 2 + offset, Integer.valueOf(slotSpacing));
            case 5:
                return Pair.of(0, slotSpacing * 2);
            case 6:
                return Pair.of(Integer.valueOf(slotSpacing), slotSpacing * 2 + offset);
            case 7:
                return Pair.of(slotSpacing * 2, slotSpacing * 2);
            default:
                return Pair.of(0, 0);
        }
    }

    @NotNull
    @Override
    public BigDissolutionChamberEntity getSelf() {
        return this;
    }

    @Override
    public void loadSettings(Player player, CompoundTag tag) {
        if (tag.contains("BDC_locked")) {
            this.input.setLocked(tag.getBoolean("BDC_locked"));
        }

        String psFilter;
        if (tag.contains("BDC_filter")) {
            for(Iterator var3 = tag.getCompound("BDC_filter").getAllKeys().iterator(); var3.hasNext(); this.input.getFilter()[Integer.parseInt(psFilter)] = ItemStack.of(tag.getCompound("BDC_filter").getCompound(psFilter))) {
                psFilter = (String)var3.next();
            }
        }


        super.loadSettings(player, tag);
    }

    @Override
    public void saveSettings(Player player, CompoundTag tag) {
        tag.putBoolean("BDC_locked", this.input.isLocked());
        CompoundTag filterTag = new CompoundTag();

        for(int i = 0; i < this.input.getFilter().length; ++i) {
            filterTag.put("" + i, this.input.getFilter()[i].serializeNBT());
        }

        tag.put("BDC_filter", filterTag);

        super.saveSettings(player, tag);
    }

    @Override
    public MultiBlockStructure multiBlockStructure() {
        return IFEUMultiBlockStructures.BIG_DISSOLUTION_CHAMBER.getStructure();
    }

    @Override
    public int[] setStructureFormingPosition() {
        return new int[]{152,4};
    }
}
