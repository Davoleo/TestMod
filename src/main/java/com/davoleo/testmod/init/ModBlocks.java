package com.davoleo.testmod.init;

import com.davoleo.testmod.block.furnace.BlockFastFurnace;
import com.davoleo.testmod.world.BlockAngelOre;

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

    public static void initModels()
    {
        blockFastFurnace.initModel();
        angelOre.initModel();
    }

}
