package com.davoleo.testmod.proxy;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.47
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ClientProxy extends CommonProxy {

    //Implementato poiché client side
    //Registrazione dei rendering degli oggetti
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item,meta, new ModelResourceLocation(TestMod.MODID + ":" + id + "inventory"));
    }
}
