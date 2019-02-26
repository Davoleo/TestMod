package com.davoleo.testmod.memory;

import com.davoleo.testmod.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 25/02/2019 / 20:16
 * Class: TilePuzzle
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TilePuzzle extends TileEntity implements ITickable {

    private ItemStack item = ItemStack.EMPTY;
    private int power = 0;
    private int timer = 0;
    private boolean solved = false;

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newSate)
    {
        return oldState.getBlock() != newSate.getBlock();
    }

    public int getPower()
    {
        return power;
    }

    public void setPower(int power)
    {
        if (this.power != power)
            this.power = power;
        if (power > 0)
            setupGame();
        markDirty();
    }

    public ItemStack getItem()
    {
        return item;
    }

    public void setItem(@Nonnull ItemStack item)
    {
        this.item = item;
        markDirty();
    }

    public boolean isSolved()
    {
        return solved;
    }

    private static final Item[] ITEMS = new Item[]
            {
                  Items.APPLE,
                  Items.ARROW,
                  Items.DIAMOND,
                  Items.EMERALD,
                  Items.BED,
                  Items.BEEF,
                  Items.BLAZE_ROD,
                  Items.BOAT,
                  Items.BONE,
                  Items.BOOK,
                  Items.BOWL,
                  Items.BREAD,
                  Items.BAKED_POTATO,
                  Items.BUCKET,
                  Items.CAKE,
                  Items.CARROT,
                  Items.CLOCK,
                  Items.COAL,
                  Items.COMPASS,
                  Items.FISH,
                  Items.IRON_BOOTS,
                  Items.IRON_INGOT,
                  Items.ENDER_EYE,
                  Items.STRING,
                  Items.NETHER_STAR,
                  Items.NETHER_WART,
                  Items.SPIDER_EYE,
                  Items.MELON,
                  Items.GOLD_INGOT,
                  Items.GOLD_NUGGET
            };


    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            if (timer > 0)
            {
                timer--;
                if (timer <= 0)
                    closeMe();
                markDirty();
            }
        }
    }

    public void activate(IBlockState state, EntityPlayer player)
    {
        Boolean open = state.getValue(BlockPuzzle.OPEN);

        // Already open, do nothing
        if (open)
            return;

        // No game, do nothing
        if (item.isEmpty())
            return;

        Set<BlockPos> blocks = findParticipatingBlocks();

        if (blocks.size() % 2 != 0)
        {
            player.sendStatusMessage(new TextComponentTranslation("status.testmod.puzzle.odd_gameblocks"), true);
            return;
        }

        // We are temporarily displaying a bad solution. Do nothing
        if (isDisplayingWrongSolution(blocks))
            return;

        int count = countOpenUnsolvedBlocks(blocks);
        if (count == 0) {
            // We can open
            openMe();
        } else if (count == 1) {
            // We can also open. Check status
            openMe();
            boolean goodsolution = checkSolution(blocks);
            if (goodsolution) {
                markResolved(blocks);
                world.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1F, 1F);
//                if (isSolved())
//                    world.playSound(null, pos, SoundEvents.ENTITY_FIREWORK_BLAST, SoundCategory.BLOCKS, 1F, 1F);
//                    world.playSound(null, pos, SoundEvents.ENTITY_FIREWORK_BLAST, SoundCategory.BLOCKS, 1F, 1F);
            } else {
                setWrongTimer(blocks);
                world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1, 1F);
            }
        }
    }

//    private boolean isPuzzleComplete(Set<BlockPos> blocks)
//    {
//
//    }

    private boolean isDisplayingWrongSolution(Set<BlockPos> blocks)
    {
        for (BlockPos block : blocks)
        {
            TileEntity te = world.getTileEntity(block);
            assert te != null;
            if (((TilePuzzle) te).timer > 0)
                return true;
        }
        return false;
    }

    private int countOpenUnsolvedBlocks(Set<BlockPos> blocks)
    {
        int count = 0;
        for (BlockPos block : blocks)
        {
            if (isUnsolved(block))
                count++;
        }
        return count;
    }

    private boolean isUnsolved(BlockPos block)
    {
        IBlockState state = world.getBlockState(block);
        TileEntity te = world.getTileEntity(block);
        TilePuzzle puzzle = (TilePuzzle) te;
        assert puzzle != null;
        if (!puzzle.solved && state.getValue(BlockPuzzle.OPEN))
            return true;
        return false;
    }

    private boolean checkSolution(Set<BlockPos> blocks)
    {
        for (BlockPos block : blocks)
            if (isUnsolved(block)) {
                TileEntity te = world.getTileEntity(block);
                assert te != null;
                if (!ItemStack.areItemsEqual(((TilePuzzle) te).getItem(), item))
                    return false;
            }
        return true;
    }

    private void markResolved(Set<BlockPos> blocks)
    {
        for (BlockPos block : blocks)
        {
            if (isUnsolved(block))
            {
                TileEntity te = world.getTileEntity(block);
                assert te != null;
                ((TilePuzzle)te).solved = true;
                te.markDirty();
                IBlockState blockState = world.getBlockState(block);
                getWorld().notifyBlockUpdate(block, blockState, blockState, 3);
            }
        }
    }

    private void setWrongTimer(Set<BlockPos> blocks)
    {
        for (BlockPos block : blocks)
        {
            if (isUnsolved(block))
            {
                TileEntity te = world.getTileEntity(block);
                assert te != null;
                ((TilePuzzle)te).timer = 10;
                te.markDirty();
            }
        }
    }

    private void setupGame()
    {
        //All the blocks of the puzzle
        Set<BlockPos> gameblocks = findParticipatingBlocks();

        List<Item> items = Arrays.asList(TilePuzzle.ITEMS);
        Collections.shuffle(items);

        int index = 0;
        List<BlockPos> randomGameBlocks = new ArrayList<>(gameblocks);
        Collections.shuffle(randomGameBlocks);

        for (BlockPos p : randomGameBlocks)
        {
            TileEntity tileEntity = world.getTileEntity(p);
            assert tileEntity != null;
            ((TilePuzzle) tileEntity).closeAndSetItem(items.get((index / 2) % items.size()));
            index++;
        }
    }

    private Set<BlockPos> findParticipatingBlocks() {
        Queue<BlockPos> todo = new ArrayDeque<>();
        todo.add(pos);

        EnumFacing thisFacing = world.getBlockState(pos).getValue(BlockPuzzle.FACING);

        Set<BlockPos> gameblocks = new HashSet<>();

        while (!todo.isEmpty()) {
            BlockPos todoPos = todo.poll();
            if (world.isBlockLoaded(todoPos)) {
                IBlockState state = world.getBlockState(todoPos);
                TileEntity te = world.getTileEntity(todoPos);
                if (te instanceof TilePuzzle && state.getBlock() == ModBlocks.blockPuzzle && state.getValue(BlockPuzzle.FACING) == thisFacing) {
                    gameblocks.add(todoPos);
                    // Add connected positions to the todo
                    for (EnumFacing facing : EnumFacing.VALUES) {
                        if (facing.getAxis() != thisFacing.getAxis()) {
                            BlockPos newPos = todoPos.offset(facing);
                            if (!gameblocks.contains(newPos)) {
                                todo.add(newPos);
                            }
                        }
                    }
                }
            }
        }
        return gameblocks;
    }

    private void openMe()
    {
        IBlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.withProperty(BlockPuzzle.OPEN, true), 3);
        world.playSound(null, pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 1, 2);
    }

    private void closeMe()
    {
        IBlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.withProperty(BlockPuzzle.OPEN, false), 3);
        world.playSound(null, pos, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 1, 2);
        //UI_BUTTON_CLICK
    }

    private void closeAndSetItem(Item item)
    {
        closeMe();
        this.item = new ItemStack(item);
        solved = false;
        markDirty();
    }

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound nbtTag = super.getUpdateTag();
        NBTTagCompound itemTag = new NBTTagCompound();
        item.writeToNBT(itemTag);
        nbtTag.setTag("item", itemTag);
        nbtTag.setBoolean("solved", solved);
        return nbtTag;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        item = new ItemStack(pkt.getNbtCompound().getCompoundTag("item"));
        solved = pkt.getNbtCompound().getBoolean("solved");
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        item = new ItemStack(compound.getCompoundTag("item"));
        power = compound.getInteger("power");
        timer = compound.getInteger("timer");
        solved = compound.getBoolean("solved");
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        NBTTagCompound itemTag = new NBTTagCompound();
        item.writeToNBT(itemTag);
        compound.setTag("item", itemTag);
        compound.setInteger("power", power);
        compound.setInteger("timer", timer);
        compound.setBoolean("solved", solved);
        return super.writeToNBT(compound);
    }
}
