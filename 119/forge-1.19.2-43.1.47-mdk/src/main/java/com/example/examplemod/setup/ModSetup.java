package com.example.examplemod.setup;

import com.example.examplemod.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.example.examplemod.comp208mod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final String TAB_NAME = "208project";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SPODUMENE_IGNOT.get());
        }
    };

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
//        bus.addGenericListener(Entity.class, ManaEvents::onAttachCapabilitiesPlayer);
//        bus.addListener(ManaEvents::onPlayerCloned);
//        bus.addListener(ManaEvents::onRegisterCapabilities);
//        bus.addListener(ManaEvents::onWorldTick);
    }

    public static void init(FMLCommonSetupEvent event) {
//        event.enqueueWork(() -> {
//            Dimensions.register();
//        });
//        Messages.register();
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
//        event.put(Registration.THIEF.get(), ThiefEntity.prepareAttributes().build());
    }
}