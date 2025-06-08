package net.yxiao233.ifeu.api.block.entity;

import com.buuz135.industrial.block.tile.IndustrialWorkingTile;
import com.buuz135.industrial.block.tile.RangeManager;
import com.buuz135.industrial.item.addon.RangeAddonItem;
import com.buuz135.industrial.proxy.client.IndustrialAssetProvider;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonAddon;
import com.hrznstudio.titanium.client.screen.addon.StateButtonInfo;
import com.hrznstudio.titanium.component.button.ButtonComponent;
import com.hrznstudio.titanium.item.AugmentWrapper;
import com.hrznstudio.titanium.module.BlockWithTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class IFEUAreaWorkingTile<T extends IFEUAreaWorkingTile<T>> extends IndustrialWorkingTile<T> {
    @Save
    protected boolean showingArea = false;
    protected ButtonComponent areaButton;
    protected RangeManager.RangeType type;
    protected boolean acceptsRangeUpgrades;

    public IFEUAreaWorkingTile(BlockWithTile basicTileBlock, final int estimatedPower, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, estimatedPower, blockPos, blockState);
        this.addButton(this.areaButton = (new ButtonComponent(136, 84, 14, 14) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                List<IFactory<? extends IScreenAddon>> addons = new ArrayList();
                addons.add(() -> {
                    return new StateButtonAddon(IFEUAreaWorkingTile.this.areaButton, new StateButtonInfo[]{new StateButtonInfo(0, IndustrialAssetProvider.BUTTON_SHOW_AREA, new String[]{"text.industrialforegoing.button.show_area"}), new StateButtonInfo(1, IndustrialAssetProvider.BUTTON_HIDE_AREA, new String[]{"text.industrialforegoing.button.hide_area"})}) {
                        public int getState() {
                            return IFEUAreaWorkingTile.this.showingArea ? 1 : 0;
                        }
                    };
                });
                return addons;
            }
        }).setId(1).setPredicate((playerEntity, compoundNBT) -> {
            this.showingArea = !this.showingArea;
            this.markForUpdate();
        }));
        this.type = type;
        this.acceptsRangeUpgrades = acceptsRangeUpgrades;
    }

    public boolean isShowingArea() {
        return this.showingArea;
    }


    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem addonItem){
            if(addonItem.getType() == IFEUAugmentTypes.SILK){
                return AugmentInventoryHelper.canAccept(this.getAugmentInventory(),augment);
            }
            return false;
        }
        return false;
    }

    public abstract int getFrameBounds();
    public abstract FrameDirection getFrameDirection();
    public abstract int getLandRange();
    public abstract int getYOffset();
    public abstract BlockState getFrameBlockState();
    public abstract BlockState getLandBlockState();
    public abstract BlockPos getCenterPos();
    public abstract Item getLandItem();
    public abstract Item getFrameItem();

    @Override
    public void serverTick(Level var1, BlockPos var2, BlockState var3, T var4) {
        super.serverTick(var1, var2, var3, var4);
        // $FF: Couldn't be decompiled
    }

    public record FrameDirection(NorthSouth northSouth, WestEast westEast){}

    public enum NorthSouth{
        NORTH(Direction.NORTH),
        SOUTH(Direction.SOUTH);
        private final Direction direction;
        NorthSouth(Direction direction){
            this.direction = direction;
        }

        public Direction get(){
            return direction;
        }
    }
    public enum WestEast{
        WEST(Direction.WEST),
        EAST(Direction.EAST);
        private final Direction direction;
        WestEast(Direction direction){
            this.direction = direction;
        }

        public Direction get(){
            return direction;
        }
    }
}
