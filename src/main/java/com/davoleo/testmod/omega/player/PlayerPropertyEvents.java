package com.davoleo.testmod.omega.player;

import com.davoleo.testmod.TestMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
            if (!event.getObject().getCapability(PlayerProperties.PLAYER_OMEGA).isPresent())
                event.addCapability(new ResourceLocation(TestMod.MODID, "omega"), new PropertiesDispatcher());
        }
    }

    //To ensure the Omega Power is not lost after death
    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event)
    {
        event.getOriginal().getCapability(PlayerProperties.PLAYER_OMEGA).ifPresent(oldStore -> {
            event.getEntityPlayer().getCapability(PlayerProperties.PLAYER_OMEGA).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
            });
        });
    }
}
