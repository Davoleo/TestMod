package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockOre;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/05/2019 / 21:59
 * Class: ItemBlockOre
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemBlockOre extends ItemBlock {

    public ItemBlockOre(Block block)
    {
        super(block, new ItemBlock.Properties().group(TestMod.testTab));
        setRegistryName(TestMod.MODID, ((BlockOre) block).getName());
    }
}
