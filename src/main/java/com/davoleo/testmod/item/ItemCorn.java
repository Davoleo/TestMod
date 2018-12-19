package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;

/*************************************************
 * Author: Davoleo
 * Date: 06/08/2018
 * Hour: 22.11
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemCorn extends ItemFood {

    public ItemCorn()
    {
        super(3, 0.6F, false);
        setTranslationKey("corn");
        setRegistryName("corn");
        setCreativeTab(TestMod.creativeTab);

    }

    public void registerItemModel(Item item)
    {
        TestMod.proxy.registerItemRenderer(this,0, "corn");
    }

    public void initOreDict()
    {
        OreDictionary.registerOre("cropCorn", this);
    }

}
