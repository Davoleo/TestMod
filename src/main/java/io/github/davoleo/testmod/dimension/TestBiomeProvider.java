/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 12/04/2020 / 11:27
 * Class: TestBiomeProvider
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.dimension;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestBiomeProvider extends BiomeProvider {

    private final Biome biome;
    private static final List<Biome> SPAWN = Collections.singletonList(Biomes.PLAINS);

    public TestBiomeProvider() {
        super(new HashSet<>(SPAWN));
        biome = Biomes.PLAINS;
    }

    @Nonnull
    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return biome;
    }

    @Nonnull
    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return SPAWN;
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
