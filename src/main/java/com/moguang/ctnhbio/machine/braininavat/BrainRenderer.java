package com.moguang.ctnhbio.machine.braininavat;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;

public class BrainRenderer extends EntityRenderer<Brain> {
    private final BrainModel<Brain> model;
    private static final ResourceLocation TEXTURE = new ResourceLocation("ctnhbio",  "textures/block/brain_in_a_vat.png");

    public BrainRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BrainModel<>(context.bakeLayer(BrainModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(Brain entity) {
        return TEXTURE;
    }

    @Override
    public void render(Brain entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);

        poseStack.pushPose();
        // 调整位置和旋转
        poseStack.translate(0.0D, 0.0D, 0.0D); // 调整高度
        poseStack.scale(1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(entityYaw)); // 旋转以匹配实体朝向

        // 渲染模型
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }
}