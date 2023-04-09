package com.example.examplemod.client;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.block.custom.ManaBE;
import com.example.examplemod.block.custom.ManaConfig;
import com.example.examplemod.comp208mod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static java.lang.Boolean.TRUE;

public class ExtractorRender implements BlockEntityRenderer<ManaBE> {
    public static final ResourceLocation HALO = new ResourceLocation(comp208mod.MOD_ID, "effect/halo");

    public ExtractorRender(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(ManaBE mana_extractor, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay){

        Boolean powered = mana_extractor.getBlockState().getValue(BlockStateProperties.POWERED);
        if (TRUE != powered){
            return;
        }

        int brightness = LightTexture.FULL_BRIGHT;
        //Following is required for a pulsating effect
        float s = (System.currentTimeMillis() % 1000) / 1000.0f;
        if (s > 0.5f){
            s = 1.0f - s;
        }
        float scale = 0.1f + s * (float)(double)ManaConfig.RENDER_SCALE.get();

        //Get texture from atlas
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(HALO);
        //Push current poseStack so it can be restored
        poseStack.pushPose();
        //
        poseStack.translate(0.5, 1.5, 0.5);
        //
        Quaternion rotation = Minecraft.getInstance().gameRenderer.getMainCamera().rotation();
        poseStack.mulPose(rotation);
        //
        VertexConsumer buffer = bufferSource.getBuffer(CustomRenderType.ADD);
        Matrix4f matrix = poseStack.last().pose();
        //Vertices
        buffer.vertex(matrix, -scale, -scale, 0.0f).color(1.0f, 1.0f, 1.0f, 0.3f).uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1, 0, 0).endVertex();
        buffer.vertex(matrix, -scale, scale, 0.0f).color(1.0f, 1.0f, 1.0f, 0.3f).uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1, 0, 0).endVertex();
        buffer.vertex(matrix, scale, scale, 0.0f).color(1.0f, 1.0f, 1.0f, 0.3f).uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1, 0, 0).endVertex();
        buffer.vertex(matrix, scale, -scale, 0.0f).color(1.0f, 1.0f, 1.0f, 0.3f).uv(sprite.getU1(), sprite.getV0()).uv2(brightness).normal(1, 0, 0).endVertex();
        poseStack.popPose();
    }

    public static void register(){
        BlockEntityRenderers.register(ModBlocks.MANA_EXTRACTOR_BE.get(), ExtractorRender::new);
    }
}
