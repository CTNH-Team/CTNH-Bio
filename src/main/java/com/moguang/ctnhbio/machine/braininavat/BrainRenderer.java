package com.moguang.ctnhbio.machine.braininavat;

import com.moguang.ctnhbio.CTNHBio;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.player.Player;
import org.joml.Quaternionf;

public class BrainRenderer extends EntityRenderer<Brain> {
    private final BrainModel<Brain> model;
    private static final ResourceLocation TEXTURE = CTNHBio.id("textures/block/brain_in_a_vat.png");

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
        float time = (entity.tickCount + partialTicks) * 0.05F; // 调整速度（0.05F 较慢）
        float floatingOffset = (float) Math.sin(time) * 0.1F; // 调整幅度（0.1F 较小浮动）
        poseStack.translate(0.0D, floatingOffset, 0.0D); // 调整高度
        poseStack.scale(1.0F, -1.0F, 1.0F);
        Player player = entity.level().getNearestPlayer(entity,16);
        if (player != null) {
            double dx = player.getX() - entity.getX();
            double dy = player.getEyeY() - entity.getY(); // 使用玩家眼睛高度
            double dz = player.getZ() - entity.getZ();

            float yaw = 90.0F - (float) Math.toDegrees(Math.atan2(dz, dx));

            double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
            float pitch = (float) -Math.toDegrees(Math.atan2(dy, horizontalDistance));

            Quaternionf rotation = new Quaternionf()
                    .rotateY((float) Math.toRadians(yaw)) // 水平旋转
                    .rotateX((float) Math.toRadians(pitch)); // 垂直旋转

            poseStack.mulPose(rotation);
        } else {
            poseStack.mulPose(Axis.YP.rotationDegrees(entityYaw));
        }

        // 渲染模型
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }
}