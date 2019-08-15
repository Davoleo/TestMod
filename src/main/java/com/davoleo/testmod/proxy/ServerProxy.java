package com.davoleo.testmod.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 26/06/2019 / 16:55
 * Class: ServerProxy
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ServerProxy implements IProxy {

    @Override
    public void setup(FMLCommonSetupEvent event)
    { }

    @Override
    public EntityPlayer getClientPlayer() {
        throw new IllegalStateException("Can't get the client player Server-Side");
    }
}
