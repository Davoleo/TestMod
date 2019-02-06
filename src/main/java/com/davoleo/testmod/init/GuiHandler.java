package com.davoleo.testmod.init;

import com.davoleo.testmod.block.furnace.TileFastFurnace;
import com.davoleo.testmod.util.IGuiTileEntity;
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

        if (te instanceof IGuiTileEntity) {
            ID = GUI_FAST_FURNACE;
            return ((IGuiTileEntity) te).createContainer(player);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        if (te instanceof IGuiTileEntity) {
            ID = GUI_FAST_FURNACE;
            TileFastFurnace containerTileEntity = (TileFastFurnace) te;
            return ((TileFastFurnace) te).createGui(player);
        }

        return null;
    }
}
