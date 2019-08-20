package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 16/06/2019 / 11:49
 * Class: ItemCopperAxe
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemCopperAxe extends ItemAxe {

    public ItemCopperAxe()
    {
        super(ToolMaterials.COPPER, 2F, -3.1F, new Item.Properties().group(TestMod.testTab));
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_axe"));
    }
}
