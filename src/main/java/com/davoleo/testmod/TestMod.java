package com.davoleo.testmod;

import com.davoleo.testmod.block.generator.DamageTracker;
import com.davoleo.testmod.config.Config;
import com.davoleo.testmod.init.*;
import com.davoleo.testmod.network.Messages;
import com.davoleo.testmod.omega.OmegaTickHandler;
import com.davoleo.testmod.omega.player.PlayerOmega;
import com.davoleo.testmod.omega.player.PlayerPropertyEvents;
import com.davoleo.testmod.proxy.ClientProxy;
import com.davoleo.testmod.proxy.IProxy;
import com.davoleo.testmod.proxy.ServerProxy;
import com.davoleo.testmod.recipe.OreDictHandler;
import com.davoleo.testmod.util.TestTab;
import com.davoleo.testmod.world.OreGenerator;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.nbt.INBTBase;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date: 03/08/2018
 * Hour: 22.34
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

@SuppressWarnings("unused")
@Mod(TestMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TestMod {

    public static final String MODID = "testmod";
    public static final String MODNAME = "Test Mod";

    @SuppressWarnings("Convert2MethodRef")
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public TestMod() {
        FluidRegistry.enableUniversalBucket();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::getClientGuiElement);

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("testmod-client.toml"));
        Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("testmod-server.toml"));
    }

    public static TestTab testTab = new TestTab();

    public static final Logger logger = LogManager.getLogger();

    //Right after the registry events are fired
    private void setup(final FMLCommonSetupEvent event)
    {
        //Old PreInit --------------------------------------------
        Messages.registerMessages("testmod");

        OreGenerator.setup();

        MinecraftForge.EVENT_BUS.register(OmegaTickHandler.instance);
        MinecraftForge.EVENT_BUS.register(PlayerPropertyEvents.instance);

        CapabilityManager.INSTANCE.register(PlayerOmega.class, new Capability.IStorage<PlayerOmega>() {
            @Nullable
            @Override
            public INBTBase writeNBT(Capability<PlayerOmega> capability, PlayerOmega playerOmega, EnumFacing enumFacing)
            {
                throw new UnsupportedOperationException();
            }

            @Override
            public void readNBT(Capability<PlayerOmega> capability, PlayerOmega playerOmega, EnumFacing enumFacing, INBTBase nbtBase)
            {
                throw new UnsupportedOperationException();
            }
        }, () -> null);

        ModFluids.init();

        //old Init
        //Oredict initialization
        OreDictHandler.initOreDictEntries();

        MinecraftForge.EVENT_BUS.register(DamageTracker.instance);

        //old postInit
        //TODO 1.13 Port
        //GameRegistry.addSmelting(ModBlocks.oreAngel, new ItemStack(ModItems.angelIngot, 1), 1F);
        //GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.copperIngot, 1), 1F);
        //GameRegistry.addSmelting(ModBlocks.oreNetherGold, new ItemStack(Items.GOLD_INGOT, 1), 1F);

        proxy.setup(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        ModBlocks.registerTileEntities(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.registerItems(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
    {
        ModEntities.registerEntities(event.getRegistry());
    }

    @SubscribeEvent
    public static void serverStarted(FMLServerStartedEvent e)
    {
        DamageTracker.instance.reset();
    }

    @SubscribeEvent
    public static void serverStopped(FMLServerStoppedEvent e)
    {
        DamageTracker.instance.reset();
    }

//    @SubscribeEvent
//    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
//    {
//        if (event.getModID().equals(MODID))
//            ConfigManager.sync(MODID, Config.Type.INSTANCE);
//    }
}