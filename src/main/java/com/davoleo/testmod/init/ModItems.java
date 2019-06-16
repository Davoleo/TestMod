package com.davoleo.testmod.init;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.counter.BlockCounter;
import com.davoleo.testmod.block.fload_creator.BlockFloadCreator;
import com.davoleo.testmod.block.furnace.BlockFastFurnace;
import com.davoleo.testmod.block.generator.BlockGenerator;
import com.davoleo.testmod.block.pedestal.BlockPedestal;
import com.davoleo.testmod.block.tank.BlockTank;
import com.davoleo.testmod.item.ItemAngelIngot;
import com.davoleo.testmod.item.ItemBlockOre;
import com.davoleo.testmod.item.ItemCopperIngot;
import com.davoleo.testmod.item.ItemWand;
import com.davoleo.testmod.memory.BlockPuzzle;
import com.davoleo.testmod.superchest.BlockSuperChest;
import com.davoleo.testmod.superchest.BlockSuperChestPart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
    public static ItemCopperIngot copperIngot = new ItemCopperIngot();
    public static ItemWand wand = new ItemWand();

    public static void registerItems(IForgeRegistry<Item> registry)
    {
        //ItemBlocks
        registry.register(new ItemBlock(ModBlocks.blockFastFurnace).setRegistryName(BlockFastFurnace.FAST_FURNACE));
        registry.register(new ItemBlock(ModBlocks.blockGenerator).setRegistryName(BlockGenerator.GENERATOR));
        registry.register(new ItemBlock(ModBlocks.blockFloadCreator).setRegistryName(BlockFloadCreator.FLOAD_CREATOR));
        registry.register(new ItemBlock(ModBlocks.blockPuzzle).setRegistryName(BlockPuzzle.PUZZLE));
        registry.register(new ItemBlock(ModBlocks.blockTank).setRegistryName(BlockTank.TANK));
        registry.register(new ItemBlock(ModBlocks.blockSuperChest).setRegistryName(BlockSuperChest.SUPERCHEST));
        registry.register(new ItemBlock(ModBlocks.blockSuperChestPart).setRegistryName(BlockSuperChestPart.SUPERCHEST_PART));
        registry.register(new ItemBlock(ModBlocks.blockCopper).setRegistryName(new ResourceLocation(TestMod.MODID, "copper_block")));
        registry.register(new ItemBlock(ModBlocks.blockPedestal).setRegistryName(BlockPedestal.PEDESTAL));
        registry.register(new ItemBlock(ModBlocks.blockCounter).setRegistryName(BlockCounter.COUNTER));

        //Items
        registry.register(angelIngot);
        registry.register(copperIngot);
        registry.register(wand);

        //Ores
        registry.register(new ItemBlockOre(ModBlocks.oreAngel));
        registry.register(new ItemBlockOre(ModBlocks.oreCopper));
        registry.register(new ItemBlockOre(ModBlocks.oreAluminum));
        registry.register(new ItemBlockOre(ModBlocks.oreNetherGold));
        registry.register(new ItemBlockOre(ModBlocks.oreZephyrite));

    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        angelIngot.initModel();
        copperIngot.initModel();
        wand.initModel();
    }

}
