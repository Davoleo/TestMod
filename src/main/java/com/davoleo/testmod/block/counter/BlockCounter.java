package com.davoleo.testmod.block.counter;

import com.davoleo.testmod.block.BlockTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date: 08/08/2018
 * Hour: 23.44
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockCounter extends BlockTileEntity<TileEntityCounter> {

    public BlockCounter()
    {
        super(Material.ROCK, "counter");
    }

    //Definisce l'azione al tasto destro
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            TileEntityCounter tile = getTileEntity(world, pos);
            if (side == EnumFacing.DOWN)
            {
                if (player.isSneaking())
                {
                    tile.decrementCountEx();
                } else
                tile.decrementCount();
            }
            else if (side == EnumFacing.UP)
            {
                if (player.isSneaking())
                {
                    tile.incrementCountEx();
                } else
                tile.incrementCount();
            }
            player.sendMessage(new TextComponentString("NUMERO BELLO: " + tile.getCount()));

        }
        return true;
    }

    @Override
    public Class<TileEntityCounter> getTileEntityClass()
    {
        return TileEntityCounter.class;
    }

    @Nullable
    @Override
    public TileEntityCounter createTileEntity(World world, IBlockState state)
    {
        return new TileEntityCounter();
    }

}
