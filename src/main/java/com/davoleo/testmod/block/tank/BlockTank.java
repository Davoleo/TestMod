package com.davoleo.testmod.block.tank;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.client.registry.ClientRegistry;

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

public class BlockTank extends BlockTEBase {

    public static final ResourceLocation TANK = new ResourceLocation(TestMod.MODID, "tank");

    public BlockTank()
    {
        super(Properties
                .create(Material.GLASS)
                .hardnessAndResistance(1F)
                .sound(SoundType.GLASS)
        );

        setRegistryName(TANK);
        //TODO 1.13 port
        //setHarvestLevel("pickaxe", 0);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound compound = stack.getTag();
        if (compound != null)
        {
            NBTTagCompound nbt = (NBTTagCompound) compound.getTag("tank");
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
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return new TileTank();
    }

    @Override
    public void initModel()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new TankTESR());
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote && FluidUtil.interactWithFluidHandler(player, hand, worldIn, pos, side))
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
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nonnull
    @OnlyIn(Dist.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}
