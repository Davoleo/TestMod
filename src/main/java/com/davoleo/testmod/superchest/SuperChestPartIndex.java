package com.davoleo.testmod.superchest;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 12/03/2019 / 23:32
 * Enum: SuperChestPartIndex
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public enum SuperChestPartIndex implements IStringSerializable {

    UNFORMED("unformed", 0, 0, 0),
    P000("p000", 0, 0, 0),
    P001("p001", 0, 0, 1),
    P010("p010", 0, 1, 0),
    P011("p011", 0, 1, 1),
    P100("p100", 1, 0, 0),
    P101("p101", 1, 0, 1),
    P110("p110", 1, 1, 0),
    P111("p111", 1, 1, 1);

    public static final SuperChestPartIndex[] VALUES = SuperChestPartIndex.values();

    private final String name;
    private final int dx;
    private final int dy;
    private final int dz;

    SuperChestPartIndex(String name, int dx, int dy, int dz)
    {
        this.name = name;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public static SuperChestPartIndex getIndex(int dx, int dy, int dz)
    {
        return VALUES[dx*4 + dy*2 + dz + 1];
    }

    @Nonnull
    @Override
    public String getName()
    {
        return name;
    }

    public int getDx()
    {
        return dx;
    }

    public int getDy()
    {
        return dy;
    }

    public int getDz()
    {
        return dz;
    }
}