package com.davoleo.testmod.proxy;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.GuiHandler;
import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.init.ModItems;
import com.davoleo.testmod.network.Messages;
import com.davoleo.testmod.world.OreGenerator;
import com.davoleo.testmod.world.WorldTickHandler;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.47
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        Messages.registerMessages("testmod");
        GameRegistry.registerWorldGenerator(OreGenerator.instance, 5);
        MinecraftForge.EVENT_BUS.register(OreGenerator.instance);
        MinecraftForge.EVENT_BUS.register(WorldTickHandler.instance);
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(TestMod.instance, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
        GameRegistry.addSmelting(ModBlocks.angelOre, new ItemStack(ModItems.angelIngot, 1), 1F);
        OreDictionary.registerOre("oreAngel", ModBlocks.angelOre);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.registerItems(event.getRegistry());
    }

    @Nullable
    public IAnimationStateMachine load(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters)
    {
        return null;
    }

    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
    {
        throw new IllegalStateException("This should only be called Client-Side");
    }

    public EntityPlayer getClientPlayer()
    {
        throw new IllegalStateException("This should only be called Client-Side");
    }
}