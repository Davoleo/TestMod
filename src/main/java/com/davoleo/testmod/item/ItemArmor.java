package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date: 08/08/2018
 * Hour: 00.07
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemArmor extends net.minecraft.item.ItemArmor {

    private String name;

    public ItemArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name)
    {
        super(material, 0, slot);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(TestMod.creativeTab);
        this.name = name;
    }

    public void registerItemModel(Item item)
    {
        TestMod.proxy.registerItemRenderer(this, 0,name);
    }

}
