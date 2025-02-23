package net.yxiao233.ifeu.common.block.entity;

import com.buuz135.industrial.block.tile.IndustrialProcessingTile;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.client.screen.addon.ProgressBarScreenAddon;
import com.hrznstudio.titanium.component.button.ArrowButtonComponent;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.inventory.SidedInventoryComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import com.hrznstudio.titanium.util.FacingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.yxiao233.ifeu.api.components.TextGuiComponent;
import net.yxiao233.ifeu.api.components.TextureGuiComponent;
import net.yxiao233.ifeu.common.config.machine.RuleControllerConfig;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;
import net.yxiao233.ifeu.common.networking.packet.BooleanSyncS2CPacket;
import net.yxiao233.ifeu.api.networking.BooleanValueSyncS2C;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.utils.GameRuleGetter;
import net.yxiao233.ifeu.common.utils.GameRuleUtil;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class RuleControllerEntity extends IndustrialProcessingTile<RuleControllerEntity> implements BooleanValueSyncS2C {
    private static GameRuleGetter[] rules = {GameRuleGetter.DO_FIRE_TICK,GameRuleGetter.MOB_GRIEFING,GameRuleGetter.KEEP_INVENTORY,GameRuleGetter.DO_MOB_SPAWNING,GameRuleGetter.DO_MOB_LOOT,GameRuleGetter.DO_BLOCK_DROPS,GameRuleGetter.DO_ENTITY_DROPS};
    private int powerPerTick;
    @Save
    private SidedInventoryComponent<RuleControllerEntity> input;
    @Save
    private ProgressBarComponent<RuleControllerEntity> bar;
    @Save
    private int rule;
    private boolean value;
    public RuleControllerEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.RULE_CONTROLLER, 66, 40, blockPos, blockState);

        this.addInventory(this.input = (SidedInventoryComponent<RuleControllerEntity>)(new SidedInventoryComponent<RuleControllerEntity>("dragon_star_input", 33, 40, 1, 0)).setColor(DyeColor.PURPLE).setInputFilter((stack, integer) -> {
            return stack.is(ModContents.DRAGON_STAR.get());
        }).setOutputFilter((stack, integer) -> {
            return false;
        }).setComponentHarness(this));

        this.addProgressBar(this.bar = (new ProgressBarComponent<RuleControllerEntity>(53, 20, 10) {
            @OnlyIn(Dist.CLIENT)
            public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
                return Collections.singletonList(() -> {
                    return new ProgressBarScreenAddon<RuleControllerEntity>(RuleControllerEntity.this.bar.getPosX(), RuleControllerEntity.this.bar.getPosY(), this) {
                        public List<Component> getTooltipLines() {
                            Component[] var10000 = new Component[1];
                            ChatFormatting var10003 = ChatFormatting.GOLD;
                            var10000[0] = Component.literal("" + var10003 + "Amount: " + ChatFormatting.WHITE + NumberFormat.getNumberInstance(Locale.ROOT).format((long)RuleControllerEntity.this.bar.getProgress()) + ChatFormatting.GOLD + "/" + ChatFormatting.WHITE + NumberFormat.getNumberInstance(Locale.ROOT).format((long)RuleControllerEntity.this.bar.getMaxProgress()));
                            return Arrays.asList(var10000);
                        }
                    };
                });
            }
        }).setCanIncrease((tileEntity) -> {
            return false;
        }).setCanReset((tileEntity) -> {
            return false;
        }).setColor(DyeColor.PURPLE));


        this.addButton((new ArrowButtonComponent(76, 20, 14, 14, FacingUtil.Sideness.LEFT)).setId(1).setPredicate((playerEntity, compoundNBT) -> {
            --this.rule;
            if (this.rule < 0) {
                this.rule = rules.length - 1;
            }

            this.markForUpdate();
        }));
        this.addButton((new ArrowButtonComponent(154, 20, 14, 14, FacingUtil.Sideness.RIGHT)).setId(2).setPredicate((playerEntity, compoundNBT) -> {
            ++this.rule;
            if (this.rule > rules.length - 1) {
                this.rule = 0;
            }

            this.markForUpdate();
        }));

        this.powerPerTick = RuleControllerConfig.powerPerTick;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initClient() {
        super.initClient();
        this.addGuiAddonFactory(() -> {
            return new TextGuiComponent(93, 21) {
                @Override
                public String getText() {
                    return rules[rule].getRuleName();
                }
            };
        });


        this.addGuiAddonFactory(() -> {
            return new TextureGuiComponent(103,42) {
                @Override
                public AllGuiTextures getTexture() {
                    if(value){
                        return AllGuiTextures.TRUE;
                    }
                    return AllGuiTextures.FALSE;
                }

                @Override
                public Component getComponent() {
                    return Component.translatable("tooltip.ifeu.gamerule",value);
                }
            };
        });
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, RuleControllerEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);
        if(!level.isClientSide()){
            this.value = GameRuleUtil.getGameRule(level,rules[rule].getRuleKey());
            PacketDistributor.sendToAllPlayers((new BooleanSyncS2CPacket(pos,value)));
        }
    }

    @Override
    public boolean canIncrease() {
        this.increaseBar(this.input.getStackInSlot(0),this.bar);
        return this.bar.getProgress() >= 1;
    }

    private void increaseBar(ItemStack stack, ProgressBarComponent bar) {
        if (bar.getProgress() + 1 <= bar.getMaxProgress() && !stack.isEmpty()) {
            stack.shrink(1);
            bar.setProgress(bar.getProgress() + 1);
            this.markForUpdate();
        }
    }

    @Override
    public Runnable onFinish() {
        return () -> {
            this.bar.setProgress(this.bar.getProgress() - 1);
            GameRules.Key<GameRules.BooleanValue> key = rules[rule].getRuleKey();
            boolean value = GameRuleUtil.getGameRule(level,rules[rule].getRuleKey());
            GameRuleUtil.setGameRule(level,key,!value);
            this.markForUpdate();
        };
    }

    @NotNull
    @Override
    protected EnergyStorageComponent<RuleControllerEntity> createEnergyStorage() {
        return new EnergyStorageComponent<>(RuleControllerConfig.maxStoredPower, 10, 20);
    }

    @Override
    protected int getTickPower() {
        return this.powerPerTick;
    }

    @NotNull
    @Override
    public RuleControllerEntity getSelf() {
        return this;
    }

    public void loadSettings(Player player, CompoundTag tag) {
        if (tag.contains("rule")) {
            this.rule = tag.getInt("rule");
        }

        super.loadSettings(player, tag);
    }

    public void saveSettings(Player player, CompoundTag tag) {
        tag.putInt("rule", this.rule);
        super.saveSettings(player, tag);
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getValues() {
        return value;
    }
}
