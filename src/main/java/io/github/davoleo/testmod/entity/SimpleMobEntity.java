/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 25/02/2020 / 17:06
 * Class: SimpleMobEntity
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SimpleMobEntity extends AnimalEntity {

    public SimpleMobEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
        return null;
    }
}
