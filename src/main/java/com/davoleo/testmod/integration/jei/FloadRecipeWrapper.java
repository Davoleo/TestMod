package com.davoleo.testmod.integration.jei;

import com.davoleo.testmod.init.ModFluids;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 22:24
 * Class: FloadRecipeWrapper
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class FloadRecipeWrapper implements ICraftingRecipeWrapper {

    public FloadRecipeWrapper()
    { }

    @Override
    public void getIngredients(@Nonnull IIngredients iIngredients)
    {
        iIngredients.setOutput(VanillaTypes.FLUID, new FluidStack(ModFluids.fload, 100));
        iIngredients.setInput(VanillaTypes.ITEM, new ItemStack(Items.FEATHER));
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    { }

    @Nonnull
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY)
    {
        return Collections.emptyList();
    }
}
