package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 21.15
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemCornSeeds extends ItemSeeds {

    public ItemCornSeeds()
    {
        super(ModBlocks.cropCorn, Blocks.FARMLAND);
        setUnlocalizedName("corn_seed");
        setRegistryName("corn_seed");
    }

    public void registerItemModel(Item item)
    {
        TestMod.proxy.registerItemRenderer(item, 0, "corn_seed");
    }

}
