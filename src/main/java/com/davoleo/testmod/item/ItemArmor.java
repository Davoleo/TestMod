package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/06/2019 / 13:48
 * Class: ItemArmor
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemArmor extends net.minecraft.item.ItemArmor {

    public ItemArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn, String name)
    {
        super(materialIn, 0, equipmentSlotIn);
        setRegistryName(TestMod.MODID, name);
        setTranslationKey(TestMod.MODID + "." + name);
        setCreativeTab(TestMod.testTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        assert getRegistryName() != null;
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
