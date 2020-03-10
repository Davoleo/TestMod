package io.github.davoleo.testmod;

import io.github.davoleo.testmod.config.Config;
import io.github.davoleo.testmod.item.ModItems;
import io.github.davoleo.testmod.network.PacketManager;
import io.github.davoleo.testmod.proxy.ClientProxy;
import io.github.davoleo.testmod.proxy.IProxy;
import io.github.davoleo.testmod.proxy.ServerProxy;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 01/11/2019 / 18:03
 * Class: TestMod
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@Mod("testmod")
public class TestMod {

    public static final String MODID = "testmod";

    @SuppressWarnings("Convert2MethodRef")
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    private static final Logger LOGGER = LogManager.getLogger();

    public static ItemGroup testTab;

    public TestMod() {

        //Register Mod Configuration
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        //Main Setup Method
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        //Load Mod Config
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("testmod-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("testmod-common.toml"));
    }

    //All the blocks, TEs and Entities are registered
    //Preinitialization
    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("TESTMOD: Pre-Initialization");
        proxy.init();
        PacketManager.registerMessages();

        testTab = new ItemGroup("test_tab") {
            @Nonnull
            @Override
            public ItemStack createIcon() {
                return new ItemStack(ModItems.copperIngot);
            }
        };
    }

}
