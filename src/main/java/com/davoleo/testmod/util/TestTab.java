package com.davoleo.testmod.util;

import com.davoleo.testmod.init.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/01/2019 / 16:36
 * Class: TestTab
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TestTab extends ItemGroup {

    public TestTab()
    {
        super("testmod");
    }

    @Nonnull
    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ModBlocks.blockFastFurnace);
    }

}
