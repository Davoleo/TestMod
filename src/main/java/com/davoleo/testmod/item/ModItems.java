package com.davoleo.testmod.item;

import com.davoleo.testmod.item.tool.*;
import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 23.05
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModItems {

    //Istanza dell'oggetto
    public static ItemOre ingotCopper = new ItemOre("ingot_copper","ingotCopper");
    public static ItemCornSeeds cornSeeds = new ItemCornSeeds();
    public static ItemCorn corn = new ItemCorn();
    public static ItemPaninazzo paninazzo = new ItemPaninazzo();

    //Istanza Tools
    public static ItemSword copperSword = new ItemSword(TestMod.copperToolMaterial, "copper_sword");
    public static ItemPickaxe copperPickaxe = new ItemPickaxe(TestMod.copperToolMaterial, "copper_pickaxe");
    public static ItemAxe copperAxe = new ItemAxe(TestMod.copperToolMaterial, "copper_axe");
    public static ItemShovel copperShovel = new ItemShovel(TestMod.copperToolMaterial,  "copper_shovel");
    public static ItemHoe copperHoe = new ItemHoe(TestMod.copperToolMaterial, "copper_hoe");


    //Registra gli oggetti nel registro di Forge
    public static void register(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                ingotCopper,
                cornSeeds,
                corn,
                paninazzo,
                copperSword,
                copperPickaxe,
                copperAxe,
                copperShovel,
                copperHoe
        );
    }

    //Registra i modelli degli oggetti
    public static void registerModels()
    {
        ingotCopper.registerItemModel();
        cornSeeds.registerItemModel(cornSeeds);
        corn.registerItemModel(corn);
        paninazzo.registerItemModel();

        // Modelli tools
        copperSword.registerItemModel(copperSword);
        copperPickaxe.registerItemModel(copperPickaxe);
        copperAxe.registerItemModel(copperAxe);
        copperShovel.registerItemModel(copperShovel);
        copperHoe.registerItemModel(copperHoe);
    }
}
