package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import com.davoleo.testmod.util.Utils;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 16:06
 * Class: BlockPedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockPedestal extends BlockTEBase implements ITileEntityProvider {

    public static final ResourceLocation PEDESTAL = new ResourceLocation(TestMod.MODID, "pedestal");

    public BlockPedestal()
    {
        super(Material.ROCK);
        setRegistryName(PEDESTAL);
        setTranslationKey(TestMod.MODID + ".pedestal");
        setHardness(3F);
        setHarvestLevel("pickaxe", 1);
        setResistance(4F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            TileEntityPedestal tile = (TileEntityPedestal) worldIn.getTileEntity(pos);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
            if (!playerIn.isSneaking())
            {
                if (playerIn.getHeldItem(hand).isEmpty())
                {
                    playerIn.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
                } else
                {
                    playerIn.setHeldItem(hand, itemHandler.insertItem(0, playerIn.getHeldItem(hand), false));
                }
                tile.markDirty();
            } else
            {
                //GUI Pedestal
               //playerIn.openGui(TestMod.instance, ModGuiHandler.PEDESTAL, world, pos.getX(), pos.getY(), pos.getZ());

                //NO-GUI Pedestal
                ItemStack stack = itemHandler.getStackInSlot(0);
                if (!stack.isEmpty())
                {
                    String localized = Utils.localize(stack.getTranslationKey() + ".name");
                    playerIn.sendMessage(new TextComponentString(stack.getCount() + "x " + localized));
                } else
                {
                    playerIn.sendMessage(new TextComponentString("Il Pedestallo è vuoto"));
                }
            }
        }
        return true;
    }

    @Override
    public void initModel()
    {
        super.initModel();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new PedestalTESR());
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
    {
        return new TileEntityPedestal();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
