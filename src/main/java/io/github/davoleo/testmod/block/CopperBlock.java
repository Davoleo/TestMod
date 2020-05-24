package io.github.davoleo.testmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

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
    }
}
