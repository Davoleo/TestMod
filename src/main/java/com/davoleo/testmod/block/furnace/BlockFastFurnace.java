package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockBase;
import com.davoleo.testmod.util.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/12/2018 / 22:25
 * Class: BlockFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockFastFurnace extends BlockBase implements IHasModel {

     public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockFastFurnace()
    {
        super(Material.IRON, "fast_furnace");

        setHarvestLevel("pickaxe", 1);
        setCreativeTab(TestMod.creativeTab);

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public void registerModels()
    {
        TestMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }

    @Nonnull
    @Override
    public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getIndex();
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7));
    }
}
