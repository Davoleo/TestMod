package com.davoleo.testmod.world;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/02/2019 / 16:08
 * Enum: EnumOreType
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public enum EnumOreType implements IStringSerializable {
    ORE_OVERWORLD("overworld"),
    ORE_NETHER("nether"),
    ORE_END("end");

    private final String name;

    EnumOreType(String name)
    {
        this.name = name;
    }

    @Nonnull
    @Override
    public String getName()
    {
        return name;
    }

    public static String namebyIndex(int index)
    {
        for (EnumOreType type : EnumOreType.values())
            if (type.ordinal() == index)
                return type.getName();
        return "";
    }
}