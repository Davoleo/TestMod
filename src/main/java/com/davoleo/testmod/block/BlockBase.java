package com.davoleo.testmod.block;


import com.davoleo.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 17.40
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockBase extends Block {

    protected String name;

    //Automatizzazione di creazione di blocchi per il nome e il materiale
    public BlockBase(Material material, String name)
    {
        super(material);

        this.name = name;

        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(TestMod.creativeTab);
    }

    //Registrazione dei modelli dei blocchi in versione Item
    public void registerItemModel(Item itemBlock)
    {
        TestMod.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    //Creazione automatica dell'oggetto di classe Itemblock
    public Item createItemBlock()
    {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
