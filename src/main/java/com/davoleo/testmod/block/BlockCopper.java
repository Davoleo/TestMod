package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 02/05/2019 / 19:19
 * Class: BlockCopper
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockCopper extends Block {

    public BlockCopper()
    {
        super(Properties
                .create(Material.IRON)
                .hardnessAndResistance(4F)
                .sound(SoundType.METAL)
        );
        setRegistryName(new ResourceLocation(TestMod.MODID, "copper_block"));
        //TODO 1.13 port
        //setCreativeTab(TestMod.testTab);
        //setHarvestLevel("pickaxe", 1);
    }
}
