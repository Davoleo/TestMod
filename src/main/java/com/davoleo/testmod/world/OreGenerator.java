package com.davoleo.testmod.world;

import com.davoleo.testmod.config.OreGenConfig;
import com.davoleo.testmod.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRange;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Predicate;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 16:46
 * Class: OreGenerator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class OreGenerator {

    private static final Predicate<IBlockState> IS_NETHERRACK = state -> state.getBlock() == Blocks.NETHERRACK;
    private static final Predicate<IBlockState> IS_ENDSTONE = state -> state.getBlock() == Blocks.END_STONE;

    public static void setup() {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            CountRangeConfig placementConfig = new CountRangeConfig(OreGenConfig.SPAWN_CHANCES.get(), OreGenConfig.MIN_Y.get(), OreGenConfig.MIN_Y.get(), OreGenConfig.MAX_Y.get());

            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    new DimensionCompositeFeature<>(
                            Feature.MINABLE,
                            new MinableConfig(MinableConfig.IS_ROCK, ModBlocks.oreCopper.getDefaultState(), OreGenConfig.MAX_VEIN_SIZE.get()),
                            new CountRange(),
                            placementConfig,
                            DimensionType.OVERWORLD));

            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    new DimensionCompositeFeature<>(
                            Feature.MINABLE,
                            new MinableConfig(MinableConfig.IS_ROCK, ModBlocks.oreAluminum.getDefaultState(), OreGenConfig.MAX_VEIN_SIZE.get()),
                            new CountRange(),
                            placementConfig,
                            DimensionType.OVERWORLD));

            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    new DimensionCompositeFeature<>(
                            Feature.MINABLE,
                            new MinableConfig(MinableConfig.IS_ROCK, ModBlocks.oreAngel.getDefaultState(), OreGenConfig.MAX_VEIN_SIZE.get()),
                            new CountRange(),
                            placementConfig,
                            DimensionType.OVERWORLD));

            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    new DimensionCompositeFeature<>(
                            Feature.MINABLE,
                            new MinableConfig(IS_NETHERRACK, ModBlocks.oreNetherGold.getDefaultState(), OreGenConfig.MAX_VEIN_SIZE.get()),
                            new CountRange(),
                            placementConfig,
                            DimensionType.NETHER));

            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    new DimensionCompositeFeature<>(
                            Feature.MINABLE,
                            new MinableConfig(IS_ENDSTONE, ModBlocks.oreZephyrite.getDefaultState(), OreGenConfig.MAX_VEIN_SIZE.get()),
                            new CountRange(),
                            placementConfig,
                            DimensionType.THE_END));

        }
    }
}
