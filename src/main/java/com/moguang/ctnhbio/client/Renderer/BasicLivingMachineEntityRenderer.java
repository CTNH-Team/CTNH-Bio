package com.moguang.ctnhbio.client.Renderer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.mojang.blaze3d.vertex.PoseStack;

public class BasicLivingMachineEntityRenderer extends EntityRenderer<LivingMetaMachineEntity> {

    private final EntityModel<LivingMetaMachineEntity> model;
    private final ResourceLocation SLIME_TEXTURE = ResourceLocation.tryBuild("minecraft",
            "textures/entity/slime/slime.png");
    private final ResourceLocation LOG_TEXTURE = ResourceLocation.tryBuild("minecraft", "textures/block/oak_log.png");

    public BasicLivingMachineEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SlimeModel<>(context.bakeLayer(ModelLayers.SLIME));
    }

    @Override
    public boolean shouldRender(LivingMetaMachineEntity entity, Frustum frustum, double p_114493_, double p_114494_,
                                double p_114495_) {
        return frustum.isVisible(entity.getBoundingBox());
    }

    @Override
    public void render(LivingMetaMachineEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {}

    @Override
    public ResourceLocation getTextureLocation(LivingMetaMachineEntity LivingMetaMachineEntity) {
        return LOG_TEXTURE;
    }
}
