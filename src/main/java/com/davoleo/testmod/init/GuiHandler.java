package com.davoleo.testmod.init;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.util.IGuiTileEntity;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/01/2019 / 17:31
 * Class: GuiHandler
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class GuiHandler {

    @Nullable
    public static GuiScreen getClientGuiElement(FMLPlayMessages.OpenContainer container) {
        PacketBuffer buf  = container.getAdditionalData();
        BlockPos pos = buf.readBlockPos();
        World world = TestMod.proxy.getClientWorld();
        EntityPlayer player = TestMod.proxy.getClientPlayer();
        TileEntity te = world.getTileEntity(pos);

        if (te instanceof IGuiTileEntity)
            return ((IGuiTileEntity) te).createGui(player);

        return null;
    }
}
