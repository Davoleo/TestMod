package com.davoleo.testmod.memory;

import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 25/02/2019 / 20:50
 * Class: PuzzleTESR
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class PuzzleTESR extends TileEntityRenderer<TilePuzzle> {

    @Override
    public void render(TilePuzzle tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        IBlockState state = tileEntityIn.getWorld().getBlockState(tileEntityIn.getPos());
        Block block = state.getBlock();
        if (!(block instanceof BlockTEBase))
            return;

        Minecraft.getInstance().entityRenderer.disableLightmap();

        GlStateManager.pushMatrix();
        GlStateManager.translatef((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
        setupRotation(state);
        GlStateManager.translatef(0F, 0F, 0.9F);

        GlStateManager.depthMask(true);
        GlStateManager.enableDepthTest();

        if (tileEntityIn.isSolved())
            renderSlotHighlight();
        renderSlot(tileEntityIn);

        GlStateManager.popMatrix();
        Minecraft.getInstance().entityRenderer.enableLightmap();
    }

    private void setupRotation(IBlockState state)
    {
        EnumFacing facing = state.get(BlockPuzzle.FACING);
        if (facing == EnumFacing.UP) {
            GlStateManager.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translatef(0.0F, 0.0F, -0.68F);
        } else if (facing == EnumFacing.DOWN) {
            GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translatef(0.0F, 0.0F, -.184F);
        } else {
            float rotY = 0.0F;
            if (facing == EnumFacing.NORTH) {
                rotY = 180.0F;
            } else if (facing == EnumFacing.WEST) {
                rotY = 90.0F;
            } else if (facing == EnumFacing.EAST) {
                rotY = -90.0F;
            }
            GlStateManager.rotatef(-rotY, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(0.0F, -0.2500F, -0.4375F);
        }
    }

    private void renderSlot(TilePuzzle te)
    {
        ItemStack stack = te.getItem();
        if (stack.isEmpty())
            return;

        RenderHelper.enableGUIStandardItemLighting();
        float factor = 4F;
        float f3 = 0.0075F;
        GlStateManager.translatef(-0.5F, 0.5F, -0.15F);
        GlStateManager.scalef(f3 * factor, -f3 * factor, 0.0001F);

        ItemRenderer itemRender = Minecraft.getInstance().getItemRenderer();
        itemRender.renderItemAndEffectIntoGUI(stack, 8, 8);

        RenderHelper.enableStandardItemLighting();
    }

    private void renderSlotHighlight()
    {
        GlStateManager.pushMatrix();

        float factor = 4.0f;
        float f3 = 0.0075F;
        GlStateManager.translatef(-0.5F, 0.5F, -0.155F);
        GlStateManager.scalef(f3 * factor, -f3 * factor, f3);
        GlStateManager.disableLighting();

        Gui.drawRect(8-3, 8-3, 8 + 21, 8 + 21, 0x5566ff66);

        GlStateManager.popMatrix();
    }


}
