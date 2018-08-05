package com.davoleo.testmod.recipe;

import com.davoleo.testmod.block.ModBlocks;
import com.davoleo.testmod.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 05/08/2018
 * Hour: 18.42
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModRecipes {

    public static void init()
    {
        GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 5F);
    }
}
