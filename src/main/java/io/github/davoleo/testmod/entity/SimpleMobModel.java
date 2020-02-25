/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 25/02/2020 / 17:20
 * Class: SimpleMobModel
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.entity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class SimpleMobModel extends EntityModel<SimpleMobEntity> {

    private RendererModel body;

    public SimpleMobModel() {
        body = new RendererModel(this, 0, 0);
        body.addBox(-3, -3, -3, 6, 6, 6);
    }

    @Override
    public void render(SimpleMobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        body.render(scale);
    }

    @Override
    public void setRotationAngles(SimpleMobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {

    }
}
