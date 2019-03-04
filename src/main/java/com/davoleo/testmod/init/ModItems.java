package com.davoleo.testmod.init;

import com.davoleo.testmod.block.furnace.BlockFastFurnace;
import com.davoleo.testmod.block.generator.BlockGenerator;
import com.davoleo.testmod.block.tank.BlockTank;
import com.davoleo.testmod.item.ItemAngelIngot;
import com.davoleo.testmod.memory.BlockPuzzle;
import com.davoleo.testmod.world.BlockAngelOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 18:58
 * Class: ModItems
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ModItems {

    public static ItemAngelIngot angelIngot = new ItemAngelIngot();

    public static void registerItems(IForgeRegistry<Item> registry)
    {
        registry.register(new ItemBlock(ModBlocks.blockFastFurnace).setRegistryName(BlockFastFurnace.FAST_FURNACE));
        registry.register(new ItemBlock(ModBlocks.blockGenerator).setRegistryName(BlockGenerator.GENERATOR));
        registry.register(new ItemBlock(ModBlocks.blockPuzzle).setRegistryName(BlockPuzzle.PUZZLE));
        registry.register(new ItemBlock(ModBlocks.blockTank).setRegistryName(BlockTank.TANK));

        registry.register(
                new ItemBlock(ModBlocks.angelOre)
                {
                    @Override
                    public int getMetadata(int damage)
                    {
                        return damage;
                    }
                }
                        .setHasSubtypes(true)
                        .setRegistryName(BlockAngelOre.ANGEL_ORE)
        );
        registry.register(ModItems.angelIngot);
    }

    public static void initModels()
    {
        angelIngot.initModel();
    }

}
