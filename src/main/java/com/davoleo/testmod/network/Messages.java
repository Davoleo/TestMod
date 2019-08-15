package com.davoleo.testmod.network;

import com.davoleo.testmod.TestMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/01/2019 / 19:43
 * Class: Messages
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class Messages {

    public static SimpleChannel INSTANCE;

    private static int ID = 0;

    private static int nextID() { return ID++; }

    //Sends packets
    public static void registerMessages(String channelName)
    {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(TestMod.MODID, channelName), () -> "1.0", s -> true, s -> true);

        //Server-side (From Client 2 Server)
        INSTANCE.registerMessage(nextID(),
                PacketToggleMode.class,
                PacketToggleMode::toBytes,
                PacketToggleMode::new,
                PacketToggleMode::handle);
        INSTANCE.registerMessage(nextID(),
                PacketRequestUpdatePedestal.class,
                PacketRequestUpdatePedestal::toBytes,
                PacketRequestUpdatePedestal::new,
                PacketRequestUpdatePedestal::handle);

        //Client-side (From Server 2 Client)
        INSTANCE.registerMessage(nextID(),
                PacketSyncMachineState.class,
                PacketSyncMachineState::toBytes,
                PacketSyncMachineState::new,
                PacketSyncMachineState::handle);
        INSTANCE.registerMessage(nextID(),
                PacketSendOmega.class,
                PacketSendOmega::toBytes,
                PacketSendOmega::new,
                PacketSendOmega::handle);
        INSTANCE.registerMessage(nextID(),
                PacketUpdatePedestal.class,
                PacketUpdatePedestal::toBytes,
                PacketUpdatePedestal::new,
                PacketUpdatePedestal::handle);
    }

}
