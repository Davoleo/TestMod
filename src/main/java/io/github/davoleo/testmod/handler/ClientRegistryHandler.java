/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 06/03/2020 / 17:09
 * Class: ClientRegistryHandler
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.handler;

import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.block.BakedBlockModel;
import io.github.davoleo.testmod.block.ModBlocks;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryHandler {

    //Bake our block texture into the Atlas
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().getBasePath().equals("textures"))
            return;

        event.addSprite(new ResourceLocation(TestMod.MODID, "block/baked_block"));
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        event.getModelRegistry().put(new ModelResourceLocation(ModBlocks.BAKED_BLOCK.getRegistryName(), ""),
                new BakedBlockModel(DefaultVertexFormats.BLOCK));
        event.getModelRegistry().put(new ModelResourceLocation(ModBlocks.BAKED_BLOCK.getRegistryName(), "inventory"),
                new BakedBlockModel(DefaultVertexFormats.ITEM));
    }

}
