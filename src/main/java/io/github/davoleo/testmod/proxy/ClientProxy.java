package io.github.davoleo.testmod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

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

    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
