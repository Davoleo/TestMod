package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        super(TestMod.COPPER_TOOL_MATERIAL);
        setTranslationKey(TestMod.MODID + ".copper_hoe");
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_hoe"));
        setCreativeTab(TestMod.testTab);
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        assert getRegistryName() != null;
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
