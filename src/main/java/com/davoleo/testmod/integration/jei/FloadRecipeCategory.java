package com.davoleo.testmod.integration.jei;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 22:28
 * Class: FloadRecipeCategory
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class FloadRecipeCategory{}

//implements IRecipeCategory<FloadRecipeWrapper> {
//
//    private final IDrawable background;
//
//    public FloadRecipeCategory(IGuiHelper guiHelper)
//    {
//        ResourceLocation location = new ResourceLocation(TestMod.MODID, "textures/gui/fload_creator.png");
//        background = guiHelper.createDrawable(location, 3, 18, 98, 30);
//    }
//
//    @Nonnull
//    @Override
//    public String getUid()
//    {
//        return IntegrationJEI.FLOAD_CREATOR_ID;
//    }
//
//    @Nonnull
//    @Override
//    public String getTitle()
//    {
//        return "Fload Creator";
//    }
//
//    @Nonnull
//    @Override
//    public String getModName()
//    {
//        return TestMod.MODNAME;
//    }
//
//    @Nonnull
//    @Override
//    public IDrawable getBackground()
//    {
//        return background;
//    }
//
//    @Override
//    public void setRecipe(@Nonnull IRecipeLayout iRecipeLayout, @Nonnull FloadRecipeWrapper floadRecipeWrapper, @Nonnull IIngredients iIngredients)
//    {
//        IGuiItemStackGroup guiItemStacks = iRecipeLayout.getItemStacks();
//        IGuiFluidStackGroup guiFluidStacks = iRecipeLayout.getFluidStacks();
//
//        guiFluidStacks.init(0, false, 65, 6, 18, 18, 100, true, null);
//        guiItemStacks.init(0, true, 6, 7);
//
//        List<ItemStack> inputs = iIngredients.getInputs(VanillaTypes.ITEM).get(0);
//        List<FluidStack> outputs = iIngredients.getOutputs(VanillaTypes.FLUID).get(0);
//
//        guiItemStacks.set(0, inputs);
//        guiFluidStacks.set(0, outputs);
//    }
//}
