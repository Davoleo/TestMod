package com.davoleo.testmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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

    public static BlockOre oreCopper = new BlockOre("ore_copper").setCreativeTab(CreativeTabs.MATERIALS);
    public static BlockBase blockCopper = new BlockBase(Material.IRON ,"block_copper").setCreativeTab(CreativeTabs.MATERIALS);

    //Registra i blocchi in versione piazzata
    public static void register(IForgeRegistry<Block> registry)
    {
        registry.registerAll(
                oreCopper,
                blockCopper
        );
    }

    //Registra i blocchi in versione Item
    public static void registerItemBlocks(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                oreCopper.createItemBlock(),
                blockCopper.createItemBlock()
        );

    }

    //Registra i modelli dei blocchi
    public static void registerModels()
    {
        oreCopper.registerItemModel(Item.getItemFromBlock(oreCopper));
        blockCopper.registerItemModel(Item.getItemFromBlock(blockCopper));
    }

}
