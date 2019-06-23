package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.world.EnumOreType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/05/2019 / 18:37
 * Class: BlockOre
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockOre extends Block {

    public static final PropertyEnum<EnumOreType> ORETYPE = PropertyEnum.create("oretype", EnumOreType.class);

    private String name;
    private boolean[] dimensions;

    /**
     * Creates a new instance of a BlockOre
     * @param name the name of the block
     * @param dimensions what dimensions the ore is created for
     */
    public BlockOre(String name, boolean[] dimensions, int harvestLevel)
    {
        super(Material.ROCK);
        setTranslationKey(TestMod.MODID + "." + name);
        setRegistryName(new ResourceLocation(TestMod.MODID, name));
        setCreativeTab(TestMod.testTab);
        setHardness(3F);
        setResistance(5F);
        setHarvestLevel("pickaxe", harvestLevel);
        this.name = name;
        this.dimensions = dimensions;
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        for (int i = 0; i < dimensions.length; i++)
            if (dimensions[i])
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, new ModelResourceLocation(getRegistryName(), "oretype=" + EnumOreType.namebyIndex(i)));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (int i = 0; i < dimensions.length; i++)
            if (dimensions[i])
                items.add(new ItemStack(this, 1, i));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(ORETYPE).ordinal();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(ORETYPE).ordinal();
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(ORETYPE, EnumOreType.values()[meta]);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, ORETYPE);
    }

    public String getName()
    {
        return name;
    }
}