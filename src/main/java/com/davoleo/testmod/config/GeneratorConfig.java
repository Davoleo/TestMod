package com.davoleo.testmod.config;

import com.davoleo.testmod.TestMod;
import net.minecraftforge.common.config.Config;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 18:19
 * Class: GeneratorConfig
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@Config(modid = TestMod.MODID, category = "generator")
public class GeneratorConfig {

    @Config.Comment(value = "The maximum amount of power the generator can store")
    public static int MAX_POWER = 100000;

}
