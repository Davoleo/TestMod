package io.github.davoleo.testmod.block;

import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.tileentity.GeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

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
        );

        setRegistryName(new ResourceLocation(TestMod.MODID, "generator"));
    }

    public Item createItemBlock() {
        return new BlockItem(this, new Item.Properties().group(TestMod.setup.testTab)).setRegistryName(this.getRegistryName());
    }

    //FACING PROP ---------------------------
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (entity != null)
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)));
    }

    public static Direction getFacingFromEntity(BlockPos activatedBlock, LivingEntity entity) {
        return Direction.getFacingFromVector((float) (entity.posX - activatedBlock.getX()), (float) (entity.posY - activatedBlock.getY()), (float) (entity.posZ - activatedBlock.getZ()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BlockStateProperties.FACING);
    }

    //TILE ENTITY SECTION ---------------------------
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GeneratorTileEntity();
    }
}
