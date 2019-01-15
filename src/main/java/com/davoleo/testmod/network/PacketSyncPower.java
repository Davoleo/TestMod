package com.davoleo.testmod.network;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.util.IEnergyContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/01/2019 / 19:46
 * Class: PacketSyncPower
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class PacketSyncPower implements IMessage {

    private int energy;

    @Override
    public void fromBytes(ByteBuf buf)
    {
        energy = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(energy);
    }

    //Used by the network system itself
    @SuppressWarnings("unused")
    public PacketSyncPower() { }

    public PacketSyncPower(int energy)
    {
        this.energy = energy;
    }

    public static class Handler implements IMessageHandler<PacketSyncPower, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSyncPower message, MessageContext ctx)
        {
            TestMod.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSyncPower message, MessageContext ctx)
        {
            EntityPlayer player = TestMod.proxy.getClientPlayer();
            if (player.openContainer instanceof IEnergyContainer)
                ((IEnergyContainer) player.openContainer).syncEnergy(message.energy);
        }
    }
}
