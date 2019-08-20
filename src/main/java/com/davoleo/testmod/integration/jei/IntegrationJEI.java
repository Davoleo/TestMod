package com.davoleo.testmod.integration.jei;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 21:51
 * Class: IntegrationJEI
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

//@JEIPlugin
public class IntegrationJEI{}

//implements IModPlugin {
//
//    public static final String FAST_FURNACE_ID = "TestMod.FastFurnace";
//    public static final String FLOAD_CREATOR_ID = "TestMod.FloadCreator";
//
//    @Override
//    public void register(IModRegistry registry)
//    {
//        registerFastFurnace(registry);
//        registerFloadCreator(registry);
//    }
//
//    private void registerFloadCreator(@Nonnull IModRegistry registry)
//    {
//        IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
//        registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockFloadCreator), FLOAD_CREATOR_ID);
//        registry.addRecipes(Collections.singletonList(new FloadRecipe()), FLOAD_CREATOR_ID);
//        registry.handleRecipes(FloadRecipe.class, floadRecipe -> new FloadRecipeWrapper(), FLOAD_CREATOR_ID);
//
//        transferRegistry.addRecipeTransferHandler(ContainerFloadCreator.class, FLOAD_CREATOR_ID, 0, TileFloadCreator.INPUT_SLOTS, TileFloadCreator.INPUT_SLOTS, 36);
//    }
//
//    private void registerFastFurnace(@Nonnull IModRegistry registry)
//    {
//        registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockFastFurnace), FAST_FURNACE_ID, VanillaRecipeCategoryUid.SMELTING);
//
//        registry.addRecipes(CustomRecipeRegistry.getCustomRecipeList(), FAST_FURNACE_ID);
//        registry.handleRecipes(CustomRecipe.class, CustomRecipeWrapper::new, FAST_FURNACE_ID);
//
//        IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
//        transferRegistry.addRecipeTransferHandler(ContainerFastFurnace.class, VanillaRecipeCategoryUid.SMELTING,
//                0, TileFastFurnace.INPUT_SLOTS, TileFastFurnace.INPUT_SLOTS + TileFastFurnace.OUTPUT_SLOTS, 36);
//        transferRegistry.addRecipeTransferHandler(ContainerFastFurnace.class, FAST_FURNACE_ID,
//                0, TileFastFurnace.INPUT_SLOTS, TileFastFurnace.INPUT_SLOTS + TileFastFurnace.OUTPUT_SLOTS, 36);
//    }
//
//    @Override
//    public void registerCategories(IRecipeCategoryRegistration registry)
//    {
//        IJeiHelpers helpers = registry.getJeiHelpers();
//        IGuiHelper guiHelper = helpers.getGuiHelper();
//
//        registry.addRecipeCategories(new FloadRecipeCategory(guiHelper));
//        registry.addRecipeCategories(new CustomRecipeCategory(guiHelper));
//    }
//}
