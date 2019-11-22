package io.github.davoleo.testmod.handler;

import io.github.davoleo.testmod.block.ModBlocks;
import io.github.davoleo.testmod.item.ModItems;
import io.github.davoleo.testmod.tileentity.GeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 01/11/2019 / 18:26
 * Class: RegistrationHandler
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistrationHandler {

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(ModBlocks.copperBlock);
        event.getRegistry().register(ModBlocks.generatorBlock);
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        //ItemBlocks
        event.getRegistry().register(ModBlocks.copperBlock.createItemBlock());
        event.getRegistry().register(ModBlocks.generatorBlock.createItemBlock());

        //Items
        event.getRegistry().register(ModItems.copperIngot);
    }

    @SubscribeEvent
    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder.create(GeneratorTileEntity::new, ModBlocks.generatorBlock).build(null).setRegistryName(ModBlocks.generatorBlock.getRegistryName()));
    }
}
