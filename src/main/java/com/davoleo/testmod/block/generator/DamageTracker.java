package com.davoleo.testmod.block.generator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 16/02/2019 / 17:23
 * Class: DamageTracker
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class DamageTracker {

    public static final DamageTracker instance = new DamageTracker();

    private Map<Integer, Map<BlockPos, Set<UUID>>> trackedMobs = new HashMap<>();

    public void reset()
    {
        trackedMobs.clear();
    }

    public void register(Integer dimension, BlockPos pos, UUID entity)
    {
        if (!trackedMobs.containsKey(dimension))
            trackedMobs.put(dimension, new HashMap<>());
        Map<BlockPos, Set<UUID>> dimensionTracking = trackedMobs.get(dimension);
        if (!dimensionTracking.containsKey(pos))
            dimensionTracking.put(pos, new HashSet<>());
        dimensionTracking.get(pos).add(entity);
    }

    public void remove(Integer dimension, BlockPos pos)
    {
        if (trackedMobs.containsKey(dimension))
            trackedMobs.get(dimension).remove(pos);
    }

    public void clear(Integer dimension, BlockPos pos)
    {
        if (trackedMobs.containsKey(dimension))
        {
            Map<BlockPos, Set<UUID>> dimensionTracking = trackedMobs.get(dimension);
            if (dimensionTracking.containsKey(pos))
                dimensionTracking.get(pos).clear();
        }
    }

    @SubscribeEvent
    public void onDamage(LivingDamageEvent event)
    {
        float amount = event.getAmount();
        EntityLivingBase entity = event.getEntityLiving();
        World world = entity.world;
        int dimension = world.getDimension().getType().getId();
        if (amount > 0 && trackedMobs.containsKey(dimension))
        {
            Map<BlockPos, Set<UUID>> dimensionTracking = trackedMobs.get(dimension);
            for (Map.Entry<BlockPos, Set<UUID>> entry : dimensionTracking.entrySet())
            {
                if (entry.getValue().contains(entity.getUniqueID()))
                {
                    if (world.isBlockLoaded(entry.getKey()))
                    {
                        TileEntity tileEntity = world.getTileEntity(entry.getKey());
                        if (tileEntity instanceof TileGenerator)
                        {
                            ((TileGenerator) tileEntity).senseDamage(entity, amount);
                        }
                    }
                }
            }

        }
    }

}
