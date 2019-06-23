package com.davoleo.testmod.network;

import com.davoleo.testmod.item.ItemWand;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/04/2019 / 16:37
 * Class: PacketToggleMode
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PacketToggleMode implements IMessage {

    @Override
    public void fromBytes(ByteBuf byteBuf)
    { }

    @Override
    public void toBytes(ByteBuf byteBuf)
    { }

    public PacketToggleMode()
    { }

    public static class Handler implements IMessageHandler<PacketToggleMode, IMessage>
    {
        @Override
        public IMessage onMessage(PacketToggleMode packetToggleMode, MessageContext messageContext)
        {
            FMLCommonHandler.instance().getWorldThread(messageContext.netHandler).addScheduledTask(() -> handle(packetToggleMode, messageContext));
            return null;
        }

        private void handle(PacketToggleMode message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().player;
            ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
            if (!heldItem.isEmpty() && heldItem.getItem() instanceof ItemWand)
            {
                ItemWand wand = (ItemWand) heldItem.getItem();
                wand.toggleMode(player, heldItem);
            }
        }
    }
}
