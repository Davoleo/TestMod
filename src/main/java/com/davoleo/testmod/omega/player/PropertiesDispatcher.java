package com.davoleo.testmod.omega.player;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 21:44
 * Class: PropertiesDispatcher
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PropertiesDispatcher implements ICapabilitySerializable<NBTTagCompound> {

    private PlayerOmega playerOmega = new PlayerOmega();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
        if (cap == PlayerProperties.PLAYER_OMEGA)
            return LazyOptional.of(() -> ((T) playerOmega));
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound compound = new NBTTagCompound();
        playerOmega.saveNBTData(compound);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound)
    {
        playerOmega.loadNBTData(compound);
    }
}
