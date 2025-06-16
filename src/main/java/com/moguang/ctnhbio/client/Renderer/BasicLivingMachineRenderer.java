package com.moguang.ctnhbio.client.Renderer;

import com.moguang.ctnhbio.common.entity.BasicLivingMachineEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BasicLivingMachineRenderer extends EntityRenderer<BasicLivingMachineEntity> {

    public BasicLivingMachineRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRender(BasicLivingMachineEntity entity, Frustum frustum, double p_114493_, double p_114494_, double p_114495_) {
        return frustum.isVisible(entity.getBoundingBox());
    }

    @Override
    public void render(BasicLivingMachineEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        //super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
        poseStack.pushPose();

        poseStack.translate(-0.5, 0, -0.5);
        //poseStack.scale(3.0F, 3.0F, 3.0F);
        BlockState state = Blocks.OAK_LOG.defaultBlockState();
        int light = LevelRenderer.getLightColor(entity.level(), BlockPos.containing(entity.position().add(0,0,0)));

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                state,
                poseStack,
                bufferSource,
                light,
                OverlayTexture.NO_OVERLAY
        );

        poseStack.popPose();
    }

    @Override
    protected int getBlockLightLevel(BasicLivingMachineEntity p_114496_, BlockPos p_114497_) {
        return super.getBlockLightLevel(p_114496_, p_114497_);
    }

    @Override
    public ResourceLocation getTextureLocation(BasicLivingMachineEntity BasicLivingMachineEntity) {
        return null;
    }
}
