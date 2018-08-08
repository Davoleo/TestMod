package com.davoleo.testmod.block;

import com.davoleo.testmod.block.counter.BlockCounter;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 18.38
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModBlocks {

    public static BlockOre oreCopper = new BlockOre("ore_copper", "oreCopper");
    public static BlockOre blockCopper = new BlockOre("block_copper", "blockCopper");
    public static BlockCropCorn cropCorn = new BlockCropCorn();
    public static BlockPedestal pedestal = new BlockPedestal();
    public static BlockCounter counter = new BlockCounter();

    //Registra i blocchi in versione piazzata
    public static void register(IForgeRegistry<Block> registry)
    {
        registry.registerAll(
                oreCopper,
                blockCopper,
                cropCorn,
                pedestal,
                counter
        );
    }

    //Registra i blocchi in versione Item
    public static void registerItemBlocks(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                oreCopper.createItemBlock(),
                blockCopper.createItemBlock(),
                pedestal.createItemBlock(),
                counter.createItemBlock()
        );

    }

    //Registra i modelli dei blocchi
    public static void registerModels()
    {
        oreCopper.registerItemModel(Item.getItemFromBlock(oreCopper));
        blockCopper.registerItemModel(Item.getItemFromBlock(blockCopper));
        pedestal.registerItemModel(Item.getItemFromBlock(pedestal));
        counter.registerItemModel(Item.getItemFromBlock(counter));
    }

}
