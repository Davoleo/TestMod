package com.davoleo.testmod.block;

import com.davoleo.testmod.util.IRestorableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 17:15
 * Class: BlockTEBase
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockTEBase extends Block {

    public BlockTEBase(Block.Properties properties)
    {
        super(properties);
        //TODO 1.13 Port
        //setCreativeTab(TestMod.testTab);
    }

    private static final Pattern COMPILE = Pattern.compile("@", Pattern.LITERAL);

    protected void addInformationLocalized(List<ITextComponent> tooltip, String key, Object... parameters)
    {
        String translated = I18n.format(key, parameters);
        translated = COMPILE.matcher(translated).replaceAll("\u00a7");
        Collections.addAll(tooltip.stream().map(ITextComponent::getFormattedText).collect(Collectors.toList()), StringUtils.split(translated, "<NL>"));
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (world.isRemote)
            return true;

        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof IInteractionObject))
            return false;

        NetworkHooks.openGui((EntityPlayerMP) player, (IInteractionObject)te, buf -> buf.writeBlockPos(pos));
        return true;
    }

    @Override
    public void getDrops(IBlockState state, NonNullList<ItemStack> drops, World world, BlockPos pos, int fortune)
    {
        TileEntity te = world.getTileEntity(pos);

        //All the tile Entities that Implement this interface
        if (te instanceof IRestorableTileEntity)
        {
            ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
            NBTTagCompound compound = new NBTTagCompound();
            ((IRestorableTileEntity)te).writeRestorableToNBT(compound);

            stack.setTag(compound);
            drops.add(stack);
        } else {
            super.getDrops(state, drops, world, pos, fortune);
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest, IFluidState fluid)
    {
        if (willHarvest)
            return true; //If the block will be harvested, delay deletion of the block until getDrops
        return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
    }

    @Override
    public void harvestBlock(@Nonnull World worldIn, EntityPlayer player, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.removeBlock(pos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te = worldIn.getTileEntity(pos);

        //All the tile Entities that implement this interface
        if (te instanceof IRestorableTileEntity)
        {
            NBTTagCompound compound = stack.getTag();
            if (compound != null)
                ((IRestorableTileEntity)te).readRestorableFromNBT(compound);
        }
    }

    public void initModel() {

    }
}
