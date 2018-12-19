package com.davoleo.testmod.client;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 21.49
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TestTab extends CreativeTabs {

    //Costruisce una creative tab utilizzando il MODID e un immagine per la GUI
    public TestTab()
    {
        super(TestMod.MODID);
        setBackgroundImageName("tab_testmod.png");
    }

    //Inserisce un oggetto come icona della Creative Tab
    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.ingotCopper);
    }

    //Decide se la Creative Tab avrà o no una barra di ricerca
    @Override
    public boolean hasSearchBar()
    {
        return false;
    }

}
