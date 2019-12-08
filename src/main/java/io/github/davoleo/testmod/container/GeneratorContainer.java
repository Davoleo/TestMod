/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 08/12/2019 / 17:54
 * Class: GeneratorContainer
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 * ----------------------------------- */

package io.github.davoleo.testmod.container;

import io.github.davoleo.testmod.block.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
}
