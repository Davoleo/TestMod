package io.github.davoleo.testmod.handler;

import io.github.davoleo.testmod.TestMod;
import io.github.davoleo.testmod.block.BakedBlock;
import io.github.davoleo.testmod.block.CopperBlock;
import io.github.davoleo.testmod.block.GeneratorBlock;
import io.github.davoleo.testmod.container.GeneratorContainer;
import io.github.davoleo.testmod.dimension.TestModDimension;
import io.github.davoleo.testmod.entity.SimpleMobEntity;
import io.github.davoleo.testmod.item.ItemIngot;
import io.github.davoleo.testmod.tileentity.BakedBlockTileEntity;
import io.github.davoleo.testmod.tileentity.GeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 01/11/2019 / 18:26
 * Class: RegistrationHandler
 * Project: TestMod
 * Copyright - © - Davoleo - 2019
 **************************************************/

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistrationHandler {

    private static final Item.Properties STANDARD_ITEM_PROPERTIES = new Item.Properties().group(TestMod.testTab);

    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TestMod.MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TestMod.MODID);
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, TestMod.MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, TestMod.MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, TestMod.MODID);
    private static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, TestMod.MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //Entities have to go before items because the egg item requires it
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<CopperBlock> COPPER_BLOCK = BLOCKS.register("copper_block", CopperBlock::new);
    public static final RegistryObject<Item> COPPER_ITEMBLOCK = ITEMS.register("copper_block", () -> new BlockItem(COPPER_BLOCK.get(), STANDARD_ITEM_PROPERTIES));

    public static final RegistryObject<GeneratorBlock> GENERATOR_BLOCK = BLOCKS.register("generator", GeneratorBlock::new);
    public static final RegistryObject<Item> GENERATOR_ITEMBLOCK = ITEMS.register("generator", () -> new BlockItem(GENERATOR_BLOCK.get(), STANDARD_ITEM_PROPERTIES));
    public static final RegistryObject<TileEntityType<GeneratorTileEntity>> GENERATOR_TE =
            TILE_ENTITIES.register("generator", () -> TileEntityType.Builder.create(GeneratorTileEntity::new, GENERATOR_BLOCK.get()).build(null));
    public static final RegistryObject<ContainerType<GeneratorContainer>> GENERATOR_CONTAINER =
            CONTAINERS.register("generator", () ->
                    IForgeContainerType.create((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        return new GeneratorContainer(windowId, TestMod.proxy.getClientWorld(), pos, inv);
                    }));

    public static final RegistryObject<BakedBlock> BAKED_BLOCK = BLOCKS.register("fancy_block", BakedBlock::new);
    public static final RegistryObject<Item> BAKED_ITEMBLOCK = ITEMS.register("fancy_block", () -> new BlockItem(BAKED_BLOCK.get(), STANDARD_ITEM_PROPERTIES));
    public static final RegistryObject<TileEntityType<BakedBlockTileEntity>> BAKED_TE =
            TILE_ENTITIES.register("fancy_block", () -> TileEntityType.Builder.create(BakedBlockTileEntity::new, BAKED_BLOCK.get()).build(null));

    public static final RegistryObject<ItemIngot> COPPER_INGOT = ITEMS.register("copper_ingot", ItemIngot::new);

    public static final RegistryObject<EntityType<SimpleMobEntity>> SIMPLE_MOB = ENTITIES.register("simple_mob",
            () -> EntityType.Builder.create(SimpleMobEntity::new, EntityClassification.CREATURE)
                    .size(1, 1)
                    .setShouldReceiveVelocityUpdates(false)
                    .build("simple_mob"));
    // TODO: 07/06/2020  
    //public static final RegistryObject<SimpleMobEgg> SIMPLE_MOB_EGG_ = ITEMS.register("simple_mob_spawn", SimpleMobEgg::new);
    public static final RegistryObject<SpawnEggItem> SIMPLE_MOB_EGG = ITEMS.register("simple_mob_spawn",
            () -> new SpawnEggItem(SIMPLE_MOB.get(), 0x77FFC8, 0x4C5EFF, STANDARD_ITEM_PROPERTIES));

    public static final RegistryObject<ModDimension> DIMENSION = DIMENSIONS.register("dimension", TestModDimension::new);
}
