package com.davoleo.testmod.network;

import com.davoleo.testmod.block.pedestal.TileEntityPedestal;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 17:01
 * Class: PacketRequestUpdatePedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PacketRequestUpdatePedestal implements IMessage {

    private BlockPos pos;
    private int dimension;

    public PacketRequestUpdatePedestal()
    { }

    public PacketRequestUpdatePedestal(BlockPos pos, int dimension)
    {
        this.pos = pos;
        this.dimension = dimension;
    }

    public PacketRequestUpdatePedestal(TileEntityPedestal te)
    {
        this(te.getPos(), te.getWorld().provider.getDimension());
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
        buf.writeInt(dimension);
    }

    public static class Handler implements IMessageHandler<PacketRequestUpdatePedestal, PacketUpdatePedestal>
    {
        @Override
        public PacketUpdatePedestal onMessage(PacketRequestUpdatePedestal message, MessageContext ctx)
        {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(message.dimension);
            TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(message.pos);

            if (te != null)
                return new PacketUpdatePedestal(te);
            else
                return null;
        }
    }
}
