package com.davoleo.testmod.superchest;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.util.MultiBlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

public class BlockSuperChestPart extends Block implements ITileEntityProvider {

    public static final ResourceLocation SUPERCHEST_PART = new ResourceLocation(TestMod.MODID, "superchest_part");

    public BlockSuperChestPart()
    {
        super(Material.IRON);
        setRegistryName(SUPERCHEST_PART);
        setTranslationKey(TestMod.MODID + ".superchest_part");
        setHarvestLevel("axe", 1);
        setHardness(2F);

        setDefaultState(blockState.getBaseState().withProperty(FORMED, SuperChestPartIndex.UNFORMED));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World world, int i)
    {
        return new TileSuperChestPart();
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (playerIn.getHeldItem(hand).getItem() == Items.STICK) {
            BlockSuperChest.toggleMultiblock(worldIn, pos, state, playerIn);
            return true;
        }

        if (state.getBlock() == ModBlocks.blockSuperChestPart && state.getValue(FORMED) != SuperChestPartIndex.UNFORMED)
        {
            BlockPos controllerPos = BlockSuperChest.getControllerPos(worldIn, pos);
            if (controllerPos != null)
            {
                IBlockState controllerState = worldIn.getBlockState(controllerPos);
                return controllerState.getBlock().onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
            }
        }
        return false;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (!worldIn.isRemote)
            MultiBlockUtils.breakMultiblock(SuperChestMultiBlock.INSTANCE, worldIn, pos);
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        if (state.getValue(FORMED) == SuperChestPartIndex.UNFORMED)
            return super.isFullCube(state);
        else
            return false;
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FORMED);
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FORMED, SuperChestPartIndex.VALUES[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FORMED).ordinal();
    }
}
