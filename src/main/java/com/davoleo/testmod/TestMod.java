package com.davoleo.testmod;

import com.davoleo.testmod.block.ModBlocks;
import com.davoleo.testmod.client.TestTab;
import com.davoleo.testmod.item.ModItems;
import com.davoleo.testmod.proxy.CommonProxy;
import com.davoleo.testmod.recipe.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
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

    public static final TestTab creativeTab = new TestTab();

    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6,2, 14);
    public static final ItemArmor.ArmorMaterial copperArmorMaterial = EnumHelper.addArmorMaterial("COPPER", MODID + ":copper", 15, new int[]{2,5,6,2},9, SoundEvents.ITEM_ARMOR_EQUIP_IRON , 0.0F);


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
    public void init (FMLInitializationEvent event)
    {
        ModRecipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {}


    //Registra gli item e i block partendo dalle classi generali ModBlocks e ModItems
    @Mod.EventBusSubscriber
    public static class RegistrationHandler
    {
        //Items
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        //Blocks
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event)
        {
            ModBlocks.register(event.getRegistry());
        }

        //Models
        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event)
        {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }
    }
}