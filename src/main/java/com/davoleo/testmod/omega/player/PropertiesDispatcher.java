package com.davoleo.testmod.omega.player;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

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

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing)
    {
        return capability == PlayerProperties.PLAYER_OMEGA;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing)
    {
        if (capability == PlayerProperties.PLAYER_OMEGA)
            return (T) playerOmega;
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        playerOmega.saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound)
    {
        playerOmega.loadNBTData(compound);
    }
}
