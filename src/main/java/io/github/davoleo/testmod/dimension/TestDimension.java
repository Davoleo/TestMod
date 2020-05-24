/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 12/04/2020 / 11:22
 * Class: TestDimension
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// command to enter the dimension
// /forge setdimension <player_selector> <id>
public class TestDimension extends Dimension {

    public TestDimension(World worldIn, DimensionType typeIn) {
        super(worldIn, typeIn, 0F);
    }

    @Nonnull
    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        return new TestChunkGenerator(world, new TestBiomeProvider());
    }

    @Nullable
    @Override
    public BlockPos findSpawn(@Nonnull ChunkPos chunkPosIn, boolean checkValid) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return null;
    }

    @Override
    public int getActualHeight() {
        return 256;
    }

    @Override
    public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
        return SleepResult.ALLOW;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        int j = 6000;
        float f1 = (j + partialTicks) / 24000F - 0.25F;

        if (f1 < 0F)
            f1 += 1F;

        if (f1 > 1F)
            f1 -=1F;

        float f2 = f1;
        f1 = 1F - (float) ((Math.cos(f1 * Math.PI) + 1D) / 2D);
        f1 = f2 + (f1 - f2) / 3F;
        return f1;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Nonnull
    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }
}
