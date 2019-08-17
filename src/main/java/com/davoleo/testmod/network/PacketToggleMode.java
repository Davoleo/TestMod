package com.davoleo.testmod.network;

import com.davoleo.testmod.item.ItemWand;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/04/2019 / 16:37
 * Class: PacketToggleMode
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PacketToggleMode {

    public PacketToggleMode()
    { }

    public PacketToggleMode(ByteBuf byteBuf)
    { }

    public void toBytes(ByteBuf byteBuf)
    { }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            EntityPlayerMP player = ctx.get().getSender();
            ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
            if (!heldItem.isEmpty() && heldItem.getItem() instanceof ItemWand)
            {
                ItemWand wand = (ItemWand) heldItem.getItem();
                wand.toggleMode(player, heldItem);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
