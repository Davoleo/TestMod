package com.davoleo.testmod.item;

import net.minecraft.creativetab.CreativeTabs;
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
    public static ItemBase ingotCopper = new ItemBase("ingot_copper").setCreativeTab(CreativeTabs.MATERIALS);

    //Registra gli oggetti nel registro di Forge
    public static void register(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                ingotCopper
        );
    }

    //Registra i modelli degli oggetti
    public static void registerModels()
    {
        ingotCopper.registerItemModel();
    }
}
