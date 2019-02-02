package com.davoleo.testmod.config;

import com.davoleo.testmod.TestMod;
import net.minecraftforge.common.config.Config;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 02/02/2019 / 21:18
 * Class: FastFurnaceConfig
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@net.minecraftforge.common.config.Config(modid = TestMod.MODID, name = "testmod", category = "fast_furnace")
public class FastFurnaceConfig {

    @Config.Comment(value = "Number of ticks to smelt one item")
    @Config.RangeInt(min = 1)
    public static int MAX_PROGRESS = 40;
    @Config.Comment(value = "Fast furnace internal power storage size")
    public static int MAX_POWER = 100000;

    @Config.Comment(value = "Maximum rate at which the furnace can receive power")
    public static int RF_PER_TICK_INPUT = 100;
    @Config.Comment(value = "How much per tick does the furnace consume while active")
    public static int RF_PER_TICK = 20;
}
