package com.davoleo.testmod.world;

import com.davoleo.testmod.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/*************************************************
 * Author: Davoleo
 * Date: 08/08/2018
 * Hour: 19.06
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModWorldGeneration implements IWorldGenerator {

//General generator
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.getDimension() == 0) //0: Overworld
        {
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }


//Overworld generator
    public void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider)
    {
        generateOre(ModBlocks.oreCopper.getDefaultState(),world, random, chunkX * 16, chunkZ * 16,16,64, 4 + random.nextInt(7), 7);
    }

//Ore generator template
    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances)
    {
        int deltaY = maxY - minY;

        for (int i = 0; i < chances; i++) {
            BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size);
            generator.generate(world, random, pos);
        }

    }

}

