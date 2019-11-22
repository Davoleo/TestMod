package io.github.davoleo.testmod.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import static io.github.davoleo.testmod.block.ModBlocks.GENERATOR_TILE_ENTITY;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/11/2019 / 22:51
 * Class: GeneratorTileEntity
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class GeneratorTileEntity extends TileEntity implements ITickableTileEntity {

    public GeneratorTileEntity() {
        super(GENERATOR_TILE_ENTITY);
    }

    @Override
    public void tick() {
        if (world.isRemote) {
            System.out.println("tick tick tick, I'm a TE");
        }
    }
}
