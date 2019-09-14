package com.davoleo.testmod.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.BasePlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;

import javax.annotation.Nonnull;
import java.util.Random;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 14/09/2019 / 21:09
 * Class: DimensionCompositeFeature
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class DimensionCompositeFeature<F extends IFeatureConfig, D extends IPlacementConfig> extends CompositeFeature<F, D> {

    private final DimensionType dimension;

    public DimensionCompositeFeature(Feature<F> featureIn, F featureConfigIn, BasePlacement<D> basePlacementIn, D placementConfigIn, @Nonnull DimensionType dimension) {
        super(featureIn, featureConfigIn, basePlacementIn, placementConfigIn);
        this.dimension = dimension;
    }

    @Override
    public boolean func_212245_a(@Nonnull IWorld world, @Nonnull IChunkGenerator<? extends IChunkGenSettings> chunkGenerator, @Nonnull Random random, @Nonnull BlockPos pos, NoFeatureConfig config) {
        if (world.getDimension().getType().equals(dimension))
            return super.func_212245_a(world, chunkGenerator, random, pos, config);
        return false;
    }
}
