package com.davoleo.testmod.fluid;

import com.davoleo.testmod.TestMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/03/2019 / 18:38
 * Class: FluidFload
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class FluidFload extends Fluid {

    public FluidFload()
    {
        super("fload",
                new ResourceLocation(TestMod.MODID, "blocks/fload_still"),
                new ResourceLocation(TestMod.MODID, "blocks/fload_flow"));
    }

}
