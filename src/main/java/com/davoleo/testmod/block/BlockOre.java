package com.davoleo.testmod.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 18.32
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockOre extends BlockBase {

    //Preset di caratteristiche per gli ore | materiale | durezza | resistenza
    public BlockOre(String name)
    {
        super(Material.ROCK, name);

        setHardness(3F);
        setResistance(5F);
    }

    //Inserimento semi-automatico nelle Creative tab degli ore
    @Override
    public BlockOre setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

}
