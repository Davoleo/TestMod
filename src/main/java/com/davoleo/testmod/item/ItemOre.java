package com.davoleo.testmod.item;

import net.minecraftforge.oredict.OreDictionary;

/*************************************************
 * Author: Davoleo
 * Date: 05/08/2018
 * Hour: 18.57
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

//Aggiunge una string in più rispetto a ItemBase per adattarlo all'API dell'Ore Dictionary
public class ItemOre extends ItemBase {

    private String oreName;

    public ItemOre(String name, String oreName)
    {
        super(name);

        this.oreName = oreName;
    }

    public void initOreDict()
    {
        OreDictionary.registerOre(oreName, this);
    }

}
