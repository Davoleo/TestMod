package com.davoleo.testmod.init;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockCopper;
import com.davoleo.testmod.block.BlockFload;
import com.davoleo.testmod.block.fload_creator.BlockFloadCreator;
import com.davoleo.testmod.block.fload_creator.TileFloadCreator;
import com.davoleo.testmod.block.furnace.BlockFastFurnace;
import com.davoleo.testmod.block.furnace.TileFastFurnace;
import com.davoleo.testmod.block.generator.BlockGenerator;
import com.davoleo.testmod.block.generator.TileGenerator;
import com.davoleo.testmod.block.tank.BlockTank;
import com.davoleo.testmod.block.tank.TileTank;
import com.davoleo.testmod.memory.BlockPuzzle;
import com.davoleo.testmod.memory.TilePuzzle;
import com.davoleo.testmod.superchest.BlockSuperChest;
import com.davoleo.testmod.superchest.BlockSuperChestPart;
import com.davoleo.testmod.superchest.TileSuperChest;
import com.davoleo.testmod.superchest.TileSuperChestPart;
import com.davoleo.testmod.world.BlockAngelOre;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
    public static BlockAngelOre angelOre = new BlockAngelOre();
    public static BlockGenerator blockGenerator = new BlockGenerator();
    public static BlockFloadCreator blockFloadCreator = new BlockFloadCreator();
    public static BlockPuzzle blockPuzzle = new BlockPuzzle();
    public static BlockFload blockFload = new BlockFload();
    public static BlockTank blockTank = new BlockTank();
    public static BlockSuperChest blockSuperChest = new BlockSuperChest();
    public static BlockSuperChestPart blockSuperChestPart = new BlockSuperChestPart();
    public static BlockCopper blockCopper = new BlockCopper();

    public static void registerBlocks(IForgeRegistry<Block> registry)
    {
        registry.register(blockFastFurnace);
        GameRegistry.registerTileEntity(TileFastFurnace.class, new ResourceLocation(TestMod.MODID, "fast_furnace"));

        registry.register(blockGenerator);
        GameRegistry.registerTileEntity(TileGenerator.class, new ResourceLocation(TestMod.MODID, "generator"));

        registry.register(blockFloadCreator);
        GameRegistry.registerTileEntity(TileFloadCreator.class, new ResourceLocation(TestMod.MODID, "fload_creator"));

        registry.register(blockPuzzle);
        GameRegistry.registerTileEntity(TilePuzzle.class, new ResourceLocation(TestMod.MODID, "puzzle"));

        registry.register(blockTank);
        GameRegistry.registerTileEntity(TileTank.class, new ResourceLocation(TestMod.MODID, "tank"));

        registry.register(blockSuperChest);
        GameRegistry.registerTileEntity(TileSuperChest.class, new ResourceLocation(TestMod.MODID, "superchest"));

        registry.register(blockSuperChestPart);
        GameRegistry.registerTileEntity(TileSuperChestPart.class, new ResourceLocation(TestMod.MODID, "superchest_part"));

        registry.register(angelOre);
        registry.register(blockFload);
        registry.register(blockCopper);
    }

    public static void initModels()
    {
        blockFastFurnace.initModel();
        blockGenerator.initModel();
        blockFloadCreator.initModel();
        blockPuzzle.initModel();
        angelOre.initModel();
        blockFload.initModel();
        blockTank.initModel();
        blockSuperChest.initModel();
        blockSuperChestPart.initModel();
        blockCopper.initModel();
    }

}
