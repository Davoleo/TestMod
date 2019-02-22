package com.davoleo.testmod.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/02/2019 / 20:03
 * Class: RenderGuard
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class RenderGuard extends RenderLiving<EntityGuard> {

    private ResourceLocation mobTexture = new ResourceLocation("testmod:textures/entity/guard.png");

    public RenderGuard(RenderManager renderManager)
    {
        super(renderManager, new ModelGuard(), 0.8F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityGuard entity)
    {
        return mobTexture;
    }

    public static final Factory FACTORY = new RenderGuard.Factory();

    public static class Factory implements IRenderFactory<EntityGuard>
    {
        @Override
        public Render<? super EntityGuard> createRenderFor(RenderManager manager)
        {
            return new RenderGuard(manager);
        }
    }
}
