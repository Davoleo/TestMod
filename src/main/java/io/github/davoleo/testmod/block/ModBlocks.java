package io.github.davoleo.testmod.block;

import com.mojang.datafixers.types.Type;
import io.github.davoleo.testmod.tileentity.GeneratorTileEntity;
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
    public static CopperBlock copperBlock = new CopperBlock();
    public static GeneratorBlock generatorBlock = new GeneratorBlock();

    //TE Types
    @ObjectHolder("testmod:generator")
    public static TileEntityType<GeneratorTileEntity> GENERATOR_TILE_ENTITY;

}
