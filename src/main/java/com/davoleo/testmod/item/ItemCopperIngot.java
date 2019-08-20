package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 02/05/2019 / 19:00
 * Class: ItemCopperIngot
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemCopperIngot extends Item {

    public ItemCopperIngot()
    {
        super(new Item.Properties().group(TestMod.testTab));
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_ingot"));
    }
}
