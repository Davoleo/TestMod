/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 12/04/2020 / 11:26
 * Class: TestChunkGenerator
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.dimension;

import io.github.davoleo.testmod.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nonnull;

//There are different kinds of ChunkGenerators you can extend from
public class TestChunkGenerator extends ChunkGenerator<TestChunkGenerator.Config> {

    public TestChunkGenerator(IWorld worldIn, BiomeProvider biomeProviderIn) {
        super(worldIn, biomeProviderIn, Config.create());
    }

    @Override
    public void generateSurface(@Nonnull IChunk chunkIn) {
        BlockState bedrock = Blocks.BEDROCK.getDefaultState();
        BlockState copper = ModBlocks.COPPER_BLOCK.getDefaultState();
        ChunkPos chunkPos = chunkIn.getPos();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int x, z;

        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                chunkIn.setBlockState(pos.setPos(x, 0, z), bedrock, false);
            }
        }

        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                int realX = chunkPos.x * 16 + x;
                int realZ = chunkPos.z * 16 + z;
                int height = (int) (65 + Math.sin(realX / 20F) * 10 + Math.cos(realZ / 20F) * 10);
                for (int y = 1; y < height; y++) {
                    chunkIn.setBlockState(pos.setPos(x, y, z), copper, false);
                }
            }
        }
    }

    @Override
    public int getGroundHeight() {
        return world.getSeaLevel() + 1;
    }

    @Override
    public void makeBase(@Nonnull IWorld worldIn, @Nonnull IChunk chunkIn) { }

    @Override
    public int func_222529_a(int p_222529_1_, int p_222529_2_, @Nonnull Heightmap.Type heightmapType) {
        return 0;
    }

    public static class Config extends GenerationSettings {

        public static Config create() {
            Config config = new Config();
            config.setDefaultBlock(Blocks.BELL.getDefaultState());
            config.setDefaultFluid(Blocks.LAVA.getDefaultState());
            return config;
        }

    }
}
