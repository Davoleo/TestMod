package com.davoleo.testmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/06/2019 / 18:48
 * Class: PedestalConfig
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PedestalConfig {

    public static ForgeConfigSpec.BooleanValue GUI;

    public static void init(final ForgeConfigSpec.Builder SERVER_BUILDER, final ForgeConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("Pedestal");
        //CLIENT_BUILDER.comment("Pedestal");

        GUI = SERVER_BUILDER
                .comment("When set to false the pedestal has no GUI")
                .define("pedestal.GUI", true);
    }

}
