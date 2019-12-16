/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 08/12/2019 / 17:54
 * Class: GeneratorContainer
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 * ----------------------------------- */

package io.github.davoleo.testmod.container;

import io.github.davoleo.testmod.block.ModBlocks;
import io.github.davoleo.testmod.util.TestEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

import static io.github.davoleo.testmod.block.ModBlocks.GENERATOR_CONTAINER;

public class GeneratorContainer extends Container {

    private TileEntity tileEntity;
    private InvWrapper playerInventory;

    public GeneratorContainer(int id, World clientWorld, BlockPos pos, PlayerInventory playerInventory) {
        super(GENERATOR_CONTAINER, id);
        tileEntity = clientWorld.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        this.playerInventory = new InvWrapper(playerInventory);

        //add Generator Slot
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            addSlot(new SlotItemHandler(handler, 0, 82, 24 ));
        });

        addPlayerInventorySlots(10, 70);

        //Energy Tracking (between server and client)
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return  getEnergy();
            }

            @Override
            public void set(int i) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorage -> ((TestEnergyStorage) energyStorage).setEnergy(i));
            }
        });
    }

    public int getEnergy() {
        return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    // TODO: 08/12/2019 generalize these methods
    public int addSlotRow(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x+=dx;
            index++;
        }
        return index;
    }

    public int addSlotBox(IItemHandler handler, int index, int x, int y, int colAmount, int dx, int rowAmount, int dy) {
        for (int j = 0; j < rowAmount; j++) {
            index = addSlotRow(handler, index, x, y, colAmount, dx);
            y += dy;
        }
        return index;
    }

    private void addPlayerInventorySlots(int leftColX, int topRowY) {
        //Player Inventory Slots
        addSlotBox(playerInventory, 9, leftColX, topRowY, 9, 18, 3, 18);

        //Player Hotbar Slots
        topRowY +=58;
        addSlotRow(playerInventory, 0, leftColX, topRowY, 9, 18);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, ModBlocks.GENERATOR_BLOCK);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.mergeItemStack(stack, 1, 37, true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(stack, itemstack);
            } else {
                //Merge with the generator's inventory
                if (stack.getItem() == Items.DIAMOND) {
                    if (!this.mergeItemStack(stack, 0, 1, false))
                        return ItemStack.EMPTY;
                    //Merge with the hotbar
                } else if (index < 28) {
                    if (!this.mergeItemStack(stack, 28, 37, false))
                        return ItemStack.EMPTY;
                    //Merge with the player inventory
                } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (stack.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, stack);
        }
        return itemstack;
    }
}
