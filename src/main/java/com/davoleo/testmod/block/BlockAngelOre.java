package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.world.OreType;
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
 * Date / Hour: 03/02/2019 / 16:08
 * Class: BlockAngelOre
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

//TODO : Generalize and merge with BlockOre
public class BlockAngelOre extends Block {

    public static final PropertyEnum<OreType> ORETYPE = PropertyEnum.create("oretype", OreType.class);
    public static final ResourceLocation ANGEL_ORE = new ResourceLocation(TestMod.MODID, "angel_ore");

    public BlockAngelOre()
    {
        super(Material.ROCK);
        setHardness(3F);
        setResistance(5F);
        setHarvestLevel("pickaxe", 2);
        setTranslationKey(TestMod.MODID + ".angel_ore");
        setRegistryName(ANGEL_ORE);
        setCreativeTab(TestMod.testTab);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(ORETYPE).ordinal();
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "oretype=overworld"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 1, new ModelResourceLocation(getRegistryName(), "oretype=nether"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 2, new ModelResourceLocation(getRegistryName(), "oretype=end"));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
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
        return getDefaultState().withProperty(ORETYPE, OreType.values()[meta]);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, ORETYPE);
    }
}
