package com.davoleo.testmod.network;

import com.davoleo.testmod.block.pedestal.TileEntityPedestal;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 17:01
 * Class: PacketRequestUpdatePedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PacketRequestUpdatePedestal {

    private BlockPos pos;
    private int dimension;

    public PacketRequestUpdatePedestal(TileEntityPedestal te) {
        this.pos = te.getPos();
        this.dimension = te.getWorld().getDimension().getType().getId();
    }

    public PacketRequestUpdatePedestal(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
        buf.writeInt(dimension);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
// TODO 1.13

//            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dimension);
//            TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(pos);

//            if (te != null)
//                return new PacketUpdatePedestal(te);
//            else
//                return null;
        });
        ctx.get().setPacketHandled(true);
    }
}
