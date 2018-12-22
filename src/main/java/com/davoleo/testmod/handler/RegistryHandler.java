package com.davoleo.testmod.handler;

import com.davoleo.testmod.block.ModBlocks;
import com.davoleo.testmod.item.ModItems;
import com.davoleo.testmod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*************************************************
 * Author: Leonardo
 * Date / Hour: 10/11/2018 / 16.06
 * Class: RegistryHandler
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

//Manages the Forge Registry [Registers Block | Items | Models]
@Mod.EventBusSubscriber
public class RegistryHandler {

    //Items
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    //Blocks
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }

    //Models
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ModItems.ITEMS)
            if(item instanceof IHasModel)
                ((IHasModel) item).registerModels();

        for (Block block : ModBlocks.BLOCKS)
            if(block instanceof IHasModel)
                ((IHasModel) block).registerModels();
    }

}
