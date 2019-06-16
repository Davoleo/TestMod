package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        super(TestMod.COPPER_TOOL_MATERIAL);
        setTranslationKey(TestMod.MODID + ".copper_pickaxe");
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_pickaxe"));
        setCreativeTab(TestMod.testTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        assert getRegistryName() != null;
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
