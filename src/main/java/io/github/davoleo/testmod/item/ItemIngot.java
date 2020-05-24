package io.github.davoleo.testmod.item;

import io.github.davoleo.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/11/2019 / 21:54
 * Class: ItemIngot
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemIngot extends Item {

    public ItemIngot() {
        super(new Item.Properties().group(TestMod.testTab));
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_ingot"));
    }
}
