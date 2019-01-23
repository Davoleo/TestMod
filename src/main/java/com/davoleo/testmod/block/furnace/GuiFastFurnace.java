package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 31/12/2018 / 01:44
 * Class: GuiFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class GuiFastFurnace extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(TestMod.MODID, "textures/gui/fast_furnace.png");
    private TileFastFurnace furnace;

    public GuiFastFurnace(TileFastFurnace te, ContainerFastFurnace container)
    {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;

        furnace = te;
    }

    //TODO Needs some tweaks
    private void drawEnergyBar(int energy)
    {
        drawRect(guiLeft + 10, guiTop + 5, guiLeft + 112, guiTop + 15, 0xFF555555);
        int percentage = energy * 100 / TileFastFurnace.MAX_POWER;
        for (int i = 0; i < percentage; i++)
        {
            drawVerticalLine(guiLeft + 10 + 1 + i, guiTop + 5, guiTop + 14, i % 2 == 0 ? 0xFFFF0000 : 0xFF000000);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);

        if (mouseX > guiLeft + 10 && mouseX < guiLeft + 112 && mouseY > guiTop + 5 && mouseY < guiTop + 15)
            drawHoveringText(Collections.singletonList("Energy: " + furnace.getClientEnergy()), mouseX, mouseY, fontRenderer);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int energy = furnace.getClientEnergy();
        drawEnergyBar(energy);

        if (furnace.getClientProgress() > 0)
        {
            int percentage = 100 - furnace.getClientProgress() * 100 / TileFastFurnace.MAX_PROGRESS;
            drawString(mc.fontRenderer, "PROGRESS: " + percentage +"%", guiLeft + 10, guiTop + 20, 0xFFFFFF);
        }
    }
}
