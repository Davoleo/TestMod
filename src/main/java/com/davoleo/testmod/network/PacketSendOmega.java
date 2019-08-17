package com.davoleo.testmod.network;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.render.OverlayRenderer;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 20:58
 * Class: PacketSendOmega
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PacketSendOmega {

    private float omega;
    private float influence;
    private float playerOmega;

    public PacketSendOmega(float omega, float influence, float playerOmega) {
        this.omega = omega;
        this.influence = influence;
        this.playerOmega = playerOmega;
    }

    public PacketSendOmega(ByteBuf byteBuf)
    {
        omega = byteBuf.readFloat();
        influence = byteBuf.readFloat();
        playerOmega = byteBuf.readFloat();
    }

    public void toBytes(ByteBuf byteBuf)
    {
        byteBuf.writeFloat(omega);
        byteBuf.writeFloat(influence);
        byteBuf.writeFloat(playerOmega);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> OverlayRenderer.instance.setOmega(omega, influence, playerOmega));
        ctx.get().setPacketHandled(true);
    }
}
