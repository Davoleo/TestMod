package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/06/2019 / 12:38
 * Class: ItemPANINAZZO
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemPANINAZZO extends ItemFood {

    public ItemPANINAZZO()
    {
        super(16, 6F,true);
        setRegistryName(TestMod.MODID, "paninazzo");
        setTranslationKey(TestMod.MODID + ".paninazzo");
        setCreativeTab(TestMod.testTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        assert getRegistryName() != null;
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
