/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 06/03/2020 / 15:49
 * Class: FancyBlock
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.block;

import io.github.davoleo.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;

public class BakedBlock extends Block {

    private final VoxelShape shape = VoxelShapes.create(0.2, 0.2, 0.2, 0.8, 0.8, 0.8);

    public BakedBlock() {
        super(Properties.create(Material.ANVIL)
                .sound(SoundType.ANVIL)
                .hardnessAndResistance(2.0F)
        );
        setRegistryName(TestMod.MODID, "fancy_block");
    }

    public Item createItemBlock() {
        return new BlockItem(this, new Item.Properties().group(TestMod.setup.testTab)).setRegistryName(this.getRegistryName());
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }
}
