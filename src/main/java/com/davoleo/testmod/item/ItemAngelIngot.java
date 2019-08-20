package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 18:35
 * Class: ItemAngelIngot
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemAngelIngot extends Item {

    public ItemAngelIngot()
    {
        super(new Item.Properties().group(TestMod.testTab));
        setRegistryName(new ResourceLocation(TestMod.MODID, "angel_ingot"));
    }

}
