package io.github.davoleo.testmod.handler;

import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.block.ModBlocks;
import io.github.davoleo.testmod.container.GeneratorContainer;
import io.github.davoleo.testmod.item.ModItems;
import io.github.davoleo.testmod.tileentity.GeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
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
        event.getRegistry().register(ModBlocks.COPPER_BLOCK);
        event.getRegistry().register(ModBlocks.GENERATOR_BLOCK);
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        //ItemBlocks
        event.getRegistry().register(ModBlocks.COPPER_BLOCK.createItemBlock());
        event.getRegistry().register(ModBlocks.GENERATOR_BLOCK.createItemBlock());

        //Items
        event.getRegistry().register(ModItems.copperIngot);
    }

    @SubscribeEvent
    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder.create(GeneratorTileEntity::new, ModBlocks.GENERATOR_BLOCK).build(null).setRegistryName(ModBlocks.GENERATOR_BLOCK.getRegistryName()));
    }

    //Registers Client-side Containers
    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {

        event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            return new GeneratorContainer(windowId, TestMod.proxy.getClientWorld(), pos, inv);
        }).setRegistryName(ModBlocks.GENERATOR_BLOCK.getRegistryName()));

    }
}
