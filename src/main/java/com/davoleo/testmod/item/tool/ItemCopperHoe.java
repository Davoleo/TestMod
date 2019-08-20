package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 16/06/2019 / 11:49
 * Class: ItemCopperHoe
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemCopperHoe extends ItemHoe {

    public ItemCopperHoe()
    {
        super(ToolMaterials.COPPER, 2F, new Item.Properties().group(TestMod.testTab));
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_hoe"));
    }
}
