package io.github.davoleo.testmod.proxy;

import io.github.davoleo.testmod.block.ModBlocks;
import io.github.davoleo.testmod.entity.SimpleMobEntity;
import io.github.davoleo.testmod.entity.SimpleMobRenderer;
import io.github.davoleo.testmod.gui.GeneratorScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 01/11/2019 / 18:31
 * Class: ClientProxy
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.GENERATOR_CONTAINER, GeneratorScreen::new);
        RenderingRegistry.registerEntityRenderingHandler(SimpleMobEntity.class, SimpleMobRenderer::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
