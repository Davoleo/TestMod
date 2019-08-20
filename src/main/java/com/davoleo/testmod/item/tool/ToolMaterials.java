package com.davoleo.testmod.item.tool;

import com.davoleo.testmod.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 20/08/2019 / 14:06
 * Enum: ToolMaterials
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public enum ToolMaterials implements IItemTier {

    COPPER(5F, 6F, 500,2, 14, ModItems.copperIngot);

    private float attackDamage;
    private float efficiency;
    private int durability;
    private int harvestLevel;
    private int enchantability;
    private Item repairMaterial;

    ToolMaterials(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial) {
        this.attackDamage = attackDamage;
        this.efficiency = efficiency;
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return this.durability;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairMaterial);
    }
}
