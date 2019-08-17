package com.davoleo.testmod.init;

import com.davoleo.testmod.fluid.FluidFload;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/03/2019 / 18:54
 * Class: ModFluids
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ModFluids {

    public static final Fluid fload = new FluidFload();

    public static void init()
    {
        // TODO: 17/08/2019 1.13 port
        //FluidRegistry.registerFluid(fload);
        //FluidRegistry.addBucketForFluid(fload);
    }

    public static boolean isValidFloadStack(FluidStack stack)
    {
        return getFluidFromStack(stack) == fload;
    }

    public static Fluid getFluidFromStack(FluidStack stack)
    {
        return stack == null ? null : stack.getFluid();
    }

    public static String getFluidName(Fluid fluid)
    {
        return fluid == null ? "null" : fluid.getName();
    }

    public static int getAmount(FluidStack stack)
    {
        return stack == null ? 0 : stack.amount;
    }

}
