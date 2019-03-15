package com.davoleo.testmod.superchest;

import com.davoleo.testmod.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/03/2019 / 17:01
 * Class: TileSuperChestPart
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TileSuperChestPart extends TileEntity {

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newSate)
    {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != ModBlocks.blockSuperChest || state.getValue(BlockSuperChest.FORMED) == SuperChestPartIndex.UNFORMED)
            return false;

        BlockPos controllerPos = BlockSuperChest.getControllerPos(world, pos);
        if (controllerPos != null)
        {
            TileEntity te = world.getTileEntity(controllerPos);
            if (te instanceof TileSuperChest)
                return te.hasCapability(capability, facing);
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != ModBlocks.blockSuperChest || state.getValue(BlockSuperChest.FORMED) == SuperChestPartIndex.UNFORMED)
            return null;

        BlockPos controllerPos = BlockSuperChest.getControllerPos(world, pos);
        if (controllerPos != null)
        {
            TileEntity te = world.getTileEntity(controllerPos);
            if (te instanceof TileSuperChest)
                return te.getCapability(capability, facing);
        }
        return super.getCapability(capability, facing);
    }
}
