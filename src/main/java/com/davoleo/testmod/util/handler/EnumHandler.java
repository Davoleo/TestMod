package com.davoleo.testmod.util.handler;

import net.minecraft.util.IStringSerializable;

/*************************************************
 * Author: Leonardo
 * Date: 09/11/2018
 * Hour: 22.56
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class EnumHandler {

    public static enum EnumType implements IStringSerializable
    {
        COPPER(0, "copper"),
        ALUMINUM(1, "aluminum"),
        NETHER_GOLD(2, "nether_gold"),
        ZEPHYRITE(3, "zephyrite");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name, unlocalizedName;

        private EnumType(int meta, String name)
        {
            this(meta, name, name);
        }

        private EnumType(int meta, String name, String unlocalizedName)
        {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMeta()
        {
            return this.meta;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        @Override
        public String toString()
        {
            return this.name;
        }

        public static EnumType byMetadata(int meta)
        {
            return META_LOOKUP[meta];
        }

        static
        {
            for (EnumType enumtype : values())
            {
                META_LOOKUP[enumtype.getMeta()] = enumtype;
            }    
        }
    }

}
