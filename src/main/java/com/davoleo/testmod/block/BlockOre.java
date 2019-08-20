package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.world.EnumOreType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/05/2019 / 18:37
 * Class: BlockOre
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockOre extends Block {

    public static final ResourceLocation ORE_OVERWORLD = new ResourceLocation(TestMod.MODID, "ore_overworld");
    public static final ResourceLocation ORE_NETHER = new ResourceLocation(TestMod.MODID, "ore_nether");
    public static final ResourceLocation ORE_END = new ResourceLocation(TestMod.MODID, "ore_end");

    private String name;
    private final EnumOreType type;

    public BlockOre(String name, EnumOreType oreType)
    {
        super(Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0F, 5.0F)
        );
        setRegistryName(name);
        //TODO move to itemblock
        //setCreativeTab(TestMod.testTab);
        //TODO 1.13 port
        //setHarvestLevel("pickaxe", harvestLevel);
        this.name = name;
        this.type = oreType;
    }

    public String getName() {
        return name;
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