package com.davoleo.testmod.init;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.entity.guard.EntityGuard;
import com.davoleo.testmod.entity.guard.RenderGuard;
import com.davoleo.testmod.entity.sphere.EntitySphere;
import com.davoleo.testmod.entity.sphere.RenderSphere;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ForgeRegistry;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/02/2019 / 20:19
 * Class: ModEntities
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ModEntities {

    public static EntityType<EntityGuard> TYPE_GUARD;
    public static EntityType<EntitySphere> TYPE_SPHERE;

    public static void registerEntities(ForgeRegistry<EntityType<?>> registry)
    {
        int id = 1;

        registry.register(TYPE_GUARD = EntityType.Builder.create(EntityGuard.class, EntityGuard::new).build("testmod_guard"));
        registry.register(TYPE_SPHERE = EntityType.Builder.create(EntitySphere.class, EntitySphere::new).build("testmod_sphere"));

//        EntityRegistry.registerModEntity(new ResourceLocation(TestMod.MODID, "testmod_guard"), EntityGuard.class, "testmod_guard",
//                id++, TestMod.instance, 64, 3, true, 0x222222, 0x555555);
//        EntityRegistry.registerModEntity(new ResourceLocation(TestMod.MODID, "testmod_sphere"), EntitySphere.class, "testmod_sphere",
//                id++, TestMod.instance, 64, 1, false);
    }

    @OnlyIn(Dist.CLIENT)
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class, RenderGuard.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySphere.class, RenderSphere.FACTORY);
    }

}
