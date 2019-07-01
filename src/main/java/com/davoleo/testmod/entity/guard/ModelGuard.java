package com.davoleo.testmod.entity.guard;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/02/2019 / 19:58
 * Class: ModelGuard
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ModelGuard extends ModelBase {

    ModelRenderer body;

    public ModelGuard()
    {
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-8F, -8F, -8F, 16, 16, 16);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    { }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(0F, 0.6F, 0F);
        this.body.render(scale);

        GlStateManager.popMatrix();
    }
}
