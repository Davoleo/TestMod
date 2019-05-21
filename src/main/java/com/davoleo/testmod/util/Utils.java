package com.davoleo.testmod.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 17:19
 * Class: Utils
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class Utils {

    //TODO merge the other method into this one
    public static boolean canInteractWithPlayer(EntityPlayer player)
    {
        return true;
    }

    public static String localize(String unlocalized)
    {
        return new TextComponentTranslation(unlocalized).getFormattedText();
    }

}
