package com.davoleo.testmod.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/01/2019 / 19:43
 * Class: Messages
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class Messages {

    public static SimpleNetworkWrapper INSTANCE;

    private static int ID = 0;

    private static int nextID() { return ID++; }

    //Sends packets
    public static void registerMessages(String channelName)
    {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);

        //Server-side (From Client 2 Server)

        //Client-side (From Server 2 Client)
        INSTANCE.registerMessage(PacketSyncPower.Handler.class, PacketSyncPower.class, nextID(), Side.CLIENT);
    }

}
