package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 18.32
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

//Aggiunge una string in più rispetto a ItemBase per adattarlo all'API dell'Ore Dictionary
public class BlockOre extends BlockBase {

    private String oreName;

    //Preset di caratteristiche per gli ore | materiale | durezza | resistenza
    public BlockOre(String name, String oreName)
    {
        super(Material.ROCK, name);

        setHardness(3F);
        setResistance(5F);

        setCreativeTab(TestMod.creativeTab);

        this.oreName = oreName;
    }

    @Override
    public BlockOre setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public void initOreDict()
    {
        OreDictionary.registerOre(oreName, this);
    }

}
