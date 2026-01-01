package com.moguang.ctnhbio.client.Renderer;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.CBValues;
import com.moguang.ctnhbio.machine.braininavat.Brain;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.joml.Quaternionf;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ColorableEntityRenderer extends GeoEntityRenderer<Brain> {
    public ColorableEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeoModel<>() {
            public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/brain.geo.json");
            protected static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/brain_in_a_vat.png");

            @Override
            public ResourceLocation getModelResource(Brain brain) {
                return MODEL;
            }

            @Override
            public ResourceLocation getTextureResource(Brain brain) {
                return TEXTURE;
            }

            @Override
            public ResourceLocation getAnimationResource(Brain brain) {
                return CBValues.EMPTY_ANIMATION;
            }
        });
    }

    @Override
    public void render(Brain entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        // 调整位置和旋转
        float time = (entity.tickCount + partialTick) * 0.05F; // 调整速度（0.05F 较慢）
        float floatingOffset = (float) Math.sin(time) * 0.1F; // 调整幅度（0.1F 较小浮动）
        poseStack.translate(0.0D, floatingOffset, 0.0D); // 调整高度
        //poseStack.scale(1.0F, -1.0F, 1.0F);
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

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }

    @Override
    public boolean shouldShowName(Brain animatable) {
        return false;
    }
}
