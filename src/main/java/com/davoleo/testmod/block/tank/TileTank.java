package com.davoleo.testmod.block.tank;

import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.util.IRestorableTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
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

    public TileTank() {
        super(ModBlocks.TYPE_TANK);
    }

    private FluidTank tank = new FluidTank(MAX_SIZE)
    {
        @Override
        protected void onContentsChanged()
        {
            IBlockState state =  world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            markDirty();
        }
    };

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
        tank.readFromNBT(pkt.getNbtCompound().getCompound("tank"));
    }

    @Override
    public void read(NBTTagCompound compound)
    {
        super.read(compound);
        readRestorableFromNBT(compound);
    }

    @Nonnull
    @Override
    public NBTTagCompound write(NBTTagCompound compound)
    {
        writeRestorableToNBT(compound);
        return super.write(compound);
    }

    @Override
    public void readRestorableFromNBT(NBTTagCompound compound)
    {
        tank.readFromNBT(compound.getCompound("tank"));
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

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return LazyOptional.of(() -> ((T) tank));
        return super.getCapability(cap);
    }
}
