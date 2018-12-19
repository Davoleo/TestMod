package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date: 07/08/2018
 * Hour: 17.52
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemHoe extends net.minecraft.item.ItemHoe {

    private String name;

    public ItemHoe(ToolMaterial material, String name)
    {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        this.name = name;
    }

    public void registerItemModel(Item item)
    {
        TestMod.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ItemHoe setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(TestMod.creativeTab);
        return this;
    }
}
