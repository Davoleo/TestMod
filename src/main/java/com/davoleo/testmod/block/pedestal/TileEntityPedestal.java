package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.network.PacketRequestUpdatePedestal;
import com.davoleo.testmod.network.PacketUpdatePedestal;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date: 10/08/2018
 * Hour: 22.22
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TileEntityPedestal extends TileEntity {

    public ItemStackHandler inventory = new ItemStackHandler(1){
    @Override
    protected void onContentsChanged(int slot) {
        if (!world.isRemote) {
            lastChangeTime = world.getTotalWorldTime();
            TestMod.network.sendToAllAround(new PacketUpdatePedestal(TileEntityPedestal.this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
        }
    }
};

    public long lastChangeTime;


    protected void onContentChanged(int slot)
    {
        if(!world.isRemote)
        {
            lastChangeTime = world.getWorldTime();
            TestMod.network.sendToAllAround(new PacketUpdatePedestal(TileEntityPedestal.this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
        }
    }



    @Override
    public void onLoad() {
        if (world.isRemote) {
            TestMod.network.sendToServer(new PacketRequestUpdatePedestal(this));
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return new AxisAlignedBB(getPos(), getPos().add(1,2,2));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setLong("lastChangeTime", lastChangeTime);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        lastChangeTime = compound.getLong("lastChangeTime");
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability,facing);
    }

}
