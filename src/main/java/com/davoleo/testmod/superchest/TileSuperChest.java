package com.davoleo.testmod.superchest;

import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.util.IGuiTileEntity;
import com.davoleo.testmod.util.IRestorableTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/03/2019 / 15:02
 * Class: TileSuperChest
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TileSuperChest extends TileEntity implements IRestorableTileEntity, IGuiTileEntity {

    private ItemStackHandler itemHandler = new ItemStackHandler(3*9)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            //Tells the tile entity that something changed and makes the changes permanent
            TileSuperChest.this.markDirty();
        }
    };

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != ModBlocks.blockSuperChest || state.getValue(BlockSuperChest.FORMED) == SuperChestPartIndex.UNFORMED)
            return false;

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;

        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != ModBlocks.blockSuperChest || state.getValue(BlockSuperChest.FORMED) == SuperChestPartIndex.UNFORMED)
            return null;

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler);

        return super.getCapability(capability, facing);
    }

    protected boolean canInteractWith(EntityPlayer player)
    {
        return !isInvalid() && (player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newSate)
    {
        return oldState.getBlock() != newSate.getBlock();
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
        if (compound.hasKey("items"))
            itemHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
    }

    @Override
    public void writeRestorableToNBT(NBTTagCompound compound)
    {
        compound.setTag("items", itemHandler.serializeNBT());
    }

    @Override
    public Container createContainer(EntityPlayer player)
    {
        return new ContainerSuperChest(player.inventory, this);
    }

    @Override
    public GuiContainer createGui(EntityPlayer player)
    {
        return new GuiSuperChest(this, new ContainerSuperChest(player.inventory, this));
    }


}
