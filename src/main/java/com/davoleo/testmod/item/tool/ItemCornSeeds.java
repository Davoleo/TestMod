package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/06/2019 / 12:27
 * Class: ItemCornSeeds
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemCornSeeds extends ItemSeeds {

    public ItemCornSeeds()
    {
        super(ModBlocks.cornCrops, Blocks.FARMLAND);
        setRegistryName(TestMod.MODID, "corn_seeds");
        setTranslationKey(TestMod.MODID + ".corn_seeds");
        setCreativeTab(TestMod.testTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        assert getRegistryName() != null;
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
