package com.example.examplemod.world.dimensions;

import com.example.examplemod.world.Structure_Biomes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;


public class CustomChunkGenerator extends ChunkGenerator {
    private static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.create(settingsInstance ->
            settingsInstance.group(
                    Codec.INT.fieldOf("base")
                            .forGetter(Settings::bottomHeight),
                    Codec.FLOAT.fieldOf("vertical_variance")
                            .forGetter(Settings::verticalVariance),
                    Codec.FLOAT.fieldOf("horizontal_variance")
                            .forGetter(Settings::horizontalVariance)
            ).apply(settingsInstance, Settings::new));

    public static final Codec<CustomChunkGenerator> CODEC = RecordCodecBuilder.create(customChunkGeneratorInstance ->
            customChunkGeneratorInstance.group(
                    RegistryOps.retrieveRegistry(Registry.STRUCTURE_SET_REGISTRY)
                            .forGetter(CustomChunkGenerator::getStructureSetRegistry),
                    RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY)
                            .forGetter(CustomChunkGenerator::getBiomeRegistry),
                    SETTINGS_CODEC.fieldOf("settings").forGetter(CustomChunkGenerator::getModSettings)
            ).apply(customChunkGeneratorInstance, CustomChunkGenerator::new));

    private final Settings settings;

    public CustomChunkGenerator(Registry<StructureSet> structureSets, Registry<Biome> registry, Settings settings){
        super(structureSets, getSet(structureSets), new CustomBiomeProvider(registry));
        this.settings = settings;
    }
    private static Optional<HolderSet<StructureSet>> getSet(Registry<StructureSet> structureSetRegistry){
        HolderSet.Named<StructureSet> structureSetNamed = structureSetRegistry.getOrCreateTag(TagKey.create(Registry.STRUCTURE_SET_REGISTRY,
                Structure_Biomes.CUSTOM_DIMENSION_SET));
        return Optional.of(structureSetNamed);
    }

    public Settings getModSettings(){
        return settings;
    }

    public Registry<Biome> getBiomeRegistry(){
        return ((CustomBiomeProvider)biomeSource).getBiomeRegistry();
    }

    public Registry<StructureSet> getStructureSetRegistry(){
        return structureSets;
    }

    private int getAlt(int bottomHeight, float verticalVariance, float horizontalVariance, int x, int z){
        return (int) (bottomHeight + Math.sin(x / horizontalVariance) * verticalVariance + Math.cos(z / horizontalVariance) * verticalVariance);
    }

    //This buildSurface() is used to generate new terrain for the dimension
    @Override
    public void buildSurface(WorldGenRegion region, StructureManager manager, RandomState state, ChunkAccess chunkAccess){
        BlockState bedrock = Blocks.BEDROCK.defaultBlockState();
        BlockState stone = Blocks.STONE.defaultBlockState();
        ChunkPos chunkPos = chunkAccess.getPos();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int x;
        int z;

        for (x = 0; x < 16; x++){
            for (z = 0; z < 16; z++){
                chunkAccess.setBlockState(pos.set(x, 0, z), bedrock, false);
            }
        }

        int bottomHeight = settings.bottomHeight();
        float verticalVariance = settings.verticalVariance();
        float horizontalVariance = settings.horizontalVariance();
        for (x = 0; x < 16; x++){
            for (z = 0; z < 16; z++){
                int real_x = chunkPos.x * 16 + x;
                int real_z = chunkPos.z * 16 + z;
                int height = getAlt(bottomHeight, verticalVariance, horizontalVariance, real_x, real_z);
                for (int i = 0; i < height; i++){
                    chunkAccess.setBlockState(pos.set(x, i, z), stone, false);
                }
            }
        }
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec(){
        return CODEC;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess){
        return CompletableFuture.completedFuture(chunkAccess);
    }

    //Structures and features in mod will need to use these following two methods to be correctly implemented
    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor, RandomState randomState){
        int bottomHeight = settings.bottomHeight();
        float verticalVariance = settings.verticalVariance();
        float horizontalVariance = settings.horizontalVariance();
        return getAlt(bottomHeight, verticalVariance, horizontalVariance, x, z);
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState){
        int y = getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor, randomState);
        BlockState stone = Blocks.STONE.defaultBlockState();
        BlockState[] states = new BlockState[y];
        //The bottom of a chunk should always be composed of bedrocks
        states[0] = Blocks.BEDROCK.defaultBlockState();
        for (int i = 1; i < y; i++){
            states[i] = stone;
            if (i > 96){
                states[i] = Blocks.GRASS.defaultBlockState();
            }
        }
        return new NoiseColumn(levelHeightAccessor.getMinBuildHeight(), states);
    }

    @Override
    public void applyCarvers(WorldGenRegion region, long seed, RandomState state, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving){

    }

    //Spawn mobs for a chunk
    @Override
    public void spawnOriginalMobs(WorldGenRegion level){
        ChunkPos chunkPos = level.getCenter();
        Holder<Biome> biome = level.getBiome(chunkPos.getWorldPosition().atY(level.getMaxBuildHeight() - 1));
        WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.generateUniqueSeed()));
        worldgenRandom.setDecorationSeed(level.getSeed(), chunkPos.getMinBlockX(), chunkPos.getMinBlockZ());
        NaturalSpawner.spawnMobsForChunkGeneration(level, biome, chunkPos, worldgenRandom);
    }

    @Override
    public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos pos){
    }

    @Override
    public int getMinY(){
        return 0;
    }

    @Override
    public int getGenDepth(){
        return 256;
    }

    @Override
    public int getSeaLevel(){
        return 63;
    }
    private record Settings(int bottomHeight, float verticalVariance, float horizontalVariance){ }
}
