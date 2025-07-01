package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.augment.AugmentTypes;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.item.AugmentWrapper;
import com.hrznstudio.titanium.util.RecipeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.config.machine.PrecisionCraftingTableConfig;
import net.yxiao233.ifeu.common.recipe.PrecisionShapedRecipe;
import net.yxiao233.ifeu.common.recipe.PrecisionShapelessRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.jetbrains.annotations.NotNull;

public class PrecisionCraftingTableEntity extends IndustrialProcessingTile<PrecisionCraftingTableEntity> {
    @Save
    public InventoryComponent<PrecisionCraftingTableEntity> inputs;
    @Save
    private SidedInventoryComponent<PrecisionCraftingTableEntity> output;
    private PrecisionShapedRecipe shapedRecipe;
    private PrecisionShapelessRecipe shapelessRecipe;
    public PrecisionCraftingTableEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.PRECISION_CRAFTING_TABLE, 102, 41, blockPos, blockState);
        int slotSpacing = 22;
        this.addInventory(this.inputs = new InventoryComponent<PrecisionCraftingTableEntity>("input",19+slotSpacing,slotSpacing,9)
                .setSlotToColorRender(0, DyeColor.LIGHT_BLUE)
                .setSlotToColorRender(1,DyeColor.LIGHT_BLUE)
                .setSlotToColorRender(2,DyeColor.LIGHT_BLUE)
                .setSlotToColorRender(3,DyeColor.LIGHT_BLUE)
                .setSlotToColorRender(4,DyeColor.LIGHT_BLUE)
                .setSlotToColorRender(5,DyeColor.LIGHT_BLUE)
                .setSlotToColorRender(6,DyeColor.LIGHT_BLUE)
                .setSlotToColorRender(7,DyeColor.LIGHT_BLUE)
                .setSlotToColorRender(8,DyeColor.LIGHT_BLUE)
                .setRange(3,3)
                .setOutputFilter(((itemStack, integer) -> false))
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setComponentHarness(this));

        this.addInventory(this.output = (SidedInventoryComponent<PrecisionCraftingTableEntity>) new SidedInventoryComponent<PrecisionCraftingTableEntity>("ot", 129, 18+slotSpacing, 1,1)
                .setColor(DyeColor.ORANGE)
                .setRange(1, 1)
                .setInputFilter((stack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setComponentHarness(this));
    }

    public void setChanged() {
        super.setChanged();
        this.checkForRecipe();
    }

    public void setLevel(Level p_155231_) {
        super.setLevel(p_155231_);
        this.checkForRecipe();
    }
    private void checkForRecipe(){
        if(isServer()){
            if(shapelessRecipe != null && shapelessRecipe.matches(inputs)){
                return;
            }

            if(shapedRecipe != null && shapedRecipe.matches(inputs)){
                return;
            }

            shapelessRecipe = RecipeUtil.getRecipes(this.level,(RecipeType<PrecisionShapelessRecipe>) ModRecipes.PRECISION_SHAPELESS_TYPE.get()).stream().filter(recipe -> recipe.matches(inputs)).findFirst().orElse(null);

            if(shapelessRecipe == null){
                shapedRecipe = RecipeUtil.getRecipes(this.level,(RecipeType<PrecisionShapedRecipe>) ModRecipes.PRECISION_SHAPED_TYPE.get()).stream().filter(recipe -> recipe.matches(inputs)).findFirst().orElse(null);
            }
        }
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, PrecisionCraftingTableEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        checkForRecipe();
    }

    @Override
    public boolean canIncrease() {
        if(shapedRecipe != null){
            return ItemHandlerHelper.insertItem(this.output,this.shapedRecipe.output.copy(),true).isEmpty();
        }else if(shapelessRecipe != null){
            return ItemHandlerHelper.insertItem(this.output,this.shapelessRecipe.output.copy(),true).isEmpty();
        }
        return false;
    }

    @Override
    public Runnable onFinish() {
        return () -> {
            float random = level.getRandom().nextFloat();
            if(shapedRecipe != null){
                PrecisionShapedRecipe shapedRecipe1 = shapedRecipe;
                for (int i = 0; i < inputs.getSlots(); i++) {
                    inputs.getStackInSlot(i).shrink(1);
                }
                ItemStack outputStack = shapedRecipe1.output.copy();
                outputStack.getItem().onCraftedBy(outputStack, this.level, null);
                float upgrade = AugmentInventoryHelper.getAugmentTier(this,IFEUAugmentTypes.CHANCE);
                float chance = shapedRecipe1.chance + (upgrade / 100) * 5;
                chance = chance >= 1 ? 1 : chance;
                if(chance >= random){
                    ItemHandlerHelper.insertItem(output, outputStack, false);
                    shapedRecipe = null;
                }
            }else if(shapelessRecipe != null){
                PrecisionShapelessRecipe shapelessRecipe1 = shapelessRecipe;
                for (int i = 0; i < inputs.getSlots(); i++) {
                    inputs.getStackInSlot(i).shrink(1);
                }
                ItemStack outputStack = shapelessRecipe1.output.copy();
                outputStack.getItem().onCraftedBy(outputStack, this.level, null);
                float upgrade = AugmentInventoryHelper.getAugmentTier(this,IFEUAugmentTypes.CHANCE);
                float chance = shapelessRecipe1.chance + (upgrade / 100) * 5;
                chance = chance >= 1 ? 1 : chance;
                if(chance >= random){
                    ItemHandlerHelper.insertItem(output, outputStack, false);
                    shapelessRecipe = null;
                }
            }
        };
    }

    @Override
    protected int getTickPower() {
        return PrecisionCraftingTableConfig.powerPerTick;
    }

    @NotNull
    @Override
    protected EnergyStorageComponent<PrecisionCraftingTableEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(PrecisionCraftingTableConfig.maxStoredPower,10,20);
    }
    @NotNull
    @Override
    public PrecisionCraftingTableEntity getSelf() {
        return this;
    }

    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(AugmentWrapper.hasType(augment, AugmentTypes.SPEED)){
            return !this.hasAugmentInstalled(AugmentTypes.SPEED);
        }else if(AugmentWrapper.hasType(augment, AugmentTypes.EFFICIENCY)){
            return !this.hasAugmentInstalled(AugmentTypes.EFFICIENCY);
        }else if(augment.getItem() instanceof IFEUAddonItem addonItem && addonItem.getType() == IFEUAugmentTypes.CHANCE){
            return AugmentInventoryHelper.canAccept(this,augment);
        }else{
            return false;
        }
    }
}
