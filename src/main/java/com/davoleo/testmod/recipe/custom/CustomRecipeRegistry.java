package com.davoleo.testmod.recipe.custom;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 21:39
 * Class: CustomRecipeRegistry
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class CustomRecipeRegistry {

    private static boolean isInitializied = false;
    private static List<CustomRecipe> customRecipeList = new ArrayList<>();

    public static List<CustomRecipe> getCustomRecipeList()
    {
        if (!isInitializied)
        {
            init();
            isInitializied = true;
        }
        return customRecipeList;
    }

    @Nullable
    public static CustomRecipe getRecipe(ItemStack input)
    {
        for (CustomRecipe recipe : getCustomRecipeList())
            if (ItemStack.areItemsEqual(input, recipe.getInput()))
                return recipe;
            return null;
    }

    private static void init()
    {
        customRecipeList.add(new CustomRecipe(new ItemStack(Items.COAL), new ItemStack(Items.DIAMOND)));
        customRecipeList.add(new CustomRecipe(new ItemStack(Items.GUNPOWDER), new ItemStack(Items.NETHER_STAR)));
        customRecipeList.add(new CustomRecipe(new ItemStack(Items.WHEAT_SEEDS), new ItemStack(Items.EMERALD)));

    }
}
