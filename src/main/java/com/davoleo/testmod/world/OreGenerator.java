package com.davoleo.testmod.world;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.config.OreGenConfig;
import com.davoleo.testmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

import java.util.ArrayDeque;
import java.util.Random;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 16:46
 * Class: OreGenerator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class OreGenerator implements IWorldGenerator {

    public static final String RETRO_NAME = "TestModOreGeneration";
    public static OreGenerator instance = new OreGenerator();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        generateWorld(random, chunkX, chunkZ, world, true);
    }

    public void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
    {
        if (!newGen && !OreGenConfig.RETROGEN)
            return;
        if (world.provider.getDimension() == DimensionType.OVERWORLD.getId()) {
            if (OreGenConfig.GENERATE_OVERWORLD) {
                addOreSpawn(ModBlocks.oreAngel, (byte) EnumOreType.ORE_OVERWORLD.ordinal(), Blocks.STONE, world, random, chunkX * 16, chunkZ * 16, OreGenConfig.MIN_VEIN_SIZE, OreGenConfig.MAX_VEIN_SIZE, OreGenConfig.SPAWN_CHANCES, OreGenConfig.MIN_Y, OreGenConfig.MAX_Y);
                addOreSpawn(ModBlocks.oreAluminum, (byte) EnumOreType.ORE_OVERWORLD.ordinal(), Blocks.STONE, world, random, chunkX * 16, chunkZ * 16, OreGenConfig.MIN_VEIN_SIZE, OreGenConfig.MAX_VEIN_SIZE, OreGenConfig.SPAWN_CHANCES, OreGenConfig.MIN_Y, OreGenConfig.MAX_Y);
                addOreSpawn(ModBlocks.oreCopper, (byte) EnumOreType.ORE_OVERWORLD.ordinal(), Blocks.STONE, world, random, chunkX * 16, chunkZ * 16, OreGenConfig.MIN_VEIN_SIZE, OreGenConfig.MAX_VEIN_SIZE, OreGenConfig.SPAWN_CHANCES, OreGenConfig.MIN_Y, OreGenConfig.MAX_Y);
            }
        } else
        if (world.provider.getDimension() == DimensionType.NETHER.getId()) {
            if (OreGenConfig.GENERATE_NETHER) {
                addOreSpawn(ModBlocks.oreAngel, (byte) EnumOreType.ORE_NETHER.ordinal(), Blocks.NETHERRACK, world, random, chunkX * 16, chunkZ * 16, OreGenConfig.MIN_VEIN_SIZE, OreGenConfig.MAX_VEIN_SIZE, OreGenConfig.SPAWN_CHANCES, OreGenConfig.MIN_Y, OreGenConfig.MAX_Y);
                addOreSpawn(ModBlocks.oreNetherGold, (byte) EnumOreType.ORE_NETHER.ordinal(), Blocks.NETHERRACK, world, random, chunkX * 16, chunkZ * 16, OreGenConfig.MIN_VEIN_SIZE, OreGenConfig.MAX_VEIN_SIZE, OreGenConfig.SPAWN_CHANCES, OreGenConfig.MIN_Y, OreGenConfig.MAX_Y);
            }
        } else
        if (world.provider.getDimension() == DimensionType.THE_END.getId()) {
            if (OreGenConfig.GENERATE_END) {
                addOreSpawn(ModBlocks.oreAngel, (byte) EnumOreType.ORE_END.ordinal(), Blocks.END_STONE, world, random, chunkX * 16, chunkZ * 16, OreGenConfig.MIN_VEIN_SIZE, OreGenConfig.MAX_VEIN_SIZE, OreGenConfig.SPAWN_CHANCES, OreGenConfig.MIN_Y, OreGenConfig.MAX_Y);
                addOreSpawn(ModBlocks.oreZephyrite, (byte) EnumOreType.ORE_END.ordinal(), Blocks.END_STONE, world, random, chunkX * 16, chunkZ * 16, OreGenConfig.MIN_VEIN_SIZE, OreGenConfig.MAX_VEIN_SIZE, OreGenConfig.SPAWN_CHANCES, OreGenConfig.MIN_Y, OreGenConfig.MAX_Y);
            }
        }

    }

    public void addOreSpawn(Block block, byte blockMeta, Block targetBlock, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int yMin, int yMax)
    {
        WorldGenMinable minable = new WorldGenMinable(block.getStateFromMeta(blockMeta), (minVeinSize + random.nextInt(maxVeinSize - minVeinSize + 1)), BlockMatcher.forBlock(targetBlock));
        for (int i = 0; i < chancesToSpawn; i++)
        {
            int posX = blockXPos + random.nextInt(16);
            int posY = yMin + random.nextInt(yMax - yMin);
            int posZ = blockZPos + random.nextInt(16);
            minable.generate(world, random, new BlockPos(posX, posY, posZ));
        }
    }

    //Retrogen data save & load

    @SubscribeEvent
    public void onChunkSave(ChunkDataEvent.Save event)
    {
        NBTTagCompound genTag = event.getData().getCompoundTag(RETRO_NAME);
        if (!genTag.hasKey("generated"))
            genTag.setBoolean("generated", true);
        event.getData().setTag(RETRO_NAME, genTag);
    }

    @SubscribeEvent
    public void onChunkLoad(ChunkDataEvent.Load event)
    {
        int dimension = event.getWorld().provider.getDimension();

        boolean regen = false;
        NBTTagCompound tag = (NBTTagCompound) event.getData().getTag(RETRO_NAME);
        ChunkPos coordinates = event.getChunk().getPos();

        if (tag != null)
        {
            boolean generated = false;
            if (generated)
            {
                if (OreGenConfig.VERBOSE)
                {
                    TestMod.logger.log(Level.DEBUG, "Queuing Retrogen for chunk: " + coordinates.toString() + ".");
                }
                regen = true;
            }
        }
        else
            regen = OreGenConfig.RETROGEN;

        if (regen)
        {
            ArrayDeque<ChunkPos> chunks = WorldTickHandler.chunksToGenerate.get(dimension);

            if (chunks == null)
            {
                WorldTickHandler.chunksToGenerate.put(dimension, new ArrayDeque<>(128));
                chunks = WorldTickHandler.chunksToGenerate.get(dimension);
            }
            if (chunks != null)
            {
                chunks.addLast(coordinates);
                WorldTickHandler.chunksToGenerate.put(dimension, chunks);
            }
        }
    }
}
