package com.davoleo.testmod.recipe;

import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.init.ModItems;
import net.minecraftforge.oredict.OreDictionary;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 02/05/2019 / 19:21
 * Class: OreDictHandler
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class OreDictHandler {

    public static void initOreDictEntries()
    {
        //Blocks
        OreDictionary.registerOre("oreAngel", ModBlocks.oreAngel);
        OreDictionary.registerOre("oreCopper", ModBlocks.oreCopper);
        OreDictionary.registerOre("oreAluminum", ModBlocks.oreAluminum);
        OreDictionary.registerOre("oreGold", ModBlocks.oreNetherGold);
        OreDictionary.registerOre("oreZephyrite", ModBlocks.oreZephyrite);

        //Ingots
        OreDictionary.registerOre("ingotCopper", ModItems.copperIngot);
        OreDictionary.registerOre("blockCopper", ModBlocks.blockCopper);
    }

}
