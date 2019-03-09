package com.davoleo.testmod.block.fload_creator;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 18:52
 * Class: BlockFloadCreator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockFloadCreator extends BlockTEBase implements ITileEntityProvider {

    public static final PropertyDirection FACING_HRZN = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final ResourceLocation FLOAD_CREATOR = new ResourceLocation(TestMod.MODID, "fload_creator");

    public BlockFloadCreator()
    {
        super(Material.IRON);

        //testmod:fast_furnace
        setRegistryName(FLOAD_CREATOR);
        setTranslationKey(TestMod.MODID + ".fload_creator");

        setHarvestLevel("pickaxe", 1);

        setDefaultState(blockState.getBaseState().withProperty(FACING_HRZN, EnumFacing.NORTH));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
    {
        return new TileFloadCreator();
    }

    @Nonnull
    @Override
    public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING_HRZN, placer.getHorizontalFacing().getOpposite());
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING_HRZN);
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING_HRZN, EnumFacing.byIndex((meta & 3) + 2));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING_HRZN).getIndex() - 2;
    }
}
