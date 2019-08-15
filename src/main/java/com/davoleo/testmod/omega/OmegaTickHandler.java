package com.davoleo.testmod.omega;

import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 20:54
 * Class: OmegaTickHandler
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class OmegaTickHandler {

    public static OmegaTickHandler instance = new OmegaTickHandler();

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START)
            return;
        World world = event.world;
        WorldOmega.get(world).tick(world);
    }

}
