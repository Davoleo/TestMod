package com.davoleo.testmod.block.furnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 31/12/2018 / 00:59
 * Class: TileFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TileFastFurnace extends TileEntity {

    public static final int SIZE = 3 + 3;

    private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileFastFurnace.this.markDirty();
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if (compound.hasKey("items"))
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        return compound;
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return !isInvalid() && player.getDistanceSq(pos.add(0.5,0.5,0.5)) <= 64;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }
}
