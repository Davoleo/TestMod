package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;

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
        super(Properties.create(Material.GRASS));
        setRegistryName(TestMod.MODID, "corn_crops");
        //TODO 1.13 port
        //setCreativeTab(TestMod.testTab);
    }

    @Nonnull
    @Override
    protected IItemProvider getCropsItem()
    {
        return ModItems.corn;
    }

    @Nonnull
    @Override
    protected IItemProvider getSeedsItem()
    {
        return ModItems.cornSeeds;
    }
}
