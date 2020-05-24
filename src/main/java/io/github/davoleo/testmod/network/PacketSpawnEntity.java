/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 10/03/2020 / 17:30
 * Class: PacketSpawnEntity
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.network;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PacketSpawnEntity {

    private final String id;
    private final DimensionType type;
    private final BlockPos pos;

    public PacketSpawnEntity(PacketBuffer buf) {
        this.id = buf.readString();
        this.type = DimensionType.getById(buf.readInt());
        this.pos = buf.readBlockPos();
    }

    public PacketSpawnEntity(String id, DimensionType type, BlockPos pos) {
        this.id = id;
        this.type = type;
        this.pos = pos;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeString(id);
        buf.writeInt(type.getId());
        buf.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerWorld spawnWorld = ctx.get().getSender().world.getServer().getWorld(type);
            EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id));
            if (entityType == null)
                throw new IllegalStateException("Unknown ID! " + id + "!");
            entityType.spawn(spawnWorld, null, null, pos, SpawnReason.SPAWN_EGG, true, true);
        });

        ctx.get().setPacketHandled(true);
    }
}
