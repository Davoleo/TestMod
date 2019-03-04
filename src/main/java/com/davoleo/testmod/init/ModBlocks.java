package com.davoleo.testmod.init;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockFload;
import com.davoleo.testmod.block.furnace.BlockFastFurnace;
import com.davoleo.testmod.block.furnace.TileFastFurnace;
import com.davoleo.testmod.block.generator.BlockGenerator;
import com.davoleo.testmod.block.generator.TileGenerator;
import com.davoleo.testmod.block.tank.BlockTank;
import com.davoleo.testmod.block.tank.TileTank;
import com.davoleo.testmod.memory.BlockPuzzle;
import com.davoleo.testmod.memory.TilePuzzle;
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
    public static BlockPuzzle blockPuzzle = new BlockPuzzle();
    public static BlockFload blockFload = new BlockFload();
    public static BlockTank blockTank = new BlockTank();

    public static void registerBlocks(IForgeRegistry<Block> registry)
    {
        registry.register(blockFastFurnace);
        GameRegistry.registerTileEntity(TileFastFurnace.class, new ResourceLocation(TestMod.MODID, "fast_furnace"));

        registry.register(blockGenerator);
        GameRegistry.registerTileEntity(TileGenerator.class, new ResourceLocation(TestMod.MODID, "generator"));

        registry.register(blockPuzzle);
        GameRegistry.registerTileEntity(TilePuzzle.class, new ResourceLocation(TestMod.MODID, "puzzle"));

        registry.register(blockTank);
        GameRegistry.registerTileEntity(TileTank.class, new ResourceLocation(TestMod.MODID, "tank"));

        registry.register(angelOre);
        registry.register(blockFload);
    }

    public static void initModels()
    {
        blockFastFurnace.initModel();
        blockGenerator.initModel();
        blockPuzzle.initModel();
        angelOre.initModel();
        blockFload.initModel();
        blockTank.initModel();
    }

}
