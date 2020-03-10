package io.github.davoleo.testmod.block;

import io.github.davoleo.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 01/11/2019 / 18:37
 * Class: CopperBlock
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class CopperBlock extends Block {

    public CopperBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2F)
        );

        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_block"));
    }

    public Item createItemBlock() {
        return new BlockItem(this, new Item.Properties().group(TestMod.testTab)).setRegistryName(this.getRegistryName());
    }
}
