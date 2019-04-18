package com.davoleo.testmod.omega;

import net.minecraft.util.math.BlockPos;

import java.util.Random;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 20:17
 * Class: OmegaSphere
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

class OmegaSphere {

    private final BlockPos center;
    private final float radius;

    private float currentOmega = 0;

    OmegaSphere(BlockPos center, float radius)
    {
        this.center = center;
        this.radius = radius;
    }

    float getCurrentOmega()
    {
        return currentOmega;
    }

    void setCurrentOmega(float currentOmega)
    {
        this.currentOmega = currentOmega;
    }

    BlockPos getCenter()
    {
        return center;
    }

    float getRadius()
    {
        return radius;
    }


    static boolean isCenterChunk(long seed, int chunkX, int chunkZ)
    {
        Random random = new Random(seed + chunkX * 1766557063L + chunkZ * 21766558031L);
        //TODO : Further Inspection on this problem
        //Default '<'
        return random.nextFloat() > .03F;
    }

    static float getRadius(long seed, int chunkX, int chunkZ)
    {
        Random random = new Random(seed + chunkX * 31766435083L + chunkZ * 655987873L);
        return random.nextFloat() * 40 + 20;
    }

    static int getRandomYOffset(long seed, int chunkX, int chunkZ)
    {
        Random random = new Random(seed + chunkX * 3556692499L + chunkZ * 2998604447L);
        return random.nextInt(60) + 40;
    }
}
