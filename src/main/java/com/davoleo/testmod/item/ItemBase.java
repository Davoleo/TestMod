package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.50
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemBase extends Item {

    protected String name;

    //Automatizza la definizione del nome Unlocalized e Registry per tutti gli oggetti creati
    public ItemBase(String name){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    //Automatizza la registrazione del modello per tutti gli oggetti creati
    public void registerItemModel()
    {
        TestMod.proxy.registerItemRenderer(this, 0, name);
    }

    //Semi-Automatizza l'inserimento in una Creative tab dell'oggetto creato
    @Override
    public ItemBase setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }


}
