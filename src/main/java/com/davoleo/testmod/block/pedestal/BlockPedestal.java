package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import com.davoleo.testmod.config.PedestalConfig;
import com.davoleo.testmod.util.Utils;
import net.minecraft.block.SoundType;
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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 16:06
 * Class: BlockPedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockPedestal extends BlockTEBase {

    public static final ResourceLocation PEDESTAL = new ResourceLocation(TestMod.MODID, "pedestal");

    public BlockPedestal()
    {
        super(Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3F, 4F)
                .sound(SoundType.STONE)
        );
        setRegistryName(PEDESTAL);
        // TODO: 20/08/2019 1.13 port
        //setHarvestLevel("pickaxe", 1);
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote)
        {
            TileEntityPedestal tile = (TileEntityPedestal) worldIn.getTileEntity(pos);
            tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {

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
                    if (PedestalConfig.GUI.get()) {
                        //player.openGui(TestMod.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
                    }
                    else
                    {
                        //NO-GUI Pedestal
                        ItemStack stack = itemHandler.getStackInSlot(0);
                        if (!stack.isEmpty())
                        {
                            String localized = Utils.localize(stack.getTranslationKey() + ".name");
                            player.sendMessage(new TextComponentString(stack.getCount() + "x " + localized));
                        }
                        else
                            player.sendMessage(new TextComponentString("Il Pedestallo è vuoto"));
                    }
                }
            });
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
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return new TileEntityPedestal();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
