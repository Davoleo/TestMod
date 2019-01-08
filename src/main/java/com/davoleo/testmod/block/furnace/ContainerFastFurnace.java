package com.davoleo.testmod.block.furnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 31/12/2018 / 01:19
 * Class: ContainerFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ContainerFastFurnace extends Container {

    private TileFastFurnace te;

    private static final int PROGRESS_ID = 0;

    public ContainerFastFurnace(IInventory playerInventory, TileFastFurnace te)
    {
        this.te = te;

        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory)
    {
        //Main Inventory Slots
        for (int row = 0; row < 3; ++row)
        {
            for (int col = 0; col < 9; ++col)
            {
                int x = 10 + col * 18;
                int y = row * 18 + 70;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
            }
        }

        //Hotbar Slots
        for (int row = 0; row < 9; ++row)
        {
            int x = 10 + row * 18;
            int y = 58 + 70;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    private void addOwnSlots()
    {
        IItemHandler handler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        //TE Slots
        int slotIndex = 0;

        //inputs
        int x = 10;
        int y = 40;
        addSlotToContainer(new SlotItemHandler(handler, slotIndex++, x, y)); x+=18;
        addSlotToContainer(new SlotItemHandler(handler, slotIndex++, x, y)); x+=18;
        addSlotToContainer(new SlotItemHandler(handler, slotIndex++, x, y));

        //outputs
        x = 118;
        addSlotToContainer(new SlotItemHandler(handler, slotIndex++, x, y)); x+=18;
        addSlotToContainer(new SlotItemHandler(handler, slotIndex++, x, y)); x+=18;
        addSlotToContainer(new SlotItemHandler(handler, slotIndex, x, y));


    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (IContainerListener listener : listeners)
            listener.sendWindowProperty(this, PROGRESS_ID, te.getProgress());
    }

    @Override
    public void updateProgressBar(int id, int data)
    {
        if (id == PROGRESS_ID)
            te.setProgress(data);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < TileFastFurnace.SIZE) {
                if (this.mergeItemStack(itemstack1, TileFastFurnace.SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, TileFastFurnace.SIZE, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();


        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
    {
        return te.canInteractWith(playerIn);
    }
}
