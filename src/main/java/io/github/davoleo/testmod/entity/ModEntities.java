/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 25/02/2020 / 17:13
 * Class: ModEntities
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.entity;

import io.github.davoleo.testmod.TestMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

public class ModEntities {

    public static EntityType<SimpleMobEntity> SIMPLE_MOB = EntityType.Builder.create(SimpleMobEntity::new, EntityClassification.CREATURE)
            .size(1, 1)
            .setShouldReceiveVelocityUpdates(false)
            .build("simple_mob");

    static {
        SIMPLE_MOB.setRegistryName(TestMod.MODID, "simple_mob");
    }
}
