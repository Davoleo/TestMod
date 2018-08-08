package com.davoleo.testmod.block.counter;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/*************************************************
 * Author: Davoleo
 * Date: 09/08/2018
 * Hour: 00.29
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TileEntityCounter extends TileEntity {

    private int count;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("count", count);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        count = compound.getInteger("count");
        super.readFromNBT(compound);
    }

    public int getCount()
    {
        return count;
    }

    public void incrementCount()
    {
        count++;
        markDirty();
    }


    public void decrementCount()
    {
        count--;
        markDirty();
    }

}
