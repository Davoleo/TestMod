package com.davoleo.testmod.block;

import com.davoleo.testmod.util.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

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

    @Nonnull
    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return super.getTranslationKey() + "_" + ((IMetaName)this.block).getSpecialName(stack);
    }
}
