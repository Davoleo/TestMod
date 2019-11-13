package io.github.davoleo.testmod;

import io.github.davoleo.testmod.proxy.ClientProxy;
import io.github.davoleo.testmod.proxy.IProxy;
import io.github.davoleo.testmod.proxy.ModSetup;
import io.github.davoleo.testmod.proxy.ServerProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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

@Mod("testmod")
public class TestMod {

    public static final String MODID = "testmod";

    @SuppressWarnings("Convert2MethodRef")
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup setup = new ModSetup();

    private static final Logger LOGGER = LogManager.getLogger();

    public TestMod() {
        //Main Setup Method
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();

        //All the blocks, TEs and Entities are registered
        //Preinitialization
        LOGGER.info("TESTMOD: Pre-Initialization");
    }


}
