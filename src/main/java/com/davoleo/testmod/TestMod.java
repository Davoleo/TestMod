package com.davoleo.testmod;

import com.davoleo.testmod.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.34
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Mod(modid = TestMod.MODID, name = TestMod.MODNAME, version = TestMod.VERSION)
public class TestMod {

    public static final String MODID = "testmod";
    public static final String MODNAME = "Test Mod";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MODID)
    public static TestMod instance;

    @SidedProxy(serverSide = "com.davoleo.testmod.proxy.CommonProxy", clientSide = "com.davoleo.testmod.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        System.out.println(MODNAME + "IS LOADING!");
    }

    @Mod.EventHandler
    public void Init (FMLInitializationEvent event)
    { }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {}
}

@Mod.EventBusSubscriber
public static class RegistrationHandler
{
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {

    }
}