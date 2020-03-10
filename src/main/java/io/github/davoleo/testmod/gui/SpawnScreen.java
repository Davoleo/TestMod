/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 10/03/2020 / 17:48
 * Class: SpawnScreen
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.network.PacketManager;
import io.github.davoleo.testmod.network.PacketSpawnEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

// TODO: 10/03/2020 Move this screen in the center of the window :P
public class SpawnScreen extends Screen {

    private static final int WIDTH = 179;
    private static final int HEIGHT = 151;

    private ResourceLocation TEXTURE = new ResourceLocation(TestMod.MODID, "textures/gui/spawn_gui.png");
    private int relX;
    private int relY;

    public SpawnScreen() {
        super(new StringTextComponent("Spawn an Entity"));

        relX = (this.width - WIDTH) / 2;
        relY = (this.height - HEIGHT) / 2;
    }

    @Override
    protected void init() {
        addButton(new Button(relX + 10, relY + 10, 160, 20, "Skeleton", button -> spawn("minecraft:skeleton")));
        addButton(new Button(relX + 10, relY + 37, 160, 20, "Zombie", button -> spawn("minecraft:zombie")));
        addButton(new Button(relX + 10, relY + 64, 160, 20, "Cow", button -> spawn("minecraft:cow")));
        addButton(new Button(relX + 10, relY + 91, 160, 20, "Sheep", button -> spawn("minecraft:sheep")));
        addButton(new Button(relX + 10, relY + 118, 160, 20, "Chicken", button -> spawn("minecraft:chicken")));
    }

    private void spawn(String id) {
        PacketManager.INSTANCE.sendToServer(new PacketSpawnEntity(id, minecraft.player.dimension, minecraft.player.getPosition()));
        minecraft.displayGuiScreen(null);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color4f(1F, 1F, 1F, 1F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        this.blit(relX, relY, 0, 0, WIDTH, HEIGHT); //Draws the background (?)
        super.render(mouseX, mouseY, partialTicks);
    }

    public static void open() {
        Minecraft.getInstance().displayGuiScreen(new SpawnScreen());
    }
}
