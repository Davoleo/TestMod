package io.github.davoleo.testmod.block;

import io.github.davoleo.testmod.container.GeneratorContainer;
import io.github.davoleo.testmod.tileentity.BakedBlockTileEntity;
import io.github.davoleo.testmod.tileentity.GeneratorTileEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 01/11/2019 / 18:39
 * Class: ModBlocks
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ModBlocks {

    //Blocks
    public static final CopperBlock COPPER_BLOCK = new CopperBlock();
    public static final GeneratorBlock GENERATOR_BLOCK = new GeneratorBlock();
    public static final BakedBlock BAKED_BLOCK = new BakedBlock();

    //TE Types
    @ObjectHolder("testmod:generator")
    public static TileEntityType<GeneratorTileEntity> GENERATOR_TILE_ENTITY;
    @ObjectHolder("testmod:fancy_block")
    public static TileEntityType<BakedBlockTileEntity> BAKED_BLOCK_TILE_ENTITY;

    //Container Types
    @ObjectHolder("testmod:generator")
    public static ContainerType<GeneratorContainer> GENERATOR_CONTAINER;

}
