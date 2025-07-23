package com.moguang.ctnhbio.machine.bioreactor;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.machine.braininavat.Brain;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BioReactorRenderer extends EntityRenderer<BioReactorEntity> {
    private final BioReactorModel<BioReactorEntity> model;
    private static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/bioreactor.png");
    int t =180;
    public BioReactorRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BioReactorModel<>(context.bakeLayer(BioReactorModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(BioReactorEntity bioReactorEntity) {
        return TEXTURE;
    }

    @Override
    public void render(BioReactorEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);

        poseStack.pushPose();

        //poseStack.mulPose(Axis.YP.rotationDegrees(t));
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
    }
}
