package com.example.examplemod;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.message.ModNetworking;
import com.example.examplemod.setup.ClientSetup;
import com.example.examplemod.setup.Config;
import com.example.examplemod.setup.ModSetup;
import com.example.examplemod.setup.Registration;
import com.example.examplemod.util.ModItemProperties;
import com.example.examplemod.world.feature.ModConfiguredFeatures;
import com.example.examplemod.world.feature.ModPlacedFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
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
        Registration.init();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        Config.register();
        ModSetup.setup();

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        modEventBus.addListener(this::modSetup);
        modEventBus.addListener(this::clientSetup);
        /* Register setups */
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        MinecraftForge.EVENT_BUS.register(this);
    }

//    @SubscribeEvent
//    public static void onEntityAttributeModificationEvent(final EntityAttributeModificationEvent event) {
//        event.add(EntityType.PLAYER, ForgeMod.ATTACK_RANGE.get());
//    }
    private void modSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork( () -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.JASMINE.getId(), ModBlocks.POTTED_JASMINE);
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
//                MenuScreens.register(ModBlocks.MANA_CONTAINER.get(), ExtractorScreen::new);
////                ItemBlockRenderTypes.setRenderLayer(ModBlocks.MANA_EXTRACTOR_BLOCK.get(), RenderType.translucent());
//                ExtractorRender.register();
//            });
//            MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
        }
        @SubscribeEvent
        public static void onEntityAttributeModificationEvent(final EntityAttributeModificationEvent event) {
            event.add(EntityType.PLAYER, ForgeMod.REACH_DISTANCE.get());
        }
    }
}
