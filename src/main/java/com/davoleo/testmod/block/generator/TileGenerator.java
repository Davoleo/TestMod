package com.davoleo.testmod.block.generator;

import com.davoleo.testmod.config.GeneratorConfig;
import com.davoleo.testmod.util.IGuiTileEntity;
import com.davoleo.testmod.util.IRestorableTileEntity;
import com.davoleo.testmod.util.TestEnergyStorage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 17:51
 * Class: TileGenerator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TileGenerator extends TileEntity implements ITickable, IRestorableTileEntity, IGuiTileEntity {

    @Override
    public void update()
    {
        if (!world.isRemote)
        {

        }
    }

    //----------------------------------------------------------------------------------------------------------

    private TestEnergyStorage energyStorage = new TestEnergyStorage(GeneratorConfig.MAX_POWER, 0);

    //----------------------------------------------------------------------------------------------------------


    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        readRestorableFromNBT(compound);
    }

    @Override
    public void readRestorableFromNBT(NBTTagCompound compound)
    {
        energyStorage.setEnergy(compound.getInteger("energy"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        writeRestorableToNBT(compound);
        return compound;
    }

    @Override
    public void writeRestorableToNBT(NBTTagCompound compound)
    {
        compound.setInteger("energy", energyStorage.getEnergyStored());
    }

    @Override
    public Container createContainer(EntityPlayer player)
    {
        return new ContainerGenerator(player.inventory, this);
    }

    @Override
    public GuiContainer createGui(EntityPlayer player)
    {
        return new GuiGenerator(this, new ContainerGenerator(player.inventory, this));
    }

    protected boolean canInteractWith(EntityPlayer player)
    {
        return !isInvalid() && player.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityEnergy.ENERGY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityEnergy.ENERGY)
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        return super.getCapability(capability, facing);
    }
}
