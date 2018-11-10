package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.item.tool.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 23.05
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModItems {

    //List of every item
    public static final List<Item> ITEMS = new ArrayList<>();

    //Item declaration
    public static ItemOre ingotCopper = new ItemOre("copper_ingot","ingotCopper");
    public static ItemPaninazzo paninazzo = new ItemPaninazzo();

    //Item Tools declaration
    public static ItemSword copperSword = new ItemSword(TestMod.copperToolMaterial, "copper_sword");
    public static ItemPickaxe copperPickaxe = new ItemPickaxe(TestMod.copperToolMaterial, "copper_pickaxe");
    public static ItemAxe copperAxe = new ItemAxe(TestMod.copperToolMaterial, "copper_axe");
    public static ItemShovel copperShovel = new ItemShovel(TestMod.copperToolMaterial,  "copper_shovel");
    public static ItemHoe copperHoe = new ItemHoe(TestMod.copperToolMaterial, "copper_hoe");

    //Item Armors declaration
    public static ItemArmor copperHelmet = new ItemArmor(TestMod.copperArmorMaterial, EntityEquipmentSlot.HEAD, "copper_helmet");
    public static ItemArmor copperChestplate = new ItemArmor(TestMod.copperArmorMaterial, EntityEquipmentSlot.CHEST, "copper_chestplate");
    public static ItemArmor copperLeggings = new ItemArmor(TestMod.copperArmorMaterial, EntityEquipmentSlot.LEGS, "copper_leggings");
    public static ItemArmor copperBoots = new ItemArmor(TestMod.copperArmorMaterial, EntityEquipmentSlot.FEET, "copper_boots");


    //Registers items in the Forge Registry
    public static void register(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                ingotCopper,
                paninazzo,
                copperSword,
                copperPickaxe,
                copperAxe,
                copperShovel,
                copperHoe,
                copperHelmet,
                copperChestplate,
                copperLeggings,
                copperBoots
        );
    }
}
