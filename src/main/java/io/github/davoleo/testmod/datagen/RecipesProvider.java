/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 08/02/2020 / 17:04
 * Class: RecipesProvider
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.datagen;

import io.github.davoleo.testmod.block.ModBlocks;
import io.github.davoleo.testmod.item.ModItems;
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
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.COPPER_BLOCK)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', ModItems.copperIngot)
                .setGroup("")
                .addCriterion("copper_ingot", InventoryChangeTrigger.Instance.forItems(ModItems.copperIngot))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.copperIngot, 9)
                .addIngredient(ModBlocks.COPPER_BLOCK)
                .setGroup("")
                .addCriterion("copper_block", InventoryChangeTrigger.Instance.forItems(ModBlocks.COPPER_BLOCK))
                .build(consumer);
    }
}
