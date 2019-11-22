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
 * Date / Hour: 22/11/2019 / 22:13
 * Class: GeneratorBlock
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class GeneratorBlock extends Block {

    public GeneratorBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2F)
        );

        setRegistryName(new ResourceLocation(TestMod.MODID, "generator"));
    }

    public Item createItemBlock() {
        return new BlockItem(this, new Item.Properties().group(TestMod.setup.testTab)).setRegistryName(this.getRegistryName());
    }
}
