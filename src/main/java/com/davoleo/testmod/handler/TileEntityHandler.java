package com.davoleo.testmod.handler;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.counter.TileEntityCounter;
import com.davoleo.testmod.block.pedestal.TileEntityPedestal;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/12/2018 / 22:40
 * Class: TileEntityHandler
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TileEntityHandler {

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCounter.class, new ResourceLocation(TestMod.MODID, "counter"));
        GameRegistry.registerTileEntity(TileEntityPedestal.class, new ResourceLocation(TestMod.MODID, "pedestal"));
    }
}
