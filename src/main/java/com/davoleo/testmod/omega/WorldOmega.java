package com.davoleo.testmod.omega;

import com.davoleo.testmod.network.Messages;
import com.davoleo.testmod.network.PacketSendOmega;
import com.davoleo.testmod.omega.player.PlayerOmega;
import com.davoleo.testmod.omega.player.PlayerProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 19:02
 * Class: WorldOmega
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class WorldOmega extends WorldSavedData {

    private static final String NAME = "TestModOmegaData";

    private Map<ChunkPos, OmegaSphere> spheres = new HashMap<>();
    private int ticker = 10;

    public WorldOmega() {
        super(NAME);
    }

    public WorldOmega(String name) {
        super(name);
    }

    public static WorldOmega get(World world) {
        MapStorage storage = world.getPerWorldStorage();
        WorldOmega instance = (WorldOmega) storage.getOrLoadData(WorldOmega.class, NAME);

        if (instance == null) {
            instance = new WorldOmega();
            storage.setData(NAME, instance);
        }
        return instance;
    }

    public float getOmegaInfluence(World world, BlockPos pos) {
        ChunkPos chunkPos = new ChunkPos(pos);
        float omega = 0.0f;
        for (int dx = -4 ; dx <= 4 ; dx++) {
            for (int dz = -4 ; dz <= 4 ; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                OmegaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(sphere.getCenter());
                    if (distanceSq < sphere.getRadius() * sphere.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        omega += (sphere.getRadius() - distance) / sphere.getRadius();
                    }
                }
            }
        }
        return omega;
    }

    public float getOmegaStrength(World world, BlockPos pos) {
        ChunkPos chunkPos = new ChunkPos(pos);
        float omega = 0.0f;
        for (int dx = -4 ; dx <= 4 ; dx++) {
            for (int dz = -4 ; dz <= 4 ; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                OmegaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(sphere.getCenter());
                    if (distanceSq < sphere.getRadius() * sphere.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        double influence = (sphere.getRadius() - distance) / sphere.getRadius();
                        omega += influence * sphere.getCurrentOmega();
                    }
                }
            }
        }
        return omega;
    }

    public float extractOmega(World world, BlockPos pos) {
        float omegaInfluence = getOmegaInfluence(world, pos);
        if (omegaInfluence <= 0) {
            return 0;
        }
        ChunkPos chunkPos = new ChunkPos(pos);
        float extracted = 0.0f;
        for (int dx = -4 ; dx <= 4 ; dx++) {
            for (int dz = -4 ; dz <= 4 ; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                OmegaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(sphere.getCenter());
                    if (distanceSq < sphere.getRadius() * sphere.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        double influence = (sphere.getRadius() - distance) / sphere.getRadius();
                        float currentOmega = sphere.getCurrentOmega();
                        if (influence > currentOmega)
                            influence = currentOmega;
                        currentOmega -= influence;
                        extracted += influence;
                        sphere.setCurrentOmega(currentOmega);
                        markDirty();
                    }
                }
            }
        }
        return extracted;
    }

    public void tick(World world) {
        ticker--;
        if (ticker > 0) {
            return;
        }
        ticker = 10;
        growOmega(world);
        sendOmega(world);
    }

    private void growOmega(World world) {
        for (Map.Entry<ChunkPos, OmegaSphere> entry : spheres.entrySet()) {
            OmegaSphere sphere = entry.getValue();
            if (sphere.getRadius() > 0) {
                if (world.isBlockLoaded(sphere.getCenter())) {
                    float currentOmega = sphere.getCurrentOmega();
                    currentOmega += .01f;
                    if (currentOmega >= 5) {
                        currentOmega = 5;
                    }
                    sphere.setCurrentOmega(currentOmega);
                    markDirty();
                }
            }
        }
    }

    private void sendOmega(World world) {
        for (EntityPlayer player : world.playerEntities) {
            float omegaStrength = getOmegaStrength(world, player.getPosition());
            float maxInfluence = getOmegaInfluence(world, player.getPosition());
            PlayerOmega playerOmega = PlayerProperties.getPlayerOmega(player);
            Messages.INSTANCE.sendTo(new PacketSendOmega(omegaStrength, maxInfluence, playerOmega.getOmega()), (EntityPlayerMP) player);
        }
    }

    private OmegaSphere getOrCreateSphereAt(World world, ChunkPos cp) {
        OmegaSphere sphere = spheres.get(cp);
        if (sphere == null) {
            BlockPos center = cp.getBlock(8, OmegaSphere.getRandomYOffset(world.getSeed(), cp.x, cp.z), 8);
            float radius = 0;
            if (OmegaSphere.isCenterChunk(world.getSeed(), cp.x, cp.z)) {
                radius = OmegaSphere.getRadius(world.getSeed(), cp.x, cp.z);
            }
            sphere = new OmegaSphere(center, radius);
            spheres.put(cp, sphere);
            markDirty();
        }
        return sphere;
    }


    @Override
    public void read(NBTTagCompound nbt)
    {
        NBTTagList list = nbt.getList("spheres", Constants.NBT.TAG_COMPOUND);
        for (int i = 0 ; i < list.size() ; i++) {
            NBTTagCompound sphereNBT = list.getCompound(i);
            ChunkPos pos = new ChunkPos(sphereNBT.getInt("cx"), sphereNBT.getInt("cz"));
            OmegaSphere sphere = new OmegaSphere(
                    new BlockPos(sphereNBT.getInt("posx"), sphereNBT.getInt("posy"), sphereNBT.getInt("posz")),
                    sphereNBT.getFloat("radius"));
            sphere.setCurrentOmega(sphereNBT.getFloat("omega"));
            spheres.put(pos, sphere);
        }
    }

    @Nonnull
    @Override
    public NBTTagCompound write(@Nonnull NBTTagCompound compound)
    {
        NBTTagList list = new NBTTagList();
        for (Map.Entry<ChunkPos, OmegaSphere> entry : spheres.entrySet()) {
            NBTTagCompound sphereNBT = new NBTTagCompound();
            ChunkPos pos = entry.getKey();
            OmegaSphere sphere = entry.getValue();
            sphereNBT.setInt("cx", pos.x);
            sphereNBT.setInt("cz", pos.z);
            sphereNBT.setInt("posx", sphere.getCenter().getX());
            sphereNBT.setInt("posy", sphere.getCenter().getY());
            sphereNBT.setInt("posz", sphere.getCenter().getZ());
            sphereNBT.setFloat("radius", sphere.getRadius());
            sphereNBT.setFloat("omega", sphere.getCurrentOmega());
            list.add(sphereNBT);
        }
        compound.setTag("spheres", list);

        return compound;
    }

}
