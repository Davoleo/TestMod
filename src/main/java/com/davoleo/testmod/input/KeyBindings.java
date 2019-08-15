package com.davoleo.testmod.input;

import net.java.games.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/04/2019 / 16:29
 * Class: KeyBindings
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@OnlyIn(Dist.CLIENT)
public class KeyBindings {

    public static KeyBinding wandMode;

    public static void init()
    {
        wandMode = new KeyBinding("key.wandmode", KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM.getOrMakeInput(GLFW.GLFW_KEY_M), "key.categories.testmod");

        ClientRegistry.registerKeyBinding(wandMode);
    }
}
