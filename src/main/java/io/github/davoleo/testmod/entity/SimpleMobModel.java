/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 25/02/2020 / 17:20
 * Class: SimpleMobModel
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.Nonnull;

public class SimpleMobModel extends EntityModel<SimpleMobEntity> {

    private ModelRenderer body;

    public SimpleMobModel() {
        body = new ModelRenderer(this, 0, 0);
        body.addBox(-3, -3, -3, 6, 6, 6);
    }

    @Override
    public void setRotationAngles(@Nonnull SimpleMobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(@Nonnull MatrixStack matrixStackIn, @Nonnull IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
