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
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.yxiao233.ifeu.common.config.machine.FluidCraftingTableConfig;
import net.yxiao233.ifeu.common.recipe.ShapedRecipe;
import net.yxiao233.ifeu.common.recipe.ShapelessRecipe;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModRecipes;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class FluidCraftingTableEntity extends IndustrialProcessingTile<FluidCraftingTableEntity> {
    @Save
    public InventoryComponent<FluidCraftingTableEntity> inputs;
    @Save
    public SidedFluidTankComponent<FluidCraftingTableEntity> inputFluid;
    @Save
    private SidedInventoryComponent<FluidCraftingTableEntity> output;
    public ShapedRecipe shapedRecipe;
    public ShapelessRecipe shapelessRecipe;
    public boolean shapedOnFinish;
    public boolean shapelessOnFinish;
    public FluidStack shapedFluidWillBeDrained;
    public FluidStack shapelessFluidWillBeDrained;
    public ShapedRecipe lastTimeShapedRecipe;
    public ShapelessRecipe lastTimeShapelessRecipe;
    private ButtonComponent buttonComponent1;
    private ButtonComponent buttonComponent2;
    @Save
    public boolean autoCraft = false;
    @Save
    public boolean isFluidRender = true;
    public FluidCraftingTableEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.FLUID_CRAFTING_TABLE,102,41,blockPos,blockState);
        int slotSpacing = 22;

        this.addInventory(this.inputs = new InventoryComponent<FluidCraftingTableEntity>("input",19+slotSpacing,slotSpacing,9)
                .setSlotToColorRender(0,DyeColor.LIGHT_BLUE)
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
                .setInputFilter((itemStack, integer) -> this.output.getStackInSlot(0).isEmpty())
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

        this.addButton(this.buttonComponent1 = (new ButtonComponent(136, 84, 14, 14) {
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
        }).setId(1));


        this.addButton(this.buttonComponent2 = (new ButtonComponent(120, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    StateButtonInfo[] buttonInfo = new StateButtonInfo[2];
                    IAssetType<IAsset> asset = AssetTypes.BUTTON_SIDENESS_ENABLED;
                    String[] tip = new String[2];
                    ChatFormatting chatFormatting = ChatFormatting.GOLD;
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.fluid_crafting_table.render_fluid", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.render_fluid_1";
                    buttonInfo[0] = new StateButtonInfo(0, asset, tip);
                    asset = AssetTypes.BUTTON_SIDENESS_DISABLED;
                    tip = new String[2];
                    tip[0] = chatFormatting + LangUtil.getString("tooltip.ifeu.fluid_crafting_table.not_render_fluid", new Object[0]);
                    tip[1] = "tooltip.ifeu.fluid_crafting_table.not_render_fluid_1";
                    buttonInfo[1] = new StateButtonInfo(1, asset, tip);
                    return new StateButtonAddon(this, buttonInfo) {
                        public int getState() {
                            return FluidCraftingTableEntity.this.isFluidRender ? 0 : 1;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.isFluidRender = !this.isFluidRender;
            this.markForUpdate();
        }).setId(2));
    }

    @Override
    public boolean canIncrease() {
        if(shapedRecipe != null){
            if(this.autoCraft){
                return ItemHandlerHelper.insertItem(output,shapedRecipe.output.copy(),true).isEmpty();
            }else{
                return ItemHandlerHelper.insertItem(output,shapedRecipe.output.copy(),true).isEmpty() && !shapedOnFinish;
            }
        }else if(shapelessRecipe != null){
            if(this.autoCraft){
                return ItemHandlerHelper.insertItem(output,shapelessRecipe.output.copy(),true).isEmpty();
            }else{
                return ItemHandlerHelper.insertItem(output,shapelessRecipe.output.copy(),true).isEmpty() && !shapelessOnFinish;
            }
        }else{
            return false;
        }
    }

    private void checkForRecipe(){
        if(isServer()){
            if(shapelessRecipe != null && shapelessRecipe.matches(inputs,inputFluid)){
                return;
            }

            if(shapedRecipe != null && shapedRecipe.matches(inputs,inputFluid)){
                return;
            }

            shapelessRecipe = RecipeUtil.getRecipes(this.level,(RecipeType<ShapelessRecipe>) ModRecipes.SHAPELESS_TYPE.get()).stream().filter(recipe -> recipe.matches(inputs,inputFluid)).findFirst().orElse(null);
            System.out.println(shapelessRecipe);

            if(shapelessRecipe == null){
                shapedRecipe = RecipeUtil.getRecipes(this.level,(RecipeType<ShapedRecipe>) ModRecipes.SHAPED_TYPE.get()).stream().filter(recipe -> recipe.matches(inputs,inputFluid)).findFirst().orElse(null);
            }
        }
    }


    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, FluidCraftingTableEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        if(!this.autoCraft){
            if(shapedOnFinish && (this.output.getStackInSlot(0).isEmpty() || this.output.getStackInSlot(0).getCount() < this.lastTimeShapedRecipe.output.getCount())){
                for (int i = 0; i < inputs.getSlots(); i++) {
                    inputs.getStackInSlot(i).shrink(1);
                }
                inputFluid.drainForced(this.shapedFluidWillBeDrained, IFluidHandler.FluidAction.EXECUTE);
                shapedOnFinish = false;
                this.shapedFluidWillBeDrained = null;
                this.lastTimeShapedRecipe = null;
                checkForRecipe();
            }else if(shapelessOnFinish && (this.output.getStackInSlot(0).isEmpty() || this.output.getStackInSlot(0).getCount() < this.lastTimeShapelessRecipe.output.getCount())){
                for (int i = 0; i < inputs.getSlots(); i++) {
                    inputs.getStackInSlot(i).shrink(1);
                }
                inputFluid.drainForced(this.shapelessFluidWillBeDrained, IFluidHandler.FluidAction.EXECUTE);
                shapelessOnFinish = false;
                this.shapedFluidWillBeDrained = null;
                this.lastTimeShapelessRecipe = null;
                checkForRecipe();
            }

            if(lastTimeShapedRecipe != null && lastTimeShapelessRecipe == null){
                if(shapedOnFinish && !lastTimeShapedRecipe.matches(inputs,inputFluid)) {
                    this.output.setStackInSlot(0, ItemStack.EMPTY);
                    this.shapedOnFinish = false;
                    this.shapedFluidWillBeDrained = null;
                    this.lastTimeShapedRecipe = null;
                    checkForRecipe();
                }
            }
            if(lastTimeShapedRecipe == null && lastTimeShapelessRecipe == null && !isInputSlotsAllMatchEmpty()){
                this.output.setStackInSlot(0, ItemStack.EMPTY);
                shapedOnFinish = false;
                shapedFluidWillBeDrained = null;
                checkForRecipe();
            }

            if(lastTimeShapelessRecipe != null && lastTimeShapedRecipe == null){
                if(shapelessOnFinish && !lastTimeShapelessRecipe.matches(inputs,inputFluid)) {
                    this.output.setStackInSlot(0, ItemStack.EMPTY);
                    this.shapelessOnFinish = false;
                    this.shapelessFluidWillBeDrained = null;
                    this.lastTimeShapelessRecipe = null;
                    checkForRecipe();
                }
            }
            if(lastTimeShapelessRecipe == null && lastTimeShapedRecipe == null && !isInputSlotsAllMatchEmpty()){
                this.output.setStackInSlot(0, ItemStack.EMPTY);
                shapelessOnFinish = false;
                shapelessFluidWillBeDrained = null;
                checkForRecipe();
            }

        }else{
            if(shapedOnFinish && lastTimeShapedRecipe != null){
                for (int i = 0; i < inputs.getSlots(); i++) {
                    inputs.getStackInSlot(i).shrink(1);
                }

                inputFluid.drainForced(lastTimeShapedRecipe.inputFluid,IFluidHandler.FluidAction.EXECUTE);

                this.shapedOnFinish = false;
                this.shapedFluidWillBeDrained = null;
                this.lastTimeShapedRecipe = null;
                checkForRecipe();
            }else if(shapelessOnFinish && lastTimeShapelessRecipe != null){
                for (int i = 0; i < inputs.getSlots(); i++) {
                    inputs.getStackInSlot(i).shrink(1);
                }

                inputFluid.drainForced(lastTimeShapelessRecipe.inputFluid,IFluidHandler.FluidAction.EXECUTE);

                this.shapelessOnFinish = false;
                this.shapedFluidWillBeDrained = null;
                this.lastTimeShapelessRecipe = null;
                checkForRecipe();
            }
        }
    }

    private boolean isInputSlotsAllMatchEmpty(){
        boolean allMatch = false;
        for (int i = 0; i < this.inputs.getSlots(); i++) {
            if(!this.inputs.getStackInSlot(i).isEmpty()){
                return false;
            }else{
                allMatch = true;
            }
        }

        return allMatch;
    }
    @Override
    public Runnable onFinish() {
        return () -> {
            if (shapedRecipe != null && this.output.getStackInSlot(0).isEmpty()) {
                ShapedRecipe shapedRecipe = this.shapedRecipe;

                this.shapedOnFinish = true;
                this.shapedFluidWillBeDrained = shapedRecipe.inputFluid;
                this.lastTimeShapedRecipe = shapedRecipe;

                ItemStack outputStack = shapedRecipe.output.copy();
                outputStack.getItem().onCraftedBy(outputStack, this.level, null);
                ItemHandlerHelper.insertItem(output, outputStack, false);
            }else if(shapelessRecipe != null && this.output.getStackInSlot(0).isEmpty()){
                ShapelessRecipe shapelessRecipe = this.shapelessRecipe;

                this.shapelessOnFinish = true;
                this.shapelessFluidWillBeDrained = shapelessRecipe.inputFluid;
                this.lastTimeShapelessRecipe = shapelessRecipe;

                ItemStack outputStack = shapelessRecipe.output.copy();
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
    public void saveSettings(Player player, CompoundTag tag) {
        super.saveSettings(player,tag);
        tag.putBoolean("shaped_on_finish",this.shapedOnFinish);
        tag.putBoolean("shapeless_on_finish",this.shapelessOnFinish);
        tag.putBoolean("auto_craft",this.autoCraft);
        tag.putBoolean("is_render_fluid",this.isFluidRender);
    }

    @Override
    public void loadSettings(Player player, CompoundTag tag) {
        super.loadSettings(player, tag);
        this.shapedOnFinish = tag.getBoolean("shaped_on_finish");
        this.shapelessOnFinish = tag.getBoolean("shapeless_on_finish");
        this.autoCraft = tag.getBoolean("auto_craft");
        this.isFluidRender = tag.getBoolean("is_render_fluid");
    }
}
