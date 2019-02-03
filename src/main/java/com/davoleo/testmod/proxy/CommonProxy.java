package com.davoleo.testmod.proxy;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.furnace.BlockFastFurnace;
import com.davoleo.testmod.block.furnace.TileFastFurnace;
import com.davoleo.testmod.init.GuiHandler;
import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.network.Messages;
import com.davoleo.testmod.world.BlockAngelOre;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(TestMod.instance, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(ModBlocks.blockFastFurnace);
        GameRegistry.registerTileEntity(TileFastFurnace.class, new ResourceLocation(TestMod.MODID, "_fast_furnace"));
        event.getRegistry().register(ModBlocks.angelOre);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.blockFastFurnace).setRegistryName(BlockFastFurnace.FAST_FURNACE));
        event.getRegistry().register(
                new ItemBlock(ModBlocks.angelOre)
                {
                    @Override
                    public int getMetadata(int damage)
                    {
                        return damage;
                    }
                }
                .setHasSubtypes(true)
                .setRegistryName(BlockAngelOre.ANGEL_ORE)
        );
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