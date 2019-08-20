package com.davoleo.testmod.block.counter;

import com.davoleo.testmod.init.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

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

    public TileEntityCounter() {
        super(ModBlocks.TYPE_COUNTER);
    }

    @Nonnull
    @Override
    public NBTTagCompound write(NBTTagCompound compound)
    {
        compound.setInt("count", count);
        return super.write(compound);
    }

    @Override
    public void read(NBTTagCompound compound)
    {
        count = compound.getInt("count");
        super.read(compound);
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
