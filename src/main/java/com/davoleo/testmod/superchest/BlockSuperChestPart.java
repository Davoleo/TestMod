package com.davoleo.testmod.superchest;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.init.ModItems;
import com.davoleo.testmod.util.MultiBlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.davoleo.testmod.superchest.BlockSuperChest.FORMED;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/03/2019 / 16:52
 * Class: BlockSuperChestPart
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockSuperChestPart extends Block {

    public static final ResourceLocation SUPERCHEST_PART = new ResourceLocation(TestMod.MODID, "superchest_part");

    public BlockSuperChestPart()
    {
        super(Properties
                .create(Material.IRON)
                .hardnessAndResistance(2F)
        );
        setRegistryName(SUPERCHEST_PART);
        //TODO 1.13 port
        //setHarvestLevel("axe", 1);
        setDefaultState(getStateContainer().getBaseState().with(FORMED, SuperChestPartIndex.UNFORMED));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return new TileSuperChest();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (player.getHeldItem(hand).getItem() == ModItems.angelIngot) {
            BlockSuperChest.toggleMultiblock(worldIn, pos, state, player);
            return true;
        }

        if (state.getBlock() == ModBlocks.blockSuperChestPart && state.get(FORMED) != SuperChestPartIndex.UNFORMED)
        {
            BlockPos controllerPos = BlockSuperChest.getControllerPos(worldIn, pos);
            if (controllerPos != null)
            {
                IBlockState controllerState = worldIn.getBlockState(controllerPos);
                return controllerState.getBlock().onBlockActivated(state, worldIn, pos, player, hand, side, hitX, hitY, hitZ);
            }
        }
        return false;
    }

    @Override
    public void onBlockHarvested(World worldIn, @Nonnull BlockPos pos, IBlockState state, @Nonnull EntityPlayer player)
    {
        if (!worldIn.isRemote)
            MultiBlockUtils.breakMultiblock(SuperChestMultiBlock.INSTANCE, worldIn, pos);
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        if (state.get(FORMED) == SuperChestPartIndex.UNFORMED)
            return super.isFullCube(state);
        else
            return false;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        builder.add(FORMED);
    }
}
