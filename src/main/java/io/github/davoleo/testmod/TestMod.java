package io.github.davoleo.testmod;

import io.github.davoleo.testmod.config.Config;
import io.github.davoleo.testmod.handler.ClientRegistryHandler;
import io.github.davoleo.testmod.handler.CommonModSetup;
import io.github.davoleo.testmod.handler.RegistrationHandler;
import io.github.davoleo.testmod.proxy.ClientProxy;
import io.github.davoleo.testmod.proxy.IProxy;
import io.github.davoleo.testmod.proxy.ServerProxy;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 01/11/2019 / 18:03
 * Class: TestMod
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@Mod(TestMod.MODID)
public class TestMod {

    public static final String MODID = "testmod";

    @SuppressWarnings("Convert2MethodRef")
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static final Logger LOGGER = LogManager.getLogger();

    public static ItemGroup testTab;

    public TestMod() {

        //Register Mod Configuration
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        RegistrationHandler.init();

        //Main Setup Method
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistryHandler::init);
    }
}
