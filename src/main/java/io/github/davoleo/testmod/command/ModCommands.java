/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 10/03/2020 / 16:39
 * Class: ModCommands
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.davoleo.testmod.TestMod;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ModCommands {

    private static final HelloWorldCommand hello = new HelloWorldCommand();
    private static final SpawnCommand spawn = new SpawnCommand();

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> testCmd = dispatcher.register(
                Commands.literal(TestMod.MODID)
                .then(build(hello))
                .then(build(spawn))
        );

        dispatcher.register(Commands.literal("test").redirect(testCmd));
    }

    public static ArgumentBuilder<CommandSource, ?> build(ICommand command) {
        return Commands.literal(command.getName())
                .requires(commandSource -> commandSource.hasPermissionLevel(command.getPermission()))
                .executes(command);
    }
}
