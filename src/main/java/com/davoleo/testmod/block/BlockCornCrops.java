package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/06/2019 / 12:28
 * Class: BlockCornCrops
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockCornCrops extends BlockCrops {

    public BlockCornCrops()
    {
        setRegistryName(TestMod.MODID, "corn_crops");
        setTranslationKey(TestMod.MODID + ".corn_crops");
        setCreativeTab(TestMod.testTab);
    }

    @Nonnull
    @Override
    protected Item getCrop()
    {
        return ModItems.corn;
    }

    @Nonnull
    @Override
    protected Item getSeed()
    {
        return ModItems.cornSeeds;
    }
}
