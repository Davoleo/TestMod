package com.davoleo.testmod;

import com.davoleo.testmod.client.TestTab;
import com.davoleo.testmod.network.PacketRequestUpdatePedestal;
import com.davoleo.testmod.network.PacketUpdatePedestal;
import com.davoleo.testmod.proxy.CommonProxy;
import com.davoleo.testmod.recipe.ModRecipes;
import com.davoleo.testmod.util.Reference;
import com.davoleo.testmod.util.handlers.ModGuiHandler;
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

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.34
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class TestMod {

    //TODO : Make block and items constant instead of static variables

    public static final TestTab creativeTab = new TestTab();

    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6,2, 14);
    public static final ItemArmor.ArmorMaterial copperArmorMaterial = EnumHelper.addArmorMaterial("COPPER", Reference.MODID + ":copper", 15, new int[]{2,5,6,2},9, SoundEvents.ITEM_ARMOR_EQUIP_IRON , 0.0F);

    public static SimpleNetworkWrapper network;

    //Mod instance
    @Mod.Instance(Reference.MODID)
    public static TestMod instance;

    //Proxy declaration
    @SidedProxy(serverSide = Reference.COMMON, clientSide = Reference.CLIENT)
    public static CommonProxy proxy;

    //Mod Initialization steps
    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        System.out.println(Reference.NAME + " is loading!");
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);

        //Networking
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
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