package com.davoleo.testmod;

import com.davoleo.testmod.client.TestTab;
import com.davoleo.testmod.handler.ModGuiHandler;
import com.davoleo.testmod.network.PacketRequestUpdatePedestal;
import com.davoleo.testmod.network.PacketUpdatePedestal;
import com.davoleo.testmod.proxy.CommonProxy;
import com.davoleo.testmod.recipe.ModRecipes;
import com.davoleo.testmod.world.ModWorldGen;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

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
    public static final String VERSION = "0.0.1";

    public static final TestTab creativeTab = new TestTab();

    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6,2, 14);
    public static final ItemArmor.ArmorMaterial copperArmorMaterial = EnumHelper.addArmorMaterial("COPPER", TestMod.MODID + ":copper", 15, new int[]{2,5,6,2},9, SoundEvents.ITEM_ARMOR_EQUIP_IRON , 0.0F);

    public static SimpleNetworkWrapper network;

    //Mod instance
    @Mod.Instance(TestMod.MODID)
    public static TestMod instance;

    public static Logger logger;

    //Definisce quale proxy sta da quale parte
    @SidedProxy(serverSide = "com.davoleo.testmod.proxy.CommonProxy", clientSide = "com.davoleo.testmod.proxy.ClientProxy")
    public static CommonProxy proxy;

    //Mod Initialization steps
    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        System.out.println(MODNAME + " is loading!");
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);

        //Networking
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
        network = NetworkRegistry.INSTANCE.newSimpleChannel(TestMod.MODID);
        network.registerMessage(new PacketUpdatePedestal.Handler(), PacketUpdatePedestal.class, 0, Side.CLIENT);
        network.registerMessage(new PacketRequestUpdatePedestal.Handler(), PacketRequestUpdatePedestal.class, 1, Side.SERVER);

        //TESR
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event)
    {
        ModRecipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {}

}