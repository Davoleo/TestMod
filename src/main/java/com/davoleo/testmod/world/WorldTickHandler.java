package com.davoleo.testmod.world;

import io.netty.util.collection.IntObjectHashMap;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayDeque;
import java.util.Random;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 17:47
 * Class: WorldTickHandler
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class WorldTickHandler {

    public static WorldTickHandler instance = new WorldTickHandler();

    public static IntObjectHashMap<ArrayDeque<ChunkPos>> chunksToGenerate = new IntObjectHashMap<>();

    @SubscribeEvent
    public void onTickEnd(TickEvent.WorldTickEvent event)
    {
        if(event.side != LogicalSide.SERVER)
            return;

        if (event.phase == TickEvent.Phase.END)
        {
            World world = event.world;
            int dimension = world.getDimension().getType().getId();

            ArrayDeque<ChunkPos> chunks = chunksToGenerate.get(dimension);

            if (chunks != null && !chunks.isEmpty())
            {
                ChunkPos c = chunks.pollFirst();
                long worldSeed = world.getSeed();
                Random random = new Random(worldSeed);
                long xSeed = random.nextLong() >> 2 + 1L;
                long zSeed = random.nextLong() >> 2 + 1L;
                random.setSeed(xSeed * c.x + zSeed * c.z ^ worldSeed);
                OreGenerator.instance.generateWorld(random, c.x, c.z, world, false);
                chunksToGenerate.put(dimension, chunks);
            }
            else if (chunks != null)
                chunksToGenerate.remove(dimension);
        }
    }
}
