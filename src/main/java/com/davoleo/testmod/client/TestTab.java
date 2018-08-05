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

    public TestTab()
    {
        super(TestMod.MODID);
        setBackgroundImageName("tab_testmod.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.ingotCopper);
    }

    @Override
    public boolean hasSearchBar()
    {
        return false;
    }

}
