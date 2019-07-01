package com.davoleo.testmod.omega.player;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 21:41
 * Class: PlayerProperties
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PlayerProperties {

    @CapabilityInject(PlayerOmega.class)
    public static Capability<PlayerOmega> PLAYER_OMEGA;

}
