/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 25/05/2020 / 00:22
 * Class: CommonModSetup
 * Project: TestMod
 * Copyright - © - Davoleo - 2020
 * ----------------------------------- */

package io.github.davoleo.testmod.handler;

import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.command.ModCommands;
import io.github.davoleo.testmod.dimension.ModDimensions;
import io.github.davoleo.testmod.network.PacketManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonModSetup {

    public static void init(final FMLCommonSetupEvent event) {
        TestMod.LOGGER.info("TESTMOD: Pre-Initialization");
        PacketManager.registerMessages();

        TestMod.testTab = new ItemGroup("test_tab") {
            @Nonnull
            @Override
            public ItemStack createIcon() {
                return new ItemStack(RegistrationHandler.COPPER_INGOT.get());
            }
        };
    }

    @SubscribeEvent
    public static void onServerLoad(FMLServerStartingEvent event) {
        ModCommands.register(event.getCommandDispatcher());
    }

    @SubscribeEvent
    public static void onDimensionRegistry(RegisterDimensionsEvent event) {
        ModDimensions.DIMENSION_TYPE = DimensionManager.registerOrGetDimension(ModDimensions.DIMENSION_ID, RegistrationHandler.DIMENSION.get(), null, true);
    }
}
