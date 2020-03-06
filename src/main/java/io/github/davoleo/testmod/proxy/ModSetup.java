package io.github.davoleo.testmod.proxy;

import io.github.davoleo.testmod.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/11/2019 / 22:20
 * Class: ModSetup
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ModSetup {

    public ItemGroup testTab = new ItemGroup("test_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.copperIngot);
        }
    };

    public void init() {
    }
}
