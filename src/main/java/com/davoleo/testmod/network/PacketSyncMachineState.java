package com.davoleo.testmod.network;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.util.IMachineStateContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/01/2019 / 19:46
 * Class: PacketSyncMachineState
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class PacketSyncMachineState {

    private int energy;
    private int progress;       //Percentage 0 to 100%


    public PacketSyncMachineState(int energy, int progress) {
        this.energy = energy;
        this.progress = progress;
    }

    public PacketSyncMachineState(ByteBuf buf)
    {
        energy = buf.readInt();
        progress = buf.readByte();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(energy);
        buf.writeByte(progress);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            EntityPlayer player = Minecraft.getInstance().player;
            if (player.openContainer instanceof IMachineStateContainer)
                ((IMachineStateContainer) player.openContainer).sync(energy, progress);
        });
        ctx.get().setPacketHandled(true);
    }
}
