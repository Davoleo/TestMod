package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.config.FastFurnaceConfig;
import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.recipe.custom.CustomRecipe;
import com.davoleo.testmod.recipe.custom.CustomRecipeRegistry;
import com.davoleo.testmod.util.IGuiTileEntity;
import com.davoleo.testmod.util.IRestorableTileEntity;
import com.davoleo.testmod.util.TestEnergyStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
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

public class TileFastFurnace extends TileEntity implements ITickable, IRestorableTileEntity, IInteractionObject, IGuiTileEntity {

    public static final int INPUT_SLOTS = 3;
    public static final int OUTPUT_SLOTS = 3;
    public static final int SIZE = INPUT_SLOTS + OUTPUT_SLOTS;

    private FurnaceState state = FurnaceState.IDLE;
    private int progress = 0;
    private int clientProgress = -1;
    private int clientEnergy = -1;

    public TileFastFurnace() {
        super(ModBlocks.TYPE_FAST_FURNACE);
    }

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
            ItemStack result = getResult(stack);
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

    // TODO: 09/03/2019 Cache to make it more efficient
    private ItemStack getResult(ItemStack stackInSlot)
    {
        CustomRecipe recipe = CustomRecipeRegistry.getRecipe(stackInSlot);
        if (recipe != null)
            return recipe.getOutput();
        //return FurnaceRecipes.instance().getSmeltingResult(stackInSlot); TODO: 15/08/2019 1.13 port
        return ItemStack.EMPTY;

    }

    public void read(NBTTagCompound compound)
    {
        super.read(compound);
        readRestorableFromNBT(compound);
        state = FurnaceState.VALUES[compound.getInt("state")];
    }

    //saved things when the block is broken
    @Override
    public void readRestorableFromNBT(NBTTagCompound compound)
    {
        progress = compound.getInt("progress");
        if (compound.hasKey("itemsIn"))
            inputHandler.deserializeNBT((NBTTagCompound) compound.getTag("itemsIn"));
        if (compound.hasKey("itemsOut"))
            outputHandler.deserializeNBT((NBTTagCompound) compound.getTag("itemsOut"));
        energyStorage.setEnergy(compound.getInt("energy"));
    }

    @Nonnull
    @Override
    public NBTTagCompound write(NBTTagCompound compound)
    {
        super.write(compound);
        writeRestorableToNBT(compound);
        compound.setInt("state", state.ordinal());
        return compound;
    }

    //saved things when the block is broken
    @Override
    public void writeRestorableToNBT(NBTTagCompound compound)
    {
        compound.setInt("progress", progress);
        compound.setTag("itemsIn", inputHandler.serializeNBT());
        compound.setTag("itemsOut", outputHandler.serializeNBT());
        compound.setInt("energy", energyStorage.getEnergyStored());
    }

    boolean canInteractWith(EntityPlayer player)
    {
        return !isRemoved() && player.getDistanceSq(pos.add(0.5,0.5,0.5)) <= 64;
    }

    @Override
    public void tick()
    {
        if (!world.isRemote)
        {
            if (energyStorage.getEnergyStored() < FastFurnaceConfig.RF_PER_TICK.get()) {
                setState(FurnaceState.NO_POWER);
                return;
            }

            if (progress > 0) {
                setState(FurnaceState.WORKING);
                energyStorage.consumePower(FastFurnaceConfig.RF_PER_TICK.get());
                progress--;
                if (progress <= 0) {
                    attemptSmelting();
                }
                markDirty();
            } else {
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
            ItemStack result = getResult(inputHandler.getStackInSlot(i));
            if (!result.isEmpty())
            {
                if (insertOutput(result.copy(), true))
                {
                    setState(FurnaceState.WORKING);
                    progress = FastFurnaceConfig.MAX_PROGRESS.get();
                    markDirty();
                    //^^^ perché una variabile dello stato interno è cambiata
                    return;
                }
            }
        }
        setState(FurnaceState.IDLE);
    }

    private void attemptSmelting()
    {
        for (int i = 0; i < INPUT_SLOTS; i++)
        {
            ItemStack result = getResult(inputHandler.getStackInSlot(i));
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

    @Nonnull
    @Override
    public Container createContainer(@Nonnull InventoryPlayer playerInventory, @Nonnull EntityPlayer playerIn) {
        return new ContainerFastFurnace(playerInventory,  this);
    }

    @Nonnull
    @Override
    public String getGuiID() {
        return "testmod:fast_furnace";
    }

    @Nonnull
    @Override
    public ITextComponent getName() {
        return new TextComponentString("Fast Furnace GUI");
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return null;
    }

    @Override
    public GuiContainer createGui(EntityPlayer player)
    {
        return new GuiFastFurnace(this, new ContainerFastFurnace(player.inventory, this));
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
        compound.setInt("state", state.ordinal());
        return compound;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        int stateIndex = pkt.getNbtCompound().getInt("state");

        if (world.isRemote && stateIndex != state.ordinal())
        {
            state = FurnaceState.VALUES[stateIndex];
            world.markBlockRangeForRenderUpdate(pos, pos);
        }
    }

    private TestEnergyStorage energyStorage = new TestEnergyStorage(FastFurnaceConfig.MAX_POWER.get(), FastFurnaceConfig.RF_PER_TICK_INPUT.get());

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, EnumFacing facing) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if (facing == null)
                return LazyOptional.of(() -> ((T) combinedHandler));
            else if (facing == EnumFacing.UP)
                return LazyOptional.of(() -> ((T) inputHandler));
            else if (facing == EnumFacing.DOWN)
                return LazyOptional.of(() -> ((T) outputHandler));
        }

        if (cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> ((T) energyStorage));

        return super.getCapability(cap);
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
