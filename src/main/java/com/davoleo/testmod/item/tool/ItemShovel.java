package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

/*************************************************
 * Author: Davoleo
 * Date: 07/08/2018
 * Hour: 17.32
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemShovel extends ItemSpade {

    private String name;

    public ItemShovel(ToolMaterial material, String name)
    {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        this.name = name;
    }

    public void registerItemModel (Item item)
    {
        TestMod.proxy.registerItemRenderer(this,0, name);
    }

    @Override
    public ItemShovel setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(TestMod.creativeTab);
        return this;
    }

}
