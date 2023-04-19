package com.example.examplemod;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.client.GeneratorModelLoader;
import com.example.examplemod.client.PowerplantRender;
import com.example.examplemod.client.PowerplantScreen;
import com.example.examplemod.entity.ModEntityTypes;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.message.ModNetworking;
import com.example.examplemod.setup.Config;
import com.example.examplemod.util.ModItemProperties;
import com.example.examplemod.world.Structure_Biomes;
import com.example.examplemod.world.dimensions.CustomDimension;
import com.example.examplemod.world.feature.ModConfiguredFeatures;
import com.example.examplemod.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import com.example.examplemod.entity.client.ChomperRenderer;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
//import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(comp208mod.MOD_ID)
public class comp208mod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "comp208mod";
//    public static final String TAB_NAME = "208project";

    // Directly reference a slf4j logger
//    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Logger LOGGER = LogManager.getLogger();

    public comp208mod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        Config.register();
        CommonSetup.setup();

        Structure_Biomes.init();
        ModPlacedFeatures.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModEntityTypes.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::modSetup);
        modEventBus.addListener(this::clientSetup);
        /* Register setups */
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(CommonSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        MinecraftForge.EVENT_BUS.register(this);
    }

//    @SubscribeEvent
//    public static void onEntityAttributeModificationEvent(final EntityAttributeModificationEvent event) {
//        event.add(EntityType.PLAYER, ForgeMod.ATTACK_RANGE.get());
//    }
    private void modSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.CHOMPER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.JASMINE.getId(),  ModBlocks.POTTED_JASMINE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PURPLEGRASS.getId(),  ModBlocks.POTTED_PURPLEGRASS);
            ModNetworking.register();
        });

    }
    private void clientSetup(final FMLClientSetupEvent event){
        ModItemProperties.addCustomItemProperties();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
//            event.enqueueWork(() -> {

//            });
//            MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
            EntityRenderers.register(ModEntityTypes.CHOMPER.get(), ChomperRenderer::new);
        }
        @SubscribeEvent
        public static void onEntityAttributeModificationEvent(final EntityAttributeModificationEvent event) {
            event.add(EntityType.PLAYER, ForgeMod.REACH_DISTANCE.get());
        }
    }
    @Mod.EventBusSubscriber(modid = MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)
    public class CommonSetup {

        public static void setup() {
            IEventBus bus = MinecraftForge.EVENT_BUS;
        }
        public static void init(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                CustomDimension.register();
            });
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class ClientSetup {
        public static void init (final FMLClientSetupEvent event){
            event.enqueueWork(() -> {
                MenuScreens.register(ModBlocks.MANA_CONTAINER.get(), PowerplantScreen::new);
                PowerplantRender.register();
            });
        }
        @SubscribeEvent
        public static void onModelRegistryEvent(ModelEvent.RegisterGeometryLoaders event){
            event.register(GeneratorModelLoader.GENERATOR_LOADER.getPath(), new GeneratorModelLoader());
        }

        @SubscribeEvent
        public static void onTextureStitch(TextureStitchEvent.Pre event){
            if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)){
                return;
            }
            event.addSprite(PowerplantRender.HOVER);
        }
    }


}
