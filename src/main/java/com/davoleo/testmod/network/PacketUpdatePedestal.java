package com.davoleo.testmod.network;

import com.davoleo.testmod.block.pedestal.TileEntityPedestal;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 16:36
 * Class: PacketUpdatePedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PacketUpdatePedestal {

    private BlockPos pos;
    private ItemStack item;
    private long lastUpdateTick;

    public PacketUpdatePedestal(TileEntityPedestal te) {
        this.pos = te.getPos();
        this.item = te.inventory.getStackInSlot(0);
        this.lastUpdateTick = te.lastUpdateTick;
    }

    public PacketUpdatePedestal(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
        //item = ByteBufUtils.readItemStack(buf); TODO 1.13
        lastUpdateTick = buf.readLong();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
        //ByteBufUtils.writeItemStack(buf, item); TODO 1.13
        buf.writeLong(lastUpdateTick);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //Lambda Runnable to execute client update tasks
            Minecraft.getInstance().addScheduledTask(() -> {
                TileEntityPedestal te = (TileEntityPedestal) Minecraft.getInstance().world.getTileEntity(pos);
                te.inventory.setStackInSlot(0, item);
                te.lastUpdateTick = lastUpdateTick;
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
