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
import net.minecraft.world.storage.MapStorage;
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

    public WorldOmega()
    {
        super(NAME);
    }

    @SuppressWarnings("unused")
    public WorldOmega(String name)
    {
        super(name);
    }

    public static WorldOmega get(World world)
    {
        MapStorage storage = world.getPerWorldStorage();  //Unique per dimension
        //world.getMapStorage(); <--- The same for all dimensions

        WorldOmega instance = (WorldOmega) storage.getOrLoadData(WorldOmega.class, NAME);

        if (instance == null)
        {
            instance = new WorldOmega();
            storage.setData(NAME, instance);
        }

        return instance;
    }

    public void tick(World world)
    {
        ticker--;
        if (ticker > 0)
            return;
        ticker = 10;
        growOmega(world);
        sendOmega(world);
    }

    public float getOmegaInfluence(World world, BlockPos pos)
    {
        ChunkPos chunkPos = new ChunkPos(pos);
        float omega = 0F;
        for (int dx = -4; dx <= 4; dx++)
        {
            for (int dz = -4; dz <= 4; dz++)
            {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                OmegaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0)
                {
                    double distanceSq = sphere.getRadius() * sphere.getRadius();
                    if (distanceSq < sphere.getRadius() * sphere.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        omega += (sphere.getRadius() - distance) / sphere.getRadius();
                    }
                }
            }
        }
        return omega;
    }

    public float getOmegaStrength(World world, BlockPos pos)
    {
        ChunkPos chunkPos = new ChunkPos(pos);
        float omega = 0F;
        for (int dx = -4; dx <= 4; dx++)
        {
            for (int dz = -4; dz <= 4; dz++)
            {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                OmegaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0)
                {
                    double distanceSq = sphere.getRadius() * sphere.getRadius();
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

    public float extractOmega(World world, BlockPos pos)
    {
        float omegaInfluence = getOmegaInfluence(world, pos);
        if (omegaInfluence <= 0)
            return 0;
        ChunkPos chunkPos = new ChunkPos(pos);
        float extracted = 0F;
        for (int dx = -4; dx <= 4; dx++)
        {
            for (int dz = -4; dz <= 4; dz++)
            {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                OmegaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0) {
                    double distanceSq = sphere.getRadius() * sphere.getRadius();
                    if (distanceSq < sphere.getRadius() * sphere.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        double factor = (sphere.getRadius() - distance) / sphere.getRadius();
                        float currentOmega = sphere.getCurrentOmega();
                        if (factor > currentOmega)
                            factor = currentOmega;
                        currentOmega -= factor;
                        extracted += factor;
                        sphere.setCurrentOmega(currentOmega);
                        markDirty();
                    }
                }
            }
        }
        return extracted;
    }

    private void growOmega(World world)
    {
        for (Map.Entry<ChunkPos, OmegaSphere> entry : spheres.entrySet())
        {
            OmegaSphere sphere = entry.getValue();

            if (sphere.getRadius() > 0 && world.isBlockLoaded(sphere.getCenter()))
            {
                float currentOmega = sphere.getCurrentOmega();
                currentOmega += 0.01;
                if (currentOmega >= 5)
                    currentOmega = 5;
                sphere.setCurrentOmega(currentOmega);
                markDirty();
            }
        }
    }

    private void sendOmega(World world)
    {
        for (EntityPlayer player : world.playerEntities)
        {
            float omegaStrength = getOmegaStrength(world, player.getPosition());
            float maxInfluence = getOmegaInfluence(world, player.getPosition());
            PlayerOmega playerOmega = PlayerProperties.getPlayerOmega(player);
            Messages.INSTANCE.sendTo(new PacketSendOmega(omegaStrength, maxInfluence, playerOmega.getOmega()), (EntityPlayerMP) player);
        }
    }

    private OmegaSphere getOrCreateSphereAt(World world, ChunkPos pos)
    {
        OmegaSphere sphere = spheres.get(pos);
        if (sphere == null)
        {
            BlockPos center = pos.getBlock(8, OmegaSphere.getRandomYOffset(world.getSeed(), pos.x, pos.z), 8);
            float radius = 0;
            if (OmegaSphere.isCenterChunk(world.getSeed(), pos.x, pos.z))
                radius = OmegaSphere.getRadius(world.getSeed(), pos.x, pos.z);
            sphere = new OmegaSphere(center, radius);
            spheres.put(pos, sphere);
            markDirty();
        }
        return sphere;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList list = nbt.getTagList("spheres", Constants.NBT.TAG_COMPOUND);
        for (int i = 0 ; i < list.tagCount() ; i++) {
            NBTTagCompound sphereNBT = list.getCompoundTagAt(i);
            ChunkPos pos = new ChunkPos(sphereNBT.getInteger("cx"), sphereNBT.getInteger("cz"));
            OmegaSphere sphere = new OmegaSphere(
                    new BlockPos(sphereNBT.getInteger("posx"), sphereNBT.getInteger("posy"), sphereNBT.getInteger("posz")),
                    sphereNBT.getFloat("radius"));
            sphere.setCurrentOmega(sphereNBT.getFloat("mana"));
            spheres.put(pos, sphere);
        }
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (Map.Entry<ChunkPos, OmegaSphere> entry : spheres.entrySet()) {
            NBTTagCompound sphereNBT = new NBTTagCompound();
            ChunkPos pos = entry.getKey();
            OmegaSphere sphere = entry.getValue();
            sphereNBT.setInteger("cx", pos.x);
            sphereNBT.setInteger("cz", pos.z);
            sphereNBT.setInteger("posx", sphere.getCenter().getX());
            sphereNBT.setInteger("posy", sphere.getCenter().getY());
            sphereNBT.setInteger("posz", sphere.getCenter().getZ());
            sphereNBT.setFloat("radius", sphere.getRadius());
            sphereNBT.setFloat("mana", sphere.getCurrentOmega());
            list.appendTag(sphereNBT);
        }
        compound.setTag("spheres", list);

        return compound;
    }
}
