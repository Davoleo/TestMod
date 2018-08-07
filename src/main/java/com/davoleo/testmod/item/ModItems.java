package com.davoleo.testmod.item;


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

    //Registra gli oggetti nel registro di Forge
    public static void register(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                ingotCopper,
                cornSeeds,
                corn,
                paninazzo
        );
    }

    //Registra i modelli degli oggetti
    public static void registerModels()
    {
        ingotCopper.registerItemModel();
        cornSeeds.registerItemModel(cornSeeds);
        corn.registerItemModel(corn);
        paninazzo.registerItemModel();
    }
}
