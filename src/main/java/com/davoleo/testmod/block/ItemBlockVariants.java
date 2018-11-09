package com.davoleo.testmod.block;

import com.davoleo.testmod.util.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Leonardo
 * Date: 09/11/2018
 * Hour: 23.18
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemBlockVariants extends ItemBlock {

    public ItemBlockVariants(Block block)
    {
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "_" + ((IMetaName)this.block).getSpecialName(stack);
    }
}
