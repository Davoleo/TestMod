package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.util.handler.EnumHandler;
import com.davoleo.testmod.util.interfaces.IMetaName;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 18.32
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

//Aggiunge una string in più rispetto a ItemBase per adattarlo all'API dell'Ore Dictionary
public class BlockOre extends BlockBase implements IMetaName {

    public static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class);

    private String oreName;
    private String dimension;

    public BlockOre(String name, String oreName)
    {
        this(name, oreName, "overworld");
    }

    //Preset di caratteristiche per gli ore | materiale | durezza | resistenza
    public BlockOre(String name, String oreName, String dimension)
    {
        super(Material.ROCK, name);

        setHardness(3F);
        setResistance(5F);

        setCreativeTab(TestMod.creativeTab);

        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.COPPER));

        this.oreName = oreName;
        this.dimension = dimension;
    }

    @Override
    public BlockOre setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return (state.getValue(VARIANT)).getMeta();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(VARIANT)).getMeta();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata(meta));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(EnumHandler.EnumType variant : EnumHandler.EnumType.values())
        {
            items.add(new ItemStack(this, 1, variant.getMeta()));
        }
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
    }

    public void initOreDict()
    {
        OreDictionary.registerOre(oreName, this);
    }

    @Override
    public void registerItemModel(Item itemBlock)
    {
        for(int i = 0; i < EnumHandler.EnumType.values().length; i++)
        {
            TestMod.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, this.dimension + "_" + EnumHandler.EnumType.values()[i] + "_ore", "inventory");
        }
    }
}
