package com.davoleo.testmod.util;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 17:09
 * Interface: IGuiTileEntity
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public interface IGuiTileEntity {

    Container createContainer(EntityPlayer player);

    GuiContainer createGui(EntityPlayer player);
}
