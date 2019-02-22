package com.davoleo.testmod.init;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.entity.EntityGuard;
import com.davoleo.testmod.entity.RenderGuard;
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

        //noinspection UnusedAssignment
        EntityRegistry.registerModEntity(new ResourceLocation(TestMod.MODID, "testmod_guard"), EntityGuard.class, "testmod_guard",
                id++, TestMod.instance, 64, 3, true, 0x222222, 0x555555);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class, RenderGuard.FACTORY);
    }

}
