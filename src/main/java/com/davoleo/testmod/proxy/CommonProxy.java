package com.davoleo.testmod.proxy;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.47
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class CommonProxy {

    public String localize(String unlocalized, Object... args)
    {
        return I18n.format(unlocalized, args);
    }

    //Vuoto perché dal lato server
    public void registerItemRenderer(Item item, int meta, String id)
    {}

    public void registerRenderers()
    {}

}
