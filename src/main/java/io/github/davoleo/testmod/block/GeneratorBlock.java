package io.github.davoleo.testmod.block;

import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.tileentity.GeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/11/2019 / 22:13
 * Class: GeneratorBlock
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class GeneratorBlock extends Block {

    public GeneratorBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2F)
                .lightValue(14)
        );

        setRegistryName(new ResourceLocation(TestMod.MODID, "generator"));
    }

    public Item createItemBlock() {
        return new BlockItem(this, new Item.Properties().group(TestMod.testTab)).setRegistryName(new ResourceLocation(TestMod.MODID, "generator"));
    }

    //PROPERTIES ---------------------------
    @Override
    public void onBlockPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity entity, @Nonnull ItemStack stack) {
        if (entity != null)
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)));
    }

    public static Direction getFacingFromEntity(BlockPos activatedBlock, LivingEntity entity) {
        Vec3d pos = entity.getPositionVec();
        return Direction.getFacingFromVector((float) (pos.x - activatedBlock.getX()), (float) (pos.y - activatedBlock.getY()), (float) (pos.z - activatedBlock.getZ()));
    }

    @Override
    protected void fillStateContainer(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
    }

    // TODO: 21/12/2019 Fix Lighting not turning off, YAY
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(BlockStateProperties.POWERED) ? super.getLightValue(state, world, pos) : 0;
    }

    //TILE ENTITY ---------------------------------------------------------
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GeneratorTileEntity();
    }

    //GUI ----------------------------------------------------------------
    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof INamedContainerProvider) {
                NetworkHooks.openGui(((ServerPlayerEntity) player), ((INamedContainerProvider) te), te.getPos());
                return ActionResultType.SUCCESS;
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
