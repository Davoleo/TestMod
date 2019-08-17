package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.network.Messages;
import com.davoleo.testmod.network.PacketRequestUpdatePedestal;
import com.davoleo.testmod.network.PacketUpdatePedestal;
import com.davoleo.testmod.util.IGuiTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 16:29
 * Class: TileEntityPedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TileEntityPedestal extends TileEntity implements IGuiTileEntity {

    public long lastUpdateTick;
    public ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot)
        {
            if (!world.isRemote) {
                lastUpdateTick = world.getTotalWorldTime();
                Messages.INSTANCE.sendToAllAround(new PacketUpdatePedestal(TileEntityPedestal.this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
            }
        }
    };

    @Override
    public void onLoad()
    {
        if (world.isRemote)
            Messages.INSTANCE.sendToServer(new PacketRequestUpdatePedestal(this));
    }

    /**
     * @return a custom render box one block taller than the default one
     */
    @Nonnull
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setLong("lastUpdateTick", lastUpdateTick);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        lastUpdateTick = compound.getLong("lastUpdateTick");
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) inventory;
        return super.getCapability(capability, facing);
    }

    @Override
    public Container createContainer(EntityPlayer player)
    {
        return new ContainerPedestal(player.inventory, this);
    }

    @Override
    public GuiContainer createGui(EntityPlayer player)
    {
        return new GuiPedestal(createContainer(player));
    }
}


