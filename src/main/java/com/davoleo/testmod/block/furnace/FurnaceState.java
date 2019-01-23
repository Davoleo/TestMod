package com.davoleo.testmod.block.furnace;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 23/01/2019 / 20:57
 * Enum: FurnaceState
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

//IStringSerializable interface allows the enum strings to be used as blockstates
public enum FurnaceState implements IStringSerializable {

    IDLE("idle"),
    WORKING("working"),
    NO_POWER("no_power");

    public static final FurnaceState[] VALUES = FurnaceState.values();

    private final String name;

    FurnaceState(String name)
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
