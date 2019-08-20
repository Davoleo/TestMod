package com.davoleo.testmod.block.counter;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 16/06/2019 / 10:32
 * Class: BlockCounter
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockCounter extends BlockTEBase {

    public static final ResourceLocation COUNTER = new ResourceLocation(TestMod.MODID, "counter");

    public BlockCounter()
    {
        super(Properties
                .create(Material.GLASS)
                .hardnessAndResistance(1F)
                .sound(SoundType.METAL)
        );
        setRegistryName(COUNTER);
        //setHarvestLevel("pickaxe", 1); todo 1.13
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity te = worldIn.getTileEntity(pos);

        if (!worldIn.isRemote && te instanceof TileEntityCounter)
        {
            TileEntityCounter counter = (TileEntityCounter) te;
            if (side == EnumFacing.DOWN)
            {
                if (player.isSneaking())
                    counter.decrementCountEx();
                else
                    counter.decrementCount();
            }
            else if (side == EnumFacing.UP)
            {
                if (player.isSneaking())
                    counter.incrementCountEx();
                else
                    counter.incrementCount();
            }
            player.sendStatusMessage(new TextComponentString("Counter number: " + counter.getCount()), false);
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return new TileEntityCounter();
    }
}
