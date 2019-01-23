package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.util.TestEnergyStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 31/12/2018 / 00:59
 * Class: TileFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TileFastFurnace extends TileEntity implements ITickable {

    public static final int INPUT_SLOTS = 3;
    public static final int OUTPUT_SLOTS = 3;
    public static final int SIZE = INPUT_SLOTS + OUTPUT_SLOTS;

    public static final int MAX_PROGRESS = 40;
    public static final int MAX_POWER = 100000;
    public static final int RF_PER_TICK_INPUT = 100;
    public static final int RF_PER_TICK = 20;

    private FurnaceState state = FurnaceState.IDLE;
    private int progress = 0;
    private int clientProgress = -1;
    private int clientEnergy = -1;


    private ItemStackHandler inputHandler = new ItemStackHandler(INPUT_SLOTS)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileFastFurnace.this.markDirty();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack)
        {
            ItemStack result = FurnaceRecipes.instance().getSmeltingResult(stack);
            return !result.isEmpty();
        }
    };

    private ItemStackHandler outputHandler = new ItemStackHandler(OUTPUT_SLOTS)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileFastFurnace.this.markDirty();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack)
        {
            return false;
        }
    };

    private CombinedInvWrapper combinedHandler = new CombinedInvWrapper(inputHandler, outputHandler);

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        progress = compound.getInteger("progress");
        if (compound.hasKey("itemsIn"))
            inputHandler.deserializeNBT((NBTTagCompound) compound.getTag("itemsIn"));
        if (compound.hasKey("itemsOut"))
            outputHandler.deserializeNBT((NBTTagCompound) compound.getTag("itemsOut"));
        energyStorage.setEnergy(compound.getInteger("energy"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("progress", progress);
        compound.setTag("itemsIn", inputHandler.serializeNBT());
        compound.setTag("itemsOut", outputHandler.serializeNBT());
        compound.setInteger("energy", energyStorage.getEnergyStored());
        return compound;
    }

    boolean canInteractWith(EntityPlayer player)
    {
        return !isInvalid() && player.getDistanceSq(pos.add(0.5,0.5,0.5)) <= 64;
    }

    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            if (energyStorage.getEnergyStored() < RF_PER_TICK) {
                setState(FurnaceState.NO_POWER);
                return;
            }

            if (progress > 0) {
                setState(FurnaceState.WORKING);
                energyStorage.consumePower(RF_PER_TICK);
                progress--;
                if (progress <= 0) {
                    attemptSmelting();
                }
                markDirty();
            } else {
                setState(FurnaceState.IDLE);
                startSmelting();
            }
        }
    }

    //Simulate serves as test
    private boolean insertOutput(ItemStack output, boolean simulate)
    {
        for (int i = 0; i < OUTPUT_SLOTS; i++)
        {
            ItemStack remaining = outputHandler.insertItem(i, output, simulate);
            if (remaining.isEmpty())
                return true;
        }
        return false;
    }

    private void startSmelting()
    {
        for (int i = 0; i < INPUT_SLOTS; i++)
        {
            ItemStack result = FurnaceRecipes.instance().getSmeltingResult(inputHandler.getStackInSlot(i));
            if (!result.isEmpty())
            {
                if (insertOutput(result.copy(), true))
                {
                    progress = MAX_PROGRESS;
                    markDirty();
                    //^^^ perché una variabile dello stato interno è cambiata
                }

                break;
            }
        }
    }

    private void attemptSmelting()
    {
        for (int i = 0; i < INPUT_SLOTS; i++)
        {
            ItemStack result = FurnaceRecipes.instance().getSmeltingResult(inputHandler.getStackInSlot(i));
            if (!result.isEmpty())
            {
                if (insertOutput(result.copy(), false))
                {
                    inputHandler.extractItem(i, 1, false);
                    break;
                }

                break;
            }
        }
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
    }

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound compound = super.getUpdateTag();
        compound.setInteger("state", state.ordinal());
        return compound;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        int stateIndex = pkt.getNbtCompound().getInteger("state");

        if (world.isRemote && stateIndex != state.ordinal())
        {
            state = FurnaceState.VALUES[stateIndex];
            world.markBlockRangeForRenderUpdate(pos, pos);
        }
    }

    private TestEnergyStorage energyStorage = new TestEnergyStorage(MAX_POWER, RF_PER_TICK_INPUT);

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        if (capability == CapabilityEnergy.ENERGY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == null)
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(combinedHandler);
            else if (facing == EnumFacing.UP)
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputHandler);
            else if (facing == EnumFacing.DOWN)
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(outputHandler);
        }
        if (capability == CapabilityEnergy.ENERGY)
        {
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        }
        return super.getCapability(capability, facing);
    }

    public void setState(FurnaceState state)
    {
        if (this.state != state)
        {
            this.state = state;
            markDirty();

            //this two lines make sure the client is notified of a block update
            IBlockState blockState = world.getBlockState(pos);
            getWorld().notifyBlockUpdate(pos, blockState, blockState, 3);
        }
    }

    public FurnaceState getState()
    {
        return state;
    }

    public int getProgress()
    {
        return progress;
    }

    public void setProgress(int progress)
    {
        this.progress = progress;
    }

    public int getClientProgress()
    {
        return clientProgress;
    }

    public void setClientProgress(int clientProgress)
    {
        this.clientProgress = clientProgress;
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
}
