package com.davoleo.testmod.omega.player;

import net.minecraft.nbt.NBTTagCompound;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 21:38
 * Class: PlayerOmega
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PlayerOmega {

    private float omega = 0F;

    public PlayerOmega()
    {
    }

    public float getOmega()
    {
        return omega;
    }

    public void setOmega(float omega)
    {
        this.omega = omega;
    }

    public void copyFrom(PlayerOmega source)
    {
        omega = source.omega;
    }

    public void saveNBTData(NBTTagCompound compound)
    {
        compound.setFloat("omega", omega);
    }

    public void loadNBTData(NBTTagCompound compound)
    {
        omega = compound.getFloat("omega");
    }
}
