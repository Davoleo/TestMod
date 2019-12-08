package io.github.davoleo.testmod.tileentity;

import io.github.davoleo.testmod.container.GeneratorContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
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

public class GeneratorTileEntity extends TileEntity implements INamedContainerProvider {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createInventoryHandler);

    public GeneratorTileEntity() {
        super(GENERATOR_TILE_ENTITY);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read(CompoundNBT compound) {
        CompoundNBT inventory = compound.getCompound("inventory");
        handler.ifPresent(handler -> ((INBTSerializable<CompoundNBT>) handler).deserializeNBT(inventory));
        super.read(compound);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (handler.isPresent()){
            CompoundNBT inventory = ((INBTSerializable<CompoundNBT>) handler).serializeNBT();
            compound.put("inventory", inventory);
        }
        return super.write(compound);
    }

    @Nonnull
    public ItemStackHandler createInventoryHandler() {
        return new ItemStackHandler(1) {

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
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return handler.cast();
        return super.getCapability(cap, side);
    }

    // INamedContainerProvider IMPLEMENTATION --------------------------------------------------------
    // TODO: 08/12/2019 localized display title
    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    //Server-Side Container creation
    @Nullable
    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity player) {
        return new GeneratorContainer(id, world, pos, playerInventory);
    }
}
