package com.davoleo.testmod.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 26/06/2019 / 16:53
 * Interface: IProxy
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public interface IProxy {

    void setup(final FMLCommonSetupEvent event);

    EntityPlayer getClientPlayer();

}
