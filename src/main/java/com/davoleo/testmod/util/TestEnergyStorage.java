package com.davoleo.testmod.util;

import net.minecraftforge.energy.EnergyStorage;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 11/01/2019 / 19:15
 * Class: TestEnergyStorage
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TestEnergyStorage extends EnergyStorage {

    public TestEnergyStorage(int capacity, int maxReceive)
    {
        super(capacity, maxReceive, 0);
    }

    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    public void consumePower(int energy)
    {
        this.energy -= energy;
        if (this.energy < 0)
            this.energy = 0;
    }
}
