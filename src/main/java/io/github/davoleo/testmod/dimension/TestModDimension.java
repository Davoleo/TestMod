/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 12/04/2020 / 11:08
 * Class: TestDimension
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.dimension;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

public class TestModDimension extends ModDimension {

    public TestModDimension() {
        setRegistryName(ModDimensions.DIMENSION_ID);
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return TestDimension::new;
    }
}
