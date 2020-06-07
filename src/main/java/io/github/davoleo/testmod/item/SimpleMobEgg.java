/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 07/06/2020 / 21:03
 * Class: SimpleMobEgg
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.item;

import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.handler.RegistrationHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SimpleMobEgg extends Item {


    public SimpleMobEgg() {
        super(new Item.Properties().maxStackSize(1).group(TestMod.testTab));
    }

    /**
     * Called when this item is used when targetting a Block
     */
    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
            ItemStack itemstack = context.getItem();
            BlockPos blockpos = context.getPos();
            Direction direction = context.getFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            if (block == Blocks.SPAWNER) {
                TileEntity tileentity = world.getTileEntity(blockpos);
                if (tileentity instanceof MobSpawnerTileEntity) {
                    AbstractSpawner abstractspawner = ((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic();
                    abstractspawner.setEntityType(RegistrationHandler.SIMPLE_MOB.get());
                    tileentity.markDirty();
                    world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
                    itemstack.shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.offset(direction);
            }

            if (RegistrationHandler.SIMPLE_MOB.get().spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
                itemstack.shrink(1);
            }

        }
        return ActionResultType.SUCCESS;
    }


}
