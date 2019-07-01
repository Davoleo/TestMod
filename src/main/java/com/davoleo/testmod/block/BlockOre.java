package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.world.EnumOreType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/05/2019 / 18:37
 * Class: BlockOre
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockOre extends Block {

    private String name;
    private final EnumOreType type;

    /**
     * Creates a new instance of a BlockOre
     * @param name the name of the block
     */
    public BlockOre(String name, EnumOreType oreType, int harvestLevel)
    {
        super(Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0F, 5.0F)
        );
        setRegistryName(new ResourceLocation(TestMod.MODID, name));
        //TODO move to itemblock
        //setCreativeTab(TestMod.testTab);
        //TODO 1.13 port
        //setHarvestLevel("pickaxe", harvestLevel);
        this.name = name;
        this.type = oreType;
    }

    //TODO 1.13 port
//    @Override
//    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
//    {
//        for (int i = 0; i < dimensions.length; i++)
//            if (dimensions[i])
//                items.add(new ItemStack(this, 1, i));
//    }
}