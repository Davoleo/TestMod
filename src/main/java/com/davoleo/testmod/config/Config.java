package com.davoleo.testmod.config;

import com.davoleo.testmod.TestMod;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

import static net.minecraftforge.fml.loading.LogMarkers.CORE;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 08/09/2019 / 19:28
 * Class: Config
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@Mod.EventBusSubscriber
public class Config {

    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SERVER_CONFIG;
    public static final ForgeConfigSpec CLIENT_CONFIG;

    static {
        FastFurnaceConfig.init(SERVER_BUILDER, CLIENT_BUILDER);
        GeneratorConfig.init(SERVER_BUILDER, CLIENT_BUILDER);
        OreGenConfig.init(SERVER_BUILDER, CLIENT_BUILDER);
        PedestalConfig.init(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        TestMod.logger.debug("Loading Config File {}", path);

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        TestMod.logger.debug("TOML Config for {} has been built", path.toString());
        configData.load();
        TestMod.logger.debug("TOML Config for {} has been loaded", path.toString());
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event) {
        TestMod.logger.debug("Loaded {} config file {}", TestMod.MODID, event.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.ConfigReloading event) {
        TestMod.logger.fatal(CORE, "A {} Config file has just changed!", TestMod.MODID);
    }


}
