package com.davoleo.testmod.proxy;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModBlocks;
import com.davoleo.testmod.init.ModEntities;
import com.davoleo.testmod.init.ModItems;
import com.davoleo.testmod.input.KeyBindings;
import com.davoleo.testmod.input.KeyInputHandler;
import com.davoleo.testmod.render.OverlayRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.47
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientProxy implements IProxy {

    @Override
    public void setup(FMLCommonSetupEvent event)
    {
        //Old PreInit
        OBJLoader.INSTANCE.addDomain(TestMod.MODID);
        MinecraftForge.EVENT_BUS.register(OverlayRenderer.instance);

        //Old Init
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        KeyBindings.init();

        //MEybe not needed anymore
        ModBlocks.initModels();
        ModItems.initModels();
        ModEntities.initModels();


    }
//      TODO 1.13 Port
//    @Override
//    public IAnimationStateMachine load(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters)
//    {
//        return ModelLoaderRegistry.loadASM(location, parameters);
//    }
}

