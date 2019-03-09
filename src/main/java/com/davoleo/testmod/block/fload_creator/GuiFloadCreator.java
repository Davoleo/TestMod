package com.davoleo.testmod.block.fload_creator;

import com.davoleo.testmod.TestMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 09/03/2019 / 19:32
 * Class: GuiFloadCreator
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class GuiFloadCreator extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(TestMod.MODID, "textures/gui/fload_creator.png");

    public GuiFloadCreator(TileFloadCreator tileEntity, ContainerFloadCreator container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        //FluidStackRenderer.renderFluidStack(new FluidStack(ModFluids.fload, 1000), guiLeft + 30, guiTop + 26);
        renderHoveredToolTip(mouseX, mouseY);
    }

}
