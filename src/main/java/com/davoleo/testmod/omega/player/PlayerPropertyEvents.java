package com.davoleo.testmod.omega.player;

import com.davoleo.testmod.TestMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 21:48
 * Class: PlayerPropertyEvents
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PlayerPropertyEvents {

    public static PlayerPropertyEvents instance = new PlayerPropertyEvents();

    @SubscribeEvent
    public void onEntityCreation(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer) {
            if (!event.getObject().hasCapability(PlayerProperties.PLAYER_OMEGA, null))
                event.addCapability(new ResourceLocation(TestMod.MODID, "Omega"), new PropertiesDispatcher());
        }
    }

    //To ensure the Omega Power is not lost after death
    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event)
    {
        if (event.isWasDeath() && event.getOriginal().hasCapability(PlayerProperties.PLAYER_OMEGA, null))
        {
            PlayerOmega oldStock = event.getOriginal().getCapability(PlayerProperties.PLAYER_OMEGA, null);
            PlayerOmega newStock = PlayerProperties.getPlayerOmega(event.getEntityPlayer());
            newStock.copyFrom(oldStock);
        }
    }
}
