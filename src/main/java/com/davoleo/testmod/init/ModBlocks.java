package com.davoleo.testmod.init;

import com.davoleo.testmod.block.BlockCopper;
import com.davoleo.testmod.block.BlockCornCrops;
import com.davoleo.testmod.block.BlockOre;
import com.davoleo.testmod.block.counter.BlockCounter;
import com.davoleo.testmod.block.counter.TileEntityCounter;
import com.davoleo.testmod.block.fload_creator.BlockFloadCreator;
import com.davoleo.testmod.block.fload_creator.TileFloadCreator;
import com.davoleo.testmod.block.furnace.BlockFastFurnace;
import com.davoleo.testmod.block.furnace.TileFastFurnace;
import com.davoleo.testmod.block.generator.BlockGenerator;
import com.davoleo.testmod.block.pedestal.BlockPedestal;
import com.davoleo.testmod.block.pedestal.TileEntityPedestal;
import com.davoleo.testmod.block.tank.BlockTank;
import com.davoleo.testmod.block.tank.TileTank;
import com.davoleo.testmod.memory.BlockPuzzle;
import com.davoleo.testmod.memory.TilePuzzle;
import com.davoleo.testmod.superchest.BlockSuperChest;
import com.davoleo.testmod.superchest.BlockSuperChestPart;
import com.davoleo.testmod.superchest.TileSuperChest;
import com.davoleo.testmod.superchest.TileSuperChestPart;
import com.davoleo.testmod.world.EnumOreType;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.IForgeRegistry;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/01/2019 / 16:38
 * Class: ModBlocks
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModBlocks {

    public static BlockFastFurnace blockFastFurnace = new BlockFastFurnace();
    public static BlockGenerator blockGenerator = new BlockGenerator();
    public static BlockFloadCreator blockFloadCreator = new BlockFloadCreator();
    public static BlockPuzzle blockPuzzle = new BlockPuzzle();
    //public static BlockFload blockFload = new BlockFload();
    public static BlockTank blockTank = new BlockTank();
    public static BlockSuperChest blockSuperChest = new BlockSuperChest();
    public static BlockSuperChestPart blockSuperChestPart = new BlockSuperChestPart();
    public static BlockCopper blockCopper = new BlockCopper();
    public static BlockPedestal blockPedestal = new BlockPedestal();
    public static BlockCounter blockCounter = new BlockCounter();
    public static BlockCornCrops cornCrops = new BlockCornCrops();

    //Ores
    public static BlockOre oreAngel = new BlockOre("angel_ore", EnumOreType.ORE_OVERWORLD);

    public static BlockOre oreCopper = new BlockOre("copper_ore", EnumOreType.ORE_OVERWORLD);
    public static BlockOre oreAluminum = new BlockOre("aluminum_ore", EnumOreType.ORE_OVERWORLD);
    public static BlockOre oreNetherGold = new BlockOre("nether_gold_ore", EnumOreType.ORE_NETHER);
    public static BlockOre oreZephyrite = new BlockOre("zephyrite_ore", EnumOreType.ORE_END);

//    Types
    public static TileEntityType<?> TYPE_FLOAD_CREATOR;
    public static TileEntityType<?> TYPE_COUNTER;
    public static TileEntityType<?> TYPE_FAST_FURNACE;
    public static TileEntityType<?> TYPE_GENERATOR;
    public static TileEntityType<?> TYPE_PEDESTAL;
    public static TileEntityType<?> TYPE_TANK;
    public static TileEntityType<?> TYPE_PUZZLE;
    public static TileEntityType<?> TYPE_SUPERCHEST;
    public static TileEntityType<?> TYPE_SUPERCHEST_PART;

    public static void registerBlocks(IForgeRegistry<Block> registry)
    {
        //TileEntities
        registry.register(blockFastFurnace);
        registry.register(blockGenerator);
        registry.register(blockFloadCreator);
        registry.register(blockPuzzle);
        registry.register(blockTank);
        registry.register(blockSuperChest);
        registry.register(blockSuperChestPart);
        registry.register(blockPedestal);
        registry.register(blockCounter);

        //registry.register(blockFload);
        registry.register(blockCopper);

        //Ores
        registry.registerAll(oreAngel, oreCopper, oreAluminum, oreNetherGold, oreZephyrite, cornCrops);
    }

    public static void registerTileEntities(IForgeRegistry<TileEntityType<?>> registry)
    {
        registry.registerAll(
                TYPE_FAST_FURNACE = TileEntityType.Builder.create(TileFastFurnace::new).build(null).setRegistryName(BlockFastFurnace.FAST_FURNACE),
                TYPE_FLOAD_CREATOR = TileEntityType.Builder.create(TileFloadCreator::new).build(null).setRegistryName(BlockFloadCreator.FLOAD_CREATOR),
                TYPE_PUZZLE = TileEntityType.Builder.create(TilePuzzle::new).build(null).setRegistryName(BlockPuzzle.PUZZLE),
                TYPE_TANK = TileEntityType.Builder.create(TileTank::new).build(null).setRegistryName(BlockTank.TANK),
                TYPE_SUPERCHEST = TileEntityType.Builder.create(TileSuperChest::new).build(null).setRegistryName(BlockSuperChest.SUPERCHEST),
                TYPE_SUPERCHEST_PART = TileEntityType.Builder.create(TileSuperChestPart::new).build(null).setRegistryName(BlockSuperChestPart.SUPERCHEST_PART),
                TYPE_COUNTER = TileEntityType.Builder.create(TileEntityPedestal::new).build(null).setRegistryName(BlockCounter.COUNTER),
                TYPE_PEDESTAL = TileEntityType.Builder.create(TileEntityCounter::new).build(null).setRegistryName(BlockPedestal.PEDESTAL)
        );
    }
}
