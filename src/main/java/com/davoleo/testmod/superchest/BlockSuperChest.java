package com.davoleo.testmod.superchest;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.init.ModItems;
import com.davoleo.testmod.util.MultiBlockUtils;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 12/03/2019 / 23:31
 * Class: BlockSuperChest
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockSuperChest extends BlockTEBase implements ITileEntityProvider {

    public static final PropertyEnum<SuperChestPartIndex> FORMED = PropertyEnum.create("formed", SuperChestPartIndex.class);

    public static final ResourceLocation SUPERCHEST = new ResourceLocation(TestMod.MODID, "superchest");

    public BlockSuperChest()
    {
        super(Material.IRON);
        setRegistryName(SUPERCHEST);
        setTranslationKey(TestMod.MODID + ".superchest");
        setHarvestLevel("axe", 1);
        setHardness(2F);

        setDefaultState(blockState.getBaseState().withProperty(FORMED, SuperChestPartIndex.UNFORMED));
    }

    //checks is the block at pos is a formed multiblock controller
    public static boolean isFormedController(World world, BlockPos pos)
    {
        IBlockState blockState = world.getBlockState(pos);
        return blockState.getBlock() == ModBlocks.blockSuperChest && blockState.getValue(FORMED) != SuperChestPartIndex.UNFORMED;
    }

    public static void toggleMultiblock(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            SuperChestPartIndex formed = state.getValue(FORMED);
            if (formed == SuperChestPartIndex.UNFORMED)
            {
                if (MultiBlockUtils.formMultiblock(SuperChestMultiBlock.INSTANCE, world, pos))
                    player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Super-Chest successfully assembled!"), false);
                else
                    player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "There was an issue while forming the Super-Chest"), false);
            }
            else
            {
                if (!MultiBlockUtils.breakMultiblock(SuperChestMultiBlock.INSTANCE, world, pos))
                    player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "The Super Chest is invalid"), false);
            }
        }
    }

    @Nullable
    public static BlockPos getControllerPos(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == ModBlocks.blockSuperChest && state.getValue(BlockSuperChest.FORMED) != SuperChestPartIndex.UNFORMED)
            return pos;

        if (state.getBlock() == ModBlocks.blockSuperChestPart && state.getValue(BlockSuperChest.FORMED) != SuperChestPartIndex.UNFORMED)
        {
            SuperChestPartIndex index = state.getValue(BlockSuperChest.FORMED);
            // This index indicates where in the superblock this part is located. From this we can find the location of the bottom-left coordinate
            BlockPos bottomLeft = pos.add(-index.getDx(), -index.getDy(), -index.getDz());

            for (SuperChestPartIndex idx : SuperChestPartIndex.VALUES)
            {
                if (idx != SuperChestPartIndex.UNFORMED)
                {
                    BlockPos p = bottomLeft.add(idx.getDx(), idx.getDy(), idx.getDz());
                    if (isFormedController(world, p))
                        return p;
                }
            }

        }
        return null;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
    {
        return new TileSuperChest();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
            if (playerIn.getHeldItem(hand).getItem() == ModItems.angelIngot) {
                toggleMultiblock(worldIn, pos, state, playerIn);
                return true;
            }

            if (state.getBlock() == ModBlocks.blockSuperChest && state.getValue(FORMED) != SuperChestPartIndex.UNFORMED)
                return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
            else
                return false;
    }

    @Override
    public void harvestBlock(@Nonnull World world, EntityPlayer player, @Nonnull BlockPos pos, @Nonnull IBlockState state, TileEntity te, ItemStack stack) {
        if (!world.isRemote)
            MultiBlockUtils.breakMultiblock(SuperChestMultiBlock.INSTANCE, world, pos);
        super.harvestBlock(world, player, pos, state, te, stack);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FORMED);
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FORMED, SuperChestPartIndex.VALUES[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(FORMED).ordinal());
    }
}
