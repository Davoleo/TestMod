/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 10/03/2020 / 17:17
 * Class: PacketManager
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.network;

import io.github.davoleo.testmod.TestMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketManager {

    public static SimpleChannel INSTANCE;
    private static int id = 0;

    public static int nextID() {
        return id++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(TestMod.MODID, "testmod"), () -> "1.0", s -> true, s -> true);

        INSTANCE.registerMessage(nextID(),
                PacketOpenGUI.class,
                PacketOpenGUI::toBytes,
                PacketOpenGUI::new,
                PacketOpenGUI::handle);

        INSTANCE.registerMessage(nextID(),
                PacketSpawnEntity.class,
                PacketSpawnEntity::toBytes,
                PacketSpawnEntity::new,
                PacketSpawnEntity::handle);
    }

}
