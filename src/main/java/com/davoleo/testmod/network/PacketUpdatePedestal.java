package com.davoleo.testmod.network;

import com.davoleo.testmod.block.pedestal.TileEntityPedestal;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 16:36
 * Class: PacketUpdatePedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PacketUpdatePedestal implements IMessage {

    private BlockPos pos;
    private ItemStack item;
    private long lastUpdateTick;

    @SuppressWarnings("unused")
    public PacketUpdatePedestal()
    { }

    public PacketUpdatePedestal(BlockPos pos, ItemStack item, long lastUpdateTick)
    {
        this.pos = pos;
        this.item = item;
        this.lastUpdateTick = lastUpdateTick;
    }

    public PacketUpdatePedestal(TileEntityPedestal te)
    {
        this(te.getPos(), te.inventory.getStackInSlot(0), te.lastUpdateTick);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
        item = ByteBufUtils.readItemStack(buf);
        lastUpdateTick = buf.readLong();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
        ByteBufUtils.writeItemStack(buf, item);
        buf.writeLong(lastUpdateTick);
    }

    public static class Handler implements IMessageHandler<PacketUpdatePedestal, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdatePedestal message, MessageContext ctx)
        {
            //Lambda Runnable to execute client update tasks
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileEntityPedestal te = (TileEntityPedestal) Minecraft.getMinecraft().world.getTileEntity(message.pos);
                te.inventory.setStackInSlot(0, message.item);
                te.lastUpdateTick = message.lastUpdateTick;
            });
            return null;
        }
    }
}
