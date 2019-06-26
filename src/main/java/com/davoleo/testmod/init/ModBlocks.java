package com.davoleo.testmod.init;

import com.davoleo.testmod.block.BlockCopper;
import com.davoleo.testmod.block.BlockCornCrops;
import com.davoleo.testmod.block.BlockFload;
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
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
    public static BlockFload blockFload = new BlockFload();
    public static BlockTank blockTank = new BlockTank();
    public static BlockSuperChest blockSuperChest = new BlockSuperChest();
    public static BlockSuperChestPart blockSuperChestPart = new BlockSuperChestPart();
    public static BlockCopper blockCopper = new BlockCopper();
    public static BlockPedestal blockPedestal = new BlockPedestal();
    public static BlockCounter blockCounter = new BlockCounter();
    public static BlockCornCrops cornCrops = new BlockCornCrops();

    //Ores
    public static BlockOre oreAngel = new BlockOre("angel_ore", new boolean[]{true, true, true}, 2);
    public static BlockOre oreCopper = new BlockOre("copper_ore", new boolean[]{true, false, false}, 1);
    public static BlockOre oreAluminum = new BlockOre("aluminum_ore", new boolean[]{true, false, false}, 1);
    public static BlockOre oreNetherGold = new BlockOre("nether_gold_ore", new boolean[]{false, true, false}, 2);
    public static BlockOre oreZephyrite = new BlockOre("zephyrite_ore", new boolean[]{false, false, true}, 3);


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

        registry.register(blockFload);
        registry.register(blockCopper);

        //Ores
        registry.registerAll(oreAngel, oreCopper, oreAluminum, oreNetherGold, oreZephyrite, cornCrops);
    }

    public static void registerTileEntities(IForgeRegistry<TileEntityType<?>> registry)
    {
        registry.registerAll(
                TileEntityType.Builder.create(TileFastFurnace::new).build(null).setRegistryName(BlockFastFurnace.FAST_FURNACE),
                TileEntityType.Builder.create(TileFloadCreator::new).build(null).setRegistryName(BlockFloadCreator.FLOAD_CREATOR),
                TileEntityType.Builder.create(TilePuzzle::new).build(null).setRegistryName(BlockPuzzle.PUZZLE),
                TileEntityType.Builder.create(TileTank::new).build(null).setRegistryName(BlockTank.TANK),
                TileEntityType.Builder.create(TileSuperChest::new).build(null).setRegistryName(BlockSuperChest.SUPERCHEST),
                TileEntityType.Builder.create(TileSuperChestPart::new).build(null).setRegistryName(BlockSuperChestPart.SUPERCHEST_PART),
                TileEntityType.Builder.create(TileEntityPedestal::new).build(null).setRegistryName(BlockCounter.COUNTER),
                TileEntityType.Builder.create(TileEntityCounter::new).build(null).setRegistryName(BlockPedestal.PEDESTAL)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public static void initModels()
    {
//        blockFastFurnace.initModel();
//        blockGenerator.initModel();
//        blockFloadCreator.initModel();
//        blockPuzzle.initModel();
//        blockFload.initModel();
//        blockTank.initModel();
//        blockSuperChest.initModel();
//        blockSuperChestPart.initModel();
//        blockCopper.initModel();
//        oreAngel.initModel();
//        oreCopper.initModel();
//        oreAluminum.initModel();
//        oreNetherGold.initModel();
//        oreZephyrite.initModel();
//        blockPedestal.initModel();
    }

}
