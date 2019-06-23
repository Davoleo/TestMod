package com.davoleo.testmod.input;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/04/2019 / 16:29
 * Class: KeyBindings
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@SideOnly(Side.CLIENT)
public class KeyBindings {

    public static KeyBinding wandMode;

    public static void init()
    {
        wandMode = new KeyBinding("key.wandmode", KeyConflictContext.IN_GAME, Keyboard.KEY_M, "key.categories.testmod");
        ClientRegistry.registerKeyBinding(wandMode);
    }
}
