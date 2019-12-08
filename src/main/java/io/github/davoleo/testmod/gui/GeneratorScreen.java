/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 08/12/2019 / 17:53
 * Class: GeneratorScreen
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 * ----------------------------------- */

package io.github.davoleo.testmod.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.container.GeneratorContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GeneratorScreen extends ContainerScreen<GeneratorContainer> {

    private final ResourceLocation GUI_TEXTURE = new ResourceLocation(TestMod.MODID, "textures/gui/generator.png");

    public GeneratorScreen(GeneratorContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
