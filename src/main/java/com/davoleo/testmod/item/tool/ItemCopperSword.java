package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 16/06/2019 / 12:09
 * Class: ItemCopperSword
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemCopperSword extends ItemSword {

    public ItemCopperSword()
    {
        super(ToolMaterials.COPPER, 0, 1F, new Item.Properties().group(TestMod.testTab));
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_sword"));
    }
}
