package com.davoleo.testmod.block.generator;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.config.GeneratorConfig;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/02/2019 / 18:12
 * Class: GuiGenerator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class GuiGenerator extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(TestMod.MODID, "textures/gui/generator.png");
    private TileGenerator tileEntity;

    public GuiGenerator(TileGenerator tileEntity, ContainerGenerator container)
    {
        super(container);

        this.xSize = WIDTH;
        this.ySize = HEIGHT;

        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int energy = tileEntity.getClientEnergy();
        drawEnergyBar(energy);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);

        if (mouseX > guiLeft + 10 && mouseX < guiLeft + 112 && mouseY > guiTop + 5 && mouseY < guiTop + 15)
            drawHoveringText(Collections.singletonList("Energy: " + tileEntity.getClientEnergy()), mouseX, mouseY, fontRenderer);
    }

    private void drawEnergyBar(int energy)
    {
        drawRect(guiLeft + 10, guiTop + 5, guiLeft + 112, guiTop + 15, 0xFF555555);
        int percentage = energy * 100 / GeneratorConfig.MAX_POWER;
        for (int i = 0; i<percentage; i++)
            drawVerticalLine(guiLeft + 10 + i, guiTop + 5, guiTop + 14, i % 2 == 0 ? 0xFFFF0000 : 0xFF000000);
    }
}
