/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 08/02/2020 / 17:19
 * Class: BaseLootTablesProvider
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseLootTablesProvider extends LootTableProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public BaseLootTablesProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    protected abstract void registerLootTables();

    protected LootTable.Builder createStandardTable(String name, Block block) {
        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                        .acceptFunction(CopyNbt.func_215881_a(CopyNbt.Source.BLOCK_ENTITY) //builder()
                                .func_216055_a("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)
                                .func_216055_a("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
                        .acceptFunction(SetContents.func_215920_b()
                                .func_216075_a(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents"))))
        );
        return LootTable.builder().addLootPool(builder);
    }

    @Override
    public void act(@Nonnull DirectoryCache cache) {
        registerLootTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        writeLootTables(cache, tables);
    }

    private void writeLootTables(DirectoryCache cache, Map<ResourceLocation, LootTable> lootTableMap) {
        Path outputFolder = this.generator.getOutputFolder();
        lootTableMap.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Nonnull
    @Override
    public String getName() {
        return "Testmod LootTables";
    }
}
