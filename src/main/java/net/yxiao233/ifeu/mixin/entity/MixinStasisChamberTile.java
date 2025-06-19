package net.yxiao233.ifeu.mixin.entity;

import com.buuz135.industrial.block.misc.tile.StasisChamberTile;
import com.buuz135.industrial.block.tile.IndustrialAreaWorkingTile;
import com.buuz135.industrial.block.tile.IndustrialWorkingTile;
import com.buuz135.industrial.block.tile.RangeManager;
import com.buuz135.industrial.config.machine.misc.StasisChamberConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import net.yxiao233.ifeu.api.item.IFEUAddonItem;
import net.yxiao233.ifeu.api.item.IFEUAugmentTypes;
import net.yxiao233.ifeu.common.utils.AugmentInventoryHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(StasisChamberTile.class)
public abstract class MixinStasisChamberTile extends IndustrialAreaWorkingTile<StasisChamberTile> {
    @Shadow private int getPowerPerOperation;

    public MixinStasisChamberTile(Pair<RegistryObject<Block>, RegistryObject<BlockEntityType<?>>> basicTileBlock, RangeManager.RangeType type, boolean acceptsRangeUpgrades, int estimatedPower, BlockPos blockPos, BlockState blockState) {
        super(basicTileBlock, type, acceptsRangeUpgrades, estimatedPower, blockPos, blockState);
    }
    @Override
    public IndustrialWorkingTile<StasisChamberTile>.WorkAction work() {
        if (this.hasEnergy(this.getPowerPerOperation)) {
            int tier = AugmentInventoryHelper.getAugmentTier(this,IFEUAugmentTypes.HEAL);
            List<Mob> entities = this.level.getEntitiesOfClass(Mob.class, this.getWorkingArea().bounds());
            Iterator<Mob> var2 = entities.iterator();

            while(var2.hasNext()) {
                Mob entity = var2.next();
                entity.setNoAi(true);
                entity.getPersistentData().putLong("StasisChamberTime", this.level.getGameTime());
                if (!entity.canChangeDimensions() && this.level instanceof ServerLevel) {
                    if (StasisChamberConfig.disableBossBars) {
                        this.level.players().forEach((entity1) -> {
                            entity.stopSeenByPlayer((ServerPlayer)entity1);
                        });
                    } else {
                        this.level.players().forEach((entity1) -> {
                            entity.startSeenByPlayer((ServerPlayer)entity1);
                        });
                    }
                }

                if (this.level.random.nextBoolean() && this.level.random.nextBoolean()) {
                    entity.heal(1 + tier * 3);
                }
            }

            List<Player> players = this.level.getEntitiesOfClass(Player.class, this.getWorkingArea().bounds());
            players.forEach((playerEntity) -> {
                playerEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 25, 135));
                if (this.level.random.nextBoolean()) {
                    playerEntity.heal(1.0F);
                }

            });
            return new IndustrialWorkingTile<StasisChamberTile>.WorkAction(0.5F, this.getPowerPerOperation);
        } else {
            return new IndustrialWorkingTile<StasisChamberTile>.WorkAction(1.0F, 0);
        }
    }



    @Override
    public boolean canAcceptAugment(ItemStack augment) {
        if(augment.getItem() instanceof IFEUAddonItem item && item.getType() == IFEUAugmentTypes.HEAL){
            return AugmentInventoryHelper.canAccept(this,augment);
        }
        return super.canAcceptAugment(augment);
    }
}
