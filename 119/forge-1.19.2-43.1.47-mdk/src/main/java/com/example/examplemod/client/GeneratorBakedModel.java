package com.example.examplemod.client;

import com.example.examplemod.block.custom.Generator;
import com.example.examplemod.block.custom.GeneratorBE;
import com.example.examplemod.comp208mod;
import com.mojang.math.Matrix4f;
import com.mojang.math.Transformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.IQuadTransformer;
import net.minecraftforge.client.model.QuadTransformers;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;


import static com.example.examplemod.client.ClientTools.v;
import static java.lang.Boolean.TRUE;

public class GeneratorBakedModel implements IDynamicBakedModel {

    private final ModelState modelState;
    private final Function<Material, TextureAtlasSprite> spriteGetter;
    private final Map<ModelKey, List<BakedQuad>> quadCache = new HashMap<>();
    private final ItemOverrides overrides;
    private final ItemTransforms itemTransforms;

    public GeneratorBakedModel(ModelState modelState, Function<Material, TextureAtlasSprite> spriteGetter,
                               ItemOverrides overrides, ItemTransforms itemTransforms) {
        this.modelState = modelState;
        this.spriteGetter = spriteGetter;
        this.overrides = overrides;
        this.itemTransforms = itemTransforms;
        generateQuadCache();
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull RandomSource rand, @Nonnull ModelData extraData, @Nullable RenderType layer) {

        if (side != null || (layer != null && !layer.equals(RenderType.solid()))) {
            return Collections.emptyList();
        }

        boolean generating = TRUE == extraData.get(GeneratorBE.GENERATING);
        boolean actuallyGenerating = TRUE == extraData.get(GeneratorBE.IS_GENERATING);

        var quads = getQuadsForGeneratingBlock(state, rand, extraData, layer);

        ModelKey key = new ModelKey(generating, actuallyGenerating, modelState);
        quads.addAll(quadCache.get(key));

        return quads;
    }

    private void generateQuadCache() {
        quadCache.put(new ModelKey(true,  false, modelState), generateQuads(true,  false));
        quadCache.put(new ModelKey(true,  false, modelState), generateQuads(true,  false));
        quadCache.put(new ModelKey(true,  true, modelState), generateQuads(true,  true));
        quadCache.put(new ModelKey(true, true, modelState), generateQuads(true,  true));
        quadCache.put(new ModelKey(false,  false, modelState), generateQuads(false,  false));
        quadCache.put(new ModelKey(false,  false, modelState), generateQuads(false,  false));
        quadCache.put(new ModelKey(false,  true, modelState), generateQuads(false,  true));
        quadCache.put(new ModelKey(false,  true, modelState), generateQuads(false,  true));
    }

    @NotNull
    private List<BakedQuad> generateQuads(boolean generating, boolean actuallyGenerating) {
        var quads = new ArrayList<BakedQuad>();
        float l = 0;
        float r = 1;
        float p = 13f / 16f; // Relative position of panel

        float bl = 1f/16f;
        float br = 7f/16f;

        float h = .5f;

        Transformation rotation = modelState.getRotation();

        TextureAtlasSprite textureSide = spriteGetter.apply(GeneratorModelLoader.MATERIAL_SIDE);
        TextureAtlasSprite textureFrontPowered = spriteGetter.apply(GeneratorModelLoader.MATERIAL_FRONT_POWERED);
        TextureAtlasSprite textureFront = spriteGetter.apply(GeneratorModelLoader.MATERIAL_FRONT);
        TextureAtlasSprite textureOn = spriteGetter.apply(GeneratorModelLoader.MATERIAL_ON);
        TextureAtlasSprite textureOff = spriteGetter.apply(GeneratorModelLoader.MATERIAL_OFF);

        // The base
        quads.add(ClientTools.createQuad(v(r, p, r), v(r, p, l), v(l, p, l), v(l, p, r), rotation, actuallyGenerating ? textureFrontPowered : textureFront));      // Top side
        quads.add(ClientTools.createQuad(v(l, l, l), v(r, l, l), v(r, l, r), v(l, l, r), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(r, p, r), v(r, l, r), v(r, l, l), v(r, p, l), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(l, p, l), v(l, l, l), v(l, l, r), v(l, p, r), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(r, p, l), v(r, l, l), v(l, l, l), v(l, p, l), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(l, p, r), v(l, l, r), v(r, l, r), v(r, p, r), rotation, textureSide));


        // The generating button
        float s = generating ? 14f/16f : r;
        float offset = h;
        quads.add(ClientTools.createQuad(v(br, s, br+offset), v(br, s, bl+offset), v(bl, s, bl+offset), v(bl, s, br+offset), rotation, generating ? textureOn : textureOff));
        quads.add(ClientTools.createQuad(v(br, s, br+offset), v(br, p, br+offset), v(br, p, bl+offset), v(br, s, bl+offset), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(bl, s, bl+offset), v(bl, p, bl+offset), v(bl, p, br+offset), v(bl, s, br+offset), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(br, s, bl+offset), v(br, p, bl+offset), v(bl, p, bl+offset), v(bl, s, bl+offset), rotation, textureSide));
        quads.add(ClientTools.createQuad(v(bl, s, br+offset), v(bl, p, br+offset), v(br, p, br+offset), v(br, s, br+offset), rotation, textureSide));
        return quads;
    }

    private List<BakedQuad> getQuadsForGeneratingBlock(@Nullable BlockState state, @NotNull RandomSource rand, @NotNull ModelData extraData, RenderType layer) {
        var quads = new ArrayList<BakedQuad>();
        BlockState generatingBlock = extraData.get(GeneratorBE.GENERATING_BLOCK);
        if (generatingBlock != null && !(generatingBlock.getBlock() instanceof Generator)) {
            if (layer == null || getRenderTypes(generatingBlock, rand, extraData).contains(layer)) {
                BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getBlockModel(generatingBlock);
                try {
                    Direction facing = state == null ? Direction.SOUTH : state.getValue(BlockStateProperties.FACING);
                    Transformation rotation = modelState.getRotation();
                    Transformation translate = transformGeneratingBlock(facing, rotation);
                    IQuadTransformer transformer = QuadTransformers.applying(translate);

                    // Get the quads for every side, transform it and add it to the list of quads
                    for (Direction s : Direction.values()) {
                        List<BakedQuad> modelQuads = model.getQuads(generatingBlock, s, rand, ModelData.EMPTY, layer);
                        for (BakedQuad quad : modelQuads) {
                            quads.add(transformer.process(quad));
                        }
                    }
                } catch (Exception e) {
                    // In case a certain mod has a bug we don't want to cause everything to crash. Instead we log the problem
                    ResourceLocation key = ForgeRegistries.BLOCKS.getKey(generatingBlock.getBlock());
                    comp208mod.LOGGER.log(Level.ERROR, "A block '" + key.toString() + "' caused a crash!");
                }
            }
        }
        return quads;
    }

    @NotNull
    private Transformation transformGeneratingBlock(Direction facing, Transformation rotation) {

        float dX = facing.getStepX();
        float dY = facing.getStepY();
        float dZ = facing.getStepZ();
        switch (facing) {
            case DOWN ->  { dX = 1; dY = 0; dZ = -1; }
            case UP ->    { dX = 1; dY = 0; dZ = 1; }
            case NORTH -> { dX = 1; dY = 1; dZ = 0; }
            case SOUTH -> { dX = -1; dY = 1; dZ = 0; }
            case WEST ->  { dX = 0; dY = 1; dZ = -1; }
            case EAST ->  { dX = 0; dY = 1; dZ = 1; }
        }
        float stepX = facing.getStepX() / 4f + dX / 4f + .5f;
        float stepY = facing.getStepY() / 4f + dY / 4f + .5f;
        float stepZ = facing.getStepZ() / 4f + dZ / 4f + .5f;

        Transformation translate = new Transformation(Matrix4f.createTranslateMatrix(stepX, stepY, stepZ));

        translate = translate.compose(new Transformation(Matrix4f.createScaleMatrix(.2f, .2f, .2f)));
        translate = translate.compose(rotation);    // The rotation from our main model

        translate = translate.compose(new Transformation(Matrix4f.createTranslateMatrix(-.5f, -.5f, -.5f)));
        return translate;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return spriteGetter.apply(GeneratorModelLoader.MATERIAL_SIDE);
    }

    @Override
    public ItemOverrides getOverrides() {
        return overrides;
    }

    @Override
    public ItemTransforms getTransforms() {
        return itemTransforms;
    }
}