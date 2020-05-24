/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 06/03/2020 / 17:53
 * Class: BakedBlockTileEntity
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.tileentity;

import io.github.davoleo.testmod.handler.RegistrationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class BakedBlockTileEntity extends TileEntity {

    public static final ModelProperty<BlockState> MIMIC = new ModelProperty<>();

    private BlockState mimic;

    public BakedBlockTileEntity() {
        super(RegistrationHandler.BAKED_TE.get());
    }

    public void setMimic(BlockState mimic) {
        this.mimic = mimic;
        markDirty();
        //Send changes to the client
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compound = super.getUpdateTag();
        if (mimic != null) {
            compound.put("mimic", NBTUtil.writeBlockState(mimic));
        }
        return compound;
    }

    //returns a packet sent to the client to update a block with new information
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    //on the client side when the packet is received this method will restore all the server information on the client side
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        //Getting the mimic state on the client
        BlockState oldMimic = mimic;
        CompoundNBT compound = pkt.getNbtCompound();
        if (compound.contains("mimic")) {
            mimic = NBTUtil.readBlockState(compound.getCompound("mimic"));
            //checking if the client mimic is the same of the server mimic state
            if (!Objects.equals(oldMimic, mimic)) {
                //requests a model data refresh causing the getModelData method to be called
                ModelDataManager.requestModelDataRefresh(this);
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
            }
        }
    }

    //A way to send data  from the tile entity to the baked model
    //You should do the bare minimum in this method
    @Nonnull
    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder().withInitial(MIMIC, mimic).build();
    }

    @Override
    public void read(@Nonnull CompoundNBT compound) {
        super.read(compound);
        if (compound.contains("mimic"))
            mimic = NBTUtil.readBlockState(compound.getCompound("mimic"));
    }

    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        if (mimic != null)
            compound.put("mimic", NBTUtil.writeBlockState(mimic));
        return super.write(compound);
    }
}
