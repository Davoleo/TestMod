package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.GuiHandler;
import com.davoleo.testmod.util.IGuiTileEntity;
import com.davoleo.testmod.util.IRestorableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 17:15
 * Class: BlockBase
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockBase extends Block {

    public BlockBase(Material materialIn)
    {
        super(materialIn);
        setCreativeTab(TestMod.testTab);
    }

    private static final Pattern COMPILE = Pattern.compile("@", Pattern.LITERAL);

    protected void addInformationLocalized(List<String> tooltip, String key, Object... parameters)
    {
        String translated = I18n.format(key, parameters);
        translated = COMPILE.matcher(translated).replaceAll("\u00a7");
        Collections.addAll(tooltip, StringUtils.split(translated, "<NL>"));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        //Se client side - non si fa niente
        if(worldIn.isRemote)
            return true;

        TileEntity te = worldIn.getTileEntity(pos);

        if (!(te instanceof IGuiTileEntity))
            return false;

        playerIn.openGui(TestMod.instance, GuiHandler.GUI_FAST_FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
    }

    @Override
    public void getDrops(@Nonnull NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, @Nonnull IBlockState state, int fortune)
    {
        TileEntity te = world.getTileEntity(pos);

        //All the tile Entities that Implement this interface
        if (te instanceof IRestorableTileEntity)
        {
            ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
            NBTTagCompound compound = new NBTTagCompound();
            ((IRestorableTileEntity)te).writeRestorableToNBT(compound);

            stack.setTagCompound(compound);
            drops.add(stack);
        } else {
            super.getDrops(drops, world, pos, state, fortune);
        }
    }

    @Override
    public boolean removedByPlayer(@Nonnull IBlockState state, World world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player, boolean willHarvest)
    {
        if (willHarvest)
            return true; //If the block will be harvested, delay deletion of the block until getDrops
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(@Nonnull World worldIn, EntityPlayer player, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te = worldIn.getTileEntity(pos);

        //All the tile Entities that implement this interface
        if (te instanceof IRestorableTileEntity)
        {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound != null)
                ((IRestorableTileEntity)te).readRestorableFromNBT(compound);
        }
    }
}
