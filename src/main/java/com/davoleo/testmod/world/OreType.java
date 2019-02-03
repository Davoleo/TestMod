package com.davoleo.testmod.world;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 16:08
 * Enum: OreType
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public enum OreType implements IStringSerializable {
    ORE_OVERWORLD("overworld"),
    ORE_NETHER("nether"),
    ORE_END("end");

    private final String name;

    OreType(String name)
    {
        this.name = name;
    }

    @Nonnull
    @Override
    public String getName()
    {
        return name;
    }
}