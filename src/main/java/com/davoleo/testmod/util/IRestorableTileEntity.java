package com.davoleo.testmod.util;

import net.minecraft.nbt.NBTTagCompound;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 17:03
 * Interface: IRestorableTileEntity
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public interface IRestorableTileEntity {
    //saved things when the block is broken
    void readRestorableFromNBT(NBTTagCompound compound);

    //saved things when the block is broken
    void writeRestorableToNBT(NBTTagCompound compound);
}
