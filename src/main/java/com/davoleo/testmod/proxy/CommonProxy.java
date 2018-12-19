package com.davoleo.testmod.proxy;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.47
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event)
    {

    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

    public String localize(String unlocalized, Object... args)
    {
        return I18n.format(unlocalized, args);
    }

    //Vuoto perché dal lato server
    public void registerItemRenderer(Item item, int meta, String id)
    {}

    public void registerVariantRenderer(Item item, int meta, String fileName, String id)
    {}

    public void registerRenderers()
    {}

}
