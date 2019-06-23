package com.davoleo.testmod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/04/2019 / 16:01
 * Class: FluidStackRenderer
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class FluidStackRenderer {

    public static boolean renderFluidStack(FluidStack fluidStack, int x, int y)
    {
        Fluid fluid = fluidStack.getFluid();
        if (fluid == null)
            return false;

        Minecraft mc = Minecraft.getMinecraft();
        TextureMap textureMapBlocks = mc.getTextureMapBlocks();
        ResourceLocation fluidStill = fluid.getStill();
        TextureAtlasSprite fluidStillSprite = null;
        if (fluidStill == null)
            fluidStillSprite = textureMapBlocks.getTextureExtry(fluidStack.toString());
        if (fluidStillSprite == null)
            fluidStillSprite = textureMapBlocks.getMissingSprite();

        int color = fluid.getColor(fluidStack);
        mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        setGLColorFromInt(color);
        drawFluidTexture(x, y, fluidStillSprite);

        return true;
    }

    private static void drawFluidTexture(double x, double y, TextureAtlasSprite sprite)
    {
        double uMin = sprite.getMinU();
        double uMax = sprite.getMaxU();
        double vMin = sprite.getMinV();
        double vMax = sprite.getMaxV();
        double zLevel = 100;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexBuffer = tessellator.getBuffer();
        vertexBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(x, y + 16, zLevel).tex(uMin, vMax).endVertex();
        vertexBuffer.pos(x + 16, y + 16, zLevel).tex(uMax, vMax).endVertex();
        vertexBuffer.pos(x + 16, y, zLevel).tex(uMax, vMin).endVertex();
        vertexBuffer.pos(x, y, zLevel).tex(uMin, vMin).endVertex();
        tessellator.draw();
    }

    private static void setGLColorFromInt(int color) {
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;

        GlStateManager.color(red, green, blue, 1.0F);
    }
}
