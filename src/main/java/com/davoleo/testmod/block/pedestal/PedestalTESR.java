package com.davoleo.testmod.block.pedestal;

import com.davoleo.testmod.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/05/2019 / 17:54
 * Class: PedestalTESR
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PedestalTESR extends TileEntityRenderer {

    @Override
    public void render(TileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        // TODO: 20/08/2019 1.13 port
        ItemStack item = /*tileEntityIn.inventory.getStackInSlot(0)*/ new ItemStack(ModItems.angelIngot);

        if (!item.isEmpty())
        {
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA, 1, 0);
            GlStateManager.pushMatrix();
            // TODO: 20/08/2019 1.13 port
            double offset = Math.sin((tileEntityIn.getWorld().getGameTime() - ((TileEntityPedestal) tileEntityIn).lastUpdateTick + partialTicks) / 8 ) / 4.0;
            GlStateManager.translated(x + 0.5, y + 1.25 + (offset/2), z + 0.5);
            GlStateManager.rotatef((tileEntityIn.getWorld().getGameTime() + partialTicks) * 4, 0, 1, 0);

            Minecraft.getInstance().getItemRenderer().renderItem(item, ItemCameraTransforms.TransformType.GROUND);

//            IBakedModel model = Minecraft.getInstance()().getRenderItem().getItemModelWithOverrides(item, te.getWorld(), null);
//            model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

            Minecraft.getInstance().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }
    }
}
