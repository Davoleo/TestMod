package com.davoleo.testmod.block.fload_creator;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 18:52
 * Class: BlockFloadCreator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockFloadCreator extends BlockTEBase {

    public static final DirectionProperty FACING_HRZN = DirectionProperty.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final ResourceLocation FLOAD_CREATOR = new ResourceLocation(TestMod.MODID, "fload_creator");

    public BlockFloadCreator()
    {
        super(Properties.create(Material.IRON));

        //testmod:fast_furnace
        setRegistryName(FLOAD_CREATOR);

        //TODO 1.13 port
        //setHarvestLevel("pickaxe", 1);

        setDefaultState(getStateContainer().getBaseState().with(FACING_HRZN, EnumFacing.NORTH));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return new TileFloadCreator();
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING_HRZN, context.getPlayer().getHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        builder.add(FACING_HRZN);
    }
}
