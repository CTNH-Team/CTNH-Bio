package com.moguang.ctnhbio.client.Renderer;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.lowdragmc.lowdraglib.utils.DummyWorld;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

public class LivingMetaMachineBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {

    private BlockEntityRendererProvider.Context pcontext;
    private final EntityModel<LivingMetaMachineEntity> model;
    private final ResourceLocation LOG_TEXTURE = new ResourceLocation("textures/block/oak_log.png");

    public LivingMetaMachineBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        pcontext = context;
        this.model = new SlimeModel<>(context.bakeLayer(ModelLayers.SLIME));
    }


    @Override
    public void render(BlockEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int light, int overlay) {
        poseStack.pushPose();
        float scale = 2.5f; // 缩小史莱姆模型
        poseStack.translate(0.5, -scale, 0.5);
        poseStack.scale(scale, scale, scale);

        int new_overlay = overlay;

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(LOG_TEXTURE));
        this.model.renderToBuffer(
                poseStack,
                vertexConsumer,
                light,
                new_overlay,
                1.0f, 1.0f, 1.0f, 1.0f
        );
        poseStack.popPose();


    }
}

