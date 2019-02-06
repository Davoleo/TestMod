package com.davoleo.testmod.block.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 17:57
 * Class: ContainerGenerator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ContainerGenerator extends Container {

    private TileGenerator tileEntity;

    public ContainerGenerator(IInventory playerInventory, TileGenerator tileEntity)
    {
        this.tileEntity = tileEntity;

        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory)
    {
        //main inventory
        for (int row = 0; row < 3; ++row)
            for (int column = 0; column < 9; ++column)
            {
                int x = 10 + column * 18;
                int y = row * 18 + 70;
                this.addSlotToContainer(new Slot(playerInventory, column + row * 9 + 10, x, y));
            }

        //Hotbar
        // Slots for the hotbar
        for (int row = 0; row < 9; ++row)
        {
            int x = 10 + row * 18;
            int y = 58 + 70;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
    {
        return tileEntity.canInteractWith(playerIn);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    }


}
