package com.davoleo.testmod.memory;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.FACING;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 25/02/2019 / 19:28
 * Class: BlockPuzzle
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockPuzzle extends BlockTEBase {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    public static final ResourceLocation PUZZLE = new ResourceLocation(TestMod.MODID, "puzzle");

    public BlockPuzzle()
    {
        super(Properties.create(Material.WOOD));
        setRegistryName(PUZZLE);
        //TODO 1.13 port
        //setHarvestLevel("axe", 1);

        setDefaultState(getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH));
    }

    @Override
    public void initModel()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TilePuzzle.class, new PuzzleTESR());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return new TilePuzzle();
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TilePuzzle && !worldIn.isRemote)
            ((TilePuzzle) tileEntity).activate(state, player);
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        int power = worldIn.getRedstonePowerFromNeighbors(pos);
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TilePuzzle)
            ((TilePuzzle) tileEntity).setPower(power);
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState()
                .with(FACING, EnumFacing.getFacingFromVector(context.getHitX(), context.getHitY(), context.getHitZ()))
                .with(OPEN, false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        builder.add(FACING).add(OPEN);
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return layer == BlockRenderLayer.TRANSLUCENT || layer == BlockRenderLayer.SOLID;
    }
}
