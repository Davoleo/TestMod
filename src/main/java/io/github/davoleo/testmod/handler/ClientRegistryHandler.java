/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 06/03/2020 / 17:09
 * Class: ClientRegistryHandler
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.handler;

import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.block.BakedModelLoader;
import io.github.davoleo.testmod.entity.SimpleMobRenderer;
import io.github.davoleo.testmod.gui.GeneratorScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryHandler {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(RegistrationHandler.GENERATOR_CONTAINER.get(), GeneratorScreen::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistrationHandler.SIMPLE_MOB.get(), SimpleMobRenderer::new);
        ModelLoaderRegistry.registerLoader(new ResourceLocation(TestMod.MODID, "baked_loader"), new BakedModelLoader());
    }
}
