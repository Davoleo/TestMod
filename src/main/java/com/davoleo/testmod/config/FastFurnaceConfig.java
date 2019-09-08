package com.davoleo.testmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 02/02/2019 / 21:18
 * Class: FastFurnaceConfig
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class FastFurnaceConfig {

    public static ForgeConfigSpec.IntValue MAX_PROGRESS;
    public static ForgeConfigSpec.IntValue MAX_POWER;
    public static ForgeConfigSpec.IntValue RF_PER_TICK_INPUT;
    public static ForgeConfigSpec.IntValue RF_PER_TICK;

    public static void init(final ForgeConfigSpec.Builder SERVER_BUILDER, final ForgeConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("Fast Furnace");
        //CLIENT_BUILDER.comment("Fast Furnace");

        MAX_PROGRESS = SERVER_BUILDER
                .comment("Number of ticks to smelt one item")
                .defineInRange("fast_furnace.maxProgress", 40, 1, 1000000000);
        MAX_POWER = SERVER_BUILDER
                .comment("Fast furnace internal power storage size")
                .defineInRange("fast_furnace.maxPower", 100000,  1, 1000000000);
        RF_PER_TICK_INPUT = SERVER_BUILDER
                .comment("Maximum rate at which the furnace can receive power")
                .defineInRange("fast_furnace.rfPerTickInput", 100, 1, 1000000000);
        RF_PER_TICK = SERVER_BUILDER
                .comment("How much per tick does the furnace consume while active")
                .defineInRange("fast_furnace.rfPerTick", 20,  1, 1000000000);
    }

}
