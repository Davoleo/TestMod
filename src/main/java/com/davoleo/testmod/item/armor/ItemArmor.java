package com.davoleo.testmod.item.armor;

import com.davoleo.testmod.TestMod;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/06/2019 / 13:48
 * Class: ItemArmor
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemArmor extends net.minecraft.item.ItemArmor {

    public ItemArmor(EntityEquipmentSlot equipmentSlotIn, String name)
    {
        super(ArmorMaterials.COPPER, equipmentSlotIn, new Item.Properties().group(TestMod.testTab));
        setRegistryName(TestMod.MODID, name);
    }
}
