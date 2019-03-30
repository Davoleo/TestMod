package com.davoleo.testmod.render;

import com.davoleo.testmod.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 22:03
 * Class: OverlayRenderer
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class OverlayRenderer {

    public static OverlayRenderer instance = new OverlayRenderer();

    private float omega = 0;
    private float omegaInfluence = 0;
    private float playerOmega = 0;

    public void setOmega(float omega, float omegaInfluence, float playerOmega)
    {
        this.omega = omega;
        this.omegaInfluence = omegaInfluence;
        this.playerOmega = playerOmega;
    }

    public void renderGameOverlayEvent(RenderGameOverlayEvent event)
    {
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE)
            return;

        if (Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() != ModItems.wand)
            return;

        GlStateManager.disableLighting();

        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

        int x = 200;
        int y  = 10;
        x = fontRenderer.drawString("Omega ", x, y, 0xFFFFFFFF);
        x = fontRenderer.drawString("" + omega, x, y, 0xFFF0000);
        x = fontRenderer.drawString(" Influence ", x, y, 0xFFFFFFFF);
        x = fontRenderer.drawString("" + omegaInfluence, x, y, 0xFFFF0000);
        y += 10;
        x = 200;
        x = fontRenderer.drawString("Player ", x, y, 0xFFFFFFFF);
        x = fontRenderer.drawString("" + playerOmega, x, y, 0xFFFF0000);
    }
}
