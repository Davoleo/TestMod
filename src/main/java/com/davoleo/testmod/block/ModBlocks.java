package com.davoleo.testmod.block;

import com.davoleo.testmod.block.counter.BlockCounter;
import com.davoleo.testmod.block.pedestal.BlockPedestal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 18.38
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();

    public static BlockBase oreCopper = new BlockBase(Material.ROCK,"copper_ore", "oreCopper");
    public static BlockBase oreAluminum = new BlockBase(Material.ROCK,"aluminum_ore");
    public static BlockBase oreGold = new BlockBase(Material.ROCK,"nether_gold_ore", "oreGold");
    public static BlockBase oreZephyrite = new BlockBase(Material.ROCK,"end_zephyrite_ore", "oreZephyrite");
    public static BlockBase blockCopper = new BlockBase(Material.ROCK,"copper_block", "blockCopper");

    public static BlockPedestal pedestal = new BlockPedestal();
    public static BlockCounter counter = new BlockCounter();

    public static BlockBase fast_furnace = new BlockBase(Material.IRON, "fast_furnace");

    //Registra i blocchi in versione piazzata
    public static void register(IForgeRegistry<Block> registry)
    {
        registry.registerAll(
                oreCopper,
                oreAluminum,
                oreGold,
                oreZephyrite,
                blockCopper,
                pedestal,
                counter,
                fast_furnace
        );
    }

}
