package com.davoleo.testmod.util.handlers;

import com.davoleo.testmod.block.pedestal.ContainerPedestal;
import com.davoleo.testmod.block.pedestal.GuiPedestal;
import com.davoleo.testmod.block.pedestal.TileEntityPedestal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/*************************************************
 * Author: Davoleo
 * Date: 12/08/2018
 * Hour: 19.22
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModGuiHandler implements IGuiHandler {

public static final int PEDESTAL = 0;

    @Override
        public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case PEDESTAL:
                return new ContainerPedestal(player.inventory, (TileEntityPedestal) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case PEDESTAL:
                return new GuiPedestal(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            default:
                return null;
        }
    }
}
