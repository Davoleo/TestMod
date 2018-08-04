package com.davoleo.testmod;

import com.davoleo.testmod.item.ModItems;
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

    //Definisce il nome l'ID e la versione della mod
    public static final String MODID = "testmod";
    public static final String MODNAME = "Test Mod";
    public static final String VERSION = "1.0.0";

    //Crea un istanza per la mod
    @Mod.Instance(MODID)
    public static TestMod instance;

    //Definisce quale proxy sta da quale parte
    @SidedProxy(serverSide = "com.davoleo.testmod.proxy.CommonProxy", clientSide = "com.davoleo.testmod.proxy.ClientProxy")
    public static CommonProxy proxy;

    //I vari step di inizializzazione della mod
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


    //Registra gli item e i block partendo dalle classi generali ModBlocks e ModItems
    @Mod.EventBusSubscriber
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
            ModItems.register(event.getRegistry());
            ModItems.registerModels();
        }
    }
}