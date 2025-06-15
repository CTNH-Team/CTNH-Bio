package com.moguang.ctnhbio.client.Renderer;

import com.moguang.ctnhbio.common.entity.BasicLivingMachineEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BasicLivingMachineRenderer extends EntityRenderer<BasicLivingMachineEntity> {

    public BasicLivingMachineRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(BasicLivingMachineEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.pushPose();

        poseStack.translate(-0.5, 0, -0.5);
        //poseStack.scale(3.0F, 3.0F, 3.0F);
        BlockState state = Blocks.OAK_LOG.defaultBlockState();

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                state,
                poseStack,
                bufferSource,
                packedLight,
                OverlayTexture.NO_OVERLAY
        );

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(BasicLivingMachineEntity BasicLivingMachineEntity) {
        return null;
    }
}
