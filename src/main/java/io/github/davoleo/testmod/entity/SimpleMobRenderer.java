/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 25/02/2020 / 17:23
 * Class: SimpleMobRenderer
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.entity;

import io.github.davoleo.testmod.TestMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SimpleMobRenderer extends MobRenderer<SimpleMobEntity, SimpleMobModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TestMod.MODID, "textures/entity/simple_mob.png");

    public SimpleMobRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SimpleMobModel(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull SimpleMobEntity entity) {
        return TEXTURE;
    }
}
