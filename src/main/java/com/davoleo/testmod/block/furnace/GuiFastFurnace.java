package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

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

    public GuiFastFurnace(TileFastFurnace te, ContainerFastFurnace container)
    {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
