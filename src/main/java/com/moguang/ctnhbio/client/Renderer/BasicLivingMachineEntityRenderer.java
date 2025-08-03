package com.moguang.ctnhbio.client.Renderer;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BasicLivingMachineEntityRenderer extends EntityRenderer<LivingMetaMachineEntity> {

    private final EntityModel<LivingMetaMachineEntity> model;
    private final ResourceLocation SLIME_TEXTURE = new ResourceLocation("textures/entity/slime/slime.png");
    private final ResourceLocation LOG_TEXTURE = new ResourceLocation("textures/block/oak_log.png");

    public BasicLivingMachineEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SlimeModel<>(context.bakeLayer(ModelLayers.SLIME));
    }

    @Override
    public boolean shouldRender(LivingMetaMachineEntity entity, Frustum frustum, double p_114493_, double p_114494_, double p_114495_) {
        return frustum.isVisible(entity.getBoundingBox());
    }

    @Override
    public void render(LivingMetaMachineEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
    }



    @Override
    public ResourceLocation getTextureLocation(LivingMetaMachineEntity LivingMetaMachineEntity) {
        return LOG_TEXTURE;
    }
}
