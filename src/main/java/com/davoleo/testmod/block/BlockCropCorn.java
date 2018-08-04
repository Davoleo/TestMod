package com.davoleo.testmod.block;

import com.davoleo.testmod.item.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date: 04/08/2018
 * Hour: 21.02
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockCropCorn extends BlockCrops {

    public BlockCropCorn()
    {
        setUnlocalizedName("crop_corn");
        setRegistryName("crop_corn");
    }

    @Override
    protected Item getSeed()
    {
        return ModItems.cornSeeds;
    }

    @Override
    protected Item getCrop()
    {
        return ModItems.corn;
    }
}
