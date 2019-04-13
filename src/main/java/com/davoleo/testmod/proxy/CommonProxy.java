package com.davoleo.testmod.proxy;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.generator.DamageTracker;
import com.davoleo.testmod.init.*;
import com.davoleo.testmod.network.Messages;
import com.davoleo.testmod.omega.OmegaTickHandler;
import com.davoleo.testmod.omega.player.PlayerOmega;
import com.davoleo.testmod.omega.player.PlayerPropertyEvents;
import com.davoleo.testmod.world.OreGenerator;
import com.davoleo.testmod.world.WorldTickHandler;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

import static com.davoleo.testmod.TestMod.MODID;

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
        MinecraftForge.EVENT_BUS.register(DamageTracker.instance);
        MinecraftForge.EVENT_BUS.register(OmegaTickHandler.instance);
        MinecraftForge.EVENT_BUS.register(PlayerPropertyEvents.instance);

        CapabilityManager.INSTANCE.register(PlayerOmega.class, new Capability.IStorage<PlayerOmega>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<PlayerOmega> capability, PlayerOmega playerOmega, EnumFacing enumFacing)
            {
                throw new UnsupportedOperationException();
            }

            @Override
            public void readNBT(Capability<PlayerOmega> capability, PlayerOmega playerOmega, EnumFacing enumFacing, NBTBase nbtBase)
            {
                throw new UnsupportedOperationException();
            }
        }, () -> null);

        ModEntities.init();
        ModFluids.init();
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

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(MODID))
            ConfigManager.sync(MODID, Config.Type.INSTANCE);
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