package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.block.ModBlocks;
import com.davoleo.testmod.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date: 12/08/2018
 * Hour: 18.58
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class GuiPedestal extends GuiContainer {

    private InventoryPlayer playerInv;

    public GuiPedestal(Container container, InventoryPlayer playerInv)
    {
        super(container);
        this.playerInv = playerInv;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(4,4,4,100);
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x,y,0,0,xSize,ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String name = I18n.format(ModBlocks.pedestal.getTranslationKey() + ".name");
        fontRenderer.drawString(name, xSize/2 - fontRenderer.getStringWidth(name)/2,6, 0x404040);
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8 ,ySize- 94, 0x404040);
    }

    public static final ResourceLocation BG_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/pedestal.png");

}
