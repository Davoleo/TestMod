/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 08/02/2020 / 17:17
 * Class: LootTablesProvider
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.datagen;

import io.github.davoleo.testmod.block.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LootTablesProvider extends BaseLootTablesProvider {

    public LootTablesProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void registerLootTables() {
        lootTables.put(ModBlocks.COPPER_BLOCK, createStandardTable("copper_block", ModBlocks.COPPER_BLOCK));
        lootTables.put(ModBlocks.GENERATOR_BLOCK, createStandardTable("generator_block", ModBlocks.GENERATOR_BLOCK));
    }
}
