package com.davoleo.testmod.init;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.entity.guard.EntityGuard;
import com.davoleo.testmod.entity.guard.RenderGuard;
import com.davoleo.testmod.entity.sphere.EntitySphere;
import com.davoleo.testmod.entity.sphere.RenderSphere;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/02/2019 / 20:19
 * Class: ModEntities
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ModEntities {

    public static void init()
    {
        int id = 1;

        EntityRegistry.registerModEntity(new ResourceLocation(TestMod.MODID, "testmod_guard"), EntityGuard.class, "testmod_guard",
                id++, TestMod.instance, 64, 3, true, 0x222222, 0x555555);
        EntityRegistry.registerModEntity(new ResourceLocation(TestMod.MODID, "testmod_sphere"), EntitySphere.class, "testmod_sphere",
                id++, TestMod.instance, 64, 1, false);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class, RenderGuard.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySphere.class, RenderSphere.FACTORY);
    }

}
