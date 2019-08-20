package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/06/2019 / 12:27
 * Class: ItemCornSeeds
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemCornSeeds extends ItemSeeds {

    public ItemCornSeeds()
    {
        super(ModBlocks.cornCrops, new Item.Properties().group(TestMod.testTab));
        setRegistryName(TestMod.MODID, "corn_seeds");
    }
}
