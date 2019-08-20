package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 16/06/2019 / 12:06
 * Class: ItemCopperPickaxe
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemCopperPickaxe extends ItemPickaxe {

    public ItemCopperPickaxe()
    {
        super(ToolMaterials.COPPER, -3, -1F, new Item.Properties().group(TestMod.testTab));
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_pickaxe"));
    }
}
