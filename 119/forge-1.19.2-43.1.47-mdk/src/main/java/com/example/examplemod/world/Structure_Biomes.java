package com.example.examplemod.world;

import com.example.examplemod.comp208mod;
import com.example.examplemod.world.ores.CustomBiomeModifier;
import com.example.examplemod.world.structures.NewPortalStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.example.examplemod.world.ores.CustomBiomeModifier.CUSTOM_BIOME_MODIFIER_NAME;

public class Structure_Biomes {
    private static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, comp208mod.MOD_ID);
    private static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, comp208mod.MOD_ID);
    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        STRUCTURES.register(bus);
        BIOME_MODIFIERS.register(bus);
    }
    private static <S extends Structure>StructureType<S> typeConvert(Codec<S> codec){
        return () -> codec;
    }
    public static final ResourceLocation CUSTOM_DIMENSION_SET = new ResourceLocation(comp208mod.MOD_ID, "custom_dimension_structure_set");
    public static final RegistryObject<Codec<? extends BiomeModifier>> CUSTOM_BIOME_MODIFIER = BIOME_MODIFIERS.register(CUSTOM_BIOME_MODIFIER_NAME, CustomBiomeModifier::makeCodec);
    public static final RegistryObject<StructureType<?>> PORTAL = STRUCTURES.register("portal", () -> typeConvert(NewPortalStructure.CODEC));
}
