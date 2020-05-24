/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 08/02/2020 / 17:17
 * Class: LootTablesProvider
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.datagen;

import io.github.davoleo.testmod.handler.RegistrationHandler;
import net.minecraft.data.DataGenerator;

public class LootTablesProvider extends BaseLootTablesProvider {

    public LootTablesProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void registerLootTables() {
        lootTables.put(RegistrationHandler.COPPER_BLOCK.get(), createStandardTable("copper_block", RegistrationHandler.COPPER_BLOCK.get()));
        lootTables.put(RegistrationHandler.GENERATOR_BLOCK.get(), createStandardTable("generator_block", RegistrationHandler.GENERATOR_BLOCK.get()));
        lootTables.put(RegistrationHandler.BAKED_BLOCK.get(), createStandardTable("baked_block", RegistrationHandler.BAKED_BLOCK.get()));
    }
}
