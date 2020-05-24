/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 08/02/2020 / 17:04
 * Class: RecipesProvider
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.datagen;

import io.github.davoleo.testmod.handler.RegistrationHandler;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class RecipesProvider extends RecipeProvider {

    public RecipesProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(RegistrationHandler.COPPER_BLOCK.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', RegistrationHandler.COPPER_INGOT.get())
                .setGroup("")
                .addCriterion("copper_ingot", InventoryChangeTrigger.Instance.forItems(RegistrationHandler.COPPER_INGOT.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(RegistrationHandler.COPPER_INGOT.get(), 9)
                .addIngredient(RegistrationHandler.COPPER_BLOCK.get())
                .setGroup("")
                .addCriterion("copper_block", InventoryChangeTrigger.Instance.forItems(RegistrationHandler.COPPER_BLOCK.get()))
                .build(consumer);
    }
}
