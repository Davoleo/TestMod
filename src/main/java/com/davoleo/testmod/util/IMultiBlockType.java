package com.davoleo.testmod.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/03/2019 / 17:20
 * Interface: IMultiBlockType
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public interface IMultiBlockType {

    /**
     * Return the anchor position for the given formed multiblock. This is the corner at the
     * bottom/lower/left position.
     * WARNING! Implementations of this function can not assume that the given block is
     * actually a valid part of the multiblock!
     * @return anchor position or null in case this is not a valid (formed) multiblock
     */
    @Nullable
    BlockPos getBottomLowerLeft(World world, BlockPos pos);

    /**
     * Return a given block in the world to its unformed state.
     * This function can assume the given position refers to a valid multiblock part
     */
    void unformBlock(World world, BlockPos pos);

    /**
     * Convert a given block in the world to its formed state for the given
     * relative position in the multiblock.
     * This function can assume the given position refers to a valid multiblock part
     */
    void formBlock(World world, BlockPos pos, int dx, int dy, int dz);

    /**
     * @param world the world where you check the TE
     * @param pos The pos of the checked TE
     * @return True if the given position is the bottom/lower/left position of an unformed multiblock. i.e. it is possible to form a multiblock here
     */
    boolean isValidUnformedMultiBlock(World world, BlockPos pos);

    /**
     * Return true if the given position is the bottom/lower/left position
     * of a formed multiblock
     */
    boolean isValidFormedMultiBlock(World world, BlockPos pos);

    /**
     * @return The dimension of this multiblock type on the X axis
     */
    int getWidth();

    /**
     * @return The dimension of this multiblock type on the Y axis
     */
    int getHeight();

    /**
      * @return The dimension of this multiblock type on the Z axis
     */
    int getDepth();

}
