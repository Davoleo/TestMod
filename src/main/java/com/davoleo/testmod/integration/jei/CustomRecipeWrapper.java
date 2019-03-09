package com.davoleo.testmod.integration.jei;

import com.davoleo.testmod.recipe.custom.CustomRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 22:00
 * Class: CustomRecipeWrapper
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class CustomRecipeWrapper implements ICraftingRecipeWrapper {

    private final List<List<ItemStack>> inputs;
    private final ItemStack output;

    public CustomRecipeWrapper(CustomRecipe recipe)
    {
        this.inputs = Collections.singletonList(Collections.singletonList((recipe.getInput().copy())));
        this.output = recipe.getOutput().copy();
    }

    @Override
    public void getIngredients(@Nonnull IIngredients iIngredients)
    {
        iIngredients.setOutput(VanillaTypes.ITEM, output);
        iIngredients.setInputLists(VanillaTypes.ITEM, inputs);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {

    }

    @Nonnull
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY)
    {
        return Collections.emptyList();
    }
}
