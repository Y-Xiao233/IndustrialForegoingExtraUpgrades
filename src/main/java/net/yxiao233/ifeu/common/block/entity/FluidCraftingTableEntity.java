package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IAsset;
import com.hrznstudio.titanium.api.client.IAssetType;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonInfo;
import com.hrznstudio.titanium.component.button.ButtonComponent;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.hrznstudio.titanium.component.fluid.SidedFluidTankComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.util.LangUtil;
import com.hrznstudio.titanium.util.RecipeUtil;
import mezz.jei.api.ingredients.IIngredientHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.yxiao233.ifeu.common.components.CustomTooltipComponent;
import net.yxiao233.ifeu.common.config.machine.FluidCraftingTableConfig;
import net.yxiao233.ifeu.common.recipe.FluidCraftingTableRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class FluidCraftingTableEntity extends IndustrialProcessingTile<FluidCraftingTableEntity> {
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input1;
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input2;
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input3;
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input4;
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input5;
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input6;
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input7;
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input8;
    @Save
    public InventoryComponent<FluidCraftingTableEntity> input9;
    @Save
    public SidedFluidTankComponent<FluidCraftingTableEntity> inputFluid;
    @Save
    private SidedInventoryComponent<FluidCraftingTableEntity> output;
    public FluidCraftingTableRecipe currentRecipe;
    public NonNullList<InventoryComponent<FluidCraftingTableEntity>> inputs;
    public boolean onFinish;
    public FluidStack fluidWillBeDrained;
    public FluidCraftingTableRecipe lastTimeRecipe;
    private ButtonComponent buttonComponent;
    @Save
    public boolean autoCraft = false;
    public FluidCraftingTableEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.FLUID_CRAFTING_TABLE,102,41,blockPos,blockState);
        int slotSpacing = 22;

        this.addInventory(this.input1 = new InventoryComponent<FluidCraftingTableEntity>("input1",19+slotSpacing,slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.input2 = new InventoryComponent<FluidCraftingTableEntity>("input2",37+slotSpacing,slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.input3 = new InventoryComponent<FluidCraftingTableEntity>("input3",55+slotSpacing,slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.input4 = new InventoryComponent<FluidCraftingTableEntity>("input4",19+slotSpacing,18+slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.input5 = new InventoryComponent<FluidCraftingTableEntity>("input5",37+slotSpacing,18+slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.input6 = new InventoryComponent<FluidCraftingTableEntity>("input6",55+slotSpacing,18+slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.input7 = new InventoryComponent<FluidCraftingTableEntity>("input7",19+slotSpacing,36+slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.input8 = new InventoryComponent<FluidCraftingTableEntity>("input8",37+slotSpacing,36+slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.input9 = new InventoryComponent<FluidCraftingTableEntity>("input9",55+slotSpacing,36+slotSpacing,1)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
                .setRange(1,1)
                .setOutputFilter((itemStack, integer) -> false)
                .setOnSlotChanged((itemStack, integer) -> checkForRecipe())
                .setSlotLimit(1)
                .setInputFilter(((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty()))
                .setComponentHarness(this));

        this.addInventory(this.output = (SidedInventoryComponent<FluidCraftingTableEntity>) new SidedInventoryComponent<FluidCraftingTableEntity>("ot", 129, 18+slotSpacing, 1,1)
                .setColor(DyeColor.ORANGE)
                .setRange(1, 1)
                .setInputFilter((stack, integer) -> false)
                .setComponentHarness(this));


        this.addTank(this.inputFluid = (SidedFluidTankComponent<FluidCraftingTableEntity>) new SidedFluidTankComponent<FluidCraftingTableEntity>("input_fluid", FluidCraftingTableConfig.maxInputTankSize,17,19,0)
                .setColor(DyeColor.LIME)
                .setTankType(FluidTankComponent.Type.NORMAL)
                .setComponentHarness(this)
                .setOnContentChange(() -> checkForRecipe()));

        this.addButton(this.buttonComponent = (new ButtonComponent(136, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    StateButtonInfo[] buttonInfo = new StateButtonInfo[2];
                    IAssetType<IAsset> asset = AssetTypes.BUTTON_SIDENESS_ENABLED;
                    String[] tip = new String[2];
                    ChatFormatting chatFormatting = ChatFormatting.GOLD;
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.fluid_crafting_table.auto_craft", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.auto_craft_1";
                    buttonInfo[0] = new StateButtonInfo(0, asset, tip);
                    asset = AssetTypes.BUTTON_SIDENESS_DISABLED;
                    tip = new String[2];
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.fluid_crafting_table.original", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.original_1";
                    buttonInfo[1] = new StateButtonInfo(1, asset, tip);
                    return new StateButtonAddon(this, buttonInfo) {
                        public int getState() {
                            return FluidCraftingTableEntity.this.autoCraft ? 0 : 1;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.autoCraft = !this.autoCraft;
            this.markForUpdate();
        }));


        this.inputs = NonNullList.of(null,
                input1,input2,input3,
                input4,input5,input6,
                input7,input8,input9
        );
    }

    @Override
    public boolean canIncrease() {
        if(currentRecipe != null){
            if(this.autoCraft){
                return ItemHandlerHelper.insertItem(output,currentRecipe.output.copy(),true).isEmpty();
            }else{
                return ItemHandlerHelper.insertItem(output,currentRecipe.output.copy(),true).isEmpty() && !onFinish;
            }
        }else{
            return false;
        }
    }

    private void checkForRecipe(){
        if(isServer()){
            if(currentRecipe != null && currentRecipe.matches(inputs,inputFluid)){
                return;
            }

            currentRecipe = RecipeUtil.getRecipes(this.level,(RecipeType<FluidCraftingTableRecipe>) ModRecipes.FLUID_CRAFTING_TABLE_TYPE.get()).stream().filter(recipe -> recipe.matches(inputs,inputFluid)).findFirst().orElse(null);
        }
    }


    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, FluidCraftingTableEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        if(!this.autoCraft){
            if(onFinish && this.output.getStackInSlot(0).isEmpty()){
                inputs.forEach(input ->{
                    input.getStackInSlot(0).shrink(1);
                });
                inputFluid.drainForced(this.fluidWillBeDrained, IFluidHandler.FluidAction.EXECUTE);
                onFinish = false;
                this.fluidWillBeDrained = null;
                this.lastTimeRecipe = null;
                checkForRecipe();
            }

            if(lastTimeRecipe != null){
                if(onFinish && !lastTimeRecipe.matches(inputs,inputFluid)) {
                    this.output.setStackInSlot(0, ItemStack.EMPTY);
                    this.onFinish = false;
                    this.fluidWillBeDrained = null;
                    this.lastTimeRecipe = null;
                    checkForRecipe();
                }
            }else if(!this.inputs.stream().allMatch(input -> input.getStackInSlot(0).isEmpty())){
                this.output.setStackInSlot(0, ItemStack.EMPTY);
                onFinish = false;
                fluidWillBeDrained = null;
                checkForRecipe();
            }
        }else{
            if(onFinish && lastTimeRecipe != null){
                inputs.forEach(input ->{
                    input.getStackInSlot(0).shrink(1);
                });

                inputFluid.drainForced(lastTimeRecipe.inputFluid,IFluidHandler.FluidAction.EXECUTE);

                this.onFinish = false;
                this.fluidWillBeDrained = null;
                this.lastTimeRecipe = null;
                checkForRecipe();
            }
        }
    }

    @Override
    public Runnable onFinish() {
        return () -> {
            if (currentRecipe != null && this.output.getStackInSlot(0).isEmpty()) {
                FluidCraftingTableRecipe fluidCraftingTableRecipe = currentRecipe;

                this.onFinish = true;
                this.fluidWillBeDrained = fluidCraftingTableRecipe.inputFluid;
                this.lastTimeRecipe = fluidCraftingTableRecipe;

                ItemStack outputStack = fluidCraftingTableRecipe.output.copy();
                outputStack.getItem().onCraftedBy(outputStack, this.level, null);
                ItemHandlerHelper.insertItem(output, outputStack, false);
            }
        };
    }

    @Override
    public int getMaxProgress() {
        return 1;
    }

    @NotNull
    @Override
    public FluidCraftingTableEntity getSelf() {
        return this;
    }

    @NotNull
    @Override
    protected EnergyStorageComponent<FluidCraftingTableEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(0,0,0,-1000,-1000);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        checkForRecipe();
    }


    @Override
    public void setLevel(Level p_155231_) {
        super.setLevel(p_155231_);
        checkForRecipe();
    }

    @Override
    protected int getTickPower() {
        return 0;
    }


    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putBoolean("on_finish",this.onFinish);
        compoundTag.putBoolean("auto_craft",this.autoCraft);
    }

    @Override
    public void loadSettings(Player player, CompoundTag tag) {
        super.loadSettings(player, tag);
        this.onFinish = tag.getBoolean("on_finish");
        this.autoCraft = tag.getBoolean("auto_craft");
    }
}