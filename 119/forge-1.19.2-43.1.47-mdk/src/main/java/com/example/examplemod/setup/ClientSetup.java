package com.example.examplemod.setup;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.client.GeneratorModelLoader;
import com.example.examplemod.client.PowerplantRender;
import com.example.examplemod.client.PowerplantScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.antlr.v4.codegen.model.ModelElement;

import static com.example.examplemod.comp208mod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static void init (final FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(ModBlocks.MANA_CONTAINER.get(), PowerplantScreen::new);
            PowerplantRender.register();
        });
//        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
    }
    @SubscribeEvent
    public static void onModelRegistryEvent(ModelEvent.RegisterGeometryLoaders event){
        event.register(GeneratorModelLoader.GENERATOR_LOADER.getPath(), new GeneratorModelLoader());
    }

//    public static void onTextureStitch(TextureStitchEvent.Pre event){
//        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)){
//            return;
//        }
//        event.addSprite(ExtractorRender.HALO);
//    }
}
