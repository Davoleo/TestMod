package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockBase;
import com.davoleo.testmod.util.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/12/2018 / 22:25
 * Class: BlockFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockFastFurnace extends BlockBase implements IHasModel {

    public BlockFastFurnace()
    {
        super(Material.IRON, "fast_furnace");

        setHarvestLevel("pickaxe", 1);
        setCreativeTab(TestMod.creativeTab);
    }

    @Override
    public void registerModels()
    {
        TestMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
