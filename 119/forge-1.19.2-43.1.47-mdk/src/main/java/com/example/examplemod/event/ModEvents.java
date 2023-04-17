package com.example.examplemod.event;

import com.example.examplemod.comp208mod;
import com.example.examplemod.entity.ModEntityTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = comp208mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.CHOMPER.get(), net.kaupenjoe.tutorialmod.entity.custom.ChomperEntity.setAttributes());
        }
    }
}
