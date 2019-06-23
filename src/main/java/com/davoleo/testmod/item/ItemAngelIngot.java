package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 18:35
 * Class: ItemAngelIngot
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemAngelIngot extends Item {

    public ItemAngelIngot()
    {
        setTranslationKey(TestMod.MODID + ".angel_ingot");
        setRegistryName(new ResourceLocation(TestMod.MODID, "angel_ingot"));
        setCreativeTab(TestMod.testTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        assert getRegistryName() != null;
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
