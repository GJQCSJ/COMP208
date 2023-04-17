package com.example.examplemod.world.structures;

import com.example.examplemod.world.Structure_Biomes;
import com.example.examplemod.world.dimensions.CustomChunkGenerator;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class NewSkyPortalStructure extends Structure {



    public static final Codec<NewSkyPortalStructure> CODEC = RecordCodecBuilder.<NewSkyPortalStructure>mapCodec(instance ->
            instance.group(Structure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, NewSkyPortalStructure::new)).codec();

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;

    public NewSkyPortalStructure(StructureSettings config,
                                 Holder<StructureTemplatePool> startPool,
                                 Optional<ResourceLocation> startJigsawName,
                                 int size,
                                 HeightProvider startHeight,
                                 Optional<Heightmap.Types> projectStartToHeightmap,
                                 int maxDistanceFromCenter){
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
    }

    private static boolean extraSpawningChecks(GenerationContext context){
        ChunkPos chunkPos = context.chunkPos();
        //Make sure this structure is not spawned above y = 120, we want this spawned on sea level as close as possible
        return context.chunkGenerator().getFirstOccupiedHeight(
                chunkPos.getMinBlockX(),
                chunkPos.getMinBlockZ(),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                context.heightAccessor(),
                context.randomState()
        ) < 220;
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context){
        boolean over_world = !(context.chunkGenerator() instanceof CustomChunkGenerator);
        //Check if the spot is suitable for generation of this structure
        if (!NewSkyPortalStructure.extraSpawningChecks(context)){
            return Optional.empty();
        }
        //Turns the coordinates of chunk into actual coordinates in game for our use
        BlockPos pos = context.chunkPos().getMiddleBlockPosition(0);
        NoiseColumn noiseColumn = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());

        int surfaceHeight = context.chunkGenerator().getFirstOccupiedHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        BlockState topBlock = noiseColumn.getBlock(surfaceHeight);

        //This portal is expected to be generated on the surface of the main world
        //And not on any kind of fluids
        if (over_world && !topBlock.getFluidState().isEmpty()){
            return Optional.empty();
        }
        ChunkPos chunkPos = context.chunkPos();
        //Y position of the structure is 0 for this structure will be generated on the surface
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), 0, chunkPos.getMinBlockZ());
        Optional<GenerationStub> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        this.startPool,//start pool needed to use the proper layout
                        this.startJigsawName,
                        this.size,//how deep a branch can go away from the center piece
                        blockPos,
                        false,
                        this.projectStartToHeightmap,
                        this.maxDistanceFromCenter
                );
        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> type(){
        return Structure_Biomes.SKY_PORTAL.get();
    }
}

