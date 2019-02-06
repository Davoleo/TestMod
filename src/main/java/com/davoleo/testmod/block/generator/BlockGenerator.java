package com.davoleo.testmod.block.generator;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 17:37
 * Class: BlockGenerator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockGenerator extends BlockTEBase implements ITileEntityProvider {

    public static final PropertyDirection FACING_HORIZONTAL = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final ResourceLocation GENERATOR = new ResourceLocation(TestMod.MODID, "generator");

    public BlockGenerator()
    {
        super(Material.IRON);
        setRegistryName(GENERATOR);
        setTranslationKey(TestMod.MODID + ".generator");
        setHarvestLevel("pickaxe", 1);

        setDefaultState(blockState.getBaseState().withProperty(FACING_HORIZONTAL, EnumFacing.NORTH));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null)
        {
            int energy = compound.getInteger("energy");
            addInformationLocalized(tooltip, "tooltip.testmod.generator", energy);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
    {
        return new TileGenerator();
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING_HORIZONTAL, placer.getHorizontalFacing().getOpposite());
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING_HORIZONTAL);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING_HORIZONTAL, EnumFacing.byIndex((meta & 3) + 2));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING_HORIZONTAL).getIndex() - 2;
    }
}
