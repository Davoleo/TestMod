package com.davoleo.testmod.config;

//import com.davoleo.testmod.TestMod;
//import net.minecraftforge.common.config.Config;

import net.minecraftforge.common.ForgeConfigSpec;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 16:47
 * Class: OreGenConfig
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

//@Config(modid = TestMod.MODID, category = "oregen")
public class OreGenConfig {

    public static ForgeConfigSpec.BooleanValue RETROGEN;
    public static ForgeConfigSpec.BooleanValue VERBOSE ;
    public static ForgeConfigSpec.BooleanValue GENERATE_OVERWORLD;
    public static ForgeConfigSpec.BooleanValue GENERATE_NETHER ;
    public static ForgeConfigSpec.BooleanValue GENERATE_END;

    public static ForgeConfigSpec.IntValue MIN_VEIN_SIZE;
    public static ForgeConfigSpec.IntValue MAX_VEIN_SIZE;
    public static ForgeConfigSpec.IntValue SPAWN_CHANCES;
    public static ForgeConfigSpec.IntValue MIN_Y;
    public static ForgeConfigSpec.IntValue MAX_Y ;

    public static void init(final ForgeConfigSpec.Builder SERVER_BUILDER, final ForgeConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("Ore Generation");
        //CLIENT_BUILDER.comment("Ore Generation");

        RETROGEN = SERVER_BUILDER
                .comment("Enable Retrogen")
                .define("oregen.retrogen", true);
        VERBOSE = SERVER_BUILDER
                .comment("Enable retrogen debug logging")
                .define("oregen.verbose", true);
        GENERATE_OVERWORLD = SERVER_BUILDER
                .comment("Overworld ore generation")
                .define("oregen.generateOverworld", true);
        GENERATE_NETHER = SERVER_BUILDER
                .comment("Nether ore generation")
                .define("oregen.generateNether", true);
        GENERATE_END = SERVER_BUILDER
                .comment("End ore generation")
                .define("oregen.generateEnd", true);

        MIN_VEIN_SIZE = SERVER_BUILDER
                .comment("Mininum vein size")
                .defineInRange("oregen.minVeinSize", 4, 0, 500);
        MAX_VEIN_SIZE = SERVER_BUILDER
                .comment("Maximum vein size")
                .defineInRange("oregen.maxVeinSize", 8, 0, 500);
        SPAWN_CHANCES = SERVER_BUILDER
                .comment("Maximum veins per chunk")
                .defineInRange("oregen.maxVeinsPerChunk", 9, 8, 1000);
        MIN_Y = SERVER_BUILDER
                .comment("Mininum value on the Y axis at which the ore spawns")
                .defineInRange("oregen.minY", 2, 1, 256);
        MAX_Y = SERVER_BUILDER
                .comment("Maximum value on the Y axis at which the ore spawn")
                .defineInRange("oregen.maxY", 50, 1, 256);

    }

}
