package com.davoleo.testmod.block.counter;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 16/06/2019 / 10:36
 * Class: TileEntityCounter
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TileEntityCounter extends TileEntity {

    private int count;

    @Nonnull
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

    int getCount()
    {
        return count;
    }

    void incrementCount()
    {
        count++;
        markDirty();
    }

    void decrementCount()
    {
        count--;
        markDirty();
    }


    void incrementCountEx()
    {
        count += 10;
        markDirty();
    }

    void decrementCountEx()
    {
        count -= 10;
        markDirty();
    }


}
