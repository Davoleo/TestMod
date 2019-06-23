package com.davoleo.testmod.input;

import com.davoleo.testmod.network.Messages;
import com.davoleo.testmod.network.PacketToggleMode;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/04/2019 / 16:29
 * Class: KeyInputHandler
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (KeyBindings.wandMode.isPressed())
            Messages.INSTANCE.sendToServer(new PacketToggleMode());
    }

}
