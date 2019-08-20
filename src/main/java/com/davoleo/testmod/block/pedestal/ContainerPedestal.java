package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 17:16
 * Class: ContainerPedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ContainerPedestal extends Container {

    public ContainerPedestal(IInventory playerInventory, final TileEntityPedestal te)
    {
        //Add player slots
        addPlayerSlots(playerInventory);

        //Add pedestal inventory slot
        addSlot(new SlotItemHandler(te.inventory, 0, 80, 35)
        {
            @Override
            public void onSlotChanged()
            {
                te.markDirty();
            }
        });

    }

    private void addPlayerSlots(IInventory playerInventory)
    {
        //Main Inventory Slots
        for(int row = 0; row<3; row++)
        {
            for (int col=0; col < 9; col++)
            {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col* 18, 84 + row * 18));
            }
        }

        //Hotbar Slots
        for (int col = 0; col < 9; col++)
        {
            addSlot(new Slot(playerInventory, col, 8+col*18, 142));
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
    {
        return Utils.canInteractWithPlayer(playerIn);
    }

    //Stolen from nooby >:)
    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = this.inventorySlots.get(index);

        if (slot == null || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack original = slot.getStack().copy();
        ItemStack itemstack = slot.getStack().copy();

        // slot that was clicked on not empty?
        int end = this.inventorySlots.size();

        // Is it a slot in the main inventory? (aka not player inventory)
        if (index < 5) {
            // try to put it into the player inventory (if we have a player inventory)
            if (!this.mergeItemStack(itemstack, 5, end, true)) {
                return ItemStack.EMPTY;
            }
        }
        // Slot is in the player inventory (if it exists), transfer to main inventory
        else if (!this.mergeItemStack(itemstack, 0, 5, false)) {
            return ItemStack.EMPTY;
        }

        slot.onSlotChanged();

        if (itemstack.getCount() == original.getCount()) {
            return ItemStack.EMPTY;
        }

        // update slot we pulled from
        slot.putStack(itemstack);
        slot.onTake(playerIn, itemstack);

        if (slot.getHasStack() && slot.getStack().isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
        }

        return original;
    }
}
