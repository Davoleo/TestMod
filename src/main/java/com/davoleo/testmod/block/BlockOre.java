package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/05/2019 / 18:37
 * Class: BlockOre
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockOre extends Block {

    private DimensionType oreType;

    public BlockOre(String name, DimensionType oreType)
    {
        super(Material.ROCK);
        setTranslationKey(TestMod.MODID + "." + name);
        setRegistryName(new ResourceLocation(TestMod.MODID, name));
        setCreativeTab(TestMod.testTab);
        setHardness(3F);
        this.oreType = oreType;
    }

    public DimensionType getType()
    {
        return oreType;
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        assert getRegistryName() != null;
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
