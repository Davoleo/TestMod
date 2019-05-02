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
        OreDictionary.registerOre("ingotCopper", ModItems.copperIngot);
        OreDictionary.registerOre("blockCopper", ModBlocks.blockCopper);
    }

}
