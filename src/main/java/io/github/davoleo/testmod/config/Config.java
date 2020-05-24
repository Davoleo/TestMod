/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 21/12/2019 / 20:41
 * Class: Config
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 * ----------------------------------- */

package io.github.davoleo.testmod.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_GENERATOR = "generator";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue generatorMaxPower;
    public static ForgeConfigSpec.IntValue generatorGenPower;
    public static ForgeConfigSpec.IntValue generatorPowerOut;
    public static ForgeConfigSpec.IntValue generatorTicks;

    static {
        COMMON_BUILDER.comment("General Settings").push(CATEGORY_GENERAL);
        COMMON_BUILDER.pop();

        setupGeneratorConfig();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupGeneratorConfig() {
        COMMON_BUILDER.comment("Generator Settings").push(CATEGORY_GENERATOR);

        generatorMaxPower = COMMON_BUILDER.comment("Maximum Energy Capacity for the generator")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);

        generatorGenPower = COMMON_BUILDER.comment("Power Generated per Item")
                .defineInRange("genPower", 1000, 0, Integer.MAX_VALUE);

        generatorPowerOut = COMMON_BUILDER.comment("Output power per tick")
                .defineInRange("outPower", 100, 0, Integer.MAX_VALUE);

        generatorTicks = COMMON_BUILDER.comment("Amount of ticks needed to consume an item")
                .defineInRange("ticks", 20, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig
                .builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading event) {
    }

}
