package net.yxiao233.ifeu.api.block.entity;

import com.google.common.collect.Sets;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.block.tile.ActiveTile;
import com.hrznstudio.titanium.client.screen.addon.BasicButtonAddon;
import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import com.hrznstudio.titanium.component.button.ButtonComponent;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.container.addon.IContainerAddon;
import com.hrznstudio.titanium.util.AssetUtil;
import com.hrznstudio.titanium.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.yxiao233.ifeu.common.utils.BlackHoleUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class IFEUBlackHoleCapacitorEntity extends ActiveTile<IFEUBlackHoleCapacitorEntity> {
    @Save
    private final EnergyStorageComponent<IFEUBlackHoleCapacitorEntity> energyStorage;
    private final LazyOptional<IEnergyStorage> lazyEnergyStorage = LazyOptional.of(this::getEnergyStorage);
    private boolean showEnergy = true;
    @Save
    public boolean display = true;
    private Rarity rarity;
    private static int maxCap;
    public IFEUBlackHoleCapacitorEntity(BasicTileBlock<IFEUBlackHoleCapacitorEntity> base, BlockEntityType<?> blockEntityType, Rarity rarity, BlockPos pos, BlockState state) {
        super(base, blockEntityType, pos, state);

        this.addButton((new ButtonComponent(142, 80, 18, 18) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    return new BasicButtonAddon(this) {
                        public void drawBackgroundLayer(GuiGraphics guiGraphics, Screen screen, IAssetProvider provider, int guiX, int guiY, int mouseX, int mouseY, float partialTicks) {
                            AssetUtil.drawAsset(guiGraphics, screen, provider.getAsset(AssetTypes.ITEM_BACKGROUND), guiX + this.getPosX(), guiY + this.getPosY());
                            guiGraphics.renderItem(new ItemStack(IFEUBlackHoleCapacitorEntity.this.display ? Items.ENDER_EYE : Items.ENDER_PEARL), guiX + this.getPosX() + 1, guiY + this.getPosY() + 1);
                        }

                        public List<Component> getTooltipLines() {
                            List<Component> lines = new ArrayList();
                            ChatFormatting var10001 = ChatFormatting.GOLD;
                            lines.add(Component.literal("" + var10001 + LangUtil.getString("tooltip.industrialforegoing.bl." + (IFEUBlackHoleCapacitorEntity.this.display ? "show_unit_display" : "hide_unit_display"), new Object[0])));
                            return lines;
                        }
                    };
                });
            }
        }).setPredicate((playerEntity, compoundNBT) -> {
            this.display = !this.display;
            this.syncObject(this.display);
        }));

        this.rarity = rarity;
        maxCap = BlackHoleUtil.getMaxEnergyCapacityByRarity(rarity);
        this.energyStorage = new EnergyStorageComponent<>(maxCap,maxCap / 10, 80, 27);
        this.energyStorage.setComponentHarness(this);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, IFEUBlackHoleCapacitorEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction facing = var5[var7];
            BlockPos checking = this.worldPosition.relative(facing);
            BlockEntity checkingTile = this.level.getBlockEntity(checking);
            if (checkingTile != null) {
                checkingTile.getCapability(ForgeCapabilities.ENERGY, facing.getOpposite()).ifPresent((storage) -> {
                    this.getEnergyStorage().extractEnergy(storage.receiveEnergy(this.getEnergyStorage().extractEnergy(maxCap / 10, true), false), false);
                });
            }
        }
        this.syncObject(this.getEnergyStorage());
    }

    public InteractionResult onActivated(Player playerIn, InteractionHand hand, Direction facing, double hitX, double hitY, double hitZ) {
        if (super.onActivated(playerIn, hand, facing, hitX, hitY, hitZ) == InteractionResult.SUCCESS) {
            return InteractionResult.SUCCESS;
        } else {
            this.openGui(playerIn);
            return InteractionResult.SUCCESS;
        }
    }

    @NotNull
    @Override
    public IFEUBlackHoleCapacitorEntity getSelf() {
        return this;
    }

    public Component getRenderText(){
        return Component.literal(getEnergyStorage().getEnergyStored() + " / " + getMaxStorage() + " FE").withStyle(ChatFormatting.WHITE);
    };
    public int getMaxStorage(){
        return getEnergyStorage().getMaxEnergyStored();
    }

    public boolean shouldDisplay() {
        return this.display;
    }

    @Nonnull
    public EnergyStorageComponent<IFEUBlackHoleCapacitorEntity> getEnergyStorage() {
        return this.energyStorage;
    }

    public Set<Direction> getValidEnergyFaces() {
        return Sets.newHashSet(Direction.values());
    }

    @Nonnull
    public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
        List<IFactory<? extends IScreenAddon>> screenAddons = super.getScreenAddons();
        if (this.showEnergy) {
            screenAddons.addAll(this.getEnergyStorage().getScreenAddons());
        }

        return screenAddons;
    }

    @Nonnull
    public List<IFactory<? extends IContainerAddon>> getContainerAddons() {
        List<IFactory<? extends IContainerAddon>> containerAddons = super.getContainerAddons();
        if (this.showEnergy) {
            containerAddons.addAll(this.getEnergyStorage().getContainerAddons());
        }

        return containerAddons;
    }

    @Nonnull
    public <U> LazyOptional<U> getCapability(@Nonnull Capability<U> cap, @Nullable Direction side) {
        return cap == ForgeCapabilities.ENERGY ? this.lazyEnergyStorage.cast() : super.getCapability(cap, side);
    }

    public void setShowEnergy(boolean showEnergy) {
        this.showEnergy = showEnergy;
    }

    public void invalidateCaps() {
        super.invalidateCaps();
        this.lazyEnergyStorage.invalidate();
    }
}
