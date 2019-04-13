package com.davoleo.testmod.network;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.render.OverlayRenderer;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 20:58
 * Class: PacketSendOmega
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PacketSendOmega implements IMessage {

    private float omega;
    private float influence;
    private float playerOmega;

    public PacketSendOmega()
    { }

    @Override
    public void fromBytes(ByteBuf byteBuf)
    {
        omega = byteBuf.readFloat();
        influence = byteBuf.readFloat();
        playerOmega = byteBuf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf byteBuf)
    {
        byteBuf.writeFloat(omega);
        byteBuf.writeFloat(influence);
        byteBuf.writeFloat(playerOmega);
    }

    public PacketSendOmega(float omega, float influence, float playerOmega)
    {
        this.omega = omega;
        this.influence = influence;
        this.playerOmega = playerOmega;
    }

    public static class Handler implements IMessageHandler<PacketSendOmega, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSendOmega packetSendOmega, MessageContext messageContext)
        {
            TestMod.proxy.addScheduledTaskClient(() -> handle(packetSendOmega, messageContext));
            return null;
        }

        private void handle(PacketSendOmega message, MessageContext context)
        {
            OverlayRenderer.instance.setOmega(message.omega, message.influence, message.playerOmega);
        }
    }
}
