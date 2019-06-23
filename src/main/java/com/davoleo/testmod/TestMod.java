package com.davoleo.testmod;

import com.davoleo.testmod.block.generator.DamageTracker;
import com.davoleo.testmod.proxy.CommonProxy;
import com.davoleo.testmod.util.TestTab;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.34
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Mod(modid = TestMod.MODID, name = TestMod.MODNAME, version = TestMod.MODVERSION, dependencies = "required-after:forge@[11.16.0.1865,)", useMetadata = true)
public class TestMod {

    public static final String MODID = "testmod";
    public static final String MODNAME = "Test Mod";
    public static final String MODVERSION= "2.0.0";

    @SidedProxy(clientSide = "com.davoleo.testmod.proxy.ClientProxy", serverSide = "com.davoleo.testmod.proxy.ServerProxy")
    public static CommonProxy proxy;

    static
    {
        FluidRegistry.enableUniversalBucket();
    }

    public static TestTab testTab = new TestTab();

    public static final Item.ToolMaterial COPPER_TOOL_MATERIAL = EnumHelper.addToolMaterial("COPPER",  2,
            500, 6,2, 14);
    public static final ItemArmor.ArmorMaterial COPPER_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("COPPER", TestMod.MODID + ":copper",
            15, new int[]{2,5,6,2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON , 0.0F);

    @Mod.Instance
    public static TestMod instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent e)
    {
        DamageTracker.instance.reset();
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent e)
    {
        DamageTracker.instance.reset();
    }
}