package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.network.Messages;
import com.davoleo.testmod.network.PacketRequestUpdatePedestal;
import com.davoleo.testmod.network.PacketUpdatePedestal;
import com.davoleo.testmod.util.IGuiTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkDirection;
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

public class TileEntityPedestal extends TileEntity implements IGuiTileEntity, IInteractionObject {

    public TileEntityPedestal() {
        super(ModBlocks.TYPE_PEDESTAL);
    }

    public long lastUpdateTick;
    public ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot)
        {
            if (!world.isRemote) {
                lastUpdateTick = world.getGameTime();
                // TODO: 20/08/2019 1.13 port - Fix Player Stub
                Messages.INSTANCE.sendTo(new PacketUpdatePedestal(TileEntityPedestal.this), Minecraft.getInstance().player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
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
    public NBTTagCompound write(NBTTagCompound compound)
    {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setLong("lastUpdateTick", lastUpdateTick);
        return super.write(compound);
    }

    @Override
    public void read(NBTTagCompound compound)
    {
        inventory.deserializeNBT(compound.getCompound("inventory"));
        lastUpdateTick = compound.getLong("lastUpdateTick");
        super.read(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return LazyOptional.of(() -> ((T) inventory));
        return super.getCapability(cap);
    }

    @Override
    public GuiContainer createGui(EntityPlayer player)
    {
        return new GuiPedestal(new ContainerPedestal(player.inventory, this));
    }

    @Nonnull
    @Override
    public Container createContainer(@Nonnull InventoryPlayer playerInventory, @Nonnull EntityPlayer playerIn) {
        return new ContainerPedestal(playerInventory, this);
    }

    @Nonnull
    @Override
    public String getGuiID() {
        return "testmod:pedestal";
    }

    @Nonnull
    @Override
    public ITextComponent getName() {
        return new TextComponentString("Pedestal GUI");
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
}


