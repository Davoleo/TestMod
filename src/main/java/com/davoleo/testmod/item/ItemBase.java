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

    public ItemBase(String name){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public void registerItemModel()
    {
        TestMod.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ItemBase setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }


}
