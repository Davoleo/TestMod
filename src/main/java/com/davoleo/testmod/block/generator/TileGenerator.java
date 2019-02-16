package com.davoleo.testmod.block.generator;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.config.GeneratorConfig;
import com.davoleo.testmod.util.IGuiTileEntity;
import com.davoleo.testmod.util.IRestorableTileEntity;
import com.davoleo.testmod.util.TestEnergyStorage;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 17:51
 * Class: TileGenerator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TileGenerator extends TileEntity implements ITickable, IRestorableTileEntity, IGuiTileEntity {

    private int trackTimer;
    private AxisAlignedBB trackingBox;
    private int clientEnergy = -1;

    @Nullable
    private final IAnimationStateMachine asm;

    public TileGenerator()
    {
        asm = TestMod.proxy.load(new ResourceLocation(TestMod.MODID, "asms/block/generator.json"), ImmutableMap.of());
    }
    //----------------------------------------------------------------------------------------------------------

    private TestEnergyStorage energyStorage = new TestEnergyStorage(GeneratorConfig.MAX_POWER, 0);

    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            trackTimer--;
            if (trackTimer <= 0)
                trackTimer = 20;
            findEntities();
        }

        sendEnergy();
    }

    //----------------------------------------------------------------------------------------------------------
    //Energy

    private void sendEnergy()
    {
        if (energyStorage.getEnergyStored() > 0)
        {                            // The six faces of the cube
            for (EnumFacing facing : EnumFacing.VALUES)
            {
                TileEntity tileEntity = world.getTileEntity(pos.offset(facing));
                if (tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()))
                {
                    IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
                    if (handler != null && handler.canReceive())
                    {
                        int accepted = handler.receiveEnergy(energyStorage.getEnergyStored(), false);
                        energyStorage.consumePower(accepted);
                        if (energyStorage.getEnergyStored() <= 0)
                            break;
                    }
                }
            }
            markDirty();
        }
    }

    public int getClientEnergy()
    {
        return clientEnergy;
    }

    public void setClientEnergy(int clientEnergy)
    {
        this.clientEnergy = clientEnergy;
    }

    public int getEnergy()
    {
        return energyStorage.getEnergyStored();
    }


    //----------------------------------------------------------------------------------------------------------
    //Damage System section

    private void findEntities()
    {
        DamageTracker.instance.clear(world.provider.getDimension(), pos);

        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getTrackingBox());
        for (EntityLivingBase entity : entities)
            DamageTracker.instance.register(world.provider.getDimension(), pos, entity.getUniqueID());
    }

    private AxisAlignedBB getTrackingBox()
    {
        if (trackingBox == null)
            trackingBox = new AxisAlignedBB(pos.add(-5, -3, -5), pos.add(5, 3, 5));
        return trackingBox;
    }

    public void senseDamage(EntityLivingBase entity, float amount)
    {
        if (getTrackingBox().contains(entity.getPositionVector()))
            energyStorage.generatePower((int) (amount * GeneratorConfig.POWER_DAMAGE_FACTOR));
    }

    //----------------------------------------------------------------------------------------------------------
    //Overrides

    @Override
    public void invalidate()
    {
        super.invalidate();
        DamageTracker.instance.remove(world.provider.getDimension(), pos);
    }

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
    public boolean hasFastRenderer()
    {
        return true;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityEnergy.ENERGY)
            return true;
        if (capability == CapabilityAnimation.ANIMATION_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityEnergy.ENERGY)
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        if (capability == CapabilityAnimation.ANIMATION_CAPABILITY)
            return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
        return super.getCapability(capability, facing);
    }
}
