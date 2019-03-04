package com.davoleo.testmod.block.tank;

import com.davoleo.testmod.util.IRestorableTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 04/03/2019 / 18:24
 * Class: TileTank
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TileTank extends TileEntity implements IRestorableTileEntity {

    public static final int MAX_SIZE = 10000; //10b = 10000mb

    private FluidTank tank = new FluidTank(MAX_SIZE);

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound NBT = super.getUpdateTag();
        NBTTagCompound tankNBT = new NBTTagCompound();
        tank.writeToNBT(tankNBT);
        NBT.setTag("tank", tankNBT);
        return NBT;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        tank.readFromNBT(pkt.getNbtCompound().getCompoundTag("tank"));
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        readRestorableFromNBT(compound);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        writeRestorableToNBT(compound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readRestorableFromNBT(NBTTagCompound compound)
    {
        tank.readFromNBT(compound.getCompoundTag("tank"));
    }

    @Override
    public void writeRestorableToNBT(NBTTagCompound compound)
    {
        NBTTagCompound tankNBT = new NBTTagCompound();
        tank.writeToNBT(tankNBT);
        compound.setTag("tank", tankNBT);
    }

    public FluidTank getTank()
    {
        return tank;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank);
        return super.getCapability(capability, facing);
    }
}
