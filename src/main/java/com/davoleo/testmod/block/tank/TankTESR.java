package com.davoleo.testmod.block.tank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 04/03/2019 / 18:27
 * Class: TankTESR
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class TankTESR extends TileEntityRenderer {

    public static final float TANK_THICKNESS = 0.05F;

    public TankTESR()
    { }

    @Override
    public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.disableBlend();
        GlStateManager.translate(x, y, z);

        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        renderFluid((TileTank)te);

        GlStateManager.popMatrix();
    }

    private void renderFluid(TileTank te)
    {
        if (te == null)
            return;

        FluidStack fluid = te.getTank().getFluid();
        if (fluid == null)
            return;

        Fluid fluidToRender = fluid.getFluid();
        if (fluidToRender == null)
            return;

        float scale = (1F - TANK_THICKNESS/2 - TANK_THICKNESS) * fluid.amount / (te.getTank().getCapacity());

        if (scale > 0F)
        {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder renderer = tessellator.getBuffer();
            ResourceLocation still = fluidToRender.getStill();
            TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(still.toString());

            RenderHelper.disableStandardItemLighting();

            GlStateManager.color(1, 1, 1, 0.5F);
            renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

            float u1 = sprite.getMinU();
            float u2 = sprite.getMaxU();
            float v1 = sprite.getMinV();
            float v2 = sprite.getMaxV();

            //Top
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();

            // Bottom
            renderer.pos(1-TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(1-TANK_THICKNESS, TANK_THICKNESS, 1-TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1-TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();

            // Sides
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1-TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1-TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1-TANK_THICKNESS, TANK_THICKNESS, 1-TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1-TANK_THICKNESS, scale + TANK_THICKNESS, 1-TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();

            renderer.pos(1-TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(1-TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();

            renderer.pos(1-TANK_THICKNESS, scale + TANK_THICKNESS, 1-TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(1-TANK_THICKNESS, TANK_THICKNESS, 1-TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1-TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1-TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();

            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1-TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1-TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();

            tessellator.draw();
            RenderHelper.enableStandardItemLighting();

        }

    }
}
