package net.yxiao233.ifeu.api.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.yxiao233.ifeu.common.registry.IFEUDataComponentTypes;

public class EnergyStorageItemStack implements IEnergyStorage {
    private static final String BLOCK_ENTITY_TAG = "BlockEntityTag";
    private static final String ENERGY = "energyStorage";
    private static final String MAX = "capacity";
    private static final String IN = "in";
    private static final String OUT = "out";
    private final ItemStack stack;
    private final CompoundTag tag;

    public EnergyStorageItemStack(ItemStack stack, int capacity, int in, int out) {
        this.stack = stack;
        boolean hasTags = stack.has(IFEUDataComponentTypes.COMPOUND_TAG);

        if(hasTags){
            tag = stack.get(IFEUDataComponentTypes.COMPOUND_TAG).getCompound(BLOCK_ENTITY_TAG);
            tag.putInt(MAX,capacity);
            tag.putInt(IN,in);
            tag.putInt(OUT,out);
        }else{
            tag = new CompoundTag();
            tag.putInt(ENERGY,0);
            tag.putInt(MAX,capacity);
            tag.putInt(IN,in);
            tag.putInt(OUT,out);
        }

    }

    private CompoundTag getTag(){
        return tag;
    }

    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!this.canReceive()) {
            return 0;
        } else {
            int energyReceived = Math.min(this.getMaxEnergyStored() - this.getEnergyStored(), Math.min(this.getMaxReceive(), maxReceive));
            if (!simulate && energyReceived != 0) {
                this.getTag().putInt("energy", this.getEnergyStored() + energyReceived);
            }

            return energyReceived;
        }
    }

    public int extractEnergy(int maxExtract, boolean simulate) {
        if (!this.canExtract()) {
            return 0;
        }else{
            int energyExtracted = Math.min(this.getEnergyStored(), Math.min(this.getMaxExtract(), maxExtract));
            if (!simulate && this.stack != null && energyExtracted != 0) {
                this.getTag().putInt("energy", this.getEnergyStored() - energyExtracted);
            }

            return energyExtracted;
        }
    }

    public int getMaxExtract() {
        return this.getTag().getInt(OUT);
    }

    public int getMaxReceive() {
        return this.getTag().getInt(IN);
    }

    public int getEnergyStored() {
        return this.getTag().getInt(ENERGY);
    }

    public int getMaxEnergyStored() {
        return this.getTag().getInt(MAX);
    }

    public boolean canExtract() {
        return this.getMaxExtract() > 0;
    }

    public boolean canReceive() {
        return this.getMaxReceive() > 0;
    }
}