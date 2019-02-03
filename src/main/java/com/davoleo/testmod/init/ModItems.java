package com.davoleo.testmod.init;

import com.davoleo.testmod.item.ItemAngelIngot;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 18:58
 * Class: ModItems
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ModItems {

    public static ItemAngelIngot angelIngot = new ItemAngelIngot();

    public static void initModels()
    {
        angelIngot.initModel();
    }

}
