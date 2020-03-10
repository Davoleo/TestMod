/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 10/03/2020 / 17:13
 * Class: SpawnCommand
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.davoleo.testmod.network.PacketManager;
import io.github.davoleo.testmod.network.PacketOpenGUI;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

public class SpawnCommand implements ICommand {

    @Override
    public int getPermission() {
        return 0;
    }

    @Override
    public String getName() {
        return "spawn";
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        PacketManager.INSTANCE.sendTo(new PacketOpenGUI(), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        return 0;
    }
}
