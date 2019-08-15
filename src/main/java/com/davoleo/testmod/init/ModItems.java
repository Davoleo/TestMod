package com.davoleo.testmod.init;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.counter.BlockCounter;
import com.davoleo.testmod.block.fload_creator.BlockFloadCreator;
import com.davoleo.testmod.block.furnace.BlockFastFurnace;
import com.davoleo.testmod.block.generator.BlockGenerator;
import com.davoleo.testmod.block.pedestal.BlockPedestal;
import com.davoleo.testmod.block.tank.BlockTank;
import com.davoleo.testmod.item.*;
import com.davoleo.testmod.item.tool.*;
import com.davoleo.testmod.memory.BlockPuzzle;
import com.davoleo.testmod.superchest.BlockSuperChest;
import com.davoleo.testmod.superchest.BlockSuperChestPart;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
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
    public static ItemCorn corn = new ItemCorn();
    public static ItemCornSeeds cornSeeds = new ItemCornSeeds();
    public static ItemPANINAZZO paninazzo = new ItemPANINAZZO();

    public static ItemCopperAxe copperAxe = new ItemCopperAxe();
    public static ItemCopperHoe copperHoe = new ItemCopperHoe();
    public static ItemCopperPickaxe copperPickaxe = new ItemCopperPickaxe();
    public static ItemCopperShovel copperShovel = new ItemCopperShovel();
    public static ItemCopperSword copperSword = new ItemCopperSword();

    public static ItemArmor copperHelmet = new ItemArmor(TestMod.COPPER_ARMOR_MATERIAL, EntityEquipmentSlot.HEAD, "copper_helmet");
    public static ItemArmor copperChestplate = new ItemArmor(TestMod.COPPER_ARMOR_MATERIAL, EntityEquipmentSlot.CHEST, "copper_chestplate");
    public static ItemArmor copperLeggings = new ItemArmor(TestMod.COPPER_ARMOR_MATERIAL, EntityEquipmentSlot.LEGS, "copper_leggings");
    public static ItemArmor copperBoots = new ItemArmor(TestMod.COPPER_ARMOR_MATERIAL, EntityEquipmentSlot.FEET, "copper_boots");


    public static void registerItems(IForgeRegistry<Item> registry)
    {
        //ItemBlocks
        registry.register(new ItemBlock(ModBlocks.blockFastFurnace, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockFastFurnace.FAST_FURNACE));
        registry.register(new ItemBlock(ModBlocks.blockGenerator, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockGenerator.GENERATOR));
        registry.register(new ItemBlock(ModBlocks.blockFloadCreator, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockFloadCreator.FLOAD_CREATOR));
        registry.register(new ItemBlock(ModBlocks.blockPuzzle, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockPuzzle.PUZZLE));
        registry.register(new ItemBlock(ModBlocks.blockTank, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockTank.TANK));
        registry.register(new ItemBlock(ModBlocks.blockSuperChest, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockSuperChest.SUPERCHEST));
        registry.register(new ItemBlock(ModBlocks.blockSuperChestPart, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockSuperChestPart.SUPERCHEST_PART));
        registry.register(new ItemBlock(ModBlocks.blockCopper, new Item.Properties().group(TestMod.testTab)).setRegistryName(new ResourceLocation(TestMod.MODID, "copper_block")));
        registry.register(new ItemBlock(ModBlocks.blockPedestal, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockPedestal.PEDESTAL));
        registry.register(new ItemBlock(ModBlocks.blockCounter, new Item.Properties().group(TestMod.testTab)).setRegistryName(BlockCounter.COUNTER));

        //Items
        registry.register(angelIngot);
        registry.register(copperIngot);
        registry.register(wand);
        registry.registerAll(corn, cornSeeds);
        registry.register(paninazzo);
        registry.registerAll(copperAxe, copperHoe, copperPickaxe, copperShovel, copperSword);
        registry.registerAll(copperHelmet, copperChestplate, copperLeggings, copperBoots);

        //Ores
        registry.register(new ItemBlockOre(ModBlocks.oreAngel));
        registry.register(new ItemBlockOre(ModBlocks.oreCopper));
        registry.register(new ItemBlockOre(ModBlocks.oreAluminum));
        registry.register(new ItemBlockOre(ModBlocks.oreNetherGold));
        registry.register(new ItemBlockOre(ModBlocks.oreZephyrite));

    }

}
