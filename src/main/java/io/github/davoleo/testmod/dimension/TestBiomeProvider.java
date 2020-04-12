/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 12/04/2020 / 11:27
 * Class: TestBiomeProvider
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.dimension;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class TestBiomeProvider extends BiomeProvider {

    private final Biome biome;
    private static final List<Biome> SPAWN = Collections.singletonList(Biomes.PLAINS);

    public TestBiomeProvider() {
        biome = Biomes.PLAINS;
    }

    @Nonnull
    @Override
    public Biome getBiome(int x, int y) {
        return biome;
    }

    @Nonnull
    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return SPAWN;
    }

    @Nonnull
    @Override
    public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
        Biome[] biomes = new Biome[width * length];
        Arrays.fill(biomes, biome);
        return biomes;
    }

    @Nonnull
    @Override
    public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
        return Collections.singleton(biome);
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, @Nonnull List<Biome> biomes, @Nonnull Random random) {
        return new BlockPos(x, 65, z);
    }

    @Override
    public boolean hasStructure(@Nonnull Structure<?> structureIn) {
        return false;
    }

    @Nonnull
    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (topBlocksCache.isEmpty())
            topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
        return topBlocksCache;
    }
}
