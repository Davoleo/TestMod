/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 10/03/2020 / 17:04
 * Class: Command
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.command;

import com.mojang.brigadier.Command;
import net.minecraft.command.CommandSource;

public interface ICommand extends Command<CommandSource> {

    int getPermission();

    String getName();

}
