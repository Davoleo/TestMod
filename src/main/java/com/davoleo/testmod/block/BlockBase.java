package com.davoleo.testmod.block;


import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.item.ModItems;
import com.davoleo.testmod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.oredict.OreDictionary;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 17.40
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockBase extends Block implements IHasModel {

    protected String name;
    protected String oreName;

    public BlockBase(Material material, String name)
    {
        this(material, name, null);
    }

    //Automatizzazione di creazione di blocchi per il nome e il materiale
    public BlockBase(Material material, String name, String oreName)
    {
        super(material);

        this.name = name;
        this.oreName = oreName;

        setTranslationKey(name);
        setRegistryName(name);

        setCreativeTab(TestMod.creativeTab);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public void initOreDict()
    {
        if(oreName != null)
            OreDictionary.registerOre(oreName, this);
    }

    @Override
    public void registerModels()
    {
        TestMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    //Registrazione dei modelli dei blocchi in versione Item
    @Deprecated
    public void registerItemModel(Item itemBlock)
    {
        TestMod.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    //Creazione automatica dell'oggetto di classe Itemblock
    @Deprecated
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
