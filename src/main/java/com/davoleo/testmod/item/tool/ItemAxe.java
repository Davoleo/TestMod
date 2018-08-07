package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date: 07/08/2018
 * Hour: 17.20
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemAxe extends net.minecraft.item.ItemAxe {

    private String name;

    public ItemAxe (ToolMaterial material, String name)
    {
        super(material, 8F, -3.1F);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.name = name;
    }

    public void registerItemModel(Item item)
    {
        TestMod.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ItemAxe setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(TestMod.creativeTab);
        return this;
    }

}
