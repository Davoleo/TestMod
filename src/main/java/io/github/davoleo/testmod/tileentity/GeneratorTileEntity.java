package io.github.davoleo.testmod.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static io.github.davoleo.testmod.block.ModBlocks.GENERATOR_TILE_ENTITY;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/11/2019 / 22:51
 * Class: GeneratorTileEntity
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class GeneratorTileEntity extends TileEntity implements ITickableTileEntity {

    private ItemStackHandler handler;

    public GeneratorTileEntity() {
        super(GENERATOR_TILE_ENTITY);
    }

    @Override
    public void tick() {
        if (world.isRemote) {
            System.out.println("tick tick tick, I'm a TE");
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        CompoundNBT inventory = compound.getCompound("inventory");
        getInventoryHandler().deserializeNBT(inventory);
        super.read(compound);
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT inventory = getInventoryHandler().serializeNBT();
        compound.put("inventory", inventory);
        return super.write(compound);
    }

    public ItemStackHandler getInventoryHandler() {
        if (handler == null)
            handler = new ItemStackHandler(1) {

                //GUI item restriction
                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                    return stack.getItem() == Items.SUGAR_CANE;
                }

                //Automation Item Restriction
                @Nonnull
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    if (stack.getItem() != Items.DIAMOND)
                        return stack;
                    return super.insertItem(slot, stack, simulate);
                }
            };
        return handler;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return LazyOptional.of(() -> ((T) getInventoryHandler()));
        return super.getCapability(cap, side);
    }
}
