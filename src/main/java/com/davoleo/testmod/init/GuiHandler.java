package com.davoleo.testmod.init;

import com.davoleo.testmod.block.furnace.ContainerFastFurnace;
import com.davoleo.testmod.block.furnace.GuiFastFurnace;
import com.davoleo.testmod.block.furnace.TileFastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/01/2019 / 17:31
 * Class: GuiHandler
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class GuiHandler implements IGuiHandler {

    public static final int GUI_FAST_FURNACE = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileFastFurnace) {
            ID = GUI_FAST_FURNACE;
            return new ContainerFastFurnace(player.inventory, (TileFastFurnace) te);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileFastFurnace) {
            ID = GUI_FAST_FURNACE;
            TileFastFurnace containerTileEntity = (TileFastFurnace) te;
            return new GuiFastFurnace(containerTileEntity, new ContainerFastFurnace(player.inventory, containerTileEntity));
        }

        return null;
    }
}
