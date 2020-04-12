/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 12/04/2020 / 11:03
 * Class: ModDimensions
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.dimension;

import io.github.davoleo.testmod.TestMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ObjectHolder;

public class ModDimensions {

    public static final ResourceLocation DIMENSION_ID = new ResourceLocation(TestMod.MODID, "dimension");

    @ObjectHolder("testmod:dimension")
    public static ModDimension DIMENSION;

    public static DimensionType DIMENSION_TYPE;

}
