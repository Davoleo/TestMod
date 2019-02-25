package com.davoleo.testmod.memory;

import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 25/02/2019 / 20:50
 * Class: PuzzleTESR
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@SideOnly(Side.CLIENT)
public class PuzzleTESR extends TileEntitySpecialRenderer<TilePuzzle> {

    @Override
    public void render(TilePuzzle te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        IBlockState state = te.getWorld().getBlockState(te.getPos());
        Block block = state.getBlock();
        if (!(block instanceof BlockTEBase))
            return;

        Minecraft.getMinecraft().entityRenderer.disableLightmap();

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x + 0.5, (float) y + 0.75, (float) z + 0.5);
        setupRotation(state);
        GlStateManager.translate(0, 0, 0.9);

        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();

        if (te.isSolved())
            renderSlotHighlight();
        renderSlot(te);

        GlStateManager.popMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap();
    }

    private void setupRotation(IBlockState state)
    {
        EnumFacing facing = state.getValue(BlockPuzzle.FACING);
        if (facing == EnumFacing.UP)
        {
            GlStateManager.rotate(-90, 1, 0, 0);
            GlStateManager.translate(0, 0, 0.68);
        }
        else if (facing == EnumFacing.DOWN)
        {
            GlStateManager.rotate(90, 1, 0, 0);
            GlStateManager.translate(0, 0, -0.184);
        }
        else {
            float rotY = 0F;
            if (facing == EnumFacing.NORTH)
                rotY = 180;
            else if (facing == EnumFacing.WEST)
                rotY = 90;
            else if (facing == EnumFacing.EAST)
                rotY = -90;
            GlStateManager.rotate(-rotY, 0, 1, 0);
            GlStateManager.translate(0, -0.2500, -0.4375);
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
        GlStateManager.translate(-0.5, 0.5, 0.15);
        GlStateManager.scale(f3 * factor, -f3 * factor, 0.0001F);

        RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
        itemRender.renderItemAndEffectIntoGUI(stack, 8, 8);

        RenderHelper.enableStandardItemLighting();
    }

    private void renderSlotHighlight()
    {
        GlStateManager.pushMatrix();

        float factor = 4F;
        float f3 = 0.0075F;
        GlStateManager.translate(-0.5, 0.5, 0.15);
        GlStateManager.scale(f3 * factor, -f3 * factor, f3);
        GlStateManager.disableLighting();

        Gui.drawRect(8-3, 8-3, 8+21, 8+21, 0x5566FF66);

        GlStateManager.popMatrix();

    }


}
