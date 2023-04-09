package com.example.examplemod.setup;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.client.ExtractorRender;
import com.example.examplemod.client.ExtractorScreen;
import com.example.examplemod.comp208mod;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.example.examplemod.comp208mod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static void init (final FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(ModBlocks.MANA_CONTAINER.get(), ExtractorScreen::new);
            ExtractorRender.register();
        });
//        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
    }

//    public static void onTextureStitch(TextureStitchEvent.Pre event){
//        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)){
//            return;
//        }
//        event.addSprite(ExtractorRender.HALO);
//    }
}
