/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 10/03/2020 / 16:50
 * Class: HelloWorldCommand
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class HelloWorldCommand implements ICommand {

    public final int PERMISSION_LEVEL = 0;
    public final String command = "hello";

    @Override
    public int run(CommandContext<CommandSource> context) {
        context.getSource().sendFeedback(new StringTextComponent("Hello, World!").applyTextStyle(TextFormatting.BOLD), false);
        return 0;
    }

    @Override
    public int getPermission() {
        return PERMISSION_LEVEL;
    }

    @Override
    public String getName() {
        return command;
    }
}
