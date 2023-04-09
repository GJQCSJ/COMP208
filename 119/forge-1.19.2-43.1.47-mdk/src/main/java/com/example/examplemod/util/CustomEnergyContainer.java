package com.example.examplemod.util;

import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyContainer extends EnergyStorage {
    public CustomEnergyContainer(int cap, int maxTransfer){
        super(cap, maxTransfer, 0);
    }

    protected void onEnergyChanged(){
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate){
        int rec = super.receiveEnergy(maxReceive, simulate);
        if (rec > 0 && !simulate){
            onEnergyChanged();
        }
        return rec;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate){
        int rec = super.extractEnergy(maxExtract, simulate);
        if (rec > 0 && !simulate){
            onEnergyChanged();
        }
        return rec;
    }

    public void setEnergy(int energy){
        this.energy = energy;
        onEnergyChanged();
    }

    public void addEnergy(int energy){
        this.energy += energy;
        if (this.energy > getMaxEnergyStored()){
            this.energy = getMaxEnergyStored();
        }
        onEnergyChanged();
    }

    public void consumeEnergy(int energy){
        this.energy -= energy;
        if (this.energy < 0){
            this.energy = 0;
        }
        onEnergyChanged();
    }
}
