package io.github.davoleo.testmod.proxy;

import net.minecraft.world.World;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 01/11/2019 / 18:31
 * Class: ServerProxy
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ServerProxy implements IProxy {

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("can't get the client world on the server!!!");
    }
}
