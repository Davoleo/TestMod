package com.davoleo.testmod.block.pedestal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.pipeline.ForgeBlockModelRenderer;
import org.lwjgl.opengl.GL11;

/*************************************************
 * Author: Davoleo
 * Date: 13/08/2018
 * Hour: 01.05
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TESRPedestal extends TileEntitySpecialRenderer<TileEntityPedestal> {

    @Override
    public void render(TileEntityPedestal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        ItemStack stack = te.inventory.getStackInSlot(0);
        if(!stack.isEmpty())
        {
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA, 1, 0);
            GlStateManager.pushMatrix();
            double offset = Math.sin((te.getWorld().getTotalWorldTime() - te.lastChangeTime + partialTicks) / 8 ) / 4.0;
            GlStateManager.translate(x + 0.5, y + 1.25, z + 0.5);
            GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4, 0, 1, 0);

            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(), null);
            model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }
    }

}
