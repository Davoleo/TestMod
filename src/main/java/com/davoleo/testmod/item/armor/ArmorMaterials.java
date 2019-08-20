package com.davoleo.testmod.item.armor;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModItems;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 20/08/2019 / 14:32
 * Enum: ArmorMaterials
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public enum ArmorMaterials implements IArmorMaterial {
    COPPER("copper", 200, new int[]{2,5,6,2}, 9, ModItems.copperIngot, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0F);


    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private String name;
    private int durability;
    private int[] damageReduction;
    private int enchantability;
    private Item repairItem;
    private SoundEvent equipSound;
    private float toughness;

    ArmorMaterials(String name, int durability, int[] damageReduction, int enchantability, Item repairItem, SoundEvent equipSound, float toughness) {
        this.name = name;
        this.durability = durability;
        this.damageReduction = damageReduction;
        this.enchantability = enchantability;
        this.repairItem = repairItem;
        this.equipSound = equipSound;
        this.toughness = toughness;
    }

    @Override
    public int getDurability(@Nonnull EntityEquipmentSlot slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * durability;
    }

    @Override
    public int getDamageReductionAmount(@Nonnull EntityEquipmentSlot slotIn) {
        return damageReduction[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Nonnull
    @Override
    public SoundEvent getSoundEvent() {
        return equipSound;
    }

    @Nonnull
    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(repairItem);
    }

    @Nonnull
    @Override
    public String getName() {
        return TestMod.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }
}
