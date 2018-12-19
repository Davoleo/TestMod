package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date: 07/08/2018
 * Hour: 17.03
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemSword extends net.minecraft.item.ItemSword {

    private String name;

    public ItemSword (ToolMaterial material, String name)
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
    public ItemSword setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(TestMod.creativeTab);
        return this;
    }

}
