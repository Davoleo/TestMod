package com.davoleo.testmod.superchest;

import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.util.IMultiBlockType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/03/2019 / 17:23
 * Class: SuperChestMultiBlock
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class SuperChestMultiBlock implements IMultiBlockType {

    public static SuperChestMultiBlock INSTANCE = new SuperChestMultiBlock();

    private boolean isBlockPart(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() == ModBlocks.blockSuperChest || state.getBlock() == ModBlocks.blockSuperChestPart;
    }

    private boolean isValidFormedBlockPart(World world, BlockPos pos, int dx, int dy, int dz) {
        BlockPos p = pos;
        if (isFormedSuperchestController(world, p)) {
            SuperChestPartIndex index = world.getBlockState(p).get(BlockSuperChest.FORMED);
            return index == SuperChestPartIndex.getIndex(dx, dy, dz);
        } else if (isFormedSuperchestPart(world, p)) {
            SuperChestPartIndex index = world.getBlockState(p).get(BlockSuperChest.FORMED);
            return index == SuperChestPartIndex.getIndex(dx, dy, dz);
        } else {
            // We can already stop here
            return false;
        }
    }

    private boolean isValidUnformedBlockPart(World world, BlockPos pos, int dx, int dy, int dz) {
        if (isUnformedSuperchestController(world, pos)) {
            return true;
        } else if (isUnformedSuperchestPart(world, pos)) {
            // We can already stop here
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public BlockPos getBottomLowerLeft(World world, BlockPos pos) {
        if (isBlockPart(world, pos)) {
            IBlockState state = world.getBlockState(pos);
            SuperChestPartIndex index = state.get(BlockSuperChest.FORMED);
            return pos.add(-index.getDx(), -index.getDy(), -index.getDz());
        } else {
            return null;
        }
    }

    @Override
    public void unformBlock(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.with(BlockSuperChest.FORMED, SuperChestPartIndex.UNFORMED), 3);
    }

    @Override
    public void formBlock(World world, BlockPos pos, int dx, int dy, int dz) {
        IBlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.with(BlockSuperChest.FORMED, SuperChestPartIndex.getIndex(dx, dy, dz)), 3);
    }

    @Override
    public boolean isValidUnformedMultiBlock(World world, BlockPos pos) {
        int cntSuper = 0;
        for (int dx = 0 ; dx < getWidth() ; dx++) {
            for (int dy = 0 ; dy < getHeight() ; dy++) {
                for (int dz = 0 ; dz < getDepth() ; dz++) {
                    BlockPos p = pos.add(dx, dy, dz);
                    if (!isValidUnformedBlockPart(world, p, dx, dy, dz)) {
                        return false;
                    }
                    if (world.getBlockState(p).getBlock() == ModBlocks.blockSuperChest) {
                        cntSuper++;
                    }
                }
            }
        }
        return cntSuper == 1;
    }

    @Override
    public boolean isValidFormedMultiBlock(World world, BlockPos pos) {
        int cntSuper = 0;
        for (int dx = 0; dx < getWidth(); dx++) {
            for (int dy = 0; dy < getHeight(); dy++) {
                for (int dz = 0; dz < getDepth(); dz++) {
                    BlockPos p = pos.add(dx, dy, dz);
                    if (!isValidFormedBlockPart(world, p, dx, dy, dz)) {
                        return false;
                    }
                    if (world.getBlockState(p).getBlock() == ModBlocks.blockSuperChest) {
                        cntSuper++;
                    }
                }
            }
        }
        return cntSuper == 1;
    }

    @Override
    public int getWidth() {
        return 2;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public int getDepth() {
        return 2;
    }


    private static boolean isUnformedSuperchestController(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() == ModBlocks.blockSuperChest && state.get(BlockSuperChest.FORMED) == SuperChestPartIndex.UNFORMED;
    }

    public static boolean isFormedSuperchestController(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() == ModBlocks.blockSuperChest && state.get(BlockSuperChest.FORMED) != SuperChestPartIndex.UNFORMED;
    }

    private static boolean isUnformedSuperchestPart(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() == ModBlocks.blockSuperChestPart && state.get(BlockSuperChest.FORMED) == SuperChestPartIndex.UNFORMED;
    }

    private static boolean isFormedSuperchestPart(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock() == ModBlocks.blockSuperChestPart && state.get(BlockSuperChest.FORMED) != SuperChestPartIndex.UNFORMED;
    }


}
