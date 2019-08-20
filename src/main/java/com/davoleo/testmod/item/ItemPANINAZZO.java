package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/06/2019 / 12:38
 * Class: ItemPANINAZZO
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemPANINAZZO extends ItemFood {

    public ItemPANINAZZO()
    {
        super(16, 6F,true, new Item.Properties().group(TestMod.testTab));
        setRegistryName(TestMod.MODID, "paninazzo");
    }

}
