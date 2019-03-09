package com.davoleo.testmod.recipe.custom;

import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 21:37
 * Class: CustomRecipe
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class CustomRecipe {

    private final ItemStack input;
    private final ItemStack output;

    public CustomRecipe(ItemStack input, ItemStack output)
    {
        this.input = input;
        this.output = output;
    }

    public ItemStack getInput()
    {
        return input;
    }

    public ItemStack getOutput()
    {
        return output;
    }
}
