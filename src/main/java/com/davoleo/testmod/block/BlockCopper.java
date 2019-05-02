package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 02/05/2019 / 19:19
 * Class: BlockCopper
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockCopper extends Block {

    public BlockCopper()
    {
        super(Material.IRON);
        setTranslationKey(TestMod.MODID + ".copper_block");
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_block"));
        setCreativeTab(TestMod.testTab);
        setHardness(4F);
        setHarvestLevel("pickaxe", 1);
        setSoundType(SoundType.METAL);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        assert getRegistryName() != null;
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
