package com.davoleo.testmod.block.generator;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.animation.TileEntityRendererAnimation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

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

public class BlockGenerator extends BlockTEBase {

    public static final DirectionProperty FACING_HORIZONTAL = DirectionProperty.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final ResourceLocation GENERATOR = new ResourceLocation(TestMod.MODID, "generator");

    public BlockGenerator()
    {
        super(Properties.create(Material.IRON));
        setRegistryName(GENERATOR);
        //TODO 1.13 port
        //setHarvestLevel("pickaxe", 1);

        setDefaultState(getStateContainer().getBaseState().with(FACING_HORIZONTAL, EnumFacing.NORTH));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound compound = stack.getTag();
        if (compound != null)
        {
            int energy = compound.getInt("energy");
            addInformationLocalized(tooltip, "tooltip.testmod.generator", energy);
        }
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return new TileGenerator();
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING_HORIZONTAL, context.getPlayer().getHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        builder.add(FACING_HORIZONTAL);
    }

    //TODO 1.13 Port
//    @Nonnull
//    @Override
//    protected BlockStateContainer createBlockState()
//    {
//        return new ExtendedBlockState(this,
//                new IProperty[]{Properties.StaticProperty, FACING_HORIZONTAL},
//                new IUnlistedProperty[]{Properties.AnimationProperty});
//    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initModel()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileGenerator.class, new TileEntityRendererAnimation<>());
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
