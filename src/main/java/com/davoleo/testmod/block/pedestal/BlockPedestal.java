package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.ModGuiHandler;
import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date: 05/08/2018
 * Hour: 19.52
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockPedestal extends BlockTileEntity<TileEntityPedestal> {

    public BlockPedestal()
    {
        super(Material.ROCK, "pedestal");
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {


        if (!world.isRemote)
            {
                TileEntityPedestal tile = getTileEntity(world, pos);
                IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
                if (!player.isSneaking())
                {
                    if (player.getHeldItem(hand).isEmpty())
                    {
                        player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
                    } else
                    {
                        player.setHeldItem(hand, itemHandler.insertItem(0, player.getHeldItem(hand), false));
                    }
                    tile.markDirty();
                } else
                {

                    player.openGui(TestMod.instance, ModGuiHandler.PEDESTAL, world, pos.getX(), pos.getY(), pos.getZ());

                    //Old PEDESTAL
                    /*ItemStack stack = itemHandler.getStackInSlot(0);
                    if (!stack.isEmpty())
                    {
                        String localized = TestMod.proxy.localize(stack.getUnlocalizedName() + ".name");
                        player.sendMessage(new TextComponentString(stack.getCount() + "x " + localized));
                    } else
                    {
                        player.sendMessage(new TextComponentString("Il Pedestallo è vuoto"));
                    }*/
                }
            }
            return true;
        }

        public void breakBlock (World world, BlockPos pos, IBlockState state)
        {
            TileEntityPedestal tile = getTileEntity(world, pos);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
            ItemStack stack = itemHandler.getStackInSlot(0);
            if(!stack.isEmpty()){
                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
                world.spawnEntity(item);
            }
            super.breakBlock(world, pos, state);
        }


        @Override
    public Class<TileEntityPedestal> getTileEntityClass()
    {
        return TileEntityPedestal.class;
    }

    @Nullable
    @Override
    public TileEntityPedestal createTileEntity(World world, IBlockState state)
    {
        return new TileEntityPedestal();
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}
