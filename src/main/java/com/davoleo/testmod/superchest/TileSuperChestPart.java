package com.davoleo.testmod.superchest;

import com.davoleo.testmod.init.ModBlocks;
import io.netty.util.internal.PendingWrite;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

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

    public TileSuperChestPart() {
        super(ModBlocks.TYPE_SUPERCHEST_PART);
    }

    // TODO: 20/08/2019 1.13 port
//    @Override
//    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newSate)
//    {
//        return oldState.getBlock() != newSate.getBlock();
//    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == ModBlocks.blockSuperChest && state.get(BlockSuperChest.FORMED) != SuperChestPartIndex.UNFORMED) {
            BlockPos controllerPos = BlockSuperChest.getControllerPos(world, pos);
            if (controllerPos != null) {
                TileEntity te = world.getTileEntity(pos);
                if (te instanceof TileSuperChest)
                    return te.getCapability(cap);
            }
        }
        return super.getCapability(cap);
    }

}
