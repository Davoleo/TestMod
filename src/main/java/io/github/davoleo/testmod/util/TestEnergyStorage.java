/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 16/12/2019 / 21:44
 * Class: TestEnergyStorage
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 * ----------------------------------- */

package io.github.davoleo.testmod.util;

import net.minecraftforge.energy.EnergyStorage;

public class TestEnergyStorage extends EnergyStorage {

    public TestEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void addEnergy(int energy) {
        this.energy += energy;

        //Block energy intake in case the machine is full
        if (this.energy > getMaxEnergyStored())
            this.energy = getEnergyStored();
    }
}
