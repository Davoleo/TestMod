package com.davoleo.testmod.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

/*************************************************
 * Author: Davoleo
 * Date: 05/08/2018
 * Hour: 19.52
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockPedestal extends BlockBase{

    public BlockPedestal()
    {
        super(Material.ROCK, "pedestal");
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}
