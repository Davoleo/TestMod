package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/06/2019 / 18:59
 * Class: GuiPedestal
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class GuiPedestal extends GuiContainer {

    public static final int WIDTH = 177;
    public static final int HEIGHT = 167;

    private static final ResourceLocation background = new ResourceLocation(TestMod.MODID, "textures/gui/pedestal.png");


    public GuiPedestal(Container containerPedestal)
    {
        super(containerPedestal);

        this.xSize = WIDTH;
        this.ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
