package com.davoleo.testmod.integration.jei;

import com.davoleo.testmod.TestMod;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 22:10
 * Class: CustomRecipeCategory
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class CustomRecipeCategory implements IRecipeCategory<CustomRecipeWrapper> {

    private final IDrawable background;

    public CustomRecipeCategory(IGuiHelper guiHelper)
    {
        ResourceLocation location = new ResourceLocation(TestMod.MODID, "textures/gui/fast_furnace.png");
        background = guiHelper.createDrawable(location, 3, 33, 172, 30);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return IntegrationJEI.FAST_FURNACE_ID;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return "Fast Furnace";
    }

    @Nonnull
    @Override
    public String getModName()
    {
        return TestMod.MODNAME;
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft)
    { }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout iRecipeLayout, @Nonnull CustomRecipeWrapper customRecipeWrapper, @Nonnull IIngredients iIngredients)
    {
        IGuiItemStackGroup guiItemStacks = iRecipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 6, 6);
        guiItemStacks.init(3, false, 114, 6);

        List<ItemStack> inputs = iIngredients.getInputs(VanillaTypes.ITEM).get(0);
        List<ItemStack> outputs = iIngredients.getOutputs(VanillaTypes.ITEM).get(0);

        guiItemStacks.set(0, inputs);
        guiItemStacks.set(3, outputs);
    }
}
