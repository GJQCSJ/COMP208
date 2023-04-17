package com.example.examplemod.datagen;

import com.example.examplemod.comp208mod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.example.examplemod.comp208mod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new ModRecipes(generator));
        // LootTable generator is still under consideration
        generator.addProvider(event.includeServer(), new ModLootTables(generator));
        ModBlockTags blockTags = new ModBlockTags(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ModItemTags(generator, blockTags, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ModBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ModItemModels(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(generator, "en_us"));
        generator.addProvider(event.includeServer(), new ModBiomeTags(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new ModStructureSetTags(generator, event.getExistingFileHelper()));
    }
}
