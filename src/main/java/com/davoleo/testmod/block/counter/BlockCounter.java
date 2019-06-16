package com.davoleo.testmod.block.counter;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 16/06/2019 / 10:32
 * Class: BlockCounter
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockCounter extends BlockTEBase implements ITileEntityProvider {

    public static final ResourceLocation COUNTER = new ResourceLocation(TestMod.MODID, "counter");

    public BlockCounter()
    {
        super(Material.ANVIL);
        setRegistryName(COUNTER);
        setTranslationKey(COUNTER.getNamespace() + "." + COUNTER.getPath());
        setHarvestLevel("pickaxe", 1);
        setHardness(3);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity te = worldIn.getTileEntity(pos);

        if (!worldIn.isRemote && te instanceof TileEntityCounter)
        {
            TileEntityCounter counter = (TileEntityCounter) te;
            if (facing == EnumFacing.DOWN)
            {
                if (playerIn.isSneaking())
                    counter.decrementCountEx();
                else
                    counter.decrementCount();
            }
            else if (facing == EnumFacing.UP)
            {
                if (playerIn.isSneaking())
                    counter.incrementCountEx();
                else
                    counter.incrementCount();
            }
            playerIn.sendStatusMessage(new TextComponentString("Counter number: " + counter.getCount()), false);
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
    {
        return new TileEntityCounter();
    }
}
