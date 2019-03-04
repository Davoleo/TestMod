package com.davoleo.testmod.block.tank;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 04/03/2019 / 18:15
 * Class: BlockTank
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockTank extends BlockTEBase implements ITileEntityProvider {

    public static final ResourceLocation TANK = new ResourceLocation(TestMod.MODID, "tank");

    public BlockTank()
    {
        super(Material.GLASS);
        setHardness(1F);
        setSoundType(SoundType.GLASS);
        setRegistryName(TANK);
        setTranslationKey(TestMod.MODID + ".tank");
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null)
        {
            NBTTagCompound nbt = compound.getCompoundTag("tank");
            FluidStack fluidStack = null;
            if (!nbt.hasKey("empty"))
                fluidStack = FluidStack.loadFluidStackFromNBT(nbt);
            if (fluidStack == null)
                addInformationLocalized(tooltip, "tooltip.testmod.tank", "empty");
            else
            {
                String name = fluidStack.getLocalizedName();
                addInformationLocalized(tooltip, "tooltip.testmod.tank", name + " (" + fluidStack.amount + ")");
            }
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
    {
        return new TileTank();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel()
    {
        super.initModel();
        ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new TankTESR());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote && FluidUtil.interactWithFluidHandler(playerIn, hand, worldIn, pos, facing))
            worldIn.notifyBlockUpdate(pos, state, state, 3);

        return true;
    }

    //Overriding is fine
    @SuppressWarnings("deprecation")
    @Override
    public boolean isBlockNormalCube(IBlockState state)
    {
        return false;
    }

    //Overriding is fine
    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    //Overriding is fine
    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}
