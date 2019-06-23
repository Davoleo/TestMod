package com.davoleo.testmod.config;

import com.davoleo.testmod.TestMod;
import net.minecraftforge.common.config.Config;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 16:47
 * Class: OreGenConfig
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@Config(modid = TestMod.MODID, category = "oregen")
public class OreGenConfig {

    @Config.Comment(value = "Enable Retrogen")
    public static boolean RETROGEN = true;
    @Config.Comment(value = "Enable retrogen debug logging")
    public static boolean VERBOSE = false;
    @Config.Comment(value = "Overworld ore generation")
    public static boolean GENERATE_OVERWORLD = true;
    @Config.Comment(value = "Nether ore generation")
    public static boolean GENERATE_NETHER =  true;
    @Config.Comment(value = "End ore generation")
    public static boolean GENERATE_END = true;

    @Config.Comment(value = "Mininum vein size")
    public static int MIN_VEIN_SIZE = 4;
    @Config.Comment(value = "Maximum vein size")
    public static int MAX_VEIN_SIZE = 8;
    @Config.Comment(value = "Maximum veins per chunk")
    public static int SPAWN_CHANCES = 9;
    @Config.Comment(value = "Mininum value on the Y axis at which the ore spawns")
    public static int MIN_Y = 2;
    @Config.Comment(value = "Maximum value on the Y axis at which the ore spawn")
    public static int MAX_Y = 90;

}
