package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.ItemFood;

/*************************************************
 * Author: Davoleo
 * Date: 06/08/2018
 * Hour: 22.25
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemPaninazzo extends ItemFood {

    public ItemPaninazzo()
    {

        super(16, 6F,true);
        setTranslationKey("paninazzo");
        setRegistryName("paninazzo");
        setCreativeTab(TestMod.creativeTab);

    }

    public void registerItemModel()
    {
        TestMod.proxy.registerItemRenderer(this, 0, "paninazzo");
    }

}
