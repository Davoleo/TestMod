package io.github.davoleo.testmod.tileentity;

import io.github.davoleo.testmod.container.GeneratorContainer;
import io.github.davoleo.testmod.util.TestEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
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

public class GeneratorTileEntity extends TileEntity implements INamedContainerProvider, ITickableTileEntity {

    private LazyOptional<IItemHandler> invHandler = LazyOptional.of(this::createInventoryHandler);
    private LazyOptional<IEnergyStorage> energyStorage = LazyOptional.of(this::createEnergyStorage);

    private int counter;

    public GeneratorTileEntity() {
        super(GENERATOR_TILE_ENTITY);
    }

    @Override
    public void tick() {
        if (counter > 0) {
            counter--;
            if (counter <= 0) {
                energyStorage.ifPresent(energyStorage -> ((TestEnergyStorage) energyStorage).addEnergy(1000));
            }
        } else {
            invHandler.ifPresent(handler -> {
                ItemStack stack = handler.getStackInSlot(0);
                if (stack.getItem() == Items.SUGAR_CANE) {
                    stack.shrink(1);
                    counter = 20;
                }
            });
        }
    }

    //Read and Write NBT Methods ---------------------------------
    @SuppressWarnings("unchecked")
    @Override
    public void read(CompoundNBT compound) {

        CompoundNBT inventory = compound.getCompound("inventory");
        invHandler.ifPresent(handler -> ((INBTSerializable<CompoundNBT>) handler).deserializeNBT(inventory));

        energyStorage.ifPresent(energyStorage -> ((TestEnergyStorage) energyStorage).setEnergy(compound.getInt("energy")));
        super.read(compound);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {

        invHandler.ifPresent(handler -> {
            CompoundNBT inventory = ((INBTSerializable<CompoundNBT>) handler).serializeNBT();
            compound.put("inventory", inventory);
        });

        energyStorage.ifPresent(energyStorage -> compound.putInt("energy", energyStorage.getEnergyStored()));

        return super.write(compound);
    }

    //Setup Capability Handlers ------------------------------
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
                if (stack.getItem() != Items.SUGAR_CANE)
                    return stack;
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    //MaxTrasfer is set to 0 because there's no energy input for an energy generator
    private IEnergyStorage createEnergyStorage() {
        return new TestEnergyStorage(100_000, 0);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return invHandler.cast();

        if (cap == CapabilityEnergy.ENERGY)
            return energyStorage.cast();

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
