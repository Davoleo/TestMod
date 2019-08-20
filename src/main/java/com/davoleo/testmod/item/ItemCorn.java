package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/06/2019 / 12:24
 * Class: ItemCorn
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemCorn extends ItemFood {

    public ItemCorn()
    {
        super(3, 0.6F, false, new Item.Properties().group(TestMod.testTab));
        setRegistryName(TestMod.MODID, "corn");
    }

}
